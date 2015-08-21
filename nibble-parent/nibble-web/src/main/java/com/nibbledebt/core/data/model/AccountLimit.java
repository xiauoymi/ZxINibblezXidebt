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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Rocky Alam
 *
 */
@Entity()
@Table(	name="nibbler_account_limit")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="nibbler_account_limit_id"))
})
public class AccountLimit extends AbstractModel {

	@Column(name="value", nullable=false)
	private Long value;	
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="nibbler_account_id", updatable=true, nullable=false)
	private NibblerAccount account;
	
	
	/**
	 * @return the value
	 */
	public Long getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Long value) {
		this.value = value;
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
