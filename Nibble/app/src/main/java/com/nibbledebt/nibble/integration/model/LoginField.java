package com.nibbledebt.nibble.integration.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"id",
		"name",
		"value",
		"description",
		"instructions",
		"displayOrder",
		"mask",
		"valueLengthMin",
		"valueLengthMax"
})
public class LoginField {

	@JsonProperty("id")
	private Object id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("value")
	private Object value;
	@JsonProperty("description")
	private String description;
	@JsonProperty("instructions")
	private Object instructions;
	@JsonProperty("displayOrder")
	private Integer displayOrder;
	@JsonProperty("mask")
	private Boolean mask;
	@JsonProperty("valueLengthMin")
	private Integer valueLengthMin;
	@JsonProperty("valueLengthMax")
	private Integer valueLengthMax;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 *
	 * @return
	 * The id
	 */
	@JsonProperty("id")
	public Object getId() {
		return id;
	}

	/**
	 *
	 * @param id
	 * The id
	 */
	@JsonProperty("id")
	public void setId(Object id) {
		this.id = id;
	}

	/**
	 *
	 * @return
	 * The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 *
	 * @param name
	 * The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 * @return
	 * The value
	 */
	@JsonProperty("value")
	public Object getValue() {
		return value;
	}

	/**
	 *
	 * @param value
	 * The value
	 */
	@JsonProperty("value")
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 *
	 * @return
	 * The description
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 *
	 * @param description
	 * The description
	 */
	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 *
	 * @return
	 * The instructions
	 */
	@JsonProperty("instructions")
	public Object getInstructions() {
		return instructions;
	}

	/**
	 *
	 * @param instructions
	 * The instructions
	 */
	@JsonProperty("instructions")
	public void setInstructions(Object instructions) {
		this.instructions = instructions;
	}

	/**
	 *
	 * @return
	 * The displayOrder
	 */
	@JsonProperty("displayOrder")
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	/**
	 *
	 * @param displayOrder
	 * The displayOrder
	 */
	@JsonProperty("displayOrder")
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	/**
	 *
	 * @return
	 * The mask
	 */
	@JsonProperty("mask")
	public Boolean getMask() {
		return mask;
	}

	/**
	 *
	 * @param mask
	 * The mask
	 */
	@JsonProperty("mask")
	public void setMask(Boolean mask) {
		this.mask = mask;
	}

	/**
	 *
	 * @return
	 * The valueLengthMin
	 */
	@JsonProperty("valueLengthMin")
	public Integer getValueLengthMin() {
		return valueLengthMin;
	}

	/**
	 *
	 * @param valueLengthMin
	 * The valueLengthMin
	 */
	@JsonProperty("valueLengthMin")
	public void setValueLengthMin(Integer valueLengthMin) {
		this.valueLengthMin = valueLengthMin;
	}

	/**
	 *
	 * @return
	 * The valueLengthMax
	 */
	@JsonProperty("valueLengthMax")
	public Integer getValueLengthMax() {
		return valueLengthMax;
	}

	/**
	 *
	 * @param valueLengthMax
	 * The valueLengthMax
	 */
	@JsonProperty("valueLengthMax")
	public void setValueLengthMax(Integer valueLengthMax) {
		this.valueLengthMax = valueLengthMax;
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