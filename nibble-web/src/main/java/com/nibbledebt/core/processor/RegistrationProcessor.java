/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.common.error.ValidationException;
import com.nibbledebt.common.notifier.Notify;
import com.nibbledebt.common.notifier.NotifyMethod;
import com.nibbledebt.common.notifier.NotifyType;
import com.nibbledebt.core.data.dao.IAccountTypeDao;
import com.nibbledebt.core.data.dao.IInstitutionDao;
import com.nibbledebt.core.data.dao.INibblerDao;
import com.nibbledebt.core.data.dao.INibblerDirectoryDao;
import com.nibbledebt.core.data.dao.INibblerRoleDao;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.AccountBalance;
import com.nibbledebt.core.data.model.AccountType;
import com.nibbledebt.core.data.model.Institution;
import com.nibbledebt.core.data.model.Nibbler;
import com.nibbledebt.core.data.model.NibblerAccount;
import com.nibbledebt.core.data.model.NibblerContributor;
import com.nibbledebt.core.data.model.NibblerDirectory;
import com.nibbledebt.core.data.model.NibblerDirectoryStatus;
import com.nibbledebt.core.data.model.NibblerPreference;
import com.nibbledebt.core.data.model.NibblerReceiver;
import com.nibbledebt.core.data.model.NibblerRole;
import com.nibbledebt.core.data.model.NibblerRoleType;
import com.nibbledebt.core.data.model.NibblerType;
import com.nibbledebt.domain.model.Bank;
import com.nibbledebt.domain.model.LoginField;
import com.nibbledebt.domain.model.NibblerData;
import com.nibbledebt.domain.model.account.Account;
import com.nibbledebt.domain.model.account.Accounts;
import com.nibbledebt.domain.model.account.AddAccountsResponse;
import com.nibbledebt.domain.model.account.MfaType;
import com.nibbledebt.integration.sao.IIntegrationSao;

/**
 * @author ralam1
 */
@Component
public class RegistrationProcessor extends AbstractProcessor implements ApplicationContextAware {
    @Autowired
    private INibblerDao nibblerDao;

    @Autowired
    private INibblerDirectoryDao nibblerDirDao;

    @Autowired
    private IAccountTypeDao accountTypeDao;

    @Autowired
    private INibblerRoleDao nibblerRoleDao;

    @Autowired
    private IInstitutionDao institutionDao;
    
    private ApplicationContext appContext;

    @Autowired
    private MessageDigestPasswordEncoder encoder;

    @Autowired
    private String salt;
    

