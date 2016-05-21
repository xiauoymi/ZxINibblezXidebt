/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.model.plaid;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "available", "current" })
public class Balance {

	@JsonProperty("available")
	private Double available;
	@JsonProperty("current")
	private Double current;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The available
	 */
	@JsonProperty("available")
	public Double getAvailable() {
		return available;
	}

	/**
	 * 
	 * @param available
	 *            The available
	 */
	@JsonProperty("available")
	public void setAvailable(Double available) {
		this.available = available;
	}

	/**
	 * 
	 * @return The current
	 */
	@JsonProperty("current")
	public Double getCurrent() {
		return current;
	}

	/**
	 * 
	 * @param current
	 *            The current
	 */
	@JsonProperty("current")
	public void setCurrent(Double current) {
		this.current = current;
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
