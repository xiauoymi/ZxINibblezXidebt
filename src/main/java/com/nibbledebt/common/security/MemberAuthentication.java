/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author ralam1
 *
 */
public class MemberAuthentication implements Authentication {

	private static final long serialVersionUID = -3091441742758356129L;

	private boolean authenticated;
	private Object details;
	private MemberDetails principal;
	private String credentials;

	@Override
	public Object getCredentials() {
		return credentials;
	}

	@Override
	public Object getDetails() {
		return details;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated)
			throws IllegalArgumentException {
		this.authenticated = authenticated;
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return new ArrayList<GrantedAuthority>(principal.getAuthorities());
	}

	/**
	 * @param grantedAuthorities
	 *            the grantedAuthorities to set
	 */
	public void setAuthorities(List<GrantedAuthority> grantedAuthorities) {
		principal.setAuthorities(grantedAuthorities);
	}

	/**
	 * @param details
	 *            the details to set
	 */
	public void setDetails(Object details) {
		this.details = details;
	}

	/**
	 * @param principal
	 *            the principal to set
	 */
	public void setPrincipal(MemberDetails principal) {
		this.principal = principal;
	}

	/**
	 * @param credentials
	 *            the credentials to set
	 */
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

}
