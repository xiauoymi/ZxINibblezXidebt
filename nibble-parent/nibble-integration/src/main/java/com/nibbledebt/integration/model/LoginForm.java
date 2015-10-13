/**
 * 
 */
package com.nibbledebt.integration.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author alam_home
 *
 */
@XmlRootElement
@XmlType(propOrder = {"loginField"})
@JsonRootName("loginForm")
public class LoginForm {
	@XmlElement(name="loginField")
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
