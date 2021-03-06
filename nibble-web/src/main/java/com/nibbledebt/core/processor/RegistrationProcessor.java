/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.common.error.ValidationException;
import com.nibbledebt.common.notifier.Notify;
import com.nibbledebt.common.notifier.NotifyMethod;
import com.nibbledebt.common.notifier.NotifyType;
import com.nibbledebt.core.data.dao.IAccountTypeDao;
import com.nibbledebt.core.data.dao.IInstitutionDao;
import com.nibbledebt.core.data.dao.INibblerAccountDao;
import com.nibbledebt.core.data.dao.INibblerDao;
import com.nibbledebt.core.data.dao.INibblerDirectoryDao;
import com.nibbledebt.core.data.dao.INibblerRoleDao;
import com.nibbledebt.core.data.dao.ISupportedInstitutionDao;
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
import com.nibbledebt.core.data.model.SupportedInstitution;
import com.nibbledebt.domain.model.Bank;
import com.nibbledebt.domain.model.LoginField;
import com.nibbledebt.domain.model.LoginForm;
import com.nibbledebt.domain.model.NibblerData;
import com.nibbledebt.domain.model.account.Account;
import com.nibbledebt.domain.model.account.Accounts;
import com.nibbledebt.domain.model.account.AddAccountsResponse;
import com.nibbledebt.domain.model.account.MfaType;
import com.nibbledebt.dwolla.IDwollaClient;
import com.nibbledebt.integration.sao.IIntegrationSao;
import com.nibbledebt.integration.sao.mandrill.AWSMailSao;

import io.swagger.client.ApiException;

/**
 * @author ralam1
 */
@Component
public class RegistrationProcessor extends AbstractProcessor implements ApplicationContextAware {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private INibblerDao nibblerDao;

	@Autowired
	private IAccountTypeDao accountTypeDao;

	@Autowired
	private INibblerRoleDao nibblerRoleDao;

	@Autowired
	private IInstitutionDao institutionDao;

	@Autowired
	private INibblerAccountDao nibblerAccountDao;

	@Autowired
	private ISupportedInstitutionDao supportedInstitutionDao;

	private ApplicationContext appContext;

	@Autowired
	private MessageDigestPasswordEncoder encoder;

	@Autowired
	private String salt;

	@Resource
	private Environment env;

	@Autowired
	private IDwollaClient dwollaClient;

	@Autowired
	private AWSMailSao awsMailSao;

	@Autowired
	private VelocityEngineFactoryBean velocityEngineFactory;

