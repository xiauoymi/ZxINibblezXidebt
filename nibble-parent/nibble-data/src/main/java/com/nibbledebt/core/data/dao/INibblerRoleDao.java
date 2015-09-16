/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.NibblerRole;

public interface INibblerRoleDao {

	public abstract NibblerRole find(String roleName)  throws RepositoryException;

}