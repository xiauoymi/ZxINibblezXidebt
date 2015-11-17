/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.AccountType;

/**
 * @author ralam1
 *
 */
public interface IAccountTypeDao extends IDao<AccountType> {
	public AccountType find(String code)  throws RepositoryException;
}
