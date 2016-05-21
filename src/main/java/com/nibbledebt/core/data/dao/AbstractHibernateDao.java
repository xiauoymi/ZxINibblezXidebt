/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nibbledebt.common.error.RepositoryException;
import com.nibbledebt.core.data.model.AbstractModel;

/**
 * @author ralam1
 *
 */
public abstract class AbstractHibernateDao<T extends AbstractModel> implements IDao<T>  {

	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> modelClass;
	
	public AbstractHibernateDao(Class<T> modelClass){
		this.modelClass = modelClass;
	}
	
	/* (non-Javadoc)
	 * @see com.globber.data.dao.IDao#findOne(long)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T findOne(long id)  throws RepositoryException{
		try {
			return (T) getCurrentSession().get(modelClass, id);
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.globber.data.dao.IDao#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll()  throws RepositoryException{
		try {
			return getCurrentSession().createQuery("from " + modelClass.getName()).list();
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.globber.data.dao.IDao#create(T)
	 */
	@Override
	public void create(T entity)  throws RepositoryException{
		try {
			getCurrentSession().persist(entity);
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.globber.data.dao.IDao#update(T)
	 */
	@Override
	public void update(T entity)  throws RepositoryException{
		try {
			getCurrentSession().update(entity);
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.globber.data.dao.IDao#delete(T)
	 */
	@Override
	public void delete(T entity)  throws RepositoryException{
		try {
			getCurrentSession().delete(entity);
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.globber.data.dao.IDao#deleteById(long)
	 */
	@Override
	public void deleteById(long entityId)  throws RepositoryException{
		try {
			T entity = findOne(entityId);
			delete(entity);
		} catch (RepositoryException e) {
			throw e;
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
