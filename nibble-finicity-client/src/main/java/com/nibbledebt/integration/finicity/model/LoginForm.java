/**
 * 
 */
package com.nibbledebt.integration.finicity.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author alam_home
 *
 */
@JsonRootName("loginForm")
public class LoginForm {
	@JacksonXmlProperty(localName = "loginField")
    @JacksonXmlElementWrapper(useWrapping = false)
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
