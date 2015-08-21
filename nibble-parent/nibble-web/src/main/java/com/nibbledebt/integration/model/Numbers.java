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
@JsonPropertyOrder({ "routing", "account", "wireRouting" })
public class Numbers {

	@JsonProperty("routing")
	private String routing;
	@JsonProperty("account")
	private String account;
	@JsonProperty("wireRouting")
	private String wireRouting;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The routing
	 */
	@JsonProperty("routing")
	public String getRouting() {
		return routing;
	}

	/**
	 * 
	 * @param routing
	 *            The routing
	 */
	@JsonProperty("routing")
	public void setRouting(String routing) {
		this.routing = routing;
	}

	/**
	 * 
	 * @return The account
	 */
	@JsonProperty("account")
	public String getAccount() {
		return account;
	}

	/**
	 * 
	 * @param account
	 *            The account
	 */
	@JsonProperty("account")
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 
	 * @return The wireRouting
	 */
	@JsonProperty("wireRouting")
	public String getWireRouting() {
		return wireRouting;
	}

	/**
	 * 
	 * @param wireRouting
	 *            The wireRouting
	 */
	@JsonProperty("wireRouting")
	public void setWireRouting(String wireRouting) {
		this.wireRouting = wireRouting;
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