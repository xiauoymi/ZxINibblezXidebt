/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.NibblerDirectory;

/**
 * @author Rocky Alam
 *
 */
public interface INibblerDirectoryDao extends IDao<NibblerDirectory> {
	public NibblerDirectory find(String username) throws RepositoryException;
}
