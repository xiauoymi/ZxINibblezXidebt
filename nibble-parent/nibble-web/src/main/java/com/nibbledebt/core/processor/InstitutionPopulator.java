/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nibbledebt.common.error.RepositoryException;
import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.core.data.dao.IInstitutionDao;
import com.nibbledebt.integration.model.cad.Institution;
import com.nibbledebt.integration.model.cad.InstitutionDetail;
import com.nibbledebt.integration.model.cad.Key;
import com.nibbledebt.integration.sao.intuit.IIntuitSao;

/**
 * @author ralam
 *
 */
@Component("instPopulate")
@Scope("prototype")
public class InstitutionPopulator implements RunnableAsync<Institution>{
	
	private static final String[] SUPPORTED_TYPES = {"JP Morgan Chase Bank", "Capital One 360", "Bank of America", "PNC Bank", "Discover Bank", "Bank of America", "U.S. Bank - SinglePoint", "USAA Bank", "American Express Credit Card", "BBVA Compass" };
	private static final String AGGREGATOR_INTTUIT = "intuit";
	
	@Autowired
	private IInstitutionDao institutionDao;
	  
	@Autowired
	@Qualifier("cadSao")
	private IIntuitSao cadSao;
	
	private Institution instFromLoop;

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRES_NEW, noRollbackFor=Exception.class)
	public void insertInstitution(Institution instFromLoop) {
		try {
			com.nibbledebt.core.data.model.Institution inst = null;
			try {
				inst = institutionDao.findByName(instFromLoop.getInstitutionName());
			} catch (RepositoryException e) {
				LoggerFactory.getLogger(InstitutionProcessor.class).warn("Error while retrieving Intuit institution from database. ", e);
			}
			
			if(inst == null){
				InstitutionDetail cadInstDetail = cadSao.getInstitution(String.valueOf(instFromLoop.getInstitutionId()));
				String instType = determineType(cadInstDetail.getInstitutionName());
				if(!StringUtils.equalsIgnoreCase(instType, "unknown")){
					inst = new com.nibbledebt.core.data.model.Institution();
					inst.setName(cadInstDetail.getInstitutionName());
					inst.setExternalId(String.valueOf(cadInstDetail.getInstitutionId()));
					inst.setAggregatorName(AGGREGATOR_INTTUIT);
					inst.setHomeUrl(cadInstDetail.getHomeUrl());
					inst.setCreatedTs(new Date());
					inst.setCreatedUser("system");
					inst.setIsPrimary((Arrays.asList((SUPPORTED_TYPES)).contains(cadInstDetail.getInstitutionName()) ? true : false));
					inst.setLastSyncedTs(new Date());
					inst.setPriority(1);
					inst.setType(instType);
					List<com.nibbledebt.core.data.model.Field> fields = new ArrayList<>();
					for(Key key : cadInstDetail.getKeys().getKey()){
						com.nibbledebt.core.data.model.Field field = new com.nibbledebt.core.data.model.Field();
						field.setName(key.getName());
						field.setDisplayName(key.getDescription());
						field.setIsMasked(key.getMask());
						field.setValue(key.getValue());
						field.setCreatedTs(new Date());
						field.setCreatedUser("system");
						field.setInstitution(inst);
						field.setInstruction(key.getInstructions());
						field.setIsDisplay(key.getDisplayFlag());
						field.setOrder(key.getDisplayOrder());
						field.setStatus(key.getStatus());
						fields.add(field);
					}
					inst.setFields(fields);
				}
			}else if(inst!=null && ( (inst.getUpdatedTs()==null && inst.getCreatedTs().getTime()<System.currentTimeMillis()-86400000) ||  (inst.getUpdatedTs()!=null && inst.getUpdatedTs().getTime()<System.currentTimeMillis()-86400000)) ){
				InstitutionDetail cadInstDetail = cadSao.getInstitution(String.valueOf(instFromLoop.getInstitutionId()));
				inst.setExternalId(String.valueOf(cadInstDetail.getInstitutionId()));
				inst.setHomeUrl(cadInstDetail.getHomeUrl());
				inst.setPhone(instFromLoop.getPhoneNumber());
				inst.setUpdatedTs(new Date());
				inst.setUpdatedUser("system");
				inst.setLastSyncedTs(new Date());
				List<com.nibbledebt.core.data.model.Field> fields = new ArrayList<>();
				for(Key key : cadInstDetail.getKeys().getKey()){
					com.nibbledebt.core.data.model.Field field = new com.nibbledebt.core.data.model.Field();
					field.setName(key.getName());
					field.setDisplayName(key.getDescription());
					field.setIsMasked(key.getMask());
					field.setValue(key.getValue());
					field.setCreatedTs(new Date());
					field.setCreatedUser("system");
					field.setInstitution(inst);
					field.setInstruction(key.getInstructions());
					field.setIsDisplay(key.getDisplayFlag());
					field.setOrder(key.getDisplayOrder());
					field.setStatus(key.getStatus());
					fields.add(field);
				}
				inst.setFields(fields);
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
	
	private String determineType(String name){
		if(StringUtils.containsIgnoreCase(name, "student") || StringUtils.containsIgnoreCase(name, "education")) return "student_loan";
		else if(StringUtils.containsIgnoreCase(name, "card") || StringUtils.containsIgnoreCase(name, "credit")) return "credit_card";
		else if(StringUtils.containsIgnoreCase(name, "bank")) return "banking";
		else if(StringUtils.containsIgnoreCase(name, "car") || StringUtils.containsIgnoreCase(name, "auto")) return "auto_loan";
		else if(StringUtils.containsIgnoreCase(name, "mortgage")) return "home_loan";
		else if(StringUtils.containsIgnoreCase(name, "401k")) return "401k";
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
