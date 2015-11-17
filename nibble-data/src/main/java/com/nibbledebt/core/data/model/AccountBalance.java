/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
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
@Table(	name="nibbler_account_balance")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="nibbler_account_balance_id"))
})
public class AccountBalance extends AbstractModel {

	@Column(name="available", nullable=false, scale=2, precision=10)
	private BigDecimal available;	

	@Column(name="current", nullable=false, scale=2, precision=10)
	private BigDecimal current;
	
	@Column(name="credit_max", nullable=true, scale=2, precision=10)
	private BigDecimal creditMaxAmount;
	
	@Column(name="payment_min_amount", nullable=true, scale=2, precision=10)
	private BigDecimal paymentMinAmount;
	
	@Column(name="last_payment_amount", nullable=true, scale=2, precision=10)
	private BigDecimal lastPaymentAmount;
	
	@Column(name="interest_rate", nullable=true, scale=2, precision=10)
	private BigDecimal interestRate;
	
	@Column(name="escrow_balance", nullable=true, scale=2, precision=10)
	private BigDecimal escrowBalance;
	
	@Column(name="principal_balance", nullable=true, scale=2, precision=10)
	private BigDecimal principalBalance;
	
	@Column(name="ytd_interest_paid", nullable=true, scale=2, precision=10)
	private BigDecimal ytdInterestPaid;
	
	@Column(name="ytd_principal_paid", nullable=true, scale=2, precision=10)
	private BigDecimal ytdPrincipalPaid;
	
	@Column(name="payoff_amount", nullable=true, scale=2, precision=10)
	private BigDecimal payoffAmount;
	
	@Column(name="cash_adv_interest_rate", nullable=true, scale=2, precision=10)
	private BigDecimal cashAdvanceInterestRate;
	
	@Column(name="last_payment_date", nullable=true)
	@Temporal(TemporalType.DATE)
	private Date lastPaymentDate;
	
	@Column(name="payment_due_date", nullable=true)
	@Temporal(TemporalType.DATE)
	private Date paymentDueDate;
	
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
	/**
	 * @return the creditMaxAmount
	 */
	public BigDecimal getCreditMaxAmount() {
		return creditMaxAmount;
	}
	/**
	 * @param creditMaxAmount the creditMaxAmount to set
	 */
	public void setCreditMaxAmount(BigDecimal creditMaxAmount) {
		this.creditMaxAmount = creditMaxAmount;
	}
	/**
	 * @return the paymentMinAmount
	 */
	public BigDecimal getPaymentMinAmount() {
		return paymentMinAmount;
	}
	/**
	 * @param paymentMinAmount the paymentMinAmount to set
	 */
	public void setPaymentMinAmount(BigDecimal paymentMinAmount) {
		this.paymentMinAmount = paymentMinAmount;
	}
	/**
	 * @return the lastPaymentAmount
	 */
	public BigDecimal getLastPaymentAmount() {
		return lastPaymentAmount;
	}
	/**
	 * @param lastPaymentAmount the lastPaymentAmount to set
	 */
	public void setLastPaymentAmount(BigDecimal lastPaymentAmount) {
		this.lastPaymentAmount = lastPaymentAmount;
	}
	/**
	 * @return the interestRate
	 */
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	/**
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	/**
	 * @return the escrowBalance
	 */
	public BigDecimal getEscrowBalance() {
		return escrowBalance;
	}
	/**
	 * @param escrowBalance the escrowBalance to set
	 */
	public void setEscrowBalance(BigDecimal escrowBalance) {
		this.escrowBalance = escrowBalance;
	}
	/**
	 * @return the principalBalance
	 */
	public BigDecimal getPrincipalBalance() {
		return principalBalance;
	}
	/**
	 * @param principalBalance the principalBalance to set
	 */
	public void setPrincipalBalance(BigDecimal principalBalance) {
		this.principalBalance = principalBalance;
	}
	/**
	 * @return the ytdInterestPaid
	 */
	public BigDecimal getYtdInterestPaid() {
		return ytdInterestPaid;
	}
	/**
	 * @param ytdInterestPaid the ytdInterestPaid to set
	 */
	public void setYtdInterestPaid(BigDecimal ytdInterestPaid) {
		this.ytdInterestPaid = ytdInterestPaid;
	}
	/**
	 * @return the ytdPrincipalPaid
	 */
	public BigDecimal getYtdPrincipalPaid() {
		return ytdPrincipalPaid;
	}
	/**
	 * @param ytdPrincipalPaid the ytdPrincipalPaid to set
	 */
	public void setYtdPrincipalPaid(BigDecimal ytdPrincipalPaid) {
		this.ytdPrincipalPaid = ytdPrincipalPaid;
	}
	/**
	 * @return the payoffAmount
	 */
	public BigDecimal getPayoffAmount() {
		return payoffAmount;
	}
	/**
	 * @param payoffAmount the payoffAmount to set
	 */
	public void setPayoffAmount(BigDecimal payoffAmount) {
		this.payoffAmount = payoffAmount;
	}
	/**
	 * @return the cashAdvanceInterestRate
	 */
	public BigDecimal getCashAdvanceInterestRate() {
		return cashAdvanceInterestRate;
	}
	/**
	 * @param cashAdvanceInterestRate the cashAdvanceInterestRate to set
	 */
	public void setCashAdvanceInterestRate(BigDecimal cashAdvanceInterestRate) {
		this.cashAdvanceInterestRate = cashAdvanceInterestRate;
	}
	/**
	 * @return the lastPaymentDate
	 */
	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}
	/**
	 * @param lastPaymentDate the lastPaymentDate to set
	 */
	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}
	/**
	 * @return the paymentDueDate
	 */
	public Date getPaymentDueDate() {
		return paymentDueDate;
	}
	/**
	 * @param paymentDueDate the paymentDueDate to set
	 */
	public void setPaymentDueDate(Date paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}
	
	

}
