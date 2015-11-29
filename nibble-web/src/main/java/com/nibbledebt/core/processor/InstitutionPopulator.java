/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.core.data.dao.IInstitutionDao;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.Field;
import com.nibbledebt.core.data.model.SupportedInstitution;
import com.nibbledebt.domain.model.Institution;
import com.nibbledebt.domain.model.LoginField;
import com.nibbledebt.domain.model.LoginForm;
import com.nibbledebt.integration.sao.IIntegrationSao;

/**
 * @author ralam
 *
 */
@Component("instPopulate")
@Scope("prototype")
public class InstitutionPopulator implements RunnableAsync<SupportedInstitution>, ApplicationContextAware{
		
	private ApplicationContext appContext;
	
	@Autowired
	private IInstitutionDao institutionDao;
	
	private SupportedInstitution supportedInstitution;

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRES_NEW, noRollbackFor=Exception.class)
	public void prepareAndPersistInstitution() {
		try {
			IIntegrationSao integrationSao = (IIntegrationSao)appContext.getBean(supportedInstitution.getAggregatorQualifier());

			com.nibbledebt.core.data.model.Institution persistedInstitution = null;
			try {
				persistedInstitution = institutionDao.findBySupportedInstitution(supportedInstitution.getId());
			} catch (RepositoryException e) {
				LoggerFactory.getLogger(InstitutionProcessor.class).warn("Error while retrieving Intuit institution from database. ", e);
			}
			
			if(persistedInstitution == null){
				Institution instDetail = integrationSao.getInstitution(supportedInstitution.getExternalId());				

				LoginForm loginForm = instDetail.getLoginForm();
				if(loginForm == null)
					loginForm = integrationSao.getInstitutionLoginForm(supportedInstitution.getExternalId());
				persistedInstitution = new com.nibbledebt.core.data.model.Institution();
				persistedInstitution.setHomeUrl(instDetail.getHomeUrl());
				persistedInstitution.setCreatedTs(new Date());
				persistedInstitution.setCreatedUser("sysscheduler");
				persistedInstitution.setLastSyncedTs(new Date());
				persistedInstitution.setSupportedInstitution(supportedInstitution);
				convertToFields(loginForm.getLoginField(), persistedInstitution);
			
			}else if(persistedInstitution!=null && ( (persistedInstitution.getUpdatedTs()==null && persistedInstitution.getCreatedTs().getTime()<System.currentTimeMillis()-86400000) 
							||  (persistedInstitution.getUpdatedTs()!=null && persistedInstitution.getUpdatedTs().getTime()<System.currentTimeMillis()-86400000)) ){
				Institution instDetail = integrationSao.getInstitution(supportedInstitution.getExternalId());
				LoginForm loginForm = instDetail.getLoginForm();
				if(loginForm == null)
					loginForm = integrationSao.getInstitutionLoginForm(supportedInstitution.getExternalId());
				persistedInstitution.setUpdatedUser("sysscheduler");
				persistedInstitution.setLastSyncedTs(new Date());
				convertToFields(loginForm.getLoginField(), persistedInstitution);
			}
			
			if(persistedInstitution != null) {
				try {
					institutionDao.saveOrUpdate(persistedInstitution);
				} catch (RepositoryException e) {
					LoggerFactory.getLogger(InstitutionProcessor.class).warn("Error while inserting Intuit institution to database: "+ persistedInstitution.toString(), e);
				}
			}
			
			
		} catch (ServiceException e) {
			LoggerFactory.getLogger(InstitutionProcessor.class).warn("Error while retrieving supported institutions list with Intuit.", e);
		}
		
	}
	
	private void convertToFields(List<LoginField> loginFields, com.nibbledebt.core.data.model.Institution inst){
		List<com.nibbledebt.core.data.model.Field> fields = new ArrayList<>();
		for(LoginField loginField : loginFields){
			Field field = new Field();
			field.setName(loginField.getName());
			field.setDisplayName(loginField.getDescription());
			field.setIsMasked(loginField.getMask());
			field.setValue(loginField.getValue());
			field.setCreatedTs(new Date());
			field.setCreatedUser("sysscheduler");
			field.setInstitution(inst);
			field.setInstruction(loginField.getInstructions());
			field.setIsDisplay(loginField.getDisplayFlag());
			field.setOrder(loginField.getDisplayOrder());
			field.setValidationMaxLength(loginField.getValueLengthMax());
			field.setValidationMinLength(loginField.getValueLengthMin());
            field.setExternalId(loginField.getId());
			fields.add(field);

		}
		inst.getFields().clear();
		inst.getFields().addAll(fields);
	}

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRES_NEW, noRollbackFor=Exception.class)
	public void run() {
		prepareAndPersistInstitution();
	}

	@Override
	public void setEntity(SupportedInstitution supportedInstitution) {
		this.supportedInstitution = supportedInstitution;
		
	}

	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		this.appContext = appContext;
		
	}	
}
