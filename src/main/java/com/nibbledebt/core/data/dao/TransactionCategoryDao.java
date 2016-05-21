/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.nibbledebt.common.error.RepositoryException;
import com.nibbledebt.core.data.model.TransactionCategory;

/**
 * @author Rocky Alam
 *
 */
@Repository
public class TransactionCategoryDao extends AbstractHibernateDao<TransactionCategory> implements ITransactionCategoryDao {
	public TransactionCategoryDao() {
		super(TransactionCategory.class);
	}

	@Override
	public TransactionCategory find(String categoryName)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findCatByName");
			query.setString("name", categoryName);
			return (TransactionCategory)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}

}
