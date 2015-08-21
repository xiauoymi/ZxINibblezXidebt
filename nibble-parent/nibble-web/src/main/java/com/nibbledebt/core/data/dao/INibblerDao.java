/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import com.nibbledebt.common.error.RepositoryException;
import com.nibbledebt.core.data.model.Nibbler;

public interface INibblerDao extends IDao<Nibbler>{

	public abstract Nibbler find(String username)  throws RepositoryException;

}