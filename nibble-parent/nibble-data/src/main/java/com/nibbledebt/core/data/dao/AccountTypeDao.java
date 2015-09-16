/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.AccountType;

/**
 * @author ralam1
 *
 */
@Repository
public class AccountTypeDao extends AbstractHibernateDao<AccountType> implements IAccountTypeDao {

	public AccountTypeDao() {
		super(AccountType.class);
	}
	
	@Override
	public AccountType find(String code)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findByCode");
			query.setString("code", code);
			return (AccountType)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
}
