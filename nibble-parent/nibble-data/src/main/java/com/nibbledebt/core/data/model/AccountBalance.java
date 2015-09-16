/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.model;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Rocky Alam
 *
 */
@Entity()
@Table(	name="nibbler_account_balance")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="nibbler_account_balance_id"))
})
public class AccountBalance extends AbstractModel {

	@Column(name="available", nullable=false, scale=2, precision=10)
	private BigDecimal available;	

	@Column(name="current", nullable=false, scale=2, precision=10)
	private BigDecimal current;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="nibbler_account_id", updatable=true, nullable=false)
	private NibblerAccount account;
	
	/**
	 * @return the available
	 */
	public BigDecimal getAvailable() {
		return available;
	}
	/**
	 * @param available the available to set
	 */
	public void setAvailable(BigDecimal available) {
		this.available = available;
	}
	/**
	 * @return the current
	 */
	public BigDecimal getCurrent() {
		return current;
	}
	/**
	 * @param current the current to set
	 */
	public void setCurrent(BigDecimal current) {
		this.current = current;
	}
	/**
	 * @return the account
	 */
	public NibblerAccount getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(NibblerAccount account) {
		this.account = account;
	}
	
	

}
