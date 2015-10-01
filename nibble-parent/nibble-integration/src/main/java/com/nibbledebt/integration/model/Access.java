/**
 * 
 */
package com.nibbledebt.integration.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author alam_home
 *
 */
@XmlRootElement
@XmlType(propOrder = {"token"})
public class Access {
	@XmlElement(name="token")
	private String token;

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
