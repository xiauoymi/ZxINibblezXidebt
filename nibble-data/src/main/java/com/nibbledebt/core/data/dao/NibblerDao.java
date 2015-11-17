/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.Nibbler;
import com.nibbledebt.core.data.model.NibblerContributor;
import com.nibbledebt.core.data.model.NibblerReceiver;

/**
 * @author ralam1
 *
 */
@Repository
public class NibblerDao extends AbstractHibernateDao<Nibbler> implements INibblerDao {

	public NibblerDao() {
		super(Nibbler.class);
	}
	
	@Override
	public Nibbler find(String username)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findNibblerByUsername");
			query.setString("username", username);
			return (Nibbler)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
	
	@Override
	public NibblerReceiver findReceiver(String username)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findReceiverByUsername");
			query.setString("username", username);
			return (NibblerReceiver)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
	
	@Override
	public List<NibblerContributor> findContributors(Long receiverId)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findContributorsByReceiver");
			query.setLong("receiver_id", receiverId);
			return (List<NibblerContributor>)query.list();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
	
	@Override
	public NibblerReceiver findByInvitationCode(Integer invitationCode)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findNibblerByInvitationCode");
			query.setInteger("invitation_code", invitationCode);
			return (NibblerReceiver)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}

}