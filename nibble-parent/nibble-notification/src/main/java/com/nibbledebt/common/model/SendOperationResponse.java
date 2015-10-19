/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * A generated object representation of the response that comes back
 * from the send operation of Mandrill.
 * 
 * @author ralam1
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"email",
	"status",
	"reject_reason",
	"_id"
})
public class SendOperationResponse {

	@JsonProperty("email")
	private String email;
	@JsonProperty("status")
	private String status;
	@JsonProperty("reject_reason")
	private String rejectReason;
	@JsonProperty("_id")
	private String Id;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return
	 * The email
	 */
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email
	 * The email
	 */
	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return
	 * The status
	 */
	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 * The status
	 */
	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return
	 * The rejectReason
	 */
	@JsonProperty("reject_reason")
	public String getRejectReason() {
		return rejectReason;
	}

	/**
	 * 
	 * @param rejectReason
	 * The reject_reason
	 */
	@JsonProperty("reject_reason")
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	/**
	 * 
	 * @return
	 * The Id
	 */
	@JsonProperty("_id")
	public String getId() {
		return Id;
	}

	/**
	 * 
	 * @param Id
	 * The _id
	 */
	@JsonProperty("_id")
	public void setId(String Id) {
		this.Id = Id;
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
