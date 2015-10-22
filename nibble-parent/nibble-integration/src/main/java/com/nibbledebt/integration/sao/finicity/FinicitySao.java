/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.sao.finicity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nibbledebt.core.data.dao.InstitutionDao;
import com.nibbledebt.integration.finicity.model.LoginField;
import com.nibbledebt.integration.model.Customer;
import com.nibbledebt.integration.model.DiscoverAccountsResponse;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.integration.finicity.FinicityClient;
import com.nibbledebt.integration.model.Institution;
import com.nibbledebt.integration.model.LoginForm;
import com.nibbledebt.integration.sao.IIntegrationSao;

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

    @Autowired
    private InstitutionDao institutionDao;
	
	public List<Institution> getInstitutions() throws ServiceException {
		List<Institution> institutions = new ArrayList<>();
		try {
			for(com.nibbledebt.integration.finicity.model.Institution finst : Arrays.asList(finicityClient.getInstitutions().getInstitution())){
				institutions.add(integrationMapper.map(finst, Institution.class));
			}
			return institutions;
		} catch (Exception e) {
			throw new ServiceException("Error while retrieving the supported institutions from Finicity.", e);
		}
	}
	
	public Institution getInstitution(String institutionIdentifier) throws ServiceException {
		try {
			return integrationMapper.map(finicityClient.getInstitution(institutionIdentifier), Institution.class);
		} catch (Exception e) {
			throw new ServiceException("Error while retrieving the supported institution from Finicity with id:"+institutionIdentifier, e);
		}
	}
	
	public LoginForm getInstitutionLoginForm(String institutionIdentifier) throws ServiceException {
		try {			
			return integrationMapper.map(finicityClient.getInstitutionLoginForm(institutionIdentifier), LoginForm.class);
		} catch (Exception e) {
			throw new ServiceException("Error while retrieving the login form for institution from Finicity with id:"+institutionIdentifier, e);
		}
	}

    public DiscoverAccountsResponse getAccounts(String customerId, Long institutionId,
                                                com.nibbledebt.integration.model.LoginField[] fields)
            throws ServiceException {
        try {
            LoginField[] loginFields = new LoginField[fields.length];
            for (int i = 0; i < fields.length; i++) {
                loginFields[i] = integrationMapper.map(fields[i], LoginField.class);
            }

//            com.nibbledebt.core.data.model.Institution institution = institutionDao.findOne(institutionId);
            DiscoverAccountsResponse response = integrationMapper.map(finicityClient.discoverAccounts(customerId, String.valueOf(institutionId),
                            loginFields), DiscoverAccountsResponse.class);

            return response;
        } catch (Exception e) {
            throw new ServiceException("Error while retrieving accounts from Finicity", e);
        }
    }

    public Customer addCustomer(String userName, String firstName, String lastName) throws ServiceException {
        try {
            //TODO:asa change when switch to prod
//            return integrationMapper.map(finicityClient.addCustomer(userName, firstName, lastName), Customer.class);
            return integrationMapper.map(finicityClient.addTestCustomer(userName, firstName, lastName), Customer.class);
        } catch (Exception e) {
            throw new ServiceException("Error while creating customer for Finicity", e);
        }
    }
}
