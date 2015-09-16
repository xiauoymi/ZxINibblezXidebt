/**
 * 
 */
package com.nibbledebt.core.data.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.AccountTransaction;

/**
 * @author Rocky Alam
 *
 */
@Repository
public class AccountTransactionDao extends AbstractHibernateDao<AccountTransaction> implements IAccountTransactionDao{

	public AccountTransactionDao() {
		super(AccountTransaction.class);
	}

	@Override
	public List<AccountTransaction> retrieveUnroundedTrxs()  throws RepositoryException {
		try {
			return this.getCurrentSession().getNamedQuery("listUnroundedTrxs").list();
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public List<AccountTransaction> retrieveTrxs(Long accountId)  throws RepositoryException {
		try {
			return this.getCurrentSession().getNamedQuery("listTrxsByAccount").setLong("accountId", accountId).list();
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}
	
	@Override
	public List<AccountTransaction> retrieveTrxs(Long accountId, Date from, Date to)  throws RepositoryException {
		try {
			return this.getCurrentSession().getNamedQuery("listTrxsByRoundedBetween")
							.setLong("accountId", accountId)
							.setDate("from", from)
							.setDate("to", to).list();
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}
	
}
