/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.notifier.Notify;
import com.nibbledebt.common.notifier.NotifyMethod;
import com.nibbledebt.common.notifier.NotifyType;
import com.nibbledebt.core.data.dao.INibblerDao;
import com.nibbledebt.core.data.dao.INibblerDirectoryDao;
import com.nibbledebt.core.data.dao.INibblerRoleDao;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.Nibbler;
import com.nibbledebt.core.data.model.NibblerDirectory;
import com.nibbledebt.core.data.model.NibblerDirectoryStatus;
import com.nibbledebt.core.data.model.NibblerRole;
import com.nibbledebt.core.data.model.NibblerRoleType;
import com.nibbledebt.web.rest.model.NibblerData;

/**
 * @author ralam
 *
 */
@Component
public class UsersProcessor extends AbstractProcessor {
	@Autowired
	private INibblerDao nibblerDao;
	
	@Autowired
	private INibblerDirectoryDao nibblerDirDao;
	
	@Autowired
	private INibblerRoleDao nibblerRoleDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	@CacheEvict(value="nibblerCache", key="#username")
	public void updateLastLogin(String username) throws RepositoryException{
		Nibbler nibbler = nibblerDao.find(username);
		nibbler.getNibblerDir().setLastLoginTs(new Date(System.currentTimeMillis()));
		nibblerDao.update(nibbler);
	}

	@Transactional(readOnly=true)
	@Cacheable(value="nibblerCache")
	public NibblerData retrieveNibbler(String username) throws RepositoryException{
		Nibbler nibbler = nibblerDao.find(username);
		if(nibbler!=null){
			NibblerData mData = new NibblerData();
			mData.setFirstName(nibbler.getFirstName());
			mData.setLastName(nibbler.getLastName());
			mData.setUsername(nibbler.getNibblerDir().getUsername());
			mData.setPassword(nibbler.getNibblerDir().getPassword());
			mData.setStatus(nibbler.getNibblerDir().getStatus());
			mData.setResetCode(nibbler.getNibblerDir().getResetCode());
			mData.setIsFirstLogin(nibbler.getNibblerDir().getLastLoginTs()==null);
			for(NibblerRole role : nibbler.getNibblerDir().getRoles()){
				mData.getRoles().add(role.getName());
			}
			return mData;
		}else{
			return null;
		}
		
	}
	
	/**
	 * 
	 * @param username
	 * @param resetCode
	 * @throws ProcessingException
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@CacheEvict(value="nibblerCache", key="#username")
	public void resetPassword(String username, String resetCode) throws ProcessingException, RepositoryException{
		
		NibblerDirectory nibblerDir = nibblerDirDao.find(username);
		if(nibblerDir == null){
			throw new ProcessingException("The username you have provded does not exist.");
		}
		if(StringUtils.equals(nibblerDir.getStatus(), NibblerDirectoryStatus.RESET_REQUIRED.name())
				&& StringUtils.equals(resetCode, nibblerDir.getResetCode())){
			
			nibblerDir.setStatus(NibblerDirectoryStatus.ACTIVE.name());
			nibblerDir.setResetCode(null);
			
			setUpdated(nibblerDir, username);
			nibblerDirDao.update(nibblerDir);
			
		}else{
			throw new ProcessingException("This password was not reset or the reset code is invalid.");
		}		
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Notify(notifyMethod=NotifyMethod.EMAIL, notifyType=NotifyType.PASSWORD_RESET)
	@CacheEvict(value="nibblerCache", key="#username")
	public void generateResetCode(String username, String resetCode) throws ProcessingException, RepositoryException{
		
		NibblerDirectory nibblerDir = nibblerDirDao.find(username);
		if(nibblerDir == null){
			throw new ProcessingException("The username you have provded does not exist.");
		}
		if(StringUtils.equals(nibblerDir.getStatus(), NibblerDirectoryStatus.CREATED.name()) || 
				StringUtils.equals(nibblerDir.getStatus(), NibblerDirectoryStatus.ACTIVE.name())){
			
			nibblerDir.setStatus(NibblerDirectoryStatus.RESET_REQUIRED.name());
			String rcode  = UUID.randomUUID().toString();
			nibblerDir.setResetCode(rcode);
			resetCode = rcode;
			
			if(nibblerDir.getRoles() == null || nibblerDir.getRoles().isEmpty()){
				NibblerRole nibblerRole = nibblerRoleDao.find(NibblerRoleType.nibbler_level_1.name());
				if(nibblerRole == null){
					nibblerRole = new NibblerRole();	
					setCreated(nibblerRole, username);
					nibblerRole.setName(NibblerRoleType.nibbler_level_1.name());	
				}
				
				Set<NibblerRole> nibblerRoles = new HashSet<>();
				nibblerRoles.add(nibblerRole);
				nibblerDir.setRoles(nibblerRoles);
			}			
			
			setUpdated(nibblerDir, username);
			nibblerDirDao.update(nibblerDir);
			
		}else{
			throw new ProcessingException("This operation is not available on a locked account.");
		}		
	}
	
}
