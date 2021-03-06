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
	public List<Institution> findByType(String type)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findInstitutionByType");
			query.setString("type", type);
			return (List<Institution>)query.list();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
	
	@Override
	public Institution findBySupportedInstitution(Long supportedInstitutionId)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findInstitutionBySupportedInstitution");
			query.setLong("supportedInstitutionId", supportedInstitutionId);
			return (Institution)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
	
	@Override
	public List<Institution> findByTestModeSupport(String type)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findInstitutionByTypeAndTestModeSupport");
			query.setString("type", type);
			return (List<Institution>)query.list();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}

	@Override
	public Institution findByExternalId(Long externalId) throws RepositoryException {
		try {
			Query query = this.getCurrentSession().getNamedQuery("findByExternalId");
			query.setString("externalId", ""+externalId);
			return (Institution)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
	
	
	
}
