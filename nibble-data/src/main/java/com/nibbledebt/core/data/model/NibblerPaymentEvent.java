/**
 * Copyright 2015-2017. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Rocky Alam
 *
 */

@Entity()
@Table(	name="nibbler_payment_event" )
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="nibbler_payment_event_id"))
})
public class NibblerPaymentEvent extends AbstractModel{
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="source_account_id", updatable=true, nullable=false)
	private NibblerAccount sourceAccount;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="destination_account_id", updatable=true, nullable=false)
	private NibblerAccount destinationAccount;

	@Column(name="amount", nullable=false, scale=2, precision=10)
	private BigDecimal amount;

	@Temporal(TemporalType.DATE)
	@Column(name = "posted_date", nullable = false)
	private Date postedDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "cleared_date", nullable = true)
	private Date clearedDate;
	
	@Column(name="number", nullable=true, length=250)
	private String extReferralCode;

	/**
	 * @return the sourceAccount
	 */
	public NibblerAccount getSourceAccount() {
		return sourceAccount;
	}

	/**
	 * @param sourceAccount the sourceAccount to set
	 */
	public void setSourceAccount(NibblerAccount sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	/**
	 * @return the destinationAccount
	 */
	public NibblerAccount getDestinationAccount() {
		return destinationAccount;
	}

	/**
	 * @param destinationAccount the destinationAccount to set
	 */
	public void setDestinationAccount(NibblerAccount destinationAccount) {
		this.destinationAccount = destinationAccount;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the postedDate
	 */
	public Date getPostedDate() {
		return postedDate;
	}

	/**
	 * @param postedDate the postedDate to set
	 */
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	/**
	 * @return the clearedDate
	 */
	public Date getClearedDate() {
		return clearedDate;
	}

	/**
	 * @param clearedDate the clearedDate to set
	 */
	public void setClearedDate(Date clearedDate) {
		this.clearedDate = clearedDate;
	}

	/**
	 * @return the extReferralCode
	 */
	public String getExtReferralCode() {
		return extReferralCode;
	}

	/**
	 * @param extReferralCode the extReferralCode to set
	 */
	public void setExtReferralCode(String extReferralCode) {
		this.extReferralCode = extReferralCode;
	}
	
	
}
