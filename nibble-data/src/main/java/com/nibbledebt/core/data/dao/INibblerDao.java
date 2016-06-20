/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import java.util.List;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.Nibbler;

public interface INibblerDao extends IDao<Nibbler> {

	public abstract Nibbler find(String username) throws RepositoryException;

	public abstract Nibbler findByInvitationCode(Integer invitationCode) throws RepositoryException;

	List<Nibbler> findContributors(Long receiverId) throws RepositoryException;

	Nibbler findReceiver(String username) throws RepositoryException;

	List<Nibbler> find(Nibbler nibbler) throws RepositoryException;
	
	 Nibbler findByReferral(String referralCode) throws RepositoryException;
	 
	 List<Nibbler> findByStatus(String... status) throws RepositoryException;
}