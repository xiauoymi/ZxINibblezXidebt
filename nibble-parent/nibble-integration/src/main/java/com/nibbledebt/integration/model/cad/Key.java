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
@JsonPropertyOrder({ "name", "mask", "description", "status", "displayOrder",
		"valueLengthMin", "valueLengthMax", "displayFlag", "instructions", "value" })
public class Key extends AbstractModel {

	@JsonProperty("name")
	private String name;
	@JsonProperty("mask")
	private Boolean mask;
	@JsonProperty("description")
	private String description;
	@JsonProperty("status")
	private String status;
	@JsonProperty("displayOrder")
	private Integer displayOrder;
	@JsonProperty("valueLengthMin")
	private Integer valueLengthMin;
	@JsonProperty("valueLengthMax")
	private Integer valueLengthMax;
	@JsonProperty("displayFlag")
	private Boolean displayFlag;
	@JsonProperty("instructions")
	private String instructions;
	@JsonProperty("val")
	private String value;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
	 * @return The mask
	 */
	@JsonProperty("mask")
	public Boolean getMask() {
		return mask;
	}

	/**
	 * 
	 * @param mask
	 *            The mask
	 */
	@JsonProperty("mask")
	public void setMask(Boolean mask) {
		this.mask = mask;
	}

	/**
	 * 
	 * @return The description
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 *            The description
	 */
	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return The status
	 */
	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 *            The status
	 */
	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return The displayOrder
	 */
	@JsonProperty("displayOrder")
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * 
	 * @param displayOrder
	 *            The displayOrder
	 */
	@JsonProperty("displayOrder")
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	/**
	 * 
	 * @return The valueLengthMin
	 */
	@JsonProperty("valueLengthMin")
	public Integer getValueLengthMin() {
		return valueLengthMin;
	}

	/**
	 * 
	 * @param valueLengthMin
	 *            The valueLengthMin
	 */
	@JsonProperty("valueLengthMin")
	public void setValueLengthMin(Integer valueLengthMin) {
		this.valueLengthMin = valueLengthMin;
	}

	/**
	 * 
	 * @return The valueLengthMax
	 */
	@JsonProperty("valueLengthMax")
	public Integer getValueLengthMax() {
		return valueLengthMax;
	}

	/**
	 * 
	 * @param valueLengthMax
	 *            The valueLengthMax
	 */
	@JsonProperty("valueLengthMax")
	public void setValueLengthMax(Integer valueLengthMax) {
		this.valueLengthMax = valueLengthMax;
	}

	/**
	 * 
	 * @return The displayFlag
	 */
	@JsonProperty("displayFlag")
	public Boolean getDisplayFlag() {
		return displayFlag;
	}

	/**
	 * 
	 * @param displayFlag
	 *            The displayFlag
	 */
	@JsonProperty("displayFlag")
	public void setDisplayFlag(Boolean displayFlag) {
		this.displayFlag = displayFlag;
	}

	/**
	 * 
	 * @return The instructions
	 */
	@JsonProperty("instructions")
	public String getInstructions() {
		return instructions;
	}

	/**
	 * 
	 * @param instructions
	 *            The instructions
	 */
	@JsonProperty("instructions")
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param additionalProperties the additionalProperties to set
	 */
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
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