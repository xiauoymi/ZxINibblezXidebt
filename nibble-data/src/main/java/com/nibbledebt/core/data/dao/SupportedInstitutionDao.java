/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.SupportedInstitution;

/**
 * @author ralam1
 *
 */
@Repository
public class SupportedInstitutionDao extends AbstractHibernateDao<SupportedInstitution> implements IDao<SupportedInstitution>, ISupportedInstitutionDao {

	public SupportedInstitutionDao() {
		super(SupportedInstitution.class);
	}
	
	/* (non-Javadoc)
	 * @see com.nibbledebt.core.data.dao.INibblerRoleDao#find(java.lang.String)
	 */
	@Override
	public SupportedInstitution findByDisplayName(String displayName)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findSupportedInstitutionByDisplayName");
			query.setString("displayName", displayName);
			return (SupportedInstitution)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
	
	@Override
	public SupportedInstitution findByType(String type)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findSupportedInstitutionByType");
			query.setString("type", type);
			return (SupportedInstitution)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
	
	@Override
	public SupportedInstitution findByTestModeSupport(String type)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findSupportedInstitutionByTypeAndTestModeSupport");
			query.setString("type", type);
			return (SupportedInstitution)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
	
	
}
