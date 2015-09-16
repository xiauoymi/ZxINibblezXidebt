/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.NibblerRole;

/**
 * @author ralam1
 *
 */
@Repository
public class NibblerRoleDao extends AbstractHibernateDao<NibblerRole> implements IDao<NibblerRole>, INibblerRoleDao {

	public NibblerRoleDao() {
		super(NibblerRole.class);
	}

	/* (non-Javadoc)
	 * @see com.nibbledebt.core.data.dao.INibblerRoleDao#find(java.lang.String)
	 */
	@Override
	public NibblerRole find(String roleName)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findNibblerRoleByName");
			query.setString("name", roleName);
			return (NibblerRole)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
	
}
