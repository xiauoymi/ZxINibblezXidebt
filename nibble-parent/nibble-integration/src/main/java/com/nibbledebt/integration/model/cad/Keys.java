/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.model.cad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nibbledebt.common.model.AbstractModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "key" })
public class Keys extends AbstractModel {

	@JsonProperty("key")
	private List<Key> key = new ArrayList<Key>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The key
	 */
	@JsonProperty("key")
	public List<Key> getKey() {
		return key;
	}

	/**
	 * 
	 * @param key
	 *            The key
	 */
	@JsonProperty("key")
	public void setKey(List<Key> key) {
		this.key = key;
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