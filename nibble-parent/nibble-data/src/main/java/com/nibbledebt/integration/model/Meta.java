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
@JsonPropertyOrder({ "limit", "number", "name" })
public class Meta {

	@JsonProperty("limit")
	private Long limit;
	@JsonProperty("number")
	private String number;
	@JsonProperty("name")
	private String name;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The limit
	 */
	@JsonProperty("limit")
	public Long getLimit() {
		return limit;
	}

	/**
	 * 
	 * @param limit
	 *            The limit
	 */
	@JsonProperty("limit")
	public void setLimit(Long limit) {
		this.limit = limit;
	}

	/**
	 * 
	 * @return The number
	 */
	@JsonProperty("number")
	public String getNumber() {
		return number;
	}

	/**
	 * 
	 * @param number
	 *            The number
	 */
	@JsonProperty("number")
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * 
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
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