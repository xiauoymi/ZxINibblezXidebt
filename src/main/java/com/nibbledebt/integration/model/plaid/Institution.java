/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.model.plaid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@JsonPropertyOrder({ "credentials", "has_mfa", "id", "mfa", "name", "products",
		"type" })
public class Institution {

	@JsonProperty("credentials")
	private Credentials credentials;
	@JsonProperty("has_mfa")
	private Boolean hasMfa;
	@JsonProperty("id")
	private String id;
	@JsonProperty("mfa")
	private List<Object> mfa = new ArrayList<Object>();
	@JsonProperty("name")
	private String name;
	@JsonProperty("products")
	private List<String> products = new ArrayList<String>();
	@JsonProperty("type")
	private String type;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The credentials
	 */
	@JsonProperty("credentials")
	public Credentials getCredentials() {
		return credentials;
	}

	/**
	 * 
	 * @param credentials
	 *            The credentials
	 */
	@JsonProperty("credentials")
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	/**
	 * 
	 * @return The hasMfa
	 */
	@JsonProperty("has_mfa")
	public Boolean getHasMfa() {
		return hasMfa;
	}

	/**
	 * 
	 * @param hasMfa
	 *            The has_mfa
	 */
	@JsonProperty("has_mfa")
	public void setHasMfa(Boolean hasMfa) {
		this.hasMfa = hasMfa;
	}

	/**
	 * 
	 * @return The id
	 */
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            The id
	 */
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return The mfa
	 */
	@JsonProperty("mfa")
	public List<Object> getMfa() {
		return mfa;
	}

	/**
	 * 
	 * @param mfa
	 *            The mfa
	 */
	@JsonProperty("mfa")
	public void setMfa(List<Object> mfa) {
		this.mfa = mfa;
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

	/**
	 * 
	 * @return The products
	 */
	@JsonProperty("products")
	public List<String> getProducts() {
		return products;
	}

	/**
	 * 
	 * @param products
	 *            The products
	 */
	@JsonProperty("products")
	public void setProducts(List<String> products) {
		this.products = products;
	}

	/**
	 * 
	 * @return The type
	 */
	@JsonProperty("type")
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            The type
	 */
	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
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