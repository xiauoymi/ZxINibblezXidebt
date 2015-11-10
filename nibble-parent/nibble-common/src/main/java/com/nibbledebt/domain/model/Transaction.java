/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.domain.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ralam
 *
 */
public class Transaction {
	private String trxId;
	private String aggregatorTrxId;
	private String institutionName;
	private String accountNumber;
	private BigDecimal trxAmount;
	private BigDecimal roundupAmount;
	private String description;
	private Date trxDate;
	private Date trxPostDate;
	private String city;
	private String state;
	private String category;
	private BigDecimal principalAmount;
	private BigDecimal interestAmount;
	private BigDecimal feeAmount;
	private BigDecimal escrowAmount;
	private String customerId;
	private String accountId;
	
	/**
	 * @return the aggregatorTrxId
	 */
	public String getAggregatorTrxId() {
		return aggregatorTrxId;
	}
	/**
	 * @param aggregatorTrxId the aggregatorTrxId to set
	 */
	public void setAggregatorTrxId(String aggregatorTrxId) {
		this.aggregatorTrxId = aggregatorTrxId;
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
	 * @return the trxPostDate
	 */
	public Date getTrxPostDate() {
		return trxPostDate;
	}
	/**
	 * @param trxPostDate the trxPostDate to set
	 */
	public void setTrxPostDate(Date trxPostDate) {
		this.trxPostDate = trxPostDate;
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
	 * @return the trxId
	 */
	public String getTrxId() {
		return trxId;
	}
	/**
	 * @param trxId the trxId to set
	 */
	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}
	/**
	 * @return the institutionName
	 */
	public String getInstitutionName() {
		return institutionName;
	}
	/**
	 * @param institutionName the institutionName to set
	 */
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	/**
	 * @return the trxAmount
	 */
	public BigDecimal getTrxAmount() {
		return trxAmount;
	}
	/**
	 * @param trxAmount the trxAmount to set
	 */
	public void setTrxAmount(BigDecimal trxAmount) {
		this.trxAmount = trxAmount;
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
	 * @return the trxDate
	 */
	public Date getTrxDate() {
		return trxDate;
	}
	/**
	 * @param trxDate the trxDate to set
	 */
	public void setTrxDate(Date trxDate) {
		this.trxDate = trxDate;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
}
