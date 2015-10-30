/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.sao.finicity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.nibbledebt.domain.model.account.MfaType;
import com.nibbledebt.integration.finicity.SecurityContext;
import org.apache.commons.lang.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.domain.model.Institution;
import com.nibbledebt.domain.model.LoginForm;
import com.nibbledebt.domain.model.Transaction;
import com.nibbledebt.domain.model.account.AddAccountsResponse;
import com.nibbledebt.integration.finicity.FinicityClient;
import com.nibbledebt.integration.finicity.model.LoginField;
import com.nibbledebt.integration.finicity.model.trxs.Transactions;
import com.nibbledebt.integration.sao.IIntegrationSao;

/**
 * @author ralam
 *
 */
@Component("finicitySao")
public class FinicitySao implements IIntegrationSao{
	@Autowired
	private Mapper integrationMapper;
	
	@Resource
	private Environment env;
	
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

    @Override
    public AddAccountsResponse addAccounts(String customerId, String institutionId,
                                           com.nibbledebt.domain.model.LoginField[] fields) throws ServiceException {
        try {
            LoginField[] loginFields = new LoginField[fields.length];
            for (int i = 0; i < fields.length; i++) {
                loginFields[i] = integrationMapper.map(fields[i], LoginField.class);
            }
            AddAccountsResponse response = integrationMapper.map(finicityClient.addAccounts(customerId, institutionId,
                    loginFields), AddAccountsResponse.class);
            return response;
        } catch (Exception e) {
            throw new ServiceException("Error while retrieving accounts from Finicity", e);
        }
    }

    @Override
    public AddAccountsResponse addAccountsMfaAnswer(String customerId, String institutionId, String question,
                                                    String answer) throws ServiceException {
        try {
             AddAccountsResponse response = integrationMapper.
                     map(finicityClient.addAccountsWithMfaAnswers(customerId, institutionId, question, answer), AddAccountsResponse.class);
            return response;
        } catch (Exception e) {
            throw new ServiceException("Error while retrieving accounts from Finicity", e);
        }
    }

    public String addCustomer(String userName, String firstName, String lastName) throws ServiceException {
        try {
            return !StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod") ? finicityClient.addTestCustomer(userName, firstName, lastName).getId() : finicityClient.addCustomer(userName, firstName, lastName).getId();
        } catch (Exception e) {
            throw new ServiceException("Error while creating customer for Finicity", e);
        }
    }

	@Override
	public List<Transaction> retrieveTransactions(String customerId, String accountId, Date fromDate, Date toDate, String sort) throws ServiceException{
		 try {
			 Transactions ftrxs = finicityClient.getCustomerAccountTransactions(customerId, accountId, fromDate, toDate, 1, 1000, sort);
			 List<Transaction> trxs = new ArrayList<>();
			 if(ftrxs != null && ftrxs.getTransaction()!=null){
				 for(com.nibbledebt.integration.finicity.model.trxs.Transaction ftrx: ftrxs.getTransaction())
					 trxs.add(integrationMapper.map(ftrx, Transaction.class));
			 }
			 return trxs;
		 } catch (Exception e) {
            throw new ServiceException("Error while retrieving transactions for customer with customerId["+customerId+"] and accountId["+accountId+"].", e);
        }
	}
}
