/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.NibblerDirectory;

/**
 * @author Rocky Alam
 *
 */
@Repository
public class NibblerDirectoryDao extends AbstractHibernateDao<NibblerDirectory> implements INibblerDirectoryDao {
	public NibblerDirectoryDao() {
		super(NibblerDirectory.class);
	}

	@Override
	public NibblerDirectory find(String username)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findNibblerDirByUsername");
			query.setString("username", username);
			return (NibblerDirectory)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}

}
