/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.error.RepositoryException;
import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.common.logging.LogLevel;
import com.nibbledebt.common.logging.Loggable;
import com.nibbledebt.core.data.dao.IInstitutionDao;
import com.nibbledebt.core.data.model.Field;
import com.nibbledebt.integration.model.cad.Institution;
import com.nibbledebt.integration.model.cad.InstitutionDetail;
import com.nibbledebt.integration.model.cad.Key;
import com.nibbledebt.integration.model.cad.Keys;
import com.nibbledebt.integration.sao.intuit.IIntuitSao;

/**
 * @author ralam1
 *
 */
@Component
public class InstitutionProcessor {
	private static final String LOGO_LOCATION = "com/nibbledebt/institutions/";
//	private static final String[] SUPPORTED_PLAID_TYPES = {"amex", "capone360", "citi", "fidelity", "pnc", "schwab", "svb", "us", "wells", "bofa", "usaa", "chase"};
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private IInstitutionDao institutionDao;
	
	@Autowired
	@Qualifier("cadSao")
	private IIntuitSao cadSao;
	
	@Autowired
	private ThreadPoolTaskExecutor instSyncExecutor;
	
	@Cacheable(value="instCache", unless="#result == null")
	@Transactional(readOnly=true)
	public List<InstitutionDetail> getSupportedInstitutions() throws ProcessingException, ServiceException{
		try {
			List<InstitutionDetail> insts = null;
			List<com.nibbledebt.core.data.model.Institution> primaries = institutionDao.listPrimaries();
			if(primaries !=null && !primaries.isEmpty()){
				insts = new ArrayList<>();
				for(com.nibbledebt.core.data.model.Institution inst : primaries){
					InstitutionDetail institution = new InstitutionDetail();
					institution.setInstitutionName(inst.getName());
					institution.setInstitutionId(Long.valueOf(inst.getExternalId()));
					institution.setHomeUrl(inst.getHomeUrl());
					Keys keys = new Keys();
					for(Field field : inst.getFields()){
						Key key = new Key();
						key.setName(field.getName());
						key.setDescription(field.getDisplayName());
						key.setMask(field.getIsMasked());
						key.setValue(field.getValue());
						key.setDisplayFlag(field.getIsDisplay());
						key.setDisplayOrder(field.getOrder());
						key.setInstructions(field.getInstruction());
						key.setStatus(field.getStatus());
						keys.getKey().add(key);
					}
					institution.setKeys(keys);
					insts.add(institution);
				}
			}
			return insts;
		} catch (RepositoryException e) {
			throw new ProcessingException("Error while retrieving supported institutions.", e);
		}
	}
	
	@Cacheable(value="logoCache")
	@Transactional(readOnly=true)
	public byte[] getLogo(String institutionType) throws ProcessingException{
		try {	
			return IOUtils.toByteArray(this.getClass().getClassLoader().getResourceAsStream(LOGO_LOCATION+institutionType));
		} catch (IOException e) {
			throw new ProcessingException("Error while converting logo inputstream to byte[].", e);
		}
	}
	
//	@Scheduled(cron="0 0 * * * *")
	@Scheduled(fixedRate=60000)
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
			List<Institution> cadInsts = cadSao.getInstitutions();
			for(Institution cadInst : cadInsts){
//				InstitutionPopulator pop = context.getBean("instPopulate", InstitutionPopulator.class);
//				pop.insertInstitution(cadInst);
				RunnableAsync<Institution> pop = context.getBean("instPopulate", RunnableAsync.class);
				pop.setEntity(cadInst);
				instSyncExecutor.execute(pop);
			}
		}  catch (ServiceException e) {
			throw new ProcessingException("Error while retrieving supported institutions list with Intuit.", e);
		}
	}	
}