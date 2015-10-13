/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.nibble.integration.model;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author ralam
 *
 */
@JsonRootName("institutionDetail")
public class InstitutionDetail {
	public Institution institution;
	private LoginForm loginForm;
	/**
	 * @return the institution
	 */
	public Institution getInstitution() {
		return institution;
	}
	/**
	 * @param institution the institution to set
	 */
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	/**
	 * @return the loginForm
	 */
	public LoginForm getLoginForm() {
		return loginForm;
	}
	/**
	 * @param loginForm the loginForm to set
	 */
	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
	}
	
	
}
