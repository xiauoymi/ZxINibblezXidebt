/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.model.cad;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nibbledebt.common.model.AbstractModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "address", "keys", "currencyCode", "emailAddress",
		"specialText", "institutionName", "institutionId", "homeUrl" })
public class InstitutionDetail extends AbstractModel{

	@JsonProperty("address")
	private Address address;
	@JsonProperty("keys")
	private Keys keys;
	@JsonProperty("currencyCode")
	private String currencyCode;
	@JsonProperty("emailAddress")
	private String emailAddress;
	@JsonProperty("specialText")
	private String specialText;
	@JsonProperty("institutionName")
	private String institutionName;
	@JsonProperty("institutionId")
	private Long institutionId;
	@JsonProperty("homeUrl")
	private String homeUrl;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The address
	 */
	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}

	/**
	 * 
	 * @param address
	 *            The address
	 */
	@JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * 
	 * @return The keys
	 */
	@JsonProperty("keys")
	public Keys getKeys() {
		return keys;
	}

	/**
	 * 
	 * @param keys
	 *            The keys
	 */
	@JsonProperty("keys")
	public void setKeys(Keys keys) {
		this.keys = keys;
	}

	/**
	 * 
	 * @return The currencyCode
	 */
	@JsonProperty("currencyCode")
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * 
	 * @param currencyCode
	 *            The currencyCode
	 */
	@JsonProperty("currencyCode")
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/**
	 * 
	 * @return The emailAddress
	 */
	@JsonProperty("emailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * 
	 * @param emailAddress
	 *            The emailAddress
	 */
	@JsonProperty("emailAddress")
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * 
	 * @return The specialText
	 */
	@JsonProperty("specialText")
	public String getSpecialText() {
		return specialText;
	}

	/**
	 * 
	 * @param specialText
	 *            The specialText
	 */
	@JsonProperty("specialText")
	public void setSpecialText(String specialText) {
		this.specialText = specialText;
	}

	/**
	 * 
	 * @return The institutionName
	 */
	@JsonProperty("institutionName")
	public String getInstitutionName() {
		return institutionName;
	}

	/**
	 * 
	 * @param institutionName
	 *            The institutionName
	 */
	@JsonProperty("institutionName")
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	/**
	 * 
	 * @return The institutionId
	 */
	@JsonProperty("institutionId")
	public Long getInstitutionId() {
		return institutionId;
	}

	/**
	 * 
	 * @param institutionId
	 *            The institutionId
	 */
	@JsonProperty("institutionId")
	public void setInstitutionId(Long institutionId) {
		this.institutionId = institutionId;
	}

	/**
	 * 
	 * @return The homeUrl
	 */
	@JsonProperty("homeUrl")
	public String getHomeUrl() {
		return homeUrl;
	}

	/**
	 * 
	 * @param homeUrl
	 *            The homeUrl
	 */
	@JsonProperty("homeUrl")
	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
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