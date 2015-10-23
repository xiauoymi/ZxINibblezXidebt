/**
 * 
 */
package com.nibbledebt.nibble.integration.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author alam_home
 *
 */
@JsonRootName("loginForm")
public class LoginForm {
	private List<LoginField> loginField;

	/**
	 * @return the loginField
	 */
	public List<LoginField> getLoginField() {
		return loginField;
	}

	/**
	 * @param loginField the loginField to set
	 */
	public void setLoginField(List<LoginField> loginField) {
		this.loginField = loginField;
	}
	
	
}