	/**
	 * Registers the user in the nibble database and creates an email with
	 * activation code.
	 * 
	 * @param nibblerData
	 * @return internalUserId
	 * @throws ProcessingException
	 * @throws ServiceException
	 * @throws RepositoryException
	 * @throws ValidationException
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = { ValidationException.class, Exception.class })
	@Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.ACCOUNT_CREATED)
	public Long startRegistration(NibblerData nibblerData)
			throws ProcessingException, ServiceException, RepositoryException, ValidationException {
		return saveCustomerData(nibblerData);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.ACCOUNT_ACTIVATED)
	@CacheEvict(value = "nibblerCache", key = "#username")
	public void activateNibbler(NibblerData nibblerData)
			throws ProcessingException, RepositoryException, ServiceException {
		// Nibbler nibbler =
		// nibblerDao.findOne(nibblerData.getInternalUserId());
		Nibbler nibbler = nibblerDao.find(nibblerData.getEmail());
		try {
			if (nibbler == null) {
				throw new ProcessingException("The username you have provided does not exist.");
			}
			if (!StringUtils.equals(nibbler.getNibblerDir().getPassword(),
					encoder.encodePassword(nibblerData.getPassword(), salt))) {
				throw new ProcessingException("The password you have entered is incorrect.");
			}
			if (StringUtils.equals(nibbler.getNibblerDir().getStatus(), NibblerDirectoryStatus.CREATED.name())) {
				if (StringUtils.equals(nibbler.getNibblerDir().getActivationCode(), nibblerData.getActivationCode())) {

					IIntegrationSao integrationSao = (IIntegrationSao) appContext.getBean("finicitySao"); // TODO
																											// change
																											// to
																											// load
																											// from
																											// configration
																											// db
					String customerId = null;
					try {
						customerId = integrationSao.addCustomer(nibblerData.getEmail(), nibbler.getFirstName(),
								nibbler.getLastName());
						dwollaClient.createCustomer(nibbler);
					} catch (ServiceException e) {
						if(customerId!=null)
						integrationSao.deleteCustomer(customerId);
						throw new ProcessingException("Error while adding finicity customer.");
					} catch (UnknownHostException | ApiException e) {
						if(customerId!=null)
						integrationSao.deleteCustomer(customerId);
						throw new ProcessingException("Error while adding dwolla customer.");
					}
					try {
						if (nibblerData.isContributor()) {
							Nibbler receiver = (Nibbler) nibblerDao
									.findByInvitationCode(nibblerData.getInvitationCode());
							nibbler.getNibblerDir().setStatus(NibblerDirectoryStatus.ACTIVE.name());
							nibbler.getNibblerDir().setActivationCode("");

							Set<NibblerRole> nibblerRoles = new HashSet<>();
							// nibblerRoles.add(getRole(NibblerRoleType.nibbler_level_1));
							nibblerRoles.add(getRole(NibblerRoleType.contributor));

							Nibbler contributor = (Nibbler) nibbler;
							contributor.setExtAccessToken(customerId);
							contributor.setReceiver(receiver);

							nibbler.getNibblerDir().setActivationCode("");
							nibbler.getNibblerDir().setStatus(NibblerDirectoryStatus.ACTIVE.name());

							nibbler.getNibblerDir().setRoles(nibblerRoles);
							setUpdated(nibbler.getNibblerDir(), nibblerData.getEmail());
							setUpdated(nibbler, nibblerData.getEmail());
							nibblerDao.update(contributor);
						} else {

							Integer inviteCode = RandomUtils.nextInt();
							Nibbler receiver = (Nibbler) nibbler;
							receiver.setExtAccessToken(customerId);
							receiver.setInvitationCode(inviteCode);

							Set<NibblerRole> nibblerRoles = new HashSet<>();
							// nibblerRoles.add(getRole(NibblerRoleType.nibbler_level_1));
							nibblerRoles.add(getRole(NibblerRoleType.receiver));

							nibbler.getNibblerDir().setActivationCode("");
							nibbler.getNibblerDir().setStatus(NibblerDirectoryStatus.ACTIVE.name());

							nibblerData.setInvitationCode(inviteCode);

							setUpdated(receiver, nibblerData.getEmail());

							nibbler.getNibblerDir().setRoles(nibblerRoles);
							setUpdated(nibbler.getNibblerDir(), nibblerData.getEmail());
							setUpdated(nibbler, nibblerData.getEmail());
							nibblerDao.update(receiver);
						}
					} catch (Exception e) {
						integrationSao.deleteCustomer(customerId);
						throw new ProcessingException("Unexpected error trying to activate customer account.");
					}

				} else {
					throw new ProcessingException("Activation code does not match our records.");
				}
			} else {
				// this case for user who completed registration and linked with
				// account ,
				// Otherwise it will be redirect to complete registration
				if (!nibbler.getAccounts().isEmpty()) {
					throw new ProcessingException("Already active!");
				}
			}
		} catch (ProcessingException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingException("Unexpected error trying to activate customer account.");
		}
		nibblerData.setFirstName(nibbler.getFirstName());
		nibblerData.setLastName(nibbler.getLastName());
		nibblerData.setAddress1(nibbler.getAddressLine1());
		nibblerData.setAddress2(nibbler.getAddressLine2());
		nibblerData.setState(nibbler.getState());
		nibblerData.setZip(nibbler.getZip());
		nibblerData.setCity(nibbler.getCity());
		nibblerData.setPhone(nibbler.getPhone());
		nibblerData.setDateOfBirth(nibbler.getDateOfBirth());		
		nibblerData.setSsn(nibbler.getSsn());
		nibblerData.setIavToken(nibbler.getNibblerDir().getIavToken());
		if(nibbler.getReferral()!=null)
		nibblerData.setReferral(nibbler.getReferral());
	}

	@Transactional
	@Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.ACCOUNT_LINKED)
	public AddAccountsResponse addLoanAccountByReferral(NibblerData nibblerData, Nibbler contributor)
			throws ProcessingException, ServiceException, RepositoryException, ValidationException {
		try {
			if (nibblerData.getReferral() != null) {
				Nibbler referralNibbler = nibblerDao.findByReferral(nibblerData.getReferral());
				if (referralNibbler == null) {
					throw new ValidationException("Incorrect referral code. Please try agian.");
				}
				List<String> types = Arrays.asList("student_loan", "loan", "student_loan");
				List<NibblerAccount> nibblerAccounts = nibblerAccountDao
						.findNibblerAccountByAccountType(referralNibbler.getNibblerDir().getUsername(), types);
				referralNibbler.getContributors().add(contributor);
				contributor.setReceiver(referralNibbler);
				List<Account> accounts = saveLoanAccounts(nibblerData,
						nibblerAccounts.stream().filter(a -> a.getUseForpayoff()).collect(Collectors.toList()));
				AddAccountsResponse overallResponse = new AddAccountsResponse();
				overallResponse.getAccounts().setAccount(accounts);
				overallResponse.setReferral(contributor.getReferral());
				nibblerData.setReferral(contributor.getReferral());
				nibblerDao.saveOrUpdate(referralNibbler);
				sendNotifAccountLinked(nibblerData);
				return overallResponse;
			} else {
				throw new ValidationException("Incorrect referral code. Please try agian.");
			}
		} catch (ValidationException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProcessingException("Unexpected exception while trying to add loan account.");
		}

	}

	@Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.ACCOUNT_LINKED)
	public AddAccountsResponse addLoanAccount(NibblerData nibblerData, Nibbler nibbler)
			throws ProcessingException, ServiceException, RepositoryException, ValidationException {
		if (nibblerData.getLoanAccountBank() != null) {
			Institution loanAccountInstitution = institutionDao
					.findByExternalId(Long.parseLong(nibblerData.getLoanAccountBank().getInstitution().getId()));
			if (loanAccountInstitution == null) {
				loanAccountInstitution = createInstitution(nibblerData.getLoanAccountBank());
			}
			IIntegrationSao integrationSao = (IIntegrationSao) appContext
					.getBean(loanAccountInstitution.getSupportedInstitution().getAggregatorQualifier());

			AddAccountsResponse overallResponse = new AddAccountsResponse();
			try {
				if (nibblerData.getLoanAccountBank().getInstitution() != null) {
					if (externalAuthReqsValid(nibblerData.getLoanAccountBank())) {
						AddAccountsResponse response = integrationSao.addAccounts(nibbler.getExtAccessToken(),
								loanAccountInstitution.getSupportedInstitution().getExternalId(),
								nibblerData.getLoanAccountBank().getLoginForm().getLoginField()
										.toArray(new LoginField[] {}));
						if (response != null) {
							overallResponse.setQuestionAnswer(response.getQuestionAnswer());
							overallResponse.setMfaType(response.getMfaType());
							if (response.getMfaType() == MfaType.NON_MFA) {
								try {
									overallResponse.getAccounts().getAccount()
											.addAll(saveCustomerAccounts(nibblerData, response.getAccounts(), false));
									overallResponse.setReferral(nibbler.getReferral());
									nibblerData.setReferral(nibbler.getReferral());
								} catch (Exception e) {
									throw new ProcessingException("Unexpected error tryiung to add loan account", e);
								}
							}
						}

					} else {
						throw new ValidationException(
								"Financial institution requires fields that have failed validation.");
					}
				}
			} catch (Exception e) {
				throw e;
			}
			sendNotifAccountLinked(nibblerData);
			return overallResponse;
			// }else{
			// throw new ValidationException("A loan account institution or bank
			// account institution is required for registration.");
			// }
		} else {
			throw new ValidationException("Could not find institution to add with id: "
					+ nibblerData.getLoanAccountBank().getInstitution().getId());
		}

	}

	private AddAccountsResponse addRoundupAccount(NibblerData nibblerData, Nibbler nibbler)
			throws NumberFormatException, RepositoryException, ValidationException, ProcessingException {
		if (nibblerData.getRoundupAccountBank() != null) {
			AddAccountsResponse overallResponse = new AddAccountsResponse();
			try {
				Institution roundupAccountInstitution = institutionDao
						.findByExternalId(Long.parseLong(nibblerData.getRoundupAccountBank().getInstitution().getId()));
				if (roundupAccountInstitution == null) {
					roundupAccountInstitution = createInstitution(nibblerData.getRoundupAccountBank());
				}
				IIntegrationSao integrationSao = (IIntegrationSao) appContext.getBean("finicitySao");

				if (nibblerData.getRoundupAccountBank() != null
						&& nibblerData.getRoundupAccountBank().getInstitution() != null) {
					if (externalAuthReqsValid(nibblerData.getRoundupAccountBank())) {
						AddAccountsResponse response = integrationSao.addAccounts(nibbler.getExtAccessToken(),
								nibblerData.getRoundupAccountBank().getInstitution().getId(),
								nibblerData.getRoundupAccountBank().getLoginForm().getLoginField()
										.toArray(new LoginField[] {}));
						if (response != null) {
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
						throw new ValidationException(
								"Financial institution requires fields that have failed validation.");
					}
				}
			} catch (Exception e) {
				throw new ProcessingException("Unexpected exception while trying to add roundup account.");
			}
			return overallResponse;
			// }else{
			// throw new ValidationException("A bank account institution is
			// required for this operation.");
			// }
		} else {
			throw new ValidationException("Could not find institution to add with id: "
					+ nibblerData.getRoundupAccountBank().getInstitution().getId());
		}

	}

	private NibblerRole getRole(NibblerRoleType roleType) throws RepositoryException {
		NibblerRole role = nibblerRoleDao.find(roleType.name());
		if (role == null) {
			role = new NibblerRole();
			setCreated(role, "sysuser");
			role.setName(roleType.name());
		}
		return role;
	}

	@Transactional(readOnly = true)
	public void getIavToken(NibblerData nibblerData) throws ProcessingException {
		try {
			Nibbler nibbler = nibblerDao.find(nibblerData.getEmail());
			dwollaClient.createCustomer(nibbler);
			nibblerData.setIavToken(nibbler.getNibblerDir().getIavToken());
		} catch (RepositoryException e) {
			e.printStackTrace();
			throw new ProcessingException("Error while loading nibbler from DB.");
		} catch (UnknownHostException | ApiException e) {
			e.printStackTrace();
			throw new ProcessingException("Error while loading customer token from dwolla.");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = RepositoryException.class)
	public AddAccountsResponse addLoanAccountByReferral(NibblerData nibblerData)
			throws ProcessingException, ServiceException, RepositoryException, ValidationException {
		String username = nibblerData.getEmail();
		if (username == null) {
			throw new ValidationException("The user id must be provided to add a loan account process.");
		}
		Nibbler nibbler = nibblerDao.find(username);
		nibblerData.setFirstName(nibbler.getFirstName());
		nibblerData.setLastName(nibbler.getLastName());
		if (nibblerData.getReferral() == null) {
			nibblerData.setReferral(nibbler.getReferral());
		}
		AddAccountsResponse r = addLoanAccountByReferral(nibblerData, nibbler);
		return r;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = RepositoryException.class)
	public AddAccountsResponse addLoanAccount(NibblerData nibblerData)
			throws ProcessingException, ServiceException, RepositoryException, ValidationException {
		String username = nibblerData.getEmail();
		if (true) {
		}
		if (username == null) {
			throw new ValidationException("The user id must be provided to add a loan account process.");
		}
		loadLoginForm(nibblerData.getLoanAccountBank());
		try {
			if (nibblerData.getLoanAccountBank() != null && nibblerData.getLoanAccountBank().getInstitution() != null) {
				if (externalAuthReqsValid(nibblerData.getLoanAccountBank())) {
					Nibbler nibbler = nibblerDao.find(username);
					nibblerData.setFirstName(nibbler.getFirstName());
					nibblerData.setLastName(nibbler.getLastName());
					nibblerData.setReferral(nibbler.getReferral());
					nibblerData.setInternalUserId(nibbler.getId());
					if (nibbler != null) {
						return addLoanAccount(nibblerData, nibbler);
					} else {
						throw new ValidationException(
								"Financial institution requires fields that have failed validation.");
					}

				} else {
					throw new ValidationException("Financial institution requires fields that have failed validation.");
				}
			} else {
				throw new ValidationException("Financial institution not available.");
			}
		} catch (Exception e) {
			throw new ValidationException("Error while adding loan account, please contact support.");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = RepositoryException.class)
	public AddAccountsResponse addRoundupAccount(NibblerData nibblerData, String username)
			throws ProcessingException, ServiceException, RepositoryException, ValidationException {
		if (username == null) {
			throw new ValidationException("The user id must be provided to add a roundup account.");
		}
		loadLoginForm(nibblerData.getRoundupAccountBank());
		try {
			if (nibblerData.getRoundupAccountBank() != null
					&& nibblerData.getRoundupAccountBank().getInstitution() != null) {
				if (externalAuthReqsValid(nibblerData.getRoundupAccountBank())) {
					Nibbler nibbler = nibblerDao.find(username);
					if (!nibbler.isLinkedWith(nibblerData.getRoundupAccountBank().getInstitution().getId())) {
						nibbler.getAccounts().clear();
					}
					nibblerData.setInternalUserId(nibbler.getId());
					if (nibbler != null) {
						return addRoundupAccount(nibblerData, nibbler);
					} else {
						throw new ValidationException(
								"Financial institution requires fields that have failed validation.");
					}

				} else {
					throw new ValidationException("Financial institution requires fields that have failed validation.");
				}
			} else {
				throw new ValidationException("Financial institution not available.");
			}
		} catch (Exception e) {
			throw new ValidationException("Error while adding account, please contact support.");
		}

	}

	private void loadLoginForm(Bank bank) throws ServiceException {
		IIntegrationSao integrationSao = (IIntegrationSao) appContext.getBean("finicitySao");
		final Map<String, String> values = new HashMap<>();
		if (!StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod")) {
			bank.getInstitution().setName("FinBank");
			values.put("Banking Userid", "demo");
			values.put("Banking Password", "go");
		} else {
			values.putAll(bank.getLoginForm().getLoginField().stream()
					.collect(Collectors.toMap(LoginField::getDescription, LoginField::getValue)));
		}
		List<com.nibbledebt.domain.model.Institution> institutions = integrationSao
				.getInstitutions(bank.getInstitution().getName(), 1, 1);
		if (institutions == null || institutions.size() == 0) {
			throw new ServiceException("Financial institution not available.");
		}
		com.nibbledebt.domain.model.Institution institution = institutions.get(0);
		LoginForm loginForm = integrationSao.getInstitutionLoginForm(institution.getId());
		bank.setInstitution(institution);
		loginForm.getLoginField().forEach(l -> {
			l.setValue(values.get(l.getDescription()));
		});
		bank.setLoginForm(loginForm);
	}

	@Transactional()
	public AddAccountsResponse submitRoundupAccountMfaAnswer(NibblerData nibblerData)
			throws ServiceException, RepositoryException, ValidationException, ProcessingException {
		Nibbler nibbler = nibblerDao.find(nibblerData.getEmail());
		Institution persistedInstitution = institutionDao
				.findOne(Long.valueOf(nibblerData.getRoundupAccountBank().getInstitution().getId()));
		IIntegrationSao integrationSao = (IIntegrationSao) appContext
				.getBean(persistedInstitution.getSupportedInstitution().getAggregatorQualifier());

		if (externalAuthReqsValid(nibblerData.getRoundupAccountBank())) {
			Map<String, String> questionAnswer = new HashMap<>();
			questionAnswer.put(nibblerData.getMfaQuestion(), nibblerData.getMfaAnswer());
			Map<String, String> session = new HashMap<>();

			AddAccountsResponse response = integrationSao.addAccountsMfaAnswer(nibbler.getExtAccessToken(),
					persistedInstitution.getSupportedInstitution().getExternalId(), questionAnswer, session);
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
		Institution persistedInstitution = institutionDao
				.findOne(Long.valueOf(nibblerData.getLoanAccountBank().getInstitution().getId()));
		IIntegrationSao integrationSao = (IIntegrationSao) appContext
				.getBean(persistedInstitution.getSupportedInstitution().getAggregatorQualifier());

		if (externalAuthReqsValid(nibblerData.getRoundupAccountBank())) {
			Map<String, String> questionAnswer = new HashMap<>();
			questionAnswer.put(nibblerData.getMfaQuestion(), nibblerData.getMfaAnswer());
			Map<String, String> session = new HashMap<>();

			AddAccountsResponse response = integrationSao.addAccountsMfaAnswer(nibbler.getExtAccessToken(),
					persistedInstitution.getSupportedInstitution().getExternalId(), questionAnswer, session);
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
				if ((field.getValueLengthMin() != null && field.getValue().length() <= field.getValueLengthMin())
						|| (field.getValueLengthMax() != null && field.getValueLengthMax() != 0
								&& field.getValue().length() > field.getValueLengthMax())) {
					isValid = false;
				}
			} else {
				isValid = false;
			}

		}
		return isValid;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private Long saveCustomerData(NibblerData nibblerData)
			throws ProcessingException, RepositoryException, ValidationException {

		Nibbler nibbler = nibblerDao.find(nibblerData.getEmail());
		if (nibbler == null) {
			if (StringUtils.isNotBlank(nibblerData.getFirstName()) && StringUtils.isNotBlank(nibblerData.getLastName())
					&& StringUtils.isNotBlank(nibblerData.getEmail())
					&& StringUtils.isNotBlank(nibblerData.getPassword())
					&& StringUtils.isNotBlank(nibblerData.getCity()) && StringUtils.isNotBlank(nibblerData.getState())
					&& StringUtils.isNotBlank(String.valueOf(nibblerData.getZip()))) {

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
				nibbler.setPhone(nibblerData.getPhone());
				nibbler.setSsn(nibblerData.getSsn());
				nibbler.setDateOfBirth(nibblerData.getDateOfBirth());
				if(nibblerData.getReferral()!=null){
					try {
						Nibbler receiver=nibblerDao.findByReferral(nibblerData.getReferral());
						nibbler.setReceiver(receiver);
						nibbler.setReferral(nibblerData.getReferral());
					} catch (Exception e) {
						throw new ProcessingException("Wrong referral code");
					}
					

				}
				
				NibblerDirectory nibblerDir = new NibblerDirectory();
				nibblerDir.setUsername(nibblerData.getEmail());
				nibblerDir.setPassword(encoder.encodePassword(String.valueOf(nibblerData.getPassword()), salt));
				nibblerDir.setStatus(NibblerDirectoryStatus.CREATED.name());
				nibblerDir.setActivationCode(actCode);

				NibblerPreference prefs = new NibblerPreference();
				prefs.setNibbler(nibbler);
				prefs.setWeeklyTargetAmount(new BigDecimal("5"));
				prefs.setFeeAmount(new BigDecimal("2"));
				setCreated(prefs, nibblerData.getEmail());
				nibbler.setNibblerPreferences(prefs);

				nibbler.setNibblerDir(nibblerDir);
				nibblerDir.setNibbler(nibbler);
				setCreated(nibbler, nibblerData.getEmail());
				setCreated(nibblerDir, nibblerData.getEmail());
				nibblerDao.create(nibbler);
				nibblerData.setActivationCode(actCode);
			} else {
				throw new ValidationException("Username/Password/Name/City/State/Zip are required fields.");
			}

		} else {
			if (StringUtils.equalsIgnoreCase(nibbler.getNibblerDir().getStatus(),
					NibblerDirectoryStatus.ACTIVE.name())) {
				throw new ValidationException("Username already in use.");
			}
		}

		return nibbler.getId();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Long updateCustomerData(NibblerData nibblerData)
			throws ProcessingException, RepositoryException, ValidationException {

		Nibbler nibbler = nibblerDao.find(nibblerData.getEmail());
		if (StringUtils.isNotBlank(nibblerData.getFirstName()) && StringUtils.isNotBlank(nibblerData.getLastName())
				&& StringUtils.isNotBlank(nibblerData.getEmail()) && StringUtils.isNotBlank(nibblerData.getPassword())
				&& StringUtils.isNotBlank(nibblerData.getCity()) && StringUtils.isNotBlank(nibblerData.getState())
				&& StringUtils.isNotBlank(String.valueOf(nibblerData.getZip()))) {
			nibbler.setFirstName(nibblerData.getFirstName());
			nibbler.setLastName(nibblerData.getLastName());
			nibbler.setAddressLine1(nibblerData.getAddress1());
			nibbler.setAddressLine2(nibblerData.getAddress2());
			nibbler.setCity(nibblerData.getCity());
			nibbler.setState(nibblerData.getState());
			nibbler.setZip(nibblerData.getZip());
			nibbler.setEmail(nibblerData.getEmail());
			nibbler.setPhone(nibblerData.getPhone());
			nibbler.setDateOfBirth(nibblerData.getDateOfBirth());
			nibbler.setSsn(nibblerData.getSsn());
			nibbler.getNibblerDir()
					.setPassword(encoder.encodePassword(String.valueOf(nibblerData.getPassword()), salt));
			nibblerDao.update(nibbler);
		} else {
			throw new ValidationException("Username/Password/Name/City/State/Zip are required fields.");
		}
		return nibbler.getId();
	}

	private List<Account> saveCustomerAccounts(NibblerData nibblerData, Accounts accounts, Boolean forRoundUp)
			throws ServiceException, RepositoryException, ProcessingException {
		try {
			Nibbler nibbler = nibblerDao.findOne(nibblerData.getInternalUserId());
			List<Account> result = new ArrayList<Account>();
			for (Account account : accounts.getAccount()) {
				if (account.getAccountNumber().equals(nibblerData.getAccountNumber())
						|| !StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod")) {
					AccountType accountType = accountTypeDao.find(account.getAccountType());
					if (accountType == null) {
						accountType = new AccountType();
						accountType.setCode(account.getAccountType());
						accountType.setDescription(account.getAccountType());
						setCreated(accountType, nibblerData.getEmail());
						accountTypeDao.create(accountType);
					}

					if (StringUtils.equalsIgnoreCase(account.getAccountType(), "checking")
							|| StringUtils.equalsIgnoreCase(account.getAccountType(), "creditCard")
							|| StringUtils.equalsIgnoreCase(account.getAccountType(), "student-loan")
							|| StringUtils.equalsIgnoreCase(account.getAccountType(), "loan")) {
						NibblerAccount nibblerAccount = new NibblerAccount();
						nibblerAccount.setNumber(account.getAccountNumber());
						nibblerAccount.setNumberMask(account.getAccountNumber());
						Institution institution = institutionDao.findByExternalId(
								Long.valueOf(forRoundUp ? nibblerData.getRoundupAccountBank().getInstitution().getId()
										: nibblerData.getLoanAccountBank().getInstitution().getId()));
						nibblerAccount.setInstitution(institution);
						nibblerAccount.setNibbler(nibbler);
						nibblerAccount.setName(account.getAccountType() + " - " + account.getAccountNumber());
						nibblerAccount.setExternalId(String.valueOf(account.getAccountExternalId()));
						nibblerAccount.setAccountType(accountType);
						setCreated(nibblerAccount, nibblerData.getEmail());
						nibblerAccount.setLastTransactionPull(new Date(System.currentTimeMillis() - 86400000));

						if (forRoundUp && StringUtils.equalsIgnoreCase(account.getAccountType(), "checking")) {
							nibblerAccount.setUseForRounding(true);
							if (account.getBalance() != null) {
								AccountBalance balance = new AccountBalance();
								balance.setAvailable(new BigDecimal(
										account.getAvailable() != null ? account.getAvailable() : "0.00"));
								balance.setCurrent(
										new BigDecimal(account.getBalance() != null ? account.getBalance() : "0.00"));
								balance.setAccount(nibblerAccount);
								setCreated(balance, nibblerData.getEmail());
								nibblerAccount.getBalances().add(balance);
							}
							nibbler.addAccount(nibblerAccount);
							result.add(account);
							nibbler.getNibblerDir().setStatus(NibblerDirectoryStatus.ACTIVE.name());
							nibblerAccount.setFundingSourceId(nibblerData.getFundingSourceToken());
						} else if (forRoundUp && StringUtils.equalsIgnoreCase(account.getAccountType(), "creditCard")) {
							nibblerAccount.setUseForRounding(true);
							if (account.getBalance() != null) {
								AccountBalance balance = new AccountBalance();
								balance.setAvailable(new BigDecimal(
										account.getAvailable() != null ? account.getAvailable() : "0.00"));
								balance.setCurrent(
										new BigDecimal(account.getBalance() != null ? account.getBalance() : "0.00"));
								balance.setAccount(nibblerAccount);
								if (account.getDetail() != null) {
									balance.setInterestRate(new BigDecimal(account.getDetail().getInterestRate() != null
											? account.getDetail().getInterestRate() : "0.00"));
									balance.setCashAdvanceInterestRate(
											new BigDecimal(account.getDetail().getCashAdvanceInterestRate() != null
													? account.getDetail().getCashAdvanceInterestRate() : "0.00"));
									balance.setCreditMaxAmount(
											new BigDecimal(account.getDetail().getCreditMaxAmount() != null
													? account.getDetail().getCreditMaxAmount() : "0.00"));
									balance.setPaymentMinAmount(
											new BigDecimal(account.getDetail().getPaymentMinAmount() != null
													? account.getDetail().getPaymentMinAmount() : "0.00"));
									balance.setPaymentMinAmount(
											new BigDecimal(account.getDetail().getPaymentMinAmount() != null
													? account.getDetail().getPaymentMinAmount() : "0.00"));
									balance.setLastPaymentAmount(
											new BigDecimal(account.getDetail().getLastPaymentAmount() != null
													? account.getDetail().getLastPaymentAmount() : "0.00"));
									balance.setPaymentDueDate(account.getDetail().getPaymentDueDate() != null
											? new SimpleDateFormat().parse(account.getDetail().getPaymentDueDate())
											: new Date());
									balance.setLastPaymentDate(account.getDetail().getLastPaymentDate() != null
											? new SimpleDateFormat().parse(account.getDetail().getLastPaymentDate())
											: new Date());
									balance.setLastPaymentAmount(
											new BigDecimal(account.getDetail().getLastPaymentAmount() != null
													? account.getDetail().getLastPaymentAmount() : "0.00"));

								}
								setCreated(balance, nibblerData.getEmail());
								nibblerAccount.getBalances().add(balance);
								nibbler.addAccount(nibblerAccount);
								result.add(account);
								nibbler.getNibblerDir().setStatus(NibblerDirectoryStatus.ACTIVE.name());
							}
							nibblerAccount.setFundingSourceId(nibblerData.getFundingSourceToken());
						} else if (!forRoundUp
								&& StringUtils.equalsIgnoreCase(account.getAccountType(), "student-loan")) {
							nibblerAccount.setUseForRounding(false);
							nibblerAccount.setUseForpayoff(true);
							if (account.getBalance() != null) {
								AccountBalance balance = new AccountBalance();
								balance.setAvailable(new BigDecimal(
										account.getAvailable() != null ? account.getAvailable() : "0.00"));
								balance.setCurrent(
										new BigDecimal(account.getBalance() != null ? account.getBalance() : "0.00"));
								balance.setAccount(nibblerAccount);
								if (account.getDetail() != null) {
									balance.setInterestRate(new BigDecimal(account.getDetail().getInterestRate() != null
											? account.getDetail().getInterestRate() : "0.00"));
									balance.setPaymentMinAmount(
											new BigDecimal(account.getDetail().getNextPayment() != null
													? account.getDetail().getNextPayment() : "0.00"));
									balance.setEscrowBalance(
											new BigDecimal(account.getDetail().getEscrowBalance() != null
													? account.getDetail().getEscrowBalance() : "0.00"));
									balance.setPayoffAmount(new BigDecimal(account.getDetail().getPayoffAmount() != null
											? account.getDetail().getPayoffAmount() : "0.00"));
									balance.setLastPaymentAmount(
											new BigDecimal(account.getDetail().getLastPaymentAmount() != null
													? account.getDetail().getLastPaymentAmount() : "0.00"));
									balance.setPaymentDueDate(account.getDetail().getNextPaymentDate() != null
											? new SimpleDateFormat().parse(account.getDetail().getNextPaymentDate())
											: new Date());
									balance.setLastPaymentDate(
											account.getDetail().getLastPaymentReceiveDate() != null
													? new SimpleDateFormat()
															.parse(account.getDetail().getLastPaymentReceiveDate())
													: new Date());
									balance.setPrincipalBalance(
											new BigDecimal(account.getDetail().getPrincipalBalance() != null
													? account.getDetail().getPrincipalBalance() : "0.00"));
									balance.setYtdInterestPaid(
											new BigDecimal(account.getDetail().getYtdInterestPaid() != null
													? account.getDetail().getYtdInterestPaid() : "0.00"));
									balance.setYtdPrincipalPaid(
											new BigDecimal(account.getDetail().getYtdPrincipalPaid() != null
													? account.getDetail().getYtdPrincipalPaid() : "0.00"));
								}

								setCreated(balance, nibblerData.getEmail());
								nibblerAccount.getBalances().add(balance);
								nibbler.addAccount(nibblerAccount);
								result.add(account);
								nibbler.getNibblerDir().setStatus(NibblerDirectoryStatus.ACTIVE.name());
							}
							nibblerAccount.setDwollaLoanId(nibblerData.getLoanToken());
						} else if (!forRoundUp/**
												 * &&
												 * StringUtils.equalsIgnoreCase(
												 * account.getAccountType(),
												 * "loan")
												 **/
						) {// TODO REMOVE COMMENT IN PROD
							nibblerAccount.setUseForRounding(false);
							nibblerAccount.setUseForpayoff(true);
							if (!StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod")) {
								nibblerAccount.setUseForpayoff(true);
								AccountBalance balance = new AccountBalance();
								balance.setInterestRate(new BigDecimal("4.29"));
								balance.setPaymentMinAmount(new BigDecimal("301.73"));
								balance.setPayoffAmount(new BigDecimal("33000.00"));
								balance.setPrincipalBalance(new BigDecimal("33000.00"));
								balance.setYtdInterestPaid(new BigDecimal("0.00"));
								balance.setYtdPrincipalPaid(new BigDecimal("0.00"));
								balance.setAccount(nibblerAccount);
								Random randomGenerator = new Random();
								nibblerAccount.setNumber("2121210" + randomGenerator.nextInt(100));
								nibblerAccount.setExternalId("000" + randomGenerator.nextInt(100));
								setCreated(balance, nibblerData.getEmail());
								nibblerAccount.getBalances().add(balance);
								nibbler.addAccount(nibblerAccount);
								result.add(account);
								nibbler.getNibblerDir().setStatus(NibblerDirectoryStatus.ACTIVE.name());
								nibblerAccount.setDwollaLoanId(nibblerData.getLoanToken());
							}
						} else {
							nibblerAccount.setUseForRounding(false);
						}

					}
				}
				if (result.size() > 0) {
					break;
				}
			}

			setUpdated(nibbler, nibblerData.getEmail());
			nibblerData.setActivationCode(nibbler.getNibblerDir().getActivationCode());
			if (!forRoundUp) {
				nibbler.setReferral(nibbler.getExtAccessToken());
			}
			accounts.setAccount(result);
			nibblerDao.saveOrUpdate(nibbler);
			return result;
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ProcessingException("Error while parsing date from account.");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private List<Account> saveLoanAccounts(NibblerData nibblerData, List<NibblerAccount> nibblerAccounts)
			throws ServiceException, RepositoryException, ProcessingException {
		try {
			Nibbler nibbler = nibblerDao.find(nibblerData.getEmail());
			List<Account> accounts = new ArrayList<Account>();
			List<Account> result = new ArrayList<Account>();
			for (NibblerAccount nibblerAccountOld : nibblerAccounts) {
				NibblerAccount nibblerAccount = new NibblerAccount();
				nibblerAccount.getBalances().addAll(nibblerAccountOld.getBalances());
				nibblerAccount.getCreditActivity().addAll(nibblerAccountOld.getCreditActivity());
				nibblerAccount.getDebitActivity().addAll(nibblerAccountOld.getDebitActivity());
				nibblerAccount.getTransactions().addAll(nibblerAccountOld.getTransactions());
				nibblerAccount.getLimits().addAll(nibblerAccountOld.getLimits());
				BeanUtils.copyProperties(nibblerAccountOld, nibblerAccount, "id", "nibbler", "balances",
						"creditActivity", "debitActivity", "transactions", "limits");
				if (StringUtils.equalsIgnoreCase(nibblerAccount.getAccountType().getCode(), "checking")
						|| StringUtils.equalsIgnoreCase(nibblerAccount.getAccountType().getCode(), "creditCard")
						|| StringUtils.equalsIgnoreCase(nibblerAccount.getAccountType().getCode(), "student-loan")
						|| StringUtils.equalsIgnoreCase(nibblerAccount.getAccountType().getCode(), "loan")) {
					Account account = new Account();
					accounts.add(account);
					account.setAccountNumber(nibblerAccount.getNumber());
					nibblerAccount.setNibbler(nibbler);
					account.setAccountExternalId(nibblerAccount.getExternalId());
					setCreated(nibblerAccount, nibblerData.getEmail());
					nibblerAccount.setLastTransactionPull(new Date(System.currentTimeMillis() - 86400000));

					if (StringUtils.equalsIgnoreCase(nibblerAccount.getAccountType().getCode(), "student-loan")) {
						nibblerAccount.setUseForRounding(false);
						nibblerAccount.setUseForpayoff(true);
						nibblerAccount.getBalances().forEach(balance -> {
							setCreated(balance, nibblerData.getEmail());
						});
						nibbler.getNibblerDir().setStatus(NibblerDirectoryStatus.ACTIVE.name());
					} else if (StringUtils.equalsIgnoreCase(nibblerAccount.getAccountType().getCode(), "loan")) {
						if (!StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod")) {
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
							nibbler.addAccount(nibblerAccount);
							result.add(account);
							nibbler.getNibblerDir().setStatus(NibblerDirectoryStatus.ACTIVE.name());
						}
					} else {
						nibblerAccount.setUseForRounding(false);
					}

				}
			}
			setUpdated(nibbler, nibblerData.getEmail());
			nibblerData.setActivationCode(nibbler.getNibblerDir().getActivationCode());
			nibbler.setReferral(nibblerData.getReferral());
			nibblerDao.update(nibbler);
			return accounts;
		} catch (Exception e) {
			throw new ProcessingException("Error while parsing date from account.");
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		this.appContext = appContext;

	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = RepositoryException.class)
	public Institution createInstitution(Bank bank) throws RepositoryException, ServiceException, ValidationException {
		SupportedInstitution supportedInstitution = new SupportedInstitution();
		supportedInstitution.setAggegatorName("finicity");
		supportedInstitution.setAggregatorQualifier("finicitySao");
		supportedInstitution.setName(bank.getInstitution().getName());
		supportedInstitution.setExternalId(bank.getInstitution().getId());
		supportedInstitution.setDisplayName(bank.getInstitution().getName());
		supportedInstitution.setPriority(Short.valueOf("1"));
		supportedInstitution.setLogoCode(bank.getInstitution().getLogoCode());
		supportedInstitution.setSupportsTestMode(false);
		supportedInstitution.setCreatedUser("sysscheduler");
		supportedInstitution.setCreatedTs(new Date());
		supportedInstitution.setType(bank.getInstitution().getType());
		supportedInstitutionDao.saveOrUpdate(supportedInstitution);
		LoginForm loginForm = bank.getLoginForm();
		if (loginForm == null) {
			IIntegrationSao integrationSao = (IIntegrationSao) appContext.getBean("finicitySao");
			loginForm = integrationSao.getInstitutionLoginForm(supportedInstitution.getExternalId());
			if (loginForm == null) {
				throw new ValidationException("Financial institution requires fields that have failed validation.");
			}
		}
		com.nibbledebt.core.data.model.Institution persistedInstitution = new com.nibbledebt.core.data.model.Institution();
		persistedInstitution.setHomeUrl(bank.getInstitution().getHomeUrl());
		persistedInstitution.setCreatedTs(new Date());
		persistedInstitution.setCreatedUser("sysscheduler");
		persistedInstitution.setLastSyncedTs(new Date());
		persistedInstitution.setSupportedInstitution(supportedInstitution);
		InstitutionPopulator.convertToFields(loginForm.getLoginField(), persistedInstitution);
		institutionDao.create(persistedInstitution);
		return persistedInstitution;
	}

	private void sendNotifAccountLinked(NibblerData nibblerData) {
		try {
			List<String> toEmails = new ArrayList<>();
			toEmails.add(nibblerData.getEmail());
			toEmails.add("m.boutaskiouine@gmail.com");
			VelocityContext acCtx = new VelocityContext();
			acCtx.put("firstName", nibblerData.getFirstName());
			acCtx.put("lastName", nibblerData.getLastName());
			acCtx.put("referral", nibblerData.getReferral());
			Template acTmpl = velocityEngineFactory.createVelocityEngine().getTemplate("account-linked.vm");
			StringWriter acWriter = new StringWriter();
			acTmpl.merge(acCtx, acWriter);
			awsMailSao.sendEmail("Nibble Account Linked", acWriter.toString(), toEmails);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

}
