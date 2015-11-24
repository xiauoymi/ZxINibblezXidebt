/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.sao;

import java.util.Date;
import java.util.List;

import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.domain.model.Institution;
import com.nibbledebt.domain.model.LoginField;
import com.nibbledebt.domain.model.LoginForm;
import com.nibbledebt.domain.model.Transaction;
import com.nibbledebt.domain.model.account.AddAccountsResponse;


/**
 * @author ralam
 *
 */
public interface IIntegrationSao {
	public List<Institution> getInstitutions() throws ServiceException;
	public Institution getInstitution(String institutionIdentifier) throws ServiceException;
	public LoginForm getInstitutionLoginForm(String institutionIdentifier) throws ServiceException;
    public AddAccountsResponse addAccounts(String customerId, String institutionId,
                                           LoginField[] fields) throws ServiceException;
    public AddAccountsResponse addAccountsMfaAnswer(String customerId, String institutionId,
                                                    String question, String answer) throws ServiceException;
    public String addCustomer(String userName, String firstName, String lastName) throws ServiceException;
    public void deleteCustomer(String customerId) throws ServiceException;
    public List<Transaction> retrieveTransactions(String customerId, String accountId, Date fromDate, Date toDate, String sort) throws ServiceException;
	List<String> getSuppLoanTypes();
	List<String> getTestInstitutionTypes();
	List<String> getSuppInstitutionTypes();
}
