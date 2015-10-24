/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.sao;

import java.util.List;

import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.domain.model.Institution;
import com.nibbledebt.domain.model.LoginField;
import com.nibbledebt.domain.model.LoginForm;
import com.nibbledebt.domain.model.account.AddAccountsResponse;


/**
 * @author ralam
 *
 */
public interface IIntegrationSao {
	public List<Institution> getInstitutions() throws ServiceException;
	public Institution getInstitution(String institutionIdentifier) throws ServiceException;
	public LoginForm getInstitutionLoginForm(String institutionIdentifier) throws ServiceException;
    public AddAccountsResponse addAccounts(String customerId, Long institutionId,
                                           LoginField[] fields) throws ServiceException;
    public String addCustomer(String userName, String firstName, String lastName) throws ServiceException;
}
