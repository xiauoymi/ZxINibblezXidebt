/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.model.plaid;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ralam1
 *
 */
public class MfaRequest {
	private String[] mfa;
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("send_method")
	private String sendMethod;
	private String username;
	@JsonProperty("institution")
	private Institution institution;
	/**
	 * @return the mfa
	 */
	public String[] getMfa() {
		return mfa;
	}
	/**
	 * @param mfa the mfa to set
	 */
	public void setMfa(String[] mfa) {
		this.mfa = mfa;
	}
	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}
	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	/**
	 * @return the sendMethod
	 */
	public String getSendMethod() {
		return sendMethod;
	}
	/**
	 * @param sendMethod the sendMethod to set
	 */
	public void setSendMethod(String sendMethod) {
		this.sendMethod = sendMethod;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the institutionName
	 */
	public Institution getInstitution() {
		return institution;
	}
	/**
	 * @param institutionName the institutionName to set
	 */
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	
}