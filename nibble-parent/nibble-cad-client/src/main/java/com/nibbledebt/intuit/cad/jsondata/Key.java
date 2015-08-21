/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.intuit.cad.jsondata;


/**
 * @author ralam
 *
 */
public class Key {
	protected String name;
	protected String val;
	protected String status;
	protected Integer valueLengthMin;
	protected Integer valueLengthMax;
	protected boolean displayFlag;
	protected int displayOrder;
	protected boolean mask;
	protected String instructions;
	protected String description;

	public String getName() {
		return this.name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getVal() {
		return this.val;
	}

	public void setVal(String value) {
		this.val = value;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String value) {
		this.status = value;
	}

	public Integer getValueLengthMin() {
		return this.valueLengthMin;
	}

	public void setValueLengthMin(Integer value) {
		this.valueLengthMin = value;
	}

	public Integer getValueLengthMax() {
		return this.valueLengthMax;
	}

	public void setValueLengthMax(Integer value) {
		this.valueLengthMax = value;
	}

	public boolean isDisplayFlag() {
		return this.displayFlag;
	}

	public void setDisplayFlag(boolean value) {
		this.displayFlag = value;
	}

	public int getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(int value) {
		this.displayOrder = value;
	}

	public boolean isMask() {
		return this.mask;
	}

	public void setMask(boolean value) {
		this.mask = value;
	}

	public String getInstructions() {
		return this.instructions;
	}

	public void setInstructions(String value) {
		this.instructions = value;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String value) {
		this.description = value;
	}

	
}
