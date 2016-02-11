/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
import com.nibbledebt.core.data.model.NibblerDirectory;
import com.nibbledebt.core.data.model.NibblerDirectoryStatus;
import com.nibbledebt.core.data.model.NibblerPreference;
import com.nibbledebt.core.data.model.NibblerRole;
import com.nibbledebt.core.data.model.NibblerRoleType;
import com.nibbledebt.core.data.model.NibblerType;
import com.nibbledebt.domain.model.Bank;
import com.nibbledebt.domain.model.LoginField;
import com.nibbledebt.domain.model.NibblerData;
import com.nibbledebt.domain.model.account.Account;
import com.nibbledebt.domain.model.account.Accounts;
import com.nibbledebt.domain.model.account.AddAccountsResponse;
import com.nibbledebt.domain.model.account.AddAllAccountsResponse;
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
                
                if(StringUtils.equalsIgnoreCase(nibblerDir.getNibbler().getType(), "contributor")){
                	NibblerRole contributorRole = getRole(NibblerRoleType.contributor);
                	nibblerRoles.add(contributorRole);
                }
                
                if(StringUtils.equalsIgnoreCase(nibblerDir.getNibbler().getType(), "receiver")){
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
        Institution persistedInstitution = institutionDao.findOne(Long.valueOf(nibblerData.getLoanAccountBank().getInstitution().getId()));
        IIntegrationSao integrationSao = (IIntegrationSao) appContext.getBean(persistedInstitution.getSupportedInstitution().getAggregatorQualifier());
        
		if (nibblerData.getLoanAccountBank() != null && nibblerData.getLoanAccountBank().getInstitution() != null) {
            if (externalAuthReqsValid(nibblerData.getLoanAccountBank())) {
            	Nibbler nibbler = nibblerDao.find(username);
            	if(nibbler != null){
            		AddAccountsResponse response = integrationSao.addAccounts(nibbler.getExtAccessToken(), persistedInstitution.getSupportedInstitution().getExternalId(),
                            nibblerData.getLoanAccountBank().getLoginForm().getLoginField().toArray(new LoginField[]{}));
            		if (response != null && response.getMfaType() == MfaType.NON_MFA) {
                        saveCustomerAccounts(nibblerData, response.getAccounts(), false);
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
     * Registers the user in the nibble database and creates an email with activation code.
     * @param nibblerData
     * @return internalUserId
     * @throws ProcessingException
     * @throws ServiceException
     * @throws RepositoryException
     * @throws ValidationException
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor={ValidationException.class, Exception.class})
    @Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.ACCOUNT_CREATED)
    public Long startRegistration(NibblerData nibblerData) throws ProcessingException, ServiceException, RepositoryException, ValidationException {
    	return saveCustomerData(nibblerData);
    }
    
    /**
     * Returns a null if no MFA is required, otherwise returns the details of the MFA challenge.
     *
     * @param nibblerData -nibbler registration data
     * @return
     * @throws ProcessingException
     * @throws ServiceException
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor={ValidationException.class, Exception.class})
    public AddAllAccountsResponse completeRegistration(NibblerData nibblerData) throws ProcessingException, ServiceException, RepositoryException, ValidationException {
		if(nibblerData.getInternalUserId() == null){
			throw new ValidationException("The user id must be provided to complete the registration process.");
		}
    	
    	Institution loanAccountInstitution = null;
		Institution roundupAccountInstitution = null;
		
		boolean isInvitedCustomer = (nibblerData.getInvitationCode()!=null && nibblerData.getLoanAccountBank() == null) ? true : false ;
		
		if(nibblerData.getLoanAccountBank()!=null){
		    loanAccountInstitution = institutionDao.findOne(Long.valueOf(nibblerData.getLoanAccountBank().getInstitution().getId()));
		    
		}
		if(nibblerData.getRoundupAccountBank()!=null){
		    roundupAccountInstitution = institutionDao.findOne(Long.valueOf(nibblerData.getRoundupAccountBank().getInstitution().getId()));
		}
		if(loanAccountInstitution!=null || roundupAccountInstitution!=null){
			IIntegrationSao integrationSao = (IIntegrationSao) appContext.getBean(roundupAccountInstitution!=null ? roundupAccountInstitution.getSupportedInstitution().getAggregatorQualifier() : loanAccountInstitution.getSupportedInstitution().getAggregatorQualifier());
			
		    String customerId = integrationSao.addCustomer(nibblerData.getEmail(), nibblerData.getFirstName(), nibblerData.getLastName());
		    
		    AddAllAccountsResponse overallResponse = new AddAllAccountsResponse();
		    try {
				if (StringUtils.isNotBlank(customerId)) updateCustomerData(nibblerData, customerId);
				
				if (nibblerData.getRoundupAccountBank() != null && nibblerData.getRoundupAccountBank().getInstitution() != null) {
		            if (externalAuthReqsValid(nibblerData.getRoundupAccountBank())) {
		                AddAccountsResponse response = integrationSao.addAccounts(customerId, roundupAccountInstitution.getSupportedInstitution().getExternalId(),
		                        nibblerData.getRoundupAccountBank().getLoginForm().getLoginField().toArray(new LoginField[]{}));
		                if (response != null){
			                overallResponse.setRoundupBankQuestionAnswer(response.getQuestionAnswer());
			                overallResponse.setRoundupMfaType(response.getMfaType());
			                overallResponse.setAccounts(response.getAccounts());
			                if (response.getMfaType() == MfaType.NON_MFA) {
			                    try {
									saveCustomerAccounts(nibblerData, response.getAccounts(), true);
								} catch (Exception e) {
									integrationSao.deleteCustomer(customerId);
									throw e;
								}
			                }
		                }
		                
		            } else {
						integrationSao.deleteCustomer(customerId);
		                throw new ValidationException("Financial institution requires fields that have failed validation.");
		            }
		        } 

				if(!isInvitedCustomer){
					if(nibblerData.getLoanAccountBank().getInstitution() != null) {
			            if (externalAuthReqsValid(nibblerData.getRoundupAccountBank())) {
			            	AddAccountsResponse response = integrationSao.addAccounts(customerId, loanAccountInstitution.getSupportedInstitution().getExternalId(),
			                        nibblerData.getRoundupAccountBank().getLoginForm().getLoginField().toArray(new LoginField[]{}));
			            	if (response != null){
				            	overallResponse.setLoanBankQuestionAnswer(response.getQuestionAnswer());
				                overallResponse.setLoanMfaType(response.getMfaType());
				                overallResponse.getAccounts().getAccount().addAll(response.getAccounts().getAccount());
				                if (response.getMfaType() == MfaType.NON_MFA) {
				                    try {
										saveCustomerAccounts(nibblerData, response.getAccounts(), false);
									} catch (Exception e) {
										integrationSao.deleteCustomer(customerId);
										throw e;
									}
				                }
			            	}
			            	
			            } else {
							integrationSao.deleteCustomer(customerId);
			                throw new ValidationException("Financial institution requires fields that have failed validation.");
			            }
			        }else{
						integrationSao.deleteCustomer(customerId);
			        	throw new ValidationException("Loan account or invitation code is required"); 
			        }
				}
			} catch (Exception e) {
				integrationSao.deleteCustomer(customerId);
				throw e;
			}
		    return overallResponse;
		}else{
			throw new ValidationException("A loan account institution or bank account institution is required for registration.");
		}
        

    }

    @Transactional()
    public AddAccountsResponse submitRoundupAccountMfaAnswer(NibblerData nibblerData)
            throws ServiceException, RepositoryException, ValidationException, ProcessingException {
        Nibbler nibbler = nibblerDao.find(nibblerData.getEmail());
        Institution persistedInstitution = institutionDao.findOne(Long.valueOf(nibblerData.getRoundupAccountBank().getInstitution().getId()));
        IIntegrationSao integrationSao = (IIntegrationSao) appContext.getBean(persistedInstitution.getSupportedInstitution().getAggregatorQualifier());
        
        if (externalAuthReqsValid(nibblerData.getRoundupAccountBank())) {
        	Map<String, String> questionAnswer = new HashMap<>();
        	questionAnswer.put(nibblerData.getMfaQuestion(), nibblerData.getMfaAnswer());
        	Map<String, String> session = new HashMap<>();
        	
            AddAccountsResponse response = integrationSao.addAccountsMfaAnswer(nibbler.getExtAccessToken(),
            		persistedInstitution.getSupportedInstitution().getExternalId(),
            		questionAnswer, session);
            if (response != null && response.getMfaType() == MfaType.NON_MFA) {
                saveCustomerAccounts(nibblerData, response.getAccounts(), true);
            }
            return response;
        } else {
            throw new ValidationException("Financial institution requires fields that have failed validation.");
        }
    }
    
    @Transactional()
    public AddAccountsResponse submitLoanAccountMfaAnswer(NibblerData nibblerData)
            throws ServiceException, RepositoryException, ValidationException, ProcessingException {
        Nibbler nibbler = nibblerDao.find(nibblerData.getEmail());
        Institution persistedInstitution = institutionDao.findOne(Long.valueOf(nibblerData.getLoanAccountBank().getInstitution().getId()));
        IIntegrationSao integrationSao = (IIntegrationSao) appContext.getBean(persistedInstitution.getSupportedInstitution().getAggregatorQualifier());
        
        if (externalAuthReqsValid(nibblerData.getRoundupAccountBank())) {
        	Map<String, String> questionAnswer = new HashMap<>();
        	questionAnswer.put(nibblerData.getMfaQuestion(), nibblerData.getMfaAnswer());
        	Map<String, String> session = new HashMap<>();
        	
            AddAccountsResponse response = integrationSao.addAccountsMfaAnswer(nibbler.getExtAccessToken(),
            		persistedInstitution.getSupportedInstitution().getExternalId(),
            		questionAnswer, session);
            if (response != null && response.getMfaType() == MfaType.NON_MFA) {
                saveCustomerAccounts(nibblerData, response.getAccounts(), false);
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
    
    @Transactional(propagation = Propagation.REQUIRED)
    private Long saveCustomerData(NibblerData nibblerData) throws ProcessingException, RepositoryException, ValidationException {
    	
    	Nibbler nibbler = nibblerDao.find(nibblerData.getEmail());
    	if(nibbler == null){
    		if(StringUtils.isNotBlank(nibblerData.getFirstName()) && 
    				StringUtils.isNotBlank(nibblerData.getLastName()) &&
    				StringUtils.isNotBlank(nibblerData.getEmail()) &&
    				StringUtils.isNotBlank(nibblerData.getPassword()) &&
    				StringUtils.isNotBlank(nibblerData.getCity()) && 
    				StringUtils.isNotBlank(nibblerData.getState()) &&
    				StringUtils.isNotBlank(String.valueOf(nibblerData.getZip())) ){
    			
    			String actCode = String.valueOf(100000 + RandomUtils.nextInt(900000));
            	nibbler = new Nibbler();
            	nibbler.setFirstName(nibblerData.getFirstName());
            	nibbler.setLastName(nibblerData.getLastName());
            	nibbler.setAddressLine1(nibblerData.getAddress1());
            	nibbler.setAddressLine2(nibblerData.getAddress2());
            	nibbler.setCity(nibblerData.getCity());
            	nibbler.setState(nibblerData.getState());
            	nibbler.setZip(nibblerData.getZip());
            	nibbler.setType(NibblerType.starter.name());
            	nibbler.setEmail(nibblerData.getEmail());
            	
            	NibblerDirectory nibblerDir = new NibblerDirectory();
            	nibblerDir.setUsername(nibblerData.getEmail());
                nibblerDir.setPassword(
                        encoder.encodePassword(
                                String.valueOf(nibblerData.getPassword()),
                                salt));
                nibblerDir.setStatus(NibblerDirectoryStatus.CREATED.name());
                nibblerDir.setActivationCode(actCode);
                
                NibblerPreference prefs = new NibblerPreference();
                prefs.setNibbler(nibbler);
                prefs.setWeeklyTargetAmount(new BigDecimal("9.99"));
                setCreated(prefs, nibblerData.getEmail());
                nibbler.setNibblerPreferences(prefs);
                
                nibbler.setNibblerDir(nibblerDir);
                nibblerDir.setNibbler(nibbler);
                setCreated(nibbler, nibblerData.getEmail());
                setCreated(nibblerDir, nibblerData.getEmail());
                nibblerDao.create(nibbler);   
                
                nibblerData.setActivationCode(actCode);
    		}else{
    			throw new ValidationException("Username/Password/Name/City/State/Zip are required fields.");
    		}
    		
    	}else{
    		if(StringUtils.equalsIgnoreCase(nibbler.getNibblerDir().getStatus(), NibblerDirectoryStatus.ACTIVE.name())){
    			throw new ValidationException("Username already in use.");
    		}
    	}
    	
    	
    	
    	return nibbler.getId();
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
    private void updateCustomerData(NibblerData nibblerData,
                                  String customerId) throws ProcessingException, RepositoryException {
    	
    	Nibbler nibbler = nibblerDao.findOne(nibblerData.getInternalUserId());    	
    	NibblerDirectory nibblerDir = nibbler.getNibblerDir();
        
    	if(nibblerData.isContributor()){
    		Nibbler receiver = (Nibbler)nibblerDao.findByInvitationCode(nibblerData.getInvitationCode());
    		Nibbler contributor = (Nibbler) nibbler;
        	contributor.setExtAccessToken(customerId);
        	contributor.setReceiver(receiver);
        	
            setUpdated(contributor, nibblerData.getEmail());
            setUpdated(nibblerDir, nibblerData.getEmail());
            
            NibblerRole contributorRole = getRole(NibblerRoleType.contributor);
            nibblerDir.getRoles().add(contributorRole);
            setUpdated(nibblerDir, nibblerData.getEmail());
            
            nibblerDao.update(contributor);
        }else{

        	Integer inviteCode = RandomUtils.nextInt();
        	Nibbler receiver = (Nibbler) nibbler;
        	receiver.setExtAccessToken(customerId);
        	receiver.setInvitationCode(inviteCode);

        	nibblerData.setInvitationCode(inviteCode);
        	
        	setUpdated(receiver, nibblerData.getEmail());
        	setUpdated(nibblerDir, nibblerData.getEmail());
        	
        	NibblerRole contributorRole = getRole(NibblerRoleType.receiver);
            nibblerDir.getRoles().add(contributorRole);
            setUpdated(nibblerDir, nibblerData.getEmail());
            nibblerDao.update(receiver);
        }
       
    }

    @Transactional(propagation = Propagation.REQUIRED)
    private void saveCustomerAccounts(NibblerData nibblerData, Accounts accounts, Boolean forRoundUp) throws ServiceException, RepositoryException, ProcessingException {
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
	            
	            if(StringUtils.equalsIgnoreCase(account.getAccountType(), "checking") ||
            		StringUtils.equalsIgnoreCase(account.getAccountType(), "creditCard") ||
            		StringUtils.equalsIgnoreCase(account.getAccountType(), "student-loan") || 
            		StringUtils.equalsIgnoreCase(account.getAccountType(), "loan")){
	            	
	            	NibblerAccount nibblerAccount = new NibblerAccount();
		            nibblerAccount.setNumber(account.getAccountNumber());
		            nibblerAccount.setNumberMask(account.getAccountNumber());
		            Institution institution = institutionDao.findOne(Long.valueOf(forRoundUp ? nibblerData.getRoundupAccountBank().getInstitution().getId() : nibblerData.getLoanAccountBank().getInstitution().getId()));
		            nibblerAccount.setInstitution(institution);
		            nibblerAccount.setNibbler(nibbler);
		            nibblerAccount.setName(account.getAccountType() + " - " + account.getAccountNumber());
		            nibblerAccount.setExternalId(String.valueOf(account.getAccountExternalId()));
		            nibblerAccount.setAccountType(accountType);
		            setCreated(nibblerAccount, nibblerData.getEmail());
		            nibblerAccount.setLastTransactionPull(new Date(System.currentTimeMillis() - 86400000));
		            
		            if(forRoundUp && StringUtils.equalsIgnoreCase(account.getAccountType(), "checking")){
			    		nibblerAccount.setUseForRounding(true);
		                if (account.getBalance() != null) {
		                    AccountBalance balance = new AccountBalance();
		                    balance.setAvailable(new BigDecimal(account.getAvailable() != null ? account.getAvailable() : "0.00"));
		                    balance.setCurrent(new BigDecimal(account.getBalance() != null ? account.getBalance() : "0.00"));
		                    balance.setAccount(nibblerAccount);
		                    setCreated(balance, nibblerData.getEmail());
		                    nibblerAccount.getBalances().add(balance);
		                }
			            nibbler.getAccounts().add(nibblerAccount);
			    	}else if(forRoundUp && StringUtils.equalsIgnoreCase(account.getAccountType(), "creditCard")){
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
				            nibbler.getAccounts().add(nibblerAccount);
		                }
			    	}else if(!forRoundUp && StringUtils.equalsIgnoreCase(account.getAccountType(), "student-loan")){
			    		nibblerAccount.setUseForRounding(false);
			    		nibblerAccount.setUseForpayoff(true);
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
				            nibbler.getAccounts().add(nibblerAccount);
		                }
			    	}else if(!forRoundUp && StringUtils.equalsIgnoreCase(account.getAccountType(), "loan")){
			    		nibblerAccount.setUseForRounding(false);
			    		nibblerAccount.setUseForpayoff(true);
			    		if(!StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod")){
	                    	nibblerAccount.setUseForpayoff(true);
	                    	AccountBalance balance = new AccountBalance();
	                    	balance.setInterestRate(new BigDecimal("4.29"));
		                    balance.setPaymentMinAmount(new BigDecimal("301.73"));
		                    balance.setPayoffAmount(new BigDecimal("29400.00"));
		                    balance.setPrincipalBalance(new BigDecimal("10000.00"));
		                    balance.setYtdInterestPaid(new BigDecimal("0.00"));
		                    balance.setYtdPrincipalPaid(new BigDecimal("0.00"));
		                    balance.setAccount(nibblerAccount);
		                    
		                    setCreated(balance, nibblerData.getEmail());
		                    nibblerAccount.getBalances().add(balance);
				            nibbler.getAccounts().add(nibblerAccount);
		                    
	                    }
			    	}else{
			    		nibblerAccount.setUseForRounding(false);
			    	}
		            
	            }
	
				
				
		    	
	    	}

			
            setUpdated(nibbler, nibblerData.getEmail());
            nibblerData.setActivationCode(nibbler.getNibblerDir().getActivationCode());
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
