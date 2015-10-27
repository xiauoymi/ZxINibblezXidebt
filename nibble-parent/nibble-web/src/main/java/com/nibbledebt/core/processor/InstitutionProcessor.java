/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.common.logging.LogLevel;
import com.nibbledebt.common.logging.Loggable;
import com.nibbledebt.core.data.dao.IInstitutionDao;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.Field;
import com.nibbledebt.domain.model.Bank;
import com.nibbledebt.domain.model.Institution;
import com.nibbledebt.domain.model.LoginField;
import com.nibbledebt.domain.model.LoginForm;
import com.nibbledebt.integration.sao.IIntegrationSao;

/**
 * @author ralam1
 *
 */
@Component
public class InstitutionProcessor {
	private static final String LOGO_LOCATION = "com/nibbledebt/institutions/";
//	private static final String[] SUPPORTED_PLAID_TYPES = {"amex", "capone360", "citi", "fidelity", "pnc", "schwab", "svb", "us", "wells", "bofa", "usaa", "chase"};
	
	@Resource
	private Environment env;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private IInstitutionDao institutionDao;
	
	@Autowired
	@Qualifier("finicitySao")
	private IIntegrationSao integrationSao;
	
	@Autowired
	private ThreadPoolTaskExecutor instSyncExecutor;


    //TODO:asa stub method remove when switch to prod
    private Bank getTestInstitution() {
        Bank institutionDetail = new Bank();
        Institution institution = new Institution();
        LoginForm loginForm = new LoginForm();
        institution.setName("FinBank");
        institution.setId("101732");
        institution.setLogoCode("finbank");
        institution.setHomeUrl("http://www.finbank.com");
        List<LoginField> loginFields = new ArrayList<>();
        LoginField lField = new LoginField();
        lField.setName("Banking Userid");
        lField.setDescription("user id");
        lField.setMask(false);
        lField.setDisplayOrder(1);
        lField.setInstructions("no instructions");
        lField.setId("101732001");
        loginFields.add(lField);
        LoginField pField = new LoginField();
        pField.setName("Banking Password");
        pField.setDescription("Banking Password");
        pField.setMask(true);
        pField.setDisplayOrder(2);
        pField.setInstructions("no instructions");
        pField.setId("101732002");
        loginFields.add(pField);
        loginForm.setLoginField(loginFields);
        institutionDetail.setInstitution(institution);
        institutionDetail.setLoginForm(loginForm);
        return institutionDetail;
    }
	
	@Cacheable(value="instCache", unless="#result == null")
	@Transactional(readOnly=true)
	public List<Bank> getSupportedInstitutions() throws ProcessingException, ServiceException{
		try {
			List<Bank> banks = null;
			List<com.nibbledebt.core.data.model.Institution> primaries = !StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod") ? institutionDao.listTestPrimaries(): institutionDao.listPrimaries();
			if(primaries !=null && !primaries.isEmpty()){
				banks = new ArrayList<>();
				for(com.nibbledebt.core.data.model.Institution inst : primaries){
					Bank bank = new Bank();
					Institution institution = new Institution();
					LoginForm loginForm = new LoginForm();
					institution.setName(inst.getName());
					institution.setId(inst.getExternalId());
					institution.setLogoCode(inst.getLogoCode());
					List<LoginField> loginFields = new ArrayList<>();
					for(Field field : inst.getFields()){
						LoginField lField = new LoginField();
						lField.setId(String.valueOf(field.getExternalId()));
						lField.setName(field.getName());
						lField.setDescription(field.getDisplayName());
						lField.setMask(field.getIsMasked());
						lField.setValue(field.getValue());
						lField.setDisplayOrder(field.getOrder());
						lField.setInstructions(field.getInstruction());
						lField.setValueLengthMax(field.getValidationMaxLength());
						lField.setValueLengthMin(field.getValidationMinLength());
						loginFields.add(lField);
					}
					loginForm.setLoginField(loginFields);
					bank.setLoginForm(loginForm);
					bank.setInstitution(institution);
					banks.add(bank);
				}
			}
			return banks;
		} catch (RepositoryException e) {
			throw new ProcessingException("Error while retrieving supported institutions.", e);
		}
	}
	
    @Cacheable(value="logoCache")
    @Transactional(readOnly = true)
    public byte[] getLogoById(String institutionId) throws ProcessingException{
        try {
			InputStream fis = this.getClass().getClassLoader().getResourceAsStream(LOGO_LOCATION+institutionId);
			return IOUtils.toByteArray(fis==null ? this.getClass().getClassLoader().getResourceAsStream(LOGO_LOCATION+"genericbank") : fis);
        } catch (IOException e) {
            throw new ProcessingException("Error while converting logo inputstream to byte[].", e);
        }
    }
	
//	@Scheduled(cron="0 0 * * * *")
//	@Scheduled(fixedRate=86400000)
	@Loggable(logLevel=LogLevel.INFO)
	public void populateInstitutions() throws ProcessingException{
		try {
			int processors = Runtime.getRuntime().availableProcessors();
			instSyncExecutor.setMaxPoolSize(processors*2);
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
			List<Institution> institutions = integrationSao.getInstitutions();
			for(Institution institution : institutions){
				RunnableAsync<Institution> pop = context.getBean("instPopulate", RunnableAsync.class);
				pop.setEntity(institution);
				instSyncExecutor.execute(pop);
			}
		}  catch (ServiceException e) {
			throw new ProcessingException("Error while retrieving supported institutions list with Intuit.", e);
		}
	}	
}