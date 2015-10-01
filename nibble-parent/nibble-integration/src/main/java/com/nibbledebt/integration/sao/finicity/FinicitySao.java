/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.sao.finicity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.integration.finicity.FinicityClient;
import com.nibbledebt.integration.model.Institution;
import com.nibbledebt.integration.model.LoginForm;
import com.nibbledebt.integration.sao.intuit.IIntegrationSao;

/**
 * @author ralam
 *
 */
@Component("finicitySao")
public class FinicitySao implements IIntegrationSao{
	@Autowired
	private Mapper integrationMapper;
	
	@Autowired
	private FinicityClient finicityClient;
	
	public List<Institution> getInstitutions() throws ServiceException {
		List<Institution> institutions = new ArrayList<>();
		try {
			for(com.nibbledebt.integration.finicity.model.Institution finst : Arrays.asList(finicityClient.getInstitutions().getInstitution())){
				institutions.add(integrationMapper.map(finst, Institution.class));
			}
			return institutions;
		} catch (Exception e) {
			throw new ServiceException("Error while retrieving the supported institutions from Intuit CAD.", e);
		}
	}
	
	public Institution getInstitution(String institutionIdentifier) throws ServiceException {
		try {
			return integrationMapper.map(finicityClient.getInstitution(institutionIdentifier), Institution.class);
		} catch (Exception e) {
			throw new ServiceException("Error while retrieving the supported institutions from Intuit CAD.", e);
		}
	}
	
	public LoginForm getInstitutionLoginForm(String institutionIdentifier) throws ServiceException {
		try {			
			return integrationMapper.map(finicityClient.getInstitutionLoginForm(institutionIdentifier), LoginForm.class);
		} catch (Exception e) {
			throw new ServiceException("Error while retrieving the supported institutions from Intuit CAD.", e);
		}
	}
}
