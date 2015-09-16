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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "institutionId", "institutionName", "homeUrl",
		"phoneNumber", "virtual" })
public class Institution {

	@JsonProperty("institutionId")
	private Long institutionId;
	@JsonProperty("institutionName")
	private String institutionName;
	@JsonProperty("homeUrl")
	private String homeUrl;
	@JsonProperty("phoneNumber")
	private String phoneNumber;
	@JsonProperty("virtual")
	private Boolean virtual;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

	/**
	 * 
	 * @return The phoneNumber
	 */
	@JsonProperty("phoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 
	 * @param phoneNumber
	 *            The phoneNumber
	 */
	@JsonProperty("phoneNumber")
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 
	 * @return The virtual
	 */
	@JsonProperty("virtual")
	public Boolean getVirtual() {
		return virtual;
	}

	/**
	 * 
	 * @param virtual
	 *            The virtual
	 */
	@JsonProperty("virtual")
	public void setVirtual(Boolean virtual) {
		this.virtual = virtual;
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