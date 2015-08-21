/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author ralam
 *
 */
@Entity()
@Table(	name="institution_field"
	)
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="institution_field_id"))
})
public class Field extends AbstractModel {
	@Column(name="name", nullable=false, length=256)
	private String name;
	
	@Column(name="value", nullable=true, length=256)
	private String value;
	
	@Column(name="status", nullable=false, length=50)
	private String status;
	
	@Column(name="is_display", nullable=false)
	private Boolean isDisplay;
	
	@Column(name="display_name", nullable=true, length=256)
	private String displayName;
	
	@Column(name="is_masked", nullable=false)
	private Boolean isMasked;
	
	@Column(name="field_order", nullable=true)
	private Integer order;
	
	@Column(name="instruction", nullable=true, length=2056)
	private String instruction;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="institution_id", updatable=true, nullable=false)
	private Institution institution;

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
	 * @return the isMasked
	 */
	public Boolean getIsMasked() {
		return isMasked;
	}

	/**
	 * @param isMasked the isMasked to set
	 */
	public void setIsMasked(Boolean isMasked) {
		this.isMasked = isMasked;
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
	 * @return the instruction
	 */
	public String getInstruction() {
		return instruction;
	}

	/**
	 * @param instruction the instruction to set
	 */
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	/**
	 * @return the institution
	 */
	public Institution getInstitution() {
		return institution;
	}

	/**
	 * @param institution the institution to set
	 */
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	
}