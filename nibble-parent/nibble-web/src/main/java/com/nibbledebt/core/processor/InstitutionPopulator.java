/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.core.data.dao.IInstitutionDao;
import com.nibbledebt.core.data.error.RepositoryException;
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
public class InstitutionPopulator implements RunnableAsync<Institution>{
	@Resource
	private List<String> suppInstitutionTypes;
	
	@Resource
	private List<String> testInstitutionTypes;
	
	private static final String AGGREGATOR_FINICITY = "finicity";
	
	@Autowired
	private IInstitutionDao institutionDao;
	  
	@Autowired
	@Qualifier("finicitySao")
	private IIntegrationSao integrationSao;
	
	private Institution instFromLoop;

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRES_NEW, noRollbackFor=Exception.class)
	public void insertInstitution(Institution instFromLoop) {
		try {
			com.nibbledebt.core.data.model.Institution inst = null;
			try {
				inst = institutionDao.findByName(instFromLoop.getName());
			} catch (RepositoryException e) {
				LoggerFactory.getLogger(InstitutionProcessor.class).warn("Error while retrieving Intuit institution from database. ", e);
			}
			
			if(inst == null && suppInstitutionTypes.contains(instFromLoop.getName())){
				Institution instDetail = integrationSao.getInstitution(String.valueOf(instFromLoop.getId()));				
				String instType = determineType(instDetail.getType());
				if(!StringUtils.equalsIgnoreCase(instType, "unknown")){
					LoginForm loginForm = integrationSao.getInstitutionLoginForm(String.valueOf(instFromLoop.getId()));
					inst = new com.nibbledebt.core.data.model.Institution();
					inst.setName(instDetail.getName());
					inst.setExternalId(String.valueOf(instDetail.getId()));
					inst.setAggregatorName(AGGREGATOR_FINICITY);
					inst.setHomeUrl(instDetail.getHomeUrl());
					inst.setCreatedTs(new Date());
					inst.setCreatedUser("system");
					inst.setLogoCode(StringUtils.lowerCase(StringUtils.deleteWhitespace(instDetail.getName())));
					inst.setIsPrimary(suppInstitutionTypes.contains(instDetail.getName()) ? true : false);
					inst.setIsTest(testInstitutionTypes.contains(instDetail.getName()) ? true : false);
					inst.setLastSyncedTs(new Date());
					inst.setPriority(1);
					inst.setType(instType);
					convertToFields(loginForm.getLoginField(), inst);
				}
			}else if(inst!=null  && suppInstitutionTypes.contains(instFromLoop.getName())
					&& ( (inst.getUpdatedTs()==null && inst.getCreatedTs().getTime()<System.currentTimeMillis()-86400000) 
							||  (inst.getUpdatedTs()!=null && inst.getUpdatedTs().getTime()<System.currentTimeMillis()-86400000)) ){
				Institution instDetail = integrationSao.getInstitution(String.valueOf(instFromLoop.getId()));
				LoginForm loginForm = integrationSao.getInstitutionLoginForm(String.valueOf(instFromLoop.getId()));
				inst.setExternalId(String.valueOf(instDetail.getId()));
				inst.setHomeUrl(instDetail.getHomeUrl());
				inst.setUpdatedTs(new Date());
				inst.setUpdatedUser("system");
				inst.setLastSyncedTs(new Date());
				convertToFields(loginForm.getLoginField(), inst);
			}
			if(inst != null) {
				try {
					institutionDao.saveOrUpdate(inst);
				} catch (RepositoryException e) {
					LoggerFactory.getLogger(InstitutionProcessor.class).warn("Error while inserting Intuit institution to database: "+ inst.toString(), e);
				}
			}
			
			
		} catch (ServiceException e) {
			LoggerFactory.getLogger(InstitutionProcessor.class).warn("Error while retrieving supported institutions list with Intuit.", e);
		}
		
	}
	
	private void convertToFields(List<LoginField> loginFields, com.nibbledebt.core.data.model.Institution inst){
		List<com.nibbledebt.core.data.model.Field> fields = new ArrayList<>();
		for(LoginField loginField : loginFields){
			com.nibbledebt.core.data.model.Field field = new com.nibbledebt.core.data.model.Field();
			field.setName(loginField.getName());
			field.setDisplayName(loginField.getDescription());
			field.setIsMasked(loginField.getMask());
			field.setValue(loginField.getValue());
			field.setCreatedTs(new Date());
			field.setCreatedUser("system");
			field.setInstitution(inst);
			field.setInstruction(loginField.getInstructions());
			field.setIsDisplay(loginField.getDisplayOrder()>0 ? true : false);
			field.setOrder(loginField.getDisplayOrder());
			field.setValidationMaxLength(loginField.getValueLengthMax());
			field.setValidationMinLength(loginField.getValueLengthMin());
			fields.add(field);
		}
		inst.getFields().clear();
		inst.getFields().addAll(fields);
	}
	
	private String determineType(String name){
		if(StringUtils.containsIgnoreCase(name, "student") || StringUtils.containsIgnoreCase(name, "education")) return "student_loan";
		else if(StringUtils.containsIgnoreCase(name, "card") || StringUtils.containsIgnoreCase(name, "credit")) return "credit_card";
		else if(StringUtils.containsIgnoreCase(name, "bank")) return "banking";
		else if(StringUtils.containsIgnoreCase(name, "car") || StringUtils.containsIgnoreCase(name, "auto")) return "auto_loan";
		else if(StringUtils.containsIgnoreCase(name, "mortgage")) return "home_loan";
		else if(StringUtils.containsIgnoreCase(name, "401k")) return "401k";
		else if(StringUtils.containsIgnoreCase(name, "test")) return "test";
		else return "unknown";
	}

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRES_NEW, noRollbackFor=Exception.class)
	public void run() {
		insertInstitution(instFromLoop);
	}

	@Override
	public void setEntity(Institution instFromLoop) {
		this.instFromLoop = instFromLoop;
		
	}	
}
