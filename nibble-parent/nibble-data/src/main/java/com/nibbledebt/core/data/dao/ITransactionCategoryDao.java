/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.TransactionCategory;

public interface ITransactionCategoryDao extends IDao<TransactionCategory>{

	public abstract TransactionCategory find(String categoryName)  throws RepositoryException;

}