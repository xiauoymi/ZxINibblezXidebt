/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.sao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.domain.model.Institution;
import com.nibbledebt.domain.model.LoginField;
import com.nibbledebt.domain.model.LoginForm;
import com.nibbledebt.domain.model.Transaction;
import com.nibbledebt.domain.model.account.AddAccountsResponse;
import com.nibbledebt.integration.finicity.model.Customers;
import com.nibbledebt.integration.finicity.model.TransactionTest;
import com.nibbledebt.integration.finicity.model.accounts.Accounts;
import com.nibbledebt.integration.finicity.model.accounts.QuestionRequest;


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
    												Map<String, String> questionAnswer, Map<String, String> session) throws ServiceException;
    public String addCustomer(String userName, String firstName, String lastName) throws ServiceException;
    public void deleteCustomer(String customerId) throws ServiceException;
    public List<Transaction> retrieveTransactions(String customerId, String accountId, Date fromDate, Date toDate, String sort) throws ServiceException;
    public List<Institution>  getInstitutions(String search,Integer start,Integer limit) throws ServiceException;
	public TransactionTest addTestTx(String customerId, String accountId) throws ServiceException;
	public ResponseEntity<String> addCustomerAccountsMfaString(String customerId, String institutionId, QuestionRequest[] questions) throws ServiceException;
	public Customers getCustomers() throws ServiceException;
	public Accounts getAccounts(String customerId, String institutionId) throws ServiceException;
}
