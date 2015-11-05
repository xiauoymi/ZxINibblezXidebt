/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.nibble.integration.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author ralam1
 *
 */
public class MemberDetails  {

	private List<GrantedAuthority> authorities = new ArrayList<>();
	private String firstName;
	private String lastName;
	private String username;
	private Boolean isAccountNonLocked;
	private Boolean isAccountNonExpired;
	private Boolean isCredentialsNonExpired;
	private Boolean isAccountEnabled;
	private Boolean isFirstLogin;
	

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}


	public String getPassword() {
		return null;
	}

	public String getUsername() {
		return username;
	}


	public boolean isAccountNonExpired() {
		return this.isAccountNonExpired;
	}


	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}


	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}


	public boolean isEnabled() {
		return isAccountEnabled;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}


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