	@Resource
	private Environment env;


    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.ACCOUNT_ACTIVATED)
    @CacheEvict(value = "nibblerCache", key = "#username")
    public void activateNibbler(String username, String password, String activationCode) throws ProcessingException, RepositoryException {

        NibblerDirectory nibblerDir = nibblerDirDao.find(username);
        if (nibblerDir == null) {
            throw new ProcessingException("The username you have provded does not exist.");
        }
        if (StringUtils.equals(nibblerDir.getStatus(), NibblerDirectoryStatus.CREATED.name())) {
            if (StringUtils.equals(nibblerDir.getActivationCode(), activationCode) && nibblerDir.getPassword().equals(
                    encoder.encodePassword(
                            String.valueOf(password),
                            salt))) {
                nibblerDir.setStatus(NibblerDirectoryStatus.ACTIVE.name());
                nibblerDir.setActivationCode("");


                Set<NibblerRole> nibblerRoles = new HashSet<>();
                
            	NibblerRole nibblerRole = getRole(NibblerRoleType.nibbler_level_1);
            	nibblerRoles.add(nibblerRole);
                
                if(StringUtils.equalsIgnoreCase(nibblerDir.getNibbler().getType().name(), "contributor")){
                	NibblerRole contributorRole = getRole(NibblerRoleType.contributor);
                	nibblerRoles.add(contributorRole);
                }
                
                if(StringUtils.equalsIgnoreCase(nibblerDir.getNibbler().getType().name(), "receiver")){
                	NibblerRole receiverRole = getRole(NibblerRoleType.receiver);
                	nibblerRoles.add(receiverRole);
                }

                nibblerDir.setRoles(nibblerRoles);
                setUpdated(nibblerDir, username);
                nibblerDirDao.update(nibblerDir);

            } else {
                throw new ProcessingException("Activation code and/or password does not match the account!");
            }
        } else {
            throw new ProcessingException("Already active!");
        }
    }
    
    private NibblerRole getRole(NibblerRoleType roleType) throws RepositoryException{
    	NibblerRole role = nibblerRoleDao.find(roleType.name());
    	if (role == null) {
    		role = new NibblerRole();
            setCreated(role, "sysuser");
            role.setName(roleType.name());
        }
    	return role;
    }
    
    @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor=RepositoryException.class)
	public AddAccountsResponse addLoanAccount(NibblerData nibblerData, String username) throws ProcessingException, ServiceException, RepositoryException, ValidationException{
        Institution persistedInstitution = institutionDao.findOne(Long.valueOf(nibblerData.getBank().getInstitution().getId()));
        IIntegrationSao integrationSao = (IIntegrationSao) appContext.getBean(persistedInstitution.getSupportedInstitution().getAggregatorQualifier());
        
		if (nibblerData.getBank() != null && nibblerData.getBank().getInstitution() != null) {
            if (externalAuthReqsValid(nibblerData.getBank())) {
            	Nibbler nibbler = nibblerDao.find(username);
            	if(nibbler != null){
            		AddAccountsResponse response = integrationSao.addAccounts(nibbler.getExtAccessToken(), persistedInstitution.getSupportedInstitution().getExternalId(),
                            nibblerData.getBank().getLoginForm().getLoginField().toArray(new LoginField[]{}));
            		if (response != null && response.getMfaType() == MfaType.NON_MFA) {
                        saveCustomerAccounts(nibblerData, response.getAccounts());
                    }
                    return response;
            	}else{
            		throw new ValidationException("Financial institution requires fields that have failed validation.");
            	}  
                
            } else {
                throw new ValidationException("Financial institution requires fields that have failed validation.");
            }
        } else {
            throw new ValidationException("Financial institution not available.");
        }
	}

    /**
     * Returns a null if no MFA is required, otherwise returns the details of the MFA challenge.
     *
     * @param nibblerData -nibbler registration data
     * @return
     * @throws ProcessingException
     * @throws ServiceException
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.ACCOUNT_CREATED)
    public AddAccountsResponse registerNibbler(NibblerData nibblerData) throws ProcessingException, ServiceException, RepositoryException, ValidationException {
        Institution persistedInstitution = institutionDao.findOne(Long.valueOf(nibblerData.getBank().getInstitution().getId()));
        IIntegrationSao integrationSao = (IIntegrationSao) appContext.getBean(persistedInstitution.getSupportedInstitution().getAggregatorQualifier());
        String customerId = integrationSao.addCustomer(nibblerData.getEmail(), nibblerData.getFirstName(), nibblerData.getLastName());
        try {
			if (StringUtils.isNotBlank(customerId)) saveCustomerData(nibblerData, customerId);
		} catch (RepositoryException e) {
			integrationSao.deleteCustomer(customerId);
			throw e;
		}
        if (nibblerData.getBank() != null && nibblerData.getBank().getInstitution() != null) {
            if (externalAuthReqsValid(nibblerData.getBank())) {
                AddAccountsResponse response = integrationSao.addAccounts(customerId, persistedInstitution.getSupportedInstitution().getExternalId(),
                        nibblerData.getBank().getLoginForm().getLoginField().toArray(new LoginField[]{}));
                if (response != null && response.getMfaType() == MfaType.NON_MFA) {
                    try {
						saveCustomerAccounts(nibblerData, response.getAccounts());
					} catch (Exception e) {
						integrationSao.deleteCustomer(customerId);
						throw e;
					}
                }
                return response;
            } else {
                throw new ValidationException("Financial institution requires fields that have failed validation.");
            }
        } else {
            throw new ValidationException("Financial institution not available.");
        }
    }

    @Transactional()
    public AddAccountsResponse submitMfaAnswer(NibblerData nibblerData)
            throws ServiceException, RepositoryException, ValidationException, ProcessingException {
        Nibbler nibbler = nibblerDao.find(nibblerData.getEmail());
        Institution persistedInstitution = institutionDao.findOne(Long.valueOf(nibblerData.getBank().getInstitution().getId()));
        IIntegrationSao integrationSao = (IIntegrationSao) appContext.getBean(persistedInstitution.getSupportedInstitution().getAggregatorQualifier());
        
        if (externalAuthReqsValid(nibblerData.getBank())) {
            AddAccountsResponse response = integrationSao.addAccountsMfaAnswer(nibbler.getExtAccessToken(),
            		persistedInstitution.getSupportedInstitution().getExternalId(),
                    nibblerData.getMfaQuestion(), nibblerData.getMfaAnswer());
            if (response != null && response.getMfaType() == MfaType.NON_MFA) {
                saveCustomerAccounts(nibblerData, response.getAccounts());
            }
            return response;
        } else {
            throw new ValidationException("Financial institution requires fields that have failed validation.");
        }
    }


    private boolean externalAuthReqsValid(Bank bank) {
        boolean isValid = true;
        for (LoginField field : bank.getLoginForm().getLoginField()) {
            if (StringUtils.isNotBlank(field.getValue())) {
                if (field.getValue().length() <= field.getValueLengthMin() ||
                        (field.getValueLengthMax() != 0 && field.getValue().length() > field.getValueLengthMax())) {
                    isValid = false;
                }
            } else {
                isValid = false;
            }

        }
        return isValid;
    }

    /**
     * Returns a null if no MFA is required, otherwise returns the details of the MFA challenge.
     * @param nibblerData
     * @return
     * @throws ProcessingException
     * @throws ServiceException
     */
