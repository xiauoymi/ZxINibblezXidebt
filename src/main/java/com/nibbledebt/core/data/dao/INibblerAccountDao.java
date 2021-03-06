/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import java.util.Date;
import java.util.List;

import com.nibbledebt.common.error.RepositoryException;
import com.nibbledebt.core.data.model.NibblerAccount;

public interface INibblerAccountDao extends IDao<NibblerAccount>{
	public abstract List<NibblerAccount> find(Date lastTrxPull)  throws RepositoryException;
	public abstract List<NibblerAccount> find(String username) throws RepositoryException ;

}