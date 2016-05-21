/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.nibbledebt.common.error.RepositoryException;
import com.nibbledebt.core.data.model.NibblerAccount;

/**
 * @author ralam
 *
 */
@Repository
public class NibblerAccountDao extends AbstractHibernateDao<NibblerAccount> implements INibblerAccountDao{

	public NibblerAccountDao() {
		super(NibblerAccount.class);
	}
	
	@Override
	public List<NibblerAccount> find(Date lastTransactionPull) throws RepositoryException {
		try {
			Query query = this.getCurrentSession().getNamedQuery("findAcctByLastPull");
			query.setTimestamp("lastTransactionPull", lastTransactionPull);
			return query.list();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
	
	@Override
	public List<NibblerAccount> find(String username) throws RepositoryException {
		try {
			Query query = this.getCurrentSession().getNamedQuery("findAcctByUser");
			query.setString("username", username);
			return query.list();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
}