//	@Transactional(isolation=Isolation.READ_COMMITTED)
//	public MfaResponse registerNibblerWithMfa(NibblerData nibblerData) throws ProcessingException, ServiceException, RepositoryException{
//		MfaResponse resp = plaidSao.linkAccountMfa(nibblerData.getInstUsername(), 
//				nibblerData.getInstPassword(), 
//				nibblerData.getInstPin(), 
//				nibblerData.getInstitution());
//		if(resp!=null){
//			return resp;
//		}else{
//			throw new ProcessingException("There was an issue trying to link the account. Plaid did not respond as expected.");				
//		}
//	}

    /**
     * {"send_method":{"mask":"xxx-xxx-5309"}}
     *
     * @param accessToken
     * @param sendMethod
     * @throws ProcessingException
     * @throws ServiceException
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void sendMfaCode(String accessToken, String sendMethod) throws ProcessingException, ServiceException {
//		plaidSao.initiateMfaSend(accessToken, "{\"send_method\":{\"mask\":\""+sendMethod+"\"}");
    }

    //TODO - use Jackson
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.ACCOUNT_CREATED)
    public void submitMfa(NibblerData nibblerData) throws ProcessingException, ServiceException, RepositoryException {
//		LinkResponse response = null;
//		if(nibblerData.getMfa().length>1){
//			StringBuffer mfaBuffer = new StringBuffer("[");
//			for(int i =0; i<nibblerData.getMfa().length; i++){
//				if(i!=nibblerData.getMfa().length-1) mfaBuffer.append("\""+nibblerData.getMfa()[i]+"\",");
//				else mfaBuffer.append("\""+nibblerData.getMfa()[i]+"\"");
//			}
//			mfaBuffer.append("]");
//			response = plaidSao.submitMfaResponse(nibblerData.getAccessToken(), mfaBuffer.toString(), nibblerData.getInstitution());
//		}else {
//			if(nibblerData.getMfa().length == 1){
//				StringBuffer mfaBuffer = new StringBuffer(nibblerData.getMfa()[0]);	
//				response = plaidSao.submitMfaResponse(nibblerData.getAccessToken(), mfaBuffer.toString(), nibblerData.getInstitution());
//			}
//		}
//		
//		try {
//			if(response !=null ){
//				register(nibblerData, response.getAccessToken());
//				updateAccount(nibblerData, response);
//			}else{
//				throw new ProcessingException("MFA responses cannot be empty.");
//			}
//		} catch (ParseException e) {
//			throw new ProcessingException("Error ocurred while parsing a date." , e);
//		}
    }

    //TODO - use Jackson
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.ACCOUNT_CREATED)
    public void submitQuesMfa(NibblerData nibblerData) throws ProcessingException, ServiceException, RepositoryException {
//		StringBuffer mfaBuffer = new StringBuffer("[");
//		for(int i =0; i<nibblerData.getMfa().length; i++){
//			if(i!=nibblerData.getMfa().length-1) mfaBuffer.append("\""+nibblerData.getMfa()[i]+"\",");
//			else mfaBuffer.append("\""+nibblerData.getMfa()[i]+"\"");
//		}
//		mfaBuffer.append("]");
//		LinkResponse response = plaidSao.submitMfaResponse(nibblerData.getAccessToken(), mfaBuffer.toString(), nibblerData.getInstitution());
//		try {
//			updateAccount(nibblerData, response);
//		} catch (ParseException e) {
//			throw new ProcessingException("Error ocurred while parsing a date." , e);
//		}
    }


    /**
     * Register nibbler with customerId
     *
     * @param nibblerData - nibbler data
     * @param customerId  - customer id
     * @throws ProcessingException
     * @throws RepositoryException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    private void saveCustomerData(NibblerData nibblerData,
                                  String customerId) throws ProcessingException, RepositoryException {
    	
    	String actCode = String.valueOf(RandomUtils.nextLong());
    	Integer inviteCode = RandomUtils.nextInt();
        
    	if(nibblerData.isContributor()){
        	NibblerReceiver receiver = (NibblerReceiver)nibblerDao.findByInvitationCode(nibblerData.getInvitationCode());
        	NibblerContributor contributor = new NibblerContributor();
        	contributor.setExtAccessToken(customerId);

        	contributor.setFirstName(nibblerData.getFirstName());
        	contributor.setLastName(nibblerData.getLastName());
        	contributor.setAddressLine1(nibblerData.getAddress1());
        	contributor.setAddressLine2(nibblerData.getAddress2());
        	contributor.setCity(nibblerData.getCity());
        	contributor.setState(nibblerData.getState());
        	contributor.setZip(nibblerData.getZip());
        	contributor.setType(nibblerData.isContributor() ? NibblerType.contributor : NibblerType.receiver);
        	contributor.setEmail(nibblerData.getEmail());
        	contributor.setReceiver(receiver);

        	NibblerDirectory nibblerDir = new NibblerDirectory();
        	nibblerDir.setUsername(nibblerData.getEmail());
            nibblerDir.setPassword(
                    encoder.encodePassword(
                            String.valueOf(nibblerData.getPassword()),
                            salt));
            nibblerDir.setStatus(NibblerDirectoryStatus.CREATED.name());
            nibblerDir.setActivationCode(actCode);
            
            NibblerPreference prefs = new NibblerPreference();
            prefs.setNibbler(contributor);
            prefs.setWeeklyTargetAmount(new BigDecimal("9.99"));
            setCreated(prefs, nibblerData.getEmail());
            contributor.setNibblerPreferences(prefs);
            
        	
            contributor.setNibblerDir(nibblerDir);
            nibblerDir.setNibbler(contributor);
            setCreated(contributor, nibblerData.getEmail());
            setCreated(nibblerDir, nibblerData.getEmail());
            nibblerDao.create(contributor);
        }else{
        	NibblerReceiver receiver = new NibblerReceiver();
        	receiver.setExtAccessToken(customerId);

        	receiver.setFirstName(nibblerData.getFirstName());
        	receiver.setLastName(nibblerData.getLastName());
        	receiver.setAddressLine1(nibblerData.getAddress1());
        	receiver.setAddressLine2(nibblerData.getAddress2());
        	receiver.setCity(nibblerData.getCity());
        	receiver.setState(nibblerData.getState());
        	receiver.setZip(nibblerData.getZip());
        	receiver.setEmail(nibblerData.getEmail());
        	receiver.setType(nibblerData.isContributor() ? NibblerType.contributor : NibblerType.receiver);
        	receiver.setInvitationCode(inviteCode);

        	NibblerDirectory nibblerDir = new NibblerDirectory();
        	nibblerDir.setUsername(nibblerData.getEmail());
            nibblerDir.setPassword(
                    encoder.encodePassword(
                            String.valueOf(nibblerData.getPassword()),
                            salt));
            nibblerDir.setStatus(NibblerDirectoryStatus.CREATED.name());
            nibblerDir.setActivationCode(actCode);
            
            NibblerPreference prefs = new NibblerPreference();
            prefs.setNibbler(receiver);
            prefs.setWeeklyTargetAmount(new BigDecimal("9.99"));
            setCreated(prefs, nibblerData.getEmail());
            receiver.setNibblerPreferences(prefs);
            
        	
            receiver.setNibblerDir(nibblerDir);
            nibblerDir.setNibbler(receiver);
            setCreated(receiver, nibblerData.getEmail());
            setCreated(nibblerDir, nibblerData.getEmail());
            nibblerDao.create(receiver);
        }
    	
    	
    	nibblerData.setActivationCode(actCode);
    	nibblerData.setInvitationCode(inviteCode);
    	
        

        
    }

    @Transactional(propagation = Propagation.REQUIRED)
    private void saveCustomerAccounts(NibblerData nibblerData, Accounts accounts) throws ServiceException, RepositoryException, ProcessingException {
    	try {
	    	Nibbler nibbler = nibblerDao.find(nibblerData.getEmail());
	    	for (Account account : accounts.getAccount()) {
	    		
	    		AccountType accountType = accountTypeDao.find(account.getAccountType());
	            if (accountType == null) {
	                accountType = new AccountType();
	                accountType.setCode(account.getAccountType());
	                accountType.setDescription(account.getAccountType());
	                setCreated(accountType, nibblerData.getEmail());
	                accountTypeDao.create(accountType);
	            }
	
				NibblerAccount nibblerAccount = new NibblerAccount();
	            nibblerAccount.setNumber(account.getAccountNumber());
	            nibblerAccount.setNumberMask(account.getAccountNumber());
	            Institution institution = institutionDao.findOne(Long.valueOf(nibblerData.getBank().getInstitution().getId()));
	            nibblerAccount.setInstitution(institution);
	            nibblerAccount.setNibbler(nibbler);
	            nibblerAccount.setName(account.getAccountType() + " - " + account.getAccountNumber());
	            nibblerAccount.setExternalId(String.valueOf(account.getAccountExternalId()));
	            nibblerAccount.setAccountType(accountType);
	            setCreated(nibblerAccount, nibblerData.getEmail());
	            nibblerAccount.setLastTransactionPull(new Date(System.currentTimeMillis() - 86400000));
	            nibbler.getAccounts().add(nibblerAccount);
	
	            setUpdated(nibbler, nibblerData.getEmail());
	            nibblerData.setActivationCode(nibbler.getNibblerDir().getActivationCode());
				
		    	if(StringUtils.equalsIgnoreCase(account.getAccountType(), "checking")){
		    		nibblerAccount.setUseForRounding(true);
	                if (account.getBalance() != null) {
	                    AccountBalance balance = new AccountBalance();
	                    balance.setAvailable(new BigDecimal(account.getAvailable() != null ? account.getAvailable() : "0.00"));
	                    balance.setCurrent(new BigDecimal(account.getBalance() != null ? account.getBalance() : "0.00"));
	                    balance.setAccount(nibblerAccount);
	                    setCreated(balance, nibblerData.getEmail());
	                    nibblerAccount.getBalances().add(balance);
	                }
		    	}else if(StringUtils.equalsIgnoreCase(account.getAccountType(), "creditCard")){
		    		nibblerAccount.setUseForRounding(true);
		    		if (account.getBalance() != null) {
	                    AccountBalance balance = new AccountBalance();
	                    balance.setAvailable(new BigDecimal(account.getAvailable() != null ? account.getAvailable() : "0.00"));
	                    balance.setCurrent(new BigDecimal(account.getBalance() != null ? account.getBalance() : "0.00"));
	                    balance.setAccount(nibblerAccount);
	                    if(account.getDetail() != null){
		                    balance.setInterestRate(new BigDecimal(account.getDetail().getInterestRate() != null ? account.getDetail().getInterestRate() : "0.00"));
		                    balance.setCashAdvanceInterestRate(new BigDecimal(account.getDetail().getCashAdvanceInterestRate()!= null ? account.getDetail().getCashAdvanceInterestRate() : "0.00"));
		                    balance.setCreditMaxAmount(new BigDecimal(account.getDetail().getCreditMaxAmount()!= null ? account.getDetail().getCreditMaxAmount() : "0.00"));
		                    balance.setPaymentMinAmount(new BigDecimal(account.getDetail().getPaymentMinAmount()!= null ? account.getDetail().getPaymentMinAmount() : "0.00"));
		                    balance.setPaymentMinAmount(new BigDecimal(account.getDetail().getPaymentMinAmount()!= null ? account.getDetail().getPaymentMinAmount() : "0.00"));
		                    balance.setLastPaymentAmount(new BigDecimal(account.getDetail().getLastPaymentAmount()!= null ? account.getDetail().getLastPaymentAmount() : "0.00"));
		                    balance.setPaymentDueDate(account.getDetail().getPaymentDueDate()!= null ? new SimpleDateFormat().parse(account.getDetail().getPaymentDueDate()) : new Date());
		                    balance.setLastPaymentDate(account.getDetail().getLastPaymentDate()!= null ? new SimpleDateFormat().parse(account.getDetail().getLastPaymentDate()) : new Date());
		                    balance.setLastPaymentAmount(new BigDecimal(account.getDetail().getLastPaymentAmount()!= null ? account.getDetail().getLastPaymentAmount() : "0.00"));
	                    	
	                    }
	                    setCreated(balance, nibblerData.getEmail());
	                    nibblerAccount.getBalances().add(balance);
	                }
		    	}else if(StringUtils.equalsIgnoreCase(account.getAccountType(), "loan")){
		    		nibblerAccount.setUseForRounding(false);
//TODO		    		if(StringUtils.equalsIgnoreCase(institution.getType(), "student-loan"))
		    			
		    		
                    if(!StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod")){
                    	nibblerAccount.setUseForpayoff(true);
                    	AccountBalance balance = new AccountBalance();
                    	balance.setInterestRate(new BigDecimal("6.00"));
	                    balance.setPaymentMinAmount(new BigDecimal("193.33"));
	                    balance.setPayoffAmount(new BigDecimal("10000.00"));
	                    balance.setPrincipalBalance(new BigDecimal("10000.00"));
	                    balance.setYtdInterestPaid(new BigDecimal("0.00"));
	                    balance.setYtdPrincipalPaid(new BigDecimal("0.00"));
	                    balance.setAccount(nibblerAccount);
	                    
	                    setCreated(balance, nibblerData.getEmail());
	                    nibblerAccount.getBalances().add(balance);
	                    
                    }else{
                    	if (account.getBalance() != null) {
    	                    AccountBalance balance = new AccountBalance();
    	                    balance.setAvailable(new BigDecimal(account.getAvailable() != null ? account.getAvailable() : "0.00"));
    	                    balance.setCurrent(new BigDecimal(account.getBalance() != null ? account.getBalance() : "0.00"));
    	                    balance.setAccount(nibblerAccount);
    	                    if(account.getDetail() != null){
    	                    	balance.setInterestRate(new BigDecimal(account.getDetail().getInterestRate() != null ? account.getDetail().getInterestRate() : "0.00"));
    		                    balance.setPaymentMinAmount(new BigDecimal(account.getDetail().getNextPayment() != null ? account.getDetail().getNextPayment() : "0.00"));
    		                    balance.setEscrowBalance(new BigDecimal(account.getDetail().getEscrowBalance() != null ? account.getDetail().getEscrowBalance() : "0.00"));
    		                    balance.setPayoffAmount(new BigDecimal(account.getDetail().getPayoffAmount() != null ? account.getDetail().getPayoffAmount() : "0.00"));
    		                    balance.setLastPaymentAmount(new BigDecimal(account.getDetail().getLastPaymentAmount() != null ? account.getDetail().getLastPaymentAmount() : "0.00"));
    		                    balance.setPaymentDueDate(account.getDetail().getNextPaymentDate()!= null ? new SimpleDateFormat().parse(account.getDetail().getNextPaymentDate()) : new Date());
    		                    balance.setLastPaymentDate(account.getDetail().getLastPaymentReceiveDate()!= null ? new SimpleDateFormat().parse(account.getDetail().getLastPaymentReceiveDate()) : new Date());
    		                    balance.setPrincipalBalance(new BigDecimal(account.getDetail().getPrincipalBalance() != null ? account.getDetail().getPrincipalBalance() : "0.00"));
    		                    balance.setYtdInterestPaid(new BigDecimal(account.getDetail().getYtdInterestPaid() != null ? account.getDetail().getYtdInterestPaid() : "0.00"));
    		                    balance.setYtdPrincipalPaid(new BigDecimal(account.getDetail().getYtdPrincipalPaid() != null ? account.getDetail().getYtdPrincipalPaid() : "0.00"));
    	                    }
    	                    
    	                    setCreated(balance, nibblerData.getEmail());
    	                    nibblerAccount.getBalances().add(balance);
    	                }

                    }
		    	}else{
		    		nibblerAccount.setUseForRounding(false);
		    	}
	    	}
	    	nibblerDao.update(nibbler);
    	} catch (ParseException e) {
			throw new ProcessingException("Error while parsing date from account.", e);
		}
    }

	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		this.appContext = appContext;
		
	}
}
