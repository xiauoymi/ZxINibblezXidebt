/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.common.logging.LogLevel;
import com.nibbledebt.common.logging.Loggable;
import com.nibbledebt.core.data.dao.IInstitutionDao;
import com.nibbledebt.core.data.dao.ISupportedInstitutionDao;
import com.nibbledebt.core.data.dao.InstitutionDao;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.Field;
import com.nibbledebt.core.data.model.SupportedInstitution;
import com.nibbledebt.domain.model.Bank;
import com.nibbledebt.domain.model.Institution;
import com.nibbledebt.domain.model.LoginField;
import com.nibbledebt.domain.model.LoginForm;
import com.nibbledebt.domain.model.NibblerData;
import com.nibbledebt.integration.sao.IIntegrationSao;

/**
 * @author ralam1
 *
 */
@Component
public class InstitutionProcessor {
	private static final String LOGO_LOCATION = "com/nibbledebt/institutions/";
	// private static final String[] SUPPORTED_PLAID_TYPES = {"amex",
	// "capone360", "citi", "fidelity", "pnc", "schwab", "svb", "us", "wells",
	// "bofa", "usaa", "chase"};

	@Resource
	private Environment env;

	@Autowired
	private ApplicationContext context;

	@Autowired
	private IInstitutionDao institutionDao;

	@Autowired
	private ISupportedInstitutionDao supportedInstitutionDao;

	@Autowired
	@Qualifier("finicitySao")
	private IIntegrationSao finicitySao;

	@Autowired
	@Qualifier("intuitCadSao")
	private IIntegrationSao intuitCadSao;

	@Autowired
	private ThreadPoolTaskExecutor instSyncExecutor;

