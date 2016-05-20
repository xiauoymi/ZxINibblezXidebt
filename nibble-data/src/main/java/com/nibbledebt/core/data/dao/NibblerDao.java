/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.Nibbler;

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
	public Nibbler findReceiver(String username)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findReceiverByUsername");
			query.setString("username", username);
			return (Nibbler)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
	
	@Override
	public List<Nibbler> findContributors(Long receiverId)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findContributorsByReceiver");
			query.setLong("receiver_id", receiverId);
			return (List<Nibbler>)query.list();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}
	
	@Override
	public Nibbler findByInvitationCode(Integer invitationCode)  throws RepositoryException{
		try {
			Query query = this.getCurrentSession().getNamedQuery("findNibblerByInvitationCode");
			query.setInteger("invitation_code", invitationCode);
			return (Nibbler)query.uniqueResult();
		} catch (Exception e) {
			  throw new RepositoryException(e);
		}
	}

	@Override
	public List<Nibbler> find(Nibbler nibbler) throws RepositoryException {
		Criteria cr = this.getCurrentSession().createCriteria(Nibbler.class);
		if(!StringUtils.isEmpty(nibbler.getFirstName())){
			cr.add(Restrictions.eq("firstName", nibbler.getFirstName()));
		}
		if(!StringUtils.isEmpty(nibbler.getLastName())){
			cr.add(Restrictions.eq("lastName", nibbler.getFirstName()));
		}
		if(!StringUtils.isEmpty(nibbler.getEmail())){
			cr.add(Restrictions.eq("email", nibbler.getEmail()));
		}
		if(!StringUtils.isEmpty(nibbler.getPhone())){
			cr.add(Restrictions.eq("phone", nibbler.getPhone()));
		}
		
		return cr.list();
	}

}
