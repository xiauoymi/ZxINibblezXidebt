/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.model;


/**
 * @author ralam
 *
 */
public class Field {
	private String name;
	private String displayName;
	private String value;
	private String instructions;
	private String status;
	private boolean mask;
	private Integer order;
	private Boolean isDisplay;
	
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
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the mask
	 */
	public boolean isMask() {
		return mask;
	}
	/**
	 * @param mask the mask to set
	 */
	public void setMask(boolean mask) {
		this.mask = mask;
	}
	
	/**
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * @return the isDisplay
	 */
	public Boolean getIsDisplay() {
		return isDisplay;
	}

	/**
	 * @param isDisplay the isDisplay to set
	 */
	public void setIsDisplay(Boolean isDisplay) {
		this.isDisplay = isDisplay;
	}
	
}