	@PostConstruct
	public void initInstitutions() throws BeansException, ProcessingException {
		if (StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "heroku-dev")) {
			// TODO - have to remove comment and fix it
			context.getBean(this.getClass()).populateInstitutions();
		}
	}

	// @Cacheable(value="instCache", unless="#result == null")
	@Transactional(readOnly = true)
	public List<Bank> getSupportedInstitutions() throws ProcessingException, ServiceException {
		try {
			List<Bank> banks = null;
			List<com.nibbledebt.core.data.model.Institution> primaries = null;
			if (!StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod")) {
				primaries = institutionDao.findByTestModeSupport("banking");
				primaries.addAll(institutionDao.findByTestModeSupport("credit_card"));
				primaries.addAll(institutionDao.findByTestModeSupport("test"));
			} else {
				primaries = institutionDao.findByType("banking");
				primaries.addAll(institutionDao.findByType("credit_card"));
			}
			if (primaries != null && !primaries.isEmpty()) {
				banks = new ArrayList<>();
				for (com.nibbledebt.core.data.model.Institution inst : primaries) {
					if (inst.getSupportedInstitution().getPriority() == 2) {
						Bank bank = new Bank();
						Institution institution = new Institution();
						LoginForm loginForm = new LoginForm();
						institution.setName(inst.getSupportedInstitution().getDisplayName());
						institution.setId(String.valueOf(inst.getId()));
						institution.setLogoCode(inst.getSupportedInstitution().getLogoCode() == null ? "genericbank"
								: inst.getSupportedInstitution().getLogoCode());
						List<LoginField> loginFields = new ArrayList<>();
						if (inst.getFields() != null || inst.getFields().size() > 0) {
							for (Field field : inst.getFields()) {
								LoginField lField = new LoginField();
								lField.setId(String.valueOf(field.getExternalId()));
								lField.setName(field.getName());
								lField.setDescription(field.getDisplayName());
								lField.setMask(field.getIsMasked());
								lField.setValue(field.getValue());
								lField.setDisplayOrder(field.getOrder());
								lField.setDisplayFlag(field.getIsDisplay());
								lField.setInstructions(field.getInstruction());
								lField.setValueLengthMax(field.getValidationMaxLength());
								lField.setValueLengthMin(field.getValidationMinLength());
								loginFields.add(lField);
							}
							loginForm.setLoginField(loginFields);
							bank.setLoginForm(loginForm);
						} else {
							IIntegrationSao integrationSao = (IIntegrationSao) context.getBean("finicitySao");
							institution.setLoginForm(integrationSao
									.getInstitutionLoginForm(inst.getSupportedInstitution().getExternalId()));
						}
						bank.setInstitution(institution);
						banks.add(bank);
					}
				}
			}
			int length=banks.size()>5?5:banks.size();
			return banks.subList(0, length);
		} catch (RepositoryException e) {
			throw new ProcessingException("Error while retrieving supported institutions.");
		}
	}

	@Cacheable(value = "instLoanCache", unless = "#result == null")
	@Transactional(readOnly = true)
	public List<Bank> getSupportedLoanInstitutions() throws ProcessingException, ServiceException {
		try {
			List<Bank> banks = null;
			List<com.nibbledebt.core.data.model.Institution> primaries = null;
			if (!StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod")) {
				primaries = institutionDao.findByTestModeSupport("student_loan");
				primaries.addAll(institutionDao.findByTestModeSupport("test"));
			} else {
				primaries = institutionDao.findByType("student_loan");
			}
			if (primaries != null && !primaries.isEmpty()) {
				banks = new ArrayList<>();
				for (com.nibbledebt.core.data.model.Institution inst : primaries) {
					if (inst.getSupportedInstitution().getPriority() == 2) {
						Bank bank = new Bank();
						Institution institution = new Institution();
						LoginForm loginForm = new LoginForm();
						institution.setName(inst.getSupportedInstitution().getDisplayName());
						institution.setId(String.valueOf(inst.getId()));
						institution.setLogoCode(inst.getSupportedInstitution().getLogoCode() == null ? "genericbank"
								: inst.getSupportedInstitution().getLogoCode());
						List<LoginField> loginFields = new ArrayList<>();
						if (inst.getFields() != null || inst.getFields().size() > 0) {
							for (Field field : inst.getFields()) {
								LoginField lField = new LoginField();
								lField.setId(String.valueOf(field.getExternalId()));
								lField.setName(field.getName());
								lField.setDescription(field.getDisplayName());
								lField.setMask(field.getIsMasked());
								lField.setValue(field.getValue());
								lField.setDisplayFlag(field.getIsDisplay());
								lField.setDisplayOrder(field.getOrder());
								lField.setInstructions(field.getInstruction());
								lField.setValueLengthMax(field.getValidationMaxLength());
								lField.setValueLengthMin(field.getValidationMinLength());
								loginFields.add(lField);
							}
							loginForm.setLoginField(loginFields);
							bank.setLoginForm(loginForm);
						} else {
							IIntegrationSao integrationSao = (IIntegrationSao) context.getBean("finicitySao");
							institution.setLoginForm(integrationSao
									.getInstitutionLoginForm(inst.getSupportedInstitution().getExternalId()));
						}
						bank.setInstitution(institution);
						banks.add(bank);
					}
				}
			}
			return banks.subList(0, 5);
		} catch (RepositoryException e) {
			throw new ProcessingException("Error while retrieving supported institutions.", e);
		}
	}

	@Cacheable(value = "logoCache")
	@Transactional(readOnly = true)
	public byte[] getLogoById(String institutionId) throws ProcessingException {
		try {
			InputStream fis = this.getClass().getClassLoader().getResourceAsStream(LOGO_LOCATION + institutionId);
			return IOUtils.toByteArray(fis == null
					? this.getClass().getClassLoader().getResourceAsStream(LOGO_LOCATION + "genericbank") : fis);
		} catch (IOException e) {
			throw new ProcessingException("Error while converting logo inputstream to byte[].", e);
		}
	}

	/**
	 * Logic to use Intuit as the primary aggregator. If Intuit aggregation
	 * fails, then use Finicity as fallback.
	 * 
	 * @throws ProcessingException
	 */
	// @Scheduled(cron="0 0 * * * *")
	// @Scheduled(fixedRate=86400000)
	@Loggable(logLevel = LogLevel.INFO)
	@Transactional(readOnly = true)
	public void populateInstitutions() throws ProcessingException {
		try {
			int processors = Runtime.getRuntime().availableProcessors();
			instSyncExecutor.setMaxPoolSize(!(processors > 0) ? 6 : processors * 2);
			instSyncExecutor.setCorePoolSize(processors);
			instSyncExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
				@Override
				public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
					try {
						executor.getQueue().put(r);
					} catch (InterruptedException e) {
						throw new RuntimeException("Interrupted while submitting task", e);
					}
				}
			});

			List<SupportedInstitution> supportedInstitutions = supportedInstitutionDao.findAll();
			for (SupportedInstitution institution : supportedInstitutions) {
				RunnableAsync<SupportedInstitution> pop = context.getBean("instPopulate", RunnableAsync.class);
				pop.setEntity(institution);
				instSyncExecutor.execute(pop);
			}
			LoggerFactory.getLogger(this.getClass())
					.info("Spawned all threads for supported institutions : " + supportedInstitutions.size());

		} catch (RepositoryException e) {
			throw new ProcessingException("Error while retrieving supported institutions list.", e);
		}
	}

	@Cacheable(value = "instCache", unless = "#result == null")
	@Transactional(readOnly = true)
	public List<Institution> getApiFinicityInstitutions() throws ProcessingException, ServiceException {
		IIntegrationSao integrationSao = (IIntegrationSao) context.getBean("finicitySao");
		List<Institution> institutions = integrationSao.getInstitutions();
		return institutions;

	}

	@Cacheable(value = "instCache", unless = "#result == null")
	@Transactional(readOnly = true)
	public List<Bank> getApiBanks(String search) throws ProcessingException, ServiceException {
		List<Bank> result = new ArrayList<Bank>();
		try {
			IIntegrationSao integrationSao = (IIntegrationSao) context.getBean("finicitySao");
			List<Institution> institutions = integrationSao.getInstitutions(search, 1, 10);
			institutions.forEach(institution -> {
				Bank bank = new Bank();
				try {
					bank.setLoginForm(integrationSao.getInstitutionLoginForm(institution.getId()));
					if (institution.getLogoCode() == null) {
						SupportedInstitution supportedInstitution = supportedInstitutionDao
								.findByExternlId(institution.getId());
						institution.setLogoCode(supportedInstitution.getLogoCode() == null ? "genericbank"
								: supportedInstitution.getLogoCode());
					}
				} catch (Exception e) {
					institution.setLogoCode("genericbank");
				}
				bank.setInstitution(institution);
				result.add(bank);
			});
		} catch (Exception e) {
			throw new ProcessingException(e.getMessage());
		}
		return result;
	}
	
}