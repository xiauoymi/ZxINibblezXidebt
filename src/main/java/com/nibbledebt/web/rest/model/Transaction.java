/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.web.rest.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ralam
 *
 */
public class Transaction {
	private String trxId;
	private String institutionName;
	private String accountNumber;
	private BigDecimal trxAmount;
	private BigDecimal roundupAmount;
	private Date trxDate;
	private String city;
	private String state;
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
	
}
