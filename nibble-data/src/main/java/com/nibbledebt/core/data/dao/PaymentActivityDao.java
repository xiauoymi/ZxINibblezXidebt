/**
 * 
 */
package com.nibbledebt.core.data.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.PaymentActivity;

/**
 * @author ralam
 *
 */
@Repository
public class PaymentActivityDao extends AbstractHibernateDao<PaymentActivity> implements IPaymentActivityDao {

	public PaymentActivityDao() {
		super(PaymentActivity.class);
	}

	/* (non-Javadoc)
	 * @see com.nibbledebt.core.data.dao.IPaymentActivityDao#getByToAccountId(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentActivity> getByToAccountId(Long toAccountId) throws RepositoryException {
		try {
			Query query = this.getCurrentSession().getNamedQuery("getActivityForToAccount");
			query.setLong("toAccountId", toAccountId);
			return query.list();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}

}
