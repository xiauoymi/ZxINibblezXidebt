/**
 * 
 */
package com.nibbledebt.nibble.integration.model;

/**
 * @author alam_home
 *
 */
public class LoginField {

	private String id;

	private String name;

	private String value;

	private String description;

	private String instructions;

	private Integer displayOrder;

	private Boolean mask;

	private Integer valueLengthMin;

	private Integer valueLengthMax;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the instructions
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * @param instructions the instructions to set
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	/**
	 * @return the displayOrder
	 */
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * @param displayOrder the displayOrder to set
	 */
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	/**
	 * @return the mask
	 */
	public Boolean getMask() {
		return mask;
	}

	/**
	 * @param mask the mask to set
	 */
	public void setMask(Boolean mask) {
		this.mask = mask;
	}

	/**
	 * @return the valueLengthMin
	 */
	public Integer getValueLengthMin() {
		return valueLengthMin;
	}

	/**
	 * @param valueLengthMin the valueLengthMin to set
	 */
	public void setValueLengthMin(Integer valueLengthMin) {
		this.valueLengthMin = valueLengthMin;
	}

	/**
	 * @return the valueLengthMax
	 */
	public Integer getValueLengthMax() {
		return valueLengthMax;
	}

	/**
	 * @param valueLengthMax the valueLengthMax to set
	 */
	public void setValueLengthMax(Integer valueLengthMax) {
		this.valueLengthMax = valueLengthMax;
	}
	
}
