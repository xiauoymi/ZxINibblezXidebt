/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author ralam1
 *
 */
public class MemberDetails implements UserDetails {
	private static final long serialVersionUID = 6281849209169556656L;

	private List<GrantedAuthority> authorities = new ArrayList<>();
	private String firstName;
	private String lastName;
	private String username;
	private Boolean isAccountNonLocked;
	private Boolean isAccountNonExpired;
	private Boolean isCredentialsNonExpired;
	private Boolean isAccountEnabled;
	private Boolean isFirstLogin;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return this.isAccountNonExpired;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return isAccountEnabled;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the isAccountNonLocked
	 */
	public Boolean getIsAccountNonLocked() {
		return isAccountNonLocked;
	}

	/**
	 * @param isAccountNonLocked the isAccountNonLocked to set
	 */
	public void setIsAccountNonLocked(Boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	/**
	 * @return the isAccountNonExpired
	 */
	public Boolean getIsAccountNonExpired() {
		return isAccountNonExpired;
	}

	/**
	 * @param isAccountNonExpired the isAccountNonExpired to set
	 */
	public void setIsAccountNonExpired(Boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	/**
	 * @return the isCredentialsNonExpired
	 */
	public Boolean getIsCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	/**
	 * @param isCredentialsNonExpired the isCredentialsNonExpired to set
	 */
	public void setIsCredentialsNonExpired(Boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	/**
	 * @return the isAccountEnabled
	 */
	public Boolean getIsAccountEnabled() {
		return isAccountEnabled;
	}

	/**
	 * @param isAccountEnabled the isAccountEnabled to set
	 */
	public void setIsAccountEnabled(Boolean isAccountEnabled) {
		this.isAccountEnabled = isAccountEnabled;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * @return the isFirstLogin
	 */
	public Boolean getIsFirstLogin() {
		return isFirstLogin;
	}

	/**
	 * @param isFirstLogin the isFirstLogin to set
	 */
	public void setIsFirstLogin(Boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}



	
	
}
