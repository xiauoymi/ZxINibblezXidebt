/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import java.util.List;

import com.nibbledebt.common.error.RepositoryException;
import com.nibbledebt.core.data.model.AbstractModel;

public interface IDao<T extends AbstractModel> {

	public abstract T findOne(long id) throws RepositoryException;

	public abstract List<T> findAll() throws RepositoryException;

	public abstract void create(T entity) throws RepositoryException;

	public abstract void update(T entity) throws RepositoryException;

	public abstract void delete(T entity) throws RepositoryException;

	public abstract void deleteById(long entityId) throws RepositoryException;

}