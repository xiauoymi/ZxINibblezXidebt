/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nibbledebt.common.error.DefaultException;
import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.notifier.Notify;
import com.nibbledebt.common.notifier.NotifyMethod;
import com.nibbledebt.common.notifier.NotifyType;
import com.nibbledebt.common.security.MemberAuthentication;
import com.nibbledebt.common.security.MemberDetails;
import com.nibbledebt.core.data.dao.INibblerDao;
import com.nibbledebt.core.data.dao.INibblerDirectoryDao;
import com.nibbledebt.core.data.dao.INibblerRoleDao;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.Nibbler;
import com.nibbledebt.core.data.model.NibblerDirectory;
import com.nibbledebt.core.data.model.NibblerDirectoryStatus;
import com.nibbledebt.core.data.model.NibblerRole;
import com.nibbledebt.core.data.model.NibblerRoleType;
import com.nibbledebt.domain.model.Contributor;
import com.nibbledebt.domain.model.NibblerData;

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

	@Autowired
	private MessageDigestPasswordEncoder encoder;

	@Autowired
	private String salt;

	@Transactional(propagation = Propagation.REQUIRED)
	@CacheEvict(value = "nibblerCache", key = "#username")
	public void updateLastLogin(String username) throws RepositoryException {
		Nibbler nibbler = nibblerDao.find(username);
		nibbler.getNibblerDir().setLastLoginTs(new Date(System.currentTimeMillis()));
		nibblerDao.update(nibbler);
	}

	@Transactional(readOnly = true)
	@Cacheable(value = "nibblerCache")
	public NibblerData retrieveNibbler(String username) throws RepositoryException {
		Nibbler nibbler = nibblerDao.find(username);
		if (nibbler != null) {
			NibblerData mData = new NibblerData();
			mData.setFirstName(nibbler.getFirstName());
			mData.setLastName(nibbler.getLastName());
			mData.setEmail(nibbler.getNibblerDir().getUsername());
			mData.setPassword(nibbler.getNibblerDir().getPassword());
			mData.setStatus(nibbler.getNibblerDir().getStatus());
			mData.setResetCode(nibbler.getNibblerDir().getResetCode());
			mData.setIsFirstLogin(nibbler.getNibblerDir().getLastLoginTs() == null);
			for (NibblerRole role : nibbler.getNibblerDir().getRoles()) {
				mData.getRoles().add(role.getName());
			}
			return mData;
		} else {
			return null;
		}

	}

	@Transactional(readOnly = true)
	// @Cacheable(value="nibblerContributorCache")
	public List<Contributor> retrieveContributors(String username) throws RepositoryException {
		Nibbler receiver = nibblerDao.findReceiver(username);
		List<Contributor> contributors = new ArrayList<>();
		if (receiver != null) {
			for (Nibbler cont : receiver.getContributors()) {
				Contributor contr = new Contributor();
				contr.setFirstName(cont.getFirstName());
				contr.setLastName(cont.getLastName());
				contr.setCity(cont.getCity());
				contr.setState(cont.getState());
				contr.setUsername(cont.getNibblerDir().getUsername());
				contributors.add(contr);
			}
			return contributors;
		} else {
			return null;
		}

	}

	/**
	 * 
	 * @param username
	 * @param resetCode
	 * @throws ProcessingException
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@CacheEvict(value = "nibblerCache", key = "#username")
	public void resetPassword(String username, String newPassword, String resetCode)
			throws ProcessingException, RepositoryException {

		NibblerDirectory nibblerDir = nibblerDirDao.find(username);
		if (nibblerDir == null) {
			throw new ProcessingException("The username you have provded does not exist.");
		}
		if (StringUtils.equals(nibblerDir.getStatus(), NibblerDirectoryStatus.RESET_REQUIRED.name())
				&& StringUtils.equals(resetCode, nibblerDir.getResetCode())) {

			nibblerDir.setPassword(encoder.encodePassword(String.valueOf(newPassword), salt));
			nibblerDir.setStatus(NibblerDirectoryStatus.ACTIVE.name());
			nibblerDir.setResetCode(null);

			setUpdated(nibblerDir, username);
			nibblerDirDao.update(nibblerDir);

		} else {
			throw new ProcessingException("This password was not reset or the reset code is invalid.");
		}
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.PASSWORD_RESET)
	@CacheEvict(value = "nibblerCache", key = "#nibblerData")
	public void generateResetCode(NibblerData nibblerData) throws ProcessingException, RepositoryException {

		NibblerDirectory nibblerDir = nibblerDirDao.find(nibblerData.getEmail());
		if (nibblerDir == null) {
			throw new ProcessingException("The username you have provded does not exist.");
		}
		if (StringUtils.equals(nibblerDir.getStatus(), NibblerDirectoryStatus.CREATED.name())
				|| StringUtils.equals(nibblerDir.getStatus(), NibblerDirectoryStatus.ACTIVE.name())) {

			nibblerDir.setStatus(NibblerDirectoryStatus.RESET_REQUIRED.name());
			String rcode = UUID.randomUUID().toString();
			nibblerDir.setResetCode(rcode);
			nibblerData.setResetCode(rcode);

			if (nibblerDir.getRoles() == null || nibblerDir.getRoles().isEmpty()) {
				NibblerRole nibblerRole = nibblerRoleDao.find(NibblerRoleType.nibbler_level_1.name());
				if (nibblerRole == null) {
					nibblerRole = new NibblerRole();
					setCreated(nibblerRole, nibblerData.getEmail());
					nibblerRole.setName(NibblerRoleType.nibbler_level_1.name());
				}

				Set<NibblerRole> nibblerRoles = new HashSet<>();
				nibblerRoles.add(nibblerRole);
				nibblerDir.setRoles(nibblerRoles);
			}

			setUpdated(nibblerDir, nibblerData.getEmail());
			nibblerDirDao.update(nibblerDir);

		} else {
			throw new ProcessingException("This operation is not available on a locked account.");
		}
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.INVITE)
	public void sendInvite(NibblerData nibblerData) throws ProcessingException, RepositoryException {
		Nibbler rec = nibblerDao.findReceiver(nibblerData.getEmail());
		nibblerData.setInvitationCode(rec.getInvitationCode());
		nibblerData.setFirstName(rec.getFirstName());
		nibblerData.setLastName(rec.getLastName());
	}

	@Transactional(readOnly = true)
	@Cacheable(value = "nibblerCache")
	public List<NibblerData> loadUsers(Nibbler nibb) throws DefaultException, RepositoryException {
		List<Nibbler> nibblers = nibblerDao.find(nibb);
		List<NibblerData> nibblerDatas = new ArrayList<NibblerData>();
		for (Nibbler nibbler : nibblers) {
			if (nibbler != null) {
				NibblerData mData = new NibblerData();
				mData.setInternalUserId(nibbler.getId());
				mData.setFirstName(nibbler.getFirstName());
				mData.setLastName(nibbler.getLastName());
				mData.setEmail(nibbler.getNibblerDir().getUsername());
				mData.setPassword(nibbler.getNibblerDir().getPassword());
				mData.setStatus(nibbler.getNibblerDir().getStatus());
				mData.setAddress1(nibbler.getAddressLine1());
				mData.setAddress2(nibbler.getAddressLine2());
				mData.setCity(nibbler.getCity());
				mData.setZip(nibbler.getZip());
				mData.setState(nibbler.getState());
				mData.setPhone(nibbler.getPhone());
				mData.setStatus(nibbler.getNibblerDir().getStatus());
				if (nibbler.getNibblerDir().getLastUpdateStatus() != null) {
					float diff = (new Date().getTime() - nibbler.getNibblerDir().getLastUpdateStatus().getTime()) / 1000
							* 60 * 60 * 24;
					mData.setSuspendedUp90(diff > 90);
				}
				nibblerDatas.add(mData);
			}
		}
		return nibblerDatas;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@CacheEvict(value = "nibblerCache")
	public void update(NibblerData nibblerData) throws DefaultException, RepositoryException {
		Nibbler entity = nibblerDao.findOne(nibblerData.getInternalUserId());
		if (!StringUtils.equals(entity.getNibblerDir().getPassword(), nibblerData.getPassword())) {
			entity.getNibblerDir().setPassword(encoder.encodePassword(String.valueOf(nibblerData.getPassword()), salt));
		}
		if (!StringUtils.equals(entity.getFirstName(), nibblerData.getFirstName())) {
			entity.setFirstName(nibblerData.getFirstName());
		}
		if (!StringUtils.equals(entity.getLastName(), nibblerData.getLastName())) {
			entity.setLastName(nibblerData.getLastName());
		}
		if (!StringUtils.equals(entity.getEmail(), nibblerData.getEmail())) {
			entity.setEmail(nibblerData.getEmail());
		}
		if (!StringUtils.equals(entity.getAddressLine1(), nibblerData.getAddress1())) {
			entity.setAddressLine1(nibblerData.getAddress1());
		}
		if (!StringUtils.equals(entity.getAddressLine2(), nibblerData.getAddress2())) {
			entity.setAddressLine2(nibblerData.getAddress2());
		}
		if (!StringUtils.equals(entity.getCity(), nibblerData.getCity())) {
			entity.setCity(nibblerData.getCity());
		}
		if (!StringUtils.equals(entity.getState(), nibblerData.getState())) {
			entity.setState(nibblerData.getState());
		}
		if (Integer.compare(entity.getZip(), nibblerData.getZip()) != 0) {
			entity.setZip(nibblerData.getZip());
		}
		if (!StringUtils.equals(entity.getPhone(), nibblerData.getPhone())) {
			entity.setPhone(nibblerData.getPhone());
		}

		nibblerDao.update(entity);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@CacheEvict(value = "nibblerCache")
	public void suspend(NibblerData nibblerData) throws DefaultException, RepositoryException {
		Nibbler entity = nibblerDao.findOne(nibblerData.getInternalUserId());
		entity.getNibblerDir().setStatus(NibblerDirectoryStatus.SUSPEND.name());
		entity.getNibblerDir().setLastUpdateStatus(new Date());
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@CacheEvict(value = "nibblerCache")
	public void activateSuspendedUser(NibblerData nibblerData) throws DefaultException, RepositoryException {
		Nibbler entity = nibblerDao.findOne(nibblerData.getInternalUserId());
		float diff = 0;
		if (entity.getNibblerDir() != null)
			diff = (new Date().getTime() - entity.getNibblerDir().getLastUpdateStatus().getTime())
					/ (1000 * 60 * 60 * 24);
		else
			diff = 91;
		if (diff > 90) {
			entity.getNibblerDir().setStatus(NibblerDirectoryStatus.ACTIVE.name());
		} else {
			throw new DefaultException("The suspended accounts is not up to 90 days");
		}
	}

	@Transactional(readOnly = true)
	@Cacheable(value = "nibblerCache")
	public MemberAuthentication loginAs(NibblerData nibblerData) throws Exception {
		Nibbler nibbler = nibblerDao.findOne(nibblerData.getInternalUserId());
		MemberAuthentication userAuth = new MemberAuthentication();
		MemberDetails userDetails = new MemberDetails();
		userDetails.setFirstName(nibbler.getFirstName());
		userDetails.setLastName(nibbler.getLastName());
		userDetails.setIsFirstLogin(nibbler.getNibblerDir().getLastLoginTs() == null);
		List<GrantedAuthority> auths = new ArrayList<>();
		for (NibblerRole role : nibbler.getNibblerDir().getRoles()) {
			auths.add(new SimpleGrantedAuthority(role.getName()));
		}
		userDetails.setUsername(nibbler.getNibblerDir().getUsername());
		userDetails.setAuthorities(auths);
		userAuth.setAuthenticated(true);
		userAuth.setPrincipal(userDetails);
		return userAuth;
	}
}
