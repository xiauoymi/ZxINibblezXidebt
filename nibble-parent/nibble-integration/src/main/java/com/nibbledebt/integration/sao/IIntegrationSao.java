/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.sao;

import java.util.List;

import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.integration.model.*;

/**
 * @author ralam
 *
 */
public interface IIntegrationSao {
	public List<Institution> getInstitutions() throws ServiceException;
	public Institution getInstitution(String institutionIdentifier) throws ServiceException;
	public LoginForm getInstitutionLoginForm(String institutionIdentifier) throws ServiceException;
    public DiscoverAccountsResponse getAccounts(String customerId, Long institutionId,
                                                LoginField[] fields) throws ServiceException;
    public Customer addCustomer(String userName, String firstName, String lastName) throws ServiceException;

}
