/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.model;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author ralam
 *
 */
 @Entity()
 @Table(	name="nibbler_preference")
 @AttributeOverrides({
 	@AttributeOverride(name="id", column=@Column(name="nibbler_prefrence_id"))
 })
public class NibblerPreference extends AbstractModel {
	 @Column(name="weekly_target_amount", nullable=false, scale=2, precision=10)
	 private BigDecimal weeklyTargetAmount;
	 
	 @OneToOne(fetch = FetchType.LAZY)
	 private Nibbler nibbler;

	/**
	 * @return the weeklyTargetAmount
	 */
	public BigDecimal getWeeklyTargetAmount() {
		return weeklyTargetAmount;
	}

	/**
	 * @param weeklyTargetAmount the weeklyTargetAmount to set
	 */
	public void setWeeklyTargetAmount(BigDecimal weeklyTargetAmount) {
		this.weeklyTargetAmount = weeklyTargetAmount;
	}

	/**
	 * @return the nibbler
	 */
	public Nibbler getNibbler() {
		return nibbler;
	}

	/**
	 * @param nibbler the nibbler to set
	 */
	public void setNibbler(Nibbler nibbler) {
		this.nibbler = nibbler;
	}
	 
	 
}
