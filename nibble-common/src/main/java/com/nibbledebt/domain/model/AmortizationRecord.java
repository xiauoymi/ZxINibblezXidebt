/**
 * Copyright 2015-2017. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Rocky Alam
 *
 */
public class AmortizationRecord {
	private Integer number;
	private String month;
	private Date date;
	private BigDecimal interestPayment;
	private BigDecimal principalPayment;
	private BigDecimal balance;
	private BigDecimal extraPayment;
	
	
	public AmortizationRecord() {
	}
	
	public AmortizationRecord(Integer number, String month, Date date, BigDecimal interestPayment,
			BigDecimal principalPayment, BigDecimal balance, BigDecimal extraPayment) {
		this.number = number;
		this.month = month;
		this.date = date;
		this.interestPayment = interestPayment;
		this.principalPayment = principalPayment;
		this.balance = balance;
		this.extraPayment = extraPayment;
	}
	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
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
	 * @return the interestPayment
	 */
	public BigDecimal getInterestPayment() {
		return interestPayment;
	}
	/**
	 * @param interestPayment the interestPayment to set
	 */
	public void setInterestPayment(BigDecimal interestPayment) {
		this.interestPayment = interestPayment;
	}
	/**
	 * @return the principalPayment
	 */
	public BigDecimal getPrincipalPayment() {
		return principalPayment;
	}
	/**
	 * @param principalPayment the principalPayment to set
	 */
	public void setPrincipalPayment(BigDecimal principalPayment) {
		this.principalPayment = principalPayment;
	}
	/**
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	/**
	 * @return the extraPayment
	 */
	public BigDecimal getExtraPayment() {
		return extraPayment;
	}

	/**
	 * @param extraPayment the extraPayment to set
	 */
	public void setExtraPayment(BigDecimal extraPayment) {
		this.extraPayment = extraPayment;
	}

	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
