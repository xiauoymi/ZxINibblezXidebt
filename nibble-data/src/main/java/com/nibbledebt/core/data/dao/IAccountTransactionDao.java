/**
 * 
 */
package com.nibbledebt.core.data.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.AccountTransaction;

/**
 * @author Rocky Alam
 *
 */
public interface IAccountTransactionDao extends IDao<AccountTransaction> {
	public List<AccountTransaction> retrieveUnroundedTrxs()  throws RepositoryException;
	public List<AccountTransaction> retrieveTrxs(Long accountId)  throws RepositoryException;
	public List<AccountTransaction> retrieveTrxs(Long accountId, Date from, Date to)  throws RepositoryException;
	public BigDecimal sumOfRoundups(Long accountId)  throws RepositoryException;
}
