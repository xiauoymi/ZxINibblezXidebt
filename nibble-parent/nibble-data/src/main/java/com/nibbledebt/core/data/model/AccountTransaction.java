/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Rocky Alam
 *
 */
@NamedQueries({
	@NamedQuery(name="listUnroundedTrxs", query="from AccountTransaction atrx where atrx.rounded is false"),
	@NamedQuery(name="listTrxsByAccount", query="from AccountTransaction atrx where atrx.account.id = :accountId"),
	@NamedQuery(name="listTrxsByRoundedBetween", query="from AccountTransaction atrx where atrx.account.id = :accountId and atrx.date >= :from and atrx.date <= :to and atrx.rounded is true")
	
	
})
@Entity()
@Table(	name="account_transaction"
//		uniqueConstraints={@UniqueConstraint(columnNames={"transaction_id", "nibbler_account_id"}),
//							@UniqueConstraint(columnNames={"transaction_id", "nibbler_account_id", "rounded"})}
)
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="account_transaction_id"))
})
public class AccountTransaction extends AbstractModel {

	@Column(name="transaction_id", nullable=false, length=256)
	private String transactionId;
		
	@Column(name="date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="amount", nullable=false, scale=2, precision=10)
	private BigDecimal amount;	
	
	@Column(name="escrow_amount", nullable=true, scale=2, precision=10)
	private BigDecimal escrowAmount;
	
	@Column(name="fee_amount", nullable=true, scale=2, precision=10)
	private BigDecimal feeAmount;
	
	@Column(name="interest_amount", nullable=true, scale=2, precision=10)
	private BigDecimal interestAmount;
	
	@Column(name="principal_amount", nullable=true, scale=2, precision=10)
	private BigDecimal principalAmount;
	
	@Column(name="roundup_amount", nullable=true, scale=2, precision=10)
	private BigDecimal roundupAmount;	
	
	@Column(name="rounded", nullable=false)
	private Boolean rounded = false;	
	
	@Embedded
	private Location location;

	@Column(name="pending", nullable=false)
	private Boolean pending;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="nibbler_account_id", updatable=true, nullable=false)
	private NibblerAccount account;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(
			name="transaction_category_transaction",  
			joinColumns={@JoinColumn(name="account_transaction_id", nullable=true, updatable=true)}, 
			inverseJoinColumns={@JoinColumn(name="transaction_category_id", nullable=true, updatable=true)})
	private List<TransactionCategory> categories;

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
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
	 * @return the roundupAmount
	 */
	public BigDecimal getRoundupAmount() {
		return roundupAmount;
	}

	/**
	 * @param roundupAmount the roundupAmount to set
	 */
	public void setRoundupAmount(BigDecimal roundupAmount) {
		this.roundupAmount = roundupAmount;
	}

	/**
	 * @return the rounded
	 */
	public Boolean getRounded() {
		return rounded;
	}

	/**
	 * @param rounded the rounded to set
	 */
	public void setRounded(Boolean rounded) {
		this.rounded = rounded;
	}

	/**
	 * @return the escrowAmount
	 */
	public BigDecimal getEscrowAmount() {
		return escrowAmount;
	}

	/**
	 * @param escrowAmount the escrowAmount to set
	 */
	public void setEscrowAmount(BigDecimal escrowAmount) {
		this.escrowAmount = escrowAmount;
	}

	/**
	 * @return the feeAmount
	 */
	public BigDecimal getFeeAmount() {
		return feeAmount;
	}

	/**
	 * @param feeAmount the feeAmount to set
	 */
	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}

	/**
	 * @return the interestAmount
	 */
	public BigDecimal getInterestAmount() {
		return interestAmount;
	}

	/**
	 * @param interestAmount the interestAmount to set
	 */
	public void setInterestAmount(BigDecimal interestAmount) {
		this.interestAmount = interestAmount;
	}

	/**
	 * @return the principalAmount
	 */
	public BigDecimal getPrincipalAmount() {
		return principalAmount;
	}

	/**
	 * @param principalAmount the principalAmount to set
	 */
	public void setPrincipalAmount(BigDecimal principalAmount) {
		this.principalAmount = principalAmount;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the pending
	 */
	public Boolean getPending() {
		return pending;
	}

	/**
	 * @param pending the pending to set
	 */
	public void setPending(Boolean pending) {
		this.pending = pending;
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

	/**
	 * @return the categories
	 */
	public List<TransactionCategory> getCategories() {
		if(categories == null) categories = new ArrayList<>();
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(List<TransactionCategory> categories) {
		this.categories = categories;
	}
	
	
}
