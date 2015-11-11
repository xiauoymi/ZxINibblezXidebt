/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.Nibbler;
import com.nibbledebt.core.data.model.NibblerReceiver;

public interface INibblerDao extends IDao<Nibbler>{

	public abstract Nibbler find(String username)  throws RepositoryException;
	public abstract NibblerReceiver findByInvitationCode(Integer invitationCode)  throws RepositoryException;

}