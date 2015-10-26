/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.Institution;

/**
 * @author ralam1
 *
 */
@Repository
public class InstitutionDao extends AbstractHibernateDao<Institution> implements IDao<Institution>, IInstitutionDao {

	public InstitutionDao() {
		super(Institution.class);
	}
	
	/* (non-Javadoc)
	 * @see com.nibbledebt.core.data.dao.INibblerRoleDao#find(java.lang.String)
	 */
	@Override
	public Institution find(String instName, String externalId)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findInstitutionByNameAndId");
			query.setString("name", instName);
			query.setString("external_id", externalId);
			return (Institution)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.nibbledebt.core.data.dao.INibblerRoleDao#find(java.lang.String)
	 */
	@Override
	public Institution findByName(String instName)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findInstitutionByName");
			query.setString("name", instName);
			return (Institution)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
	
	@Override
	public Institution findByType(String type)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findInstitutionByType");
			query.setString("type", type);
			return (Institution)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}

	@Override
	public List<Institution> listPrimaries() throws RepositoryException {
		try {
			Query query = this.getCurrentSession().getNamedQuery("listPrimaryInstitutions");
			return query.list();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
	
	@Override
	public List<Institution> listTestPrimaries() throws RepositoryException {
		try {
			Query query = this.getCurrentSession().getNamedQuery("listTestPrimaryInstitutions");
			return query.list();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
}
