/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.error.RepositoryException;
import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.common.notifier.Notify;
import com.nibbledebt.common.notifier.NotifyMethod;
import com.nibbledebt.common.notifier.NotifyType;
import com.nibbledebt.core.data.dao.IAccountTypeDao;
import com.nibbledebt.core.data.dao.INibblerDao;
import com.nibbledebt.core.data.dao.INibblerDirectoryDao;
import com.nibbledebt.core.data.dao.INibblerRoleDao;
import com.nibbledebt.core.data.model.AccountBalance;
import com.nibbledebt.core.data.model.AccountLimit;
import com.nibbledebt.core.data.model.AccountType;
import com.nibbledebt.core.data.model.Nibbler;
import com.nibbledebt.core.data.model.NibblerAccount;
import com.nibbledebt.core.data.model.NibblerDirectory;
import com.nibbledebt.core.data.model.NibblerDirectoryStatus;
import com.nibbledebt.core.data.model.NibblerPreference;
import com.nibbledebt.core.data.model.NibblerRole;
import com.nibbledebt.core.data.model.NibblerRoleType;
import com.nibbledebt.integration.model.plaid.Account;
import com.nibbledebt.integration.model.plaid.LinkResponse;
import com.nibbledebt.integration.model.plaid.MfaResponse;
import com.nibbledebt.integration.sao.IAccountSao;
import com.nibbledebt.web.rest.model.NibblerData;

/**
 * @author ralam1
 *
 */
@Component
public class RegistrationProcessor extends AbstractProcessor{
	@Autowired
	private INibblerDao nibblerDao;
	
	@Autowired
	private INibblerDirectoryDao nibblerDirDao;
	
	@Autowired
	private IAccountTypeDao accountTypeDao;
	
	@Autowired
	private INibblerRoleDao nibblerRoleDao;
	
	@Autowired
	private IAccountSao extAccountSao;
	
	@Autowired
	private MessageDigestPasswordEncoder encoder;

	@Autowired
	private String salt;
	
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Notify(notifyMethod=NotifyMethod.EMAIL, notifyType=NotifyType.ACCOUNT_ACTIVATED)
	@CacheEvict(value="nibblerCache", key="#username")
	public void activateNibbler(String username, String password, String activationCode) throws ProcessingException, RepositoryException{
		
		NibblerDirectory nibblerDir = nibblerDirDao.find(username);
		if(nibblerDir == null){
			throw new ProcessingException("The username you have provded does not exist.");
		}
		if(StringUtils.equals(nibblerDir.getStatus(), NibblerDirectoryStatus.CREATED.name())){
			if(StringUtils.equals(nibblerDir.getActivationCode(), activationCode) && nibblerDir.getPassword().equals(
					encoder.encodePassword(
							String.valueOf(password),
							salt))){
				nibblerDir.setStatus(NibblerDirectoryStatus.ACTIVE.name());
				nibblerDir.setActivationCode("");
				
				NibblerRole nibblerRole = nibblerRoleDao.find(NibblerRoleType.nibbler_level_1.name());
				if(nibblerRole == null){
					nibblerRole = new NibblerRole();	
					setCreated(nibblerRole, username);
					nibblerRole.setName(NibblerRoleType.nibbler_level_1.name());	
				}
				
				Set<NibblerRole> nibblerRoles = new HashSet<>();
				nibblerRoles.add(nibblerRole);
				nibblerDir.setRoles(nibblerRoles);
				setUpdated(nibblerDir, username);
				nibblerDirDao.update(nibblerDir);
				
			}else{
				throw new ProcessingException("Activation code and/or password does not match the account!");
			}
		}else{
			throw new ProcessingException("Already active!");
		}		
	}
	
