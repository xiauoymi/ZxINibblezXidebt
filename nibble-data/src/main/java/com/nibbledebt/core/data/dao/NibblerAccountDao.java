/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.nibbledebt.core.data.error.RepositoryException;
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
	
	@Override
	public NibblerAccount findByExternalId(String externalId) throws RepositoryException {
		try {
			Query query = this.getCurrentSession().getNamedQuery("findAcctByExtId");
			query.setString("externalId", externalId);
			return (NibblerAccount)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}

    @Override
    public NibblerAccount findByUserNameAndId(String username, Long accountId) throws RepositoryException {
        try {
            Query query = this.getCurrentSession().getNamedQuery("findAcctByUserAndId");
            query.setString("username", username);
            query.setLong("id", accountId);
            return (NibblerAccount)query.uniqueResult();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
	public NibblerAccount findByUseForPayoff(String username) throws RepositoryException {
		try {
			Query query = this.getCurrentSession().getNamedQuery("findAcctByUseForPayoff");
			query.setString("username", username);
			return (NibblerAccount)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}

	@Override
	public List<NibblerAccount> findNibblerAccountByAccountType(String username,List<String> types) throws RepositoryException {
		try {
			Query query = this.getCurrentSession().getNamedQuery("findNibblerAccountByAccountType");
			query.setString("username", username);
			query.setParameterList("types", types);
			return query.list();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
}
