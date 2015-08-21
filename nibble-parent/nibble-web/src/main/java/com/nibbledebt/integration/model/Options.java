/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "login_only", "list", "webhook" })
public class Options {

	@JsonProperty("login_only")
	private Boolean loginOnly;
	@JsonProperty("list")
	private Boolean list;
	@JsonProperty("webhook")
	private String webhook;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Options(){}
	
	public Options(Boolean loginOnly){
		this.loginOnly = loginOnly;
	}
	
	public Options(Boolean loginOnly, Boolean list){
		this.loginOnly = loginOnly;
		this.list = list;
	}
	
	public Options(Boolean loginOnly, Boolean list, String webhook){
		this.loginOnly = loginOnly;
		this.list = list;
		this.webhook = webhook;
	}
	
	/**
	 * 
	 * @return The loginOnly
	 */
	@JsonProperty("login_only")
	public Boolean getLoginOnly() {
		return loginOnly;
	}

	/**
	 * 
	 * @param loginOnly
	 *            The login_only
	 */
	@JsonProperty("login_only")
	public void setLoginOnly(Boolean loginOnly) {
		this.loginOnly = loginOnly;
	}

	/**
	 * 
	 * @return The list
	 */
	@JsonProperty("list")
	public Boolean getList() {
		return list;
	}

	/**
	 * 
	 * @param list
	 *            The list
	 */
	@JsonProperty("list")
	public void setList(Boolean list) {
		this.list = list;
	}

	/**
	 * 
	 * @return The webhook
	 */
	@JsonProperty("webhook")
	public String getWebhook() {
		return webhook;
	}

	/**
	 * 
	 * @param webhook
	 *            The webhook
	 */
	@JsonProperty("webhook")
	public void setWebhook(String webhook) {
		this.webhook = webhook;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}