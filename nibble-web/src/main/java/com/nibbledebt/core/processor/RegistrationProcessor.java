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
    
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.ACCOUNT_ACTIVATED)
    @CacheEvict(value = "nibblerCache", key = "#username")
    public void activateNibbler(NibblerData nibblerData) throws ProcessingException, RepositoryException, ServiceException {
        Nibbler nibbler = nibblerDao.find(nibblerData.getEmail());
        if (nibbler == null) {
            throw new ProcessingException("The username you have provded does not exist.");
        }
        if (StringUtils.equals(nibbler.getNibblerDir().getStatus(), NibblerDirectoryStatus.CREATED.name())) {
            if (StringUtils.equals(nibbler.getNibblerDir().getActivationCode(), nibblerData.getActivationCode()) && nibbler.getNibblerDir().getPassword().equals(
                    encoder.encodePassword(
                            String.valueOf(nibblerData.getPassword()),
                            salt))) {
            	
            	IIntegrationSao integrationSao = (IIntegrationSao) appContext.getBean("finicitySao"); //TODO change to load from configration db    			
    		    String customerId = integrationSao.addCustomer(nibblerData.getEmail(), nibblerData.getFirstName(), nibblerData.getLastName());
            	
    		    try {
					if(nibblerData.isContributor()){
						Nibbler receiver = (Nibbler)nibblerDao.findByInvitationCode(nibblerData.getInvitationCode());
						nibbler.getNibblerDir().setStatus(NibblerDirectoryStatus.ACTIVE_NO_LOAN_ACCT.name());
						nibbler.getNibblerDir().setActivationCode("");

					    Set<NibblerRole> nibblerRoles = new HashSet<>();
						nibblerRoles.add(getRole(NibblerRoleType.nibbler_level_1));   
						nibblerRoles.add(getRole(NibblerRoleType.contributor));
					    
						Nibbler contributor = (Nibbler) nibbler;
						contributor.setExtAccessToken(customerId);
						contributor.setReceiver(receiver);
						
					    setUpdated(contributor, nibblerData.getEmail());
					    nibbler.getNibblerDir().setRoles(nibblerRoles);
					    setUpdated(nibbler.getNibblerDir(), nibblerData.getEmail());
					    setUpdated(nibbler, nibblerData.getEmail());                    
					    nibblerDao.update(contributor);
					}else{

						Integer inviteCode = RandomUtils.nextInt();
						Nibbler receiver = (Nibbler) nibbler;
						receiver.setExtAccessToken(customerId);
						receiver.setInvitationCode(inviteCode);

					    Set<NibblerRole> nibblerRoles = new HashSet<>();
						nibblerRoles.add(getRole(NibblerRoleType.receiver));
						nibblerRoles.add(getRole(NibblerRoleType.receiver));

						nibblerData.setInvitationCode(inviteCode);
						
						setUpdated(receiver, nibblerData.getEmail());

					    nibbler.getNibblerDir().setRoles(nibblerRoles);
					    setUpdated(nibbler.getNibblerDir(), nibblerData.getEmail());
					    setUpdated(nibbler, nibblerData.getEmail());
					    nibblerDao.update(receiver);
					}
				} catch (Exception e) {
					integrationSao.deleteCustomer(customerId);
	                throw new ProcessingException("Unexpected error trying to activate customer account.", e);
				}
            	
            } else {
                throw new ProcessingException("Activation code and/or password does not match the account!");
            }
        } else {
            throw new ProcessingException("Already active!");
        }
    }
    
    private AddAccountsResponse addLoanAccount(NibblerData nibblerData, Nibbler nibbler) throws ProcessingException, ServiceException, RepositoryException, ValidationException{
    	if(nibblerData.getInternalUserId() == null){
			throw new ValidationException("The user id must be provided to complete the registration process.");
		}
    	
		if(nibblerData.getLoanAccountBank()!=null){
			Institution loanAccountInstitution = institutionDao.findOne(Long.valueOf(nibblerData.getLoanAccountBank().getInstitution().getId()));
			if(loanAccountInstitution!=null){
				IIntegrationSao integrationSao = (IIntegrationSao) appContext.getBean(loanAccountInstitution.getSupportedInstitution().getAggregatorQualifier());
				
			    AddAccountsResponse overallResponse = new AddAccountsResponse();
			    try {				
			    	if(nibblerData.getLoanAccountBank().getInstitution() != null) {
			            if (externalAuthReqsValid(nibblerData.getRoundupAccountBank())) {
			            	AddAccountsResponse response = integrationSao.addAccounts(nibbler.getExtAccessToken(), loanAccountInstitution.getSupportedInstitution().getExternalId(),
			                        nibblerData.getRoundupAccountBank().getLoginForm().getLoginField().toArray(new LoginField[]{}));
			            	if (response != null){
				            	overallResponse.setQuestionAnswer(response.getQuestionAnswer());
				                overallResponse.setMfaType(response.getMfaType());
				                overallResponse.getAccounts().getAccount().addAll(response.getAccounts().getAccount());
				                if (response.getMfaType() == MfaType.NON_MFA) {
				                    try {
										saveCustomerAccounts(nibblerData, response.getAccounts(), false);
									} catch (Exception e) {
										throw new ProcessingException("Unexpected error tryiung to add loan account" , e);
									}
				                }
			            	}
			            	
			            } else {
			                throw new ValidationException("Financial institution requires fields that have failed validation.");
			            }
			        }
				} catch (Exception e) {
					throw e;
				}
			    return overallResponse;
			}else{
				throw new ValidationException("A loan account institution or bank account institution is required for registration.");
			}
		}else{
			throw new ValidationException("Could not find institution to add with id: "+ nibblerData.getLoanAccountBank().getInstitution().getId());
		}
		
		
	}
    
    private AddAccountsResponse addRoundupAccount(NibblerData nibblerData, Nibbler nibbler) throws NumberFormatException, RepositoryException, ValidationException, ProcessingException{

		Institution roundupAccountInstitution = null;

		if(nibblerData.getRoundupAccountBank()!=null){
		    roundupAccountInstitution = institutionDao.findOne(Long.valueOf(nibblerData.getRoundupAccountBank().getInstitution().getId()));
		}
		
		if(roundupAccountInstitution!=null){
			IIntegrationSao integrationSao = (IIntegrationSao) appContext.getBean(roundupAccountInstitution.getSupportedInstitution().getAggregatorQualifier());
		
		    AddAccountsResponse overallResponse = new AddAccountsResponse();
		    try {				
				if (nibblerData.getRoundupAccountBank() != null && nibblerData.getRoundupAccountBank().getInstitution() != null) {
		            if (externalAuthReqsValid(nibblerData.getRoundupAccountBank())) {
		                AddAccountsResponse response = integrationSao.addAccounts(nibbler.getExtAccessToken(), roundupAccountInstitution.getSupportedInstitution().getExternalId(),
		                        nibblerData.getRoundupAccountBank().getLoginForm().getLoginField().toArray(new LoginField[]{}));
		                if (response != null){
			                overallResponse.setQuestionAnswer(response.getQuestionAnswer());
			                overallResponse.setMfaType(response.getMfaType());
			                overallResponse.setAccounts(response.getAccounts());
			                if (response.getMfaType() == MfaType.NON_MFA) {
			                    try {
									saveCustomerAccounts(nibblerData, response.getAccounts(), true);
								} catch (Exception e) {
									throw e;
								}
			                }
		                }
		                
		            } else {
		                throw new ValidationException("Financial institution requires fields that have failed validation.");
		            }
		        }
			} catch (Exception e) {
				throw new ProcessingException("Unexpected exception while trying to add roundup account.", e);
			}
		    return overallResponse;
		}else{
			throw new ValidationException("A bank account institution is required for this operation.");
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
		if (nibblerData.getLoanAccountBank() != null && nibblerData.getLoanAccountBank().getInstitution() != null) {
            if (externalAuthReqsValid(nibblerData.getLoanAccountBank())) {
            	Nibbler nibbler = nibblerDao.find(username);
            	if(nibbler != null){
            		return addLoanAccount(nibblerData, nibbler);
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
    
    @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor=RepositoryException.class)
	public AddAccountsResponse addRoundupAccount(NibblerData nibblerData, String username) throws ProcessingException, ServiceException, RepositoryException, ValidationException{
		if (nibblerData.getLoanAccountBank() != null && nibblerData.getLoanAccountBank().getInstitution() != null) {
            if (externalAuthReqsValid(nibblerData.getLoanAccountBank())) {
            	Nibbler nibbler = nibblerDao.find(username);
            	if(nibbler != null){
            		return addRoundupAccount(nibblerData, nibbler);
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
		                    balance.setPayoffAmount(new BigDecimal("33000.00"));
		                    balance.setPrincipalBalance(new BigDecimal("33000.00"));
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