	/**
	 * Returns a null if no MFA is required, otherwise returns the details of the MFA challenge.
	 * @param nibblerData
	 * @return
	 * @throws ProcessingException
	 * @throws ServiceException
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Notify(notifyMethod=NotifyMethod.EMAIL, notifyType=NotifyType.ACCOUNT_CREATED)
	public LinkResponse registerNibbler(NibblerData nibblerData) throws ProcessingException, ServiceException, RepositoryException{
		LinkResponse resp = extAccountSao.linkAccount(nibblerData.getInstUsername(), 
				nibblerData.getInstPassword(), 
				nibblerData.getInstPin(), 
				nibblerData.getInstitution());	
		if(resp!=null){
			register(nibblerData, resp.getAccessToken());
			try {
				updateAccount(nibblerData, resp);
			} catch (ParseException e) {
				throw new ProcessingException("Error ocurred while parsing a date." , e);
			}
			return resp;
		}else{
			throw new ProcessingException("There was an issue trying to link the account. Plaid did not respond as expected.");				
		}	
	}
	
	/**
	 * Returns a null if no MFA is required, otherwise returns the details of the MFA challenge.
	 * @param nibblerData
	 * @return
	 * @throws ProcessingException
	 * @throws ServiceException
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public MfaResponse registerNibblerWithMfa(NibblerData nibblerData) throws ProcessingException, ServiceException, RepositoryException{
		MfaResponse resp = extAccountSao.linkAccountMfa(nibblerData.getInstUsername(), 
				nibblerData.getInstPassword(), 
				nibblerData.getInstPin(), 
				nibblerData.getInstitution());
		if(resp!=null){
			return resp;
		}else{
			throw new ProcessingException("There was an issue trying to link the account. Plaid did not respond as expected.");				
		}
	}
	
	/**
	 * {"send_method":{"mask":"xxx-xxx-5309"}}
	 * @param accessToken
	 * @param sendMethod
	 * @throws ProcessingException
	 * @throws ServiceException
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public void sendMfaCode(String accessToken, String sendMethod) throws ProcessingException, ServiceException{
		extAccountSao.initiateMfaSend(accessToken, "{\"send_method\":{\"mask\":\""+sendMethod+"\"}");
	}
	
	//TODO - use Jackson
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Notify(notifyMethod=NotifyMethod.EMAIL, notifyType=NotifyType.ACCOUNT_CREATED)
	public void submitMfa(NibblerData nibblerData ) throws ProcessingException, ServiceException, RepositoryException{
		LinkResponse response = null;
		if(nibblerData.getMfa().length>1){
			StringBuffer mfaBuffer = new StringBuffer("[");
			for(int i =0; i<nibblerData.getMfa().length; i++){
				if(i!=nibblerData.getMfa().length-1) mfaBuffer.append("\""+nibblerData.getMfa()[i]+"\",");
				else mfaBuffer.append("\""+nibblerData.getMfa()[i]+"\"");
			}
			mfaBuffer.append("]");
			response = extAccountSao.submitMfaResponse(nibblerData.getAccessToken(), mfaBuffer.toString(), nibblerData.getInstitution());
		}else {
			if(nibblerData.getMfa().length == 1){
				StringBuffer mfaBuffer = new StringBuffer(nibblerData.getMfa()[0]);	
				response = extAccountSao.submitMfaResponse(nibblerData.getAccessToken(), mfaBuffer.toString(), nibblerData.getInstitution());
			}
		}
		
		try {
			if(response !=null ){
				register(nibblerData, response.getAccessToken());
				updateAccount(nibblerData, response);
			}else{
				throw new ProcessingException("MFA responses cannot be empty.");
			}
		} catch (ParseException e) {
			throw new ProcessingException("Error ocurred while parsing a date." , e);
		}
	}
	
	//TODO - use Jackson
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Notify(notifyMethod=NotifyMethod.EMAIL, notifyType=NotifyType.ACCOUNT_CREATED)
	public void submitQuesMfa(NibblerData nibblerData ) throws ProcessingException, ServiceException, RepositoryException{
		StringBuffer mfaBuffer = new StringBuffer("[");
		for(int i =0; i<nibblerData.getMfa().length; i++){
			if(i!=nibblerData.getMfa().length-1) mfaBuffer.append("\""+nibblerData.getMfa()[i]+"\",");
			else mfaBuffer.append("\""+nibblerData.getMfa()[i]+"\"");
		}
		mfaBuffer.append("]");
		LinkResponse response = extAccountSao.submitMfaResponse(nibblerData.getAccessToken(), mfaBuffer.toString(), nibblerData.getInstitution());
		try {
			updateAccount(nibblerData, response);
		} catch (ParseException e) {
			throw new ProcessingException("Error ocurred while parsing a date." , e);
		}
	}
	
	private void register(	NibblerData nibblerData,
							String accessToken	) throws ProcessingException, RepositoryException{
		if(accessToken==null){
			throw new ProcessingException("There was an issue trying to link the account. Plaid did not respond as expected.");
		}

		Nibbler nibbler = new Nibbler();
		NibblerDirectory nibblerDir = new NibblerDirectory();

		
		setCreated(nibbler, nibblerData.getUsername());
		setCreated(nibblerDir, nibblerData.getUsername());
		
		nibbler.setExtAccessToken(accessToken);
				
		nibbler.setFirstName(nibblerData.getFirstName());
		nibbler.setLastName(nibblerData.getLastName());
		nibbler.setAddressLine1(nibblerData.getAddress1());
		nibbler.setAddressLine2(nibblerData.getAddress2());
		nibbler.setCity(nibblerData.getCity());
		nibbler.setState(nibblerData.getState());
		nibbler.setZip(nibblerData.getZip());
		nibblerDir.setUsername(nibblerData.getUsername());
		nibblerDir.setPassword(
				encoder.encodePassword(
						String.valueOf(nibblerData.getPassword()),
						salt));
		nibblerDir.setStatus(NibblerDirectoryStatus.CREATED.name());
		
		String actCode = UUID.randomUUID().toString();
		nibblerDir.setActivationCode(actCode);
		nibblerData.setActivationCode(actCode);
		nibbler.setPhone(nibblerData.getPhone());
		nibbler.setEmail(nibblerData.getEmail());		
				
		nibbler.setNibblerDir(nibblerDir);
		nibblerDir.setNibbler(nibbler);
		
		NibblerPreference prefs = new NibblerPreference();
		prefs.setNibbler(nibbler);
		prefs.setWeeklyTargetAmount(new BigDecimal("9.99"));
		setCreated(prefs, nibblerData.getUsername());
		nibbler.setNibblerPreferences(prefs);
		nibblerDao.create(nibbler);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	private void updateAccount(NibblerData nibblerData, LinkResponse accountData) throws ServiceException, ParseException, RepositoryException{
					
		Nibbler nibbler = nibblerDao.find(nibblerData.getUsername());
		
		for(Account account : accountData.getAccounts()){
			AccountType accountType = accountTypeDao.find(account.getType());
			if(accountType == null){
				accountType = new AccountType();
				accountType.setCode(account.getType());
				accountType.setDescription(account.getType());
				setCreated(accountType, nibblerData.getUsername());	
				accountTypeDao.create(accountType);
			}
			
			NibblerAccount naccount = new NibblerAccount();
			naccount.setAccountType(accountType);
			naccount.setWireRoutingNumber(account.getNumbers().getWireRouting());
			naccount.setRoutingNumber(account.getNumbers().getRouting());
			naccount.setNumber(account.getNumbers().getAccount());
			naccount.setInstitutionName(nibblerData.getInstitution().getName());
			naccount.setInstitutionType(account.getInstitutionType());
			naccount.setNibbler(nibbler);
			naccount.setName(account.getMeta().getName());
			naccount.setNumberMask(account.getMeta().getNumber());
			naccount.setExternalId(account.getId());
			setCreated(naccount, nibblerData.getUsername());
			
			if(account.getBalance() !=null){
				AccountBalance balance = new AccountBalance();
				balance.setAvailable(BigDecimal.valueOf(account.getBalance().getAvailable()));
				balance.setCurrent(BigDecimal.valueOf(account.getBalance().getCurrent()));
				balance.setAccount(naccount);
				setCreated(balance, nibblerData.getUsername());
				naccount.getBalances().add(balance);
			}
			
			if(account.getMeta().getLimit() !=null){
				AccountLimit limit = new AccountLimit();
				limit.setValue(account.getMeta().getLimit());
				limit.setAccount(naccount);
				setCreated(limit, nibblerData.getUsername());
				naccount.getLimits().add(limit);
			}
			
//			naccount.setLastTransactionPull(new Date(System.currentTimeMillis()-8640000));
			nibbler.getAccounts().add(naccount);
		}
		setUpdated(nibbler, nibblerData.getUsername());
		nibblerDao.update(nibbler);
		nibblerData.setActivationCode(nibbler.getNibblerDir().getActivationCode());
	}
}
