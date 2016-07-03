/**
 * 
 */
package com.nibbledebt.core.data.dao;

import java.util.List;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.PaymentActivity;

/**
 * @author ralam
 *
 */
public interface IPaymentActivityDao extends IDao<PaymentActivity> {
	List<PaymentActivity> getByToAccountId (final Long toAccountId) throws RepositoryException;
	public List<PaymentActivity> getByType(String username,String type) throws RepositoryException;
}
