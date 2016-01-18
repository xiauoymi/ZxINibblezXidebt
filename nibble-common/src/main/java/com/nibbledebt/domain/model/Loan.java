/**
 * Copyright 2015-2017. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Rocky Alam
 *
 */
public class Loan {
	private BigDecimal interestRate;
	private BigDecimal principalBalance;
	private BigDecimal minimumPayment;
	private BigDecimal interestSaved;
	private Integer daysSaved;
	private Integer daysSavedAtCurrentRate; 
	
	private List<AmortizationRecord> originalAmortization;
	private List<AmortizationRecord> currentProjectedAmortization;
	
	private List<Payment> payments;
	
	private Date firstDayAtNibble;
	
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
	 * @return the minimumPayment
	 */
	public BigDecimal getMinimumPayment() {
		return minimumPayment;
	}
	/**
	 * @param minimumPayment the minimumPayment to set
	 */
	public void setMinimumPayment(BigDecimal minimumPayment) {
		this.minimumPayment = minimumPayment;
	}
	/**
	 * @return the interestSaved
	 */
	public BigDecimal getInterestSaved() {
		return interestSaved;
	}
	/**
	 * @param interestSaved the interestSaved to set
	 */
	public void setInterestSaved(BigDecimal interestSaved) {
		this.interestSaved = interestSaved;
	}
	/**
	 * @return the daysSaved
	 */
	public Integer getDaysSaved() {
		return daysSaved;
	}
	/**
	 * @param daysSaved the daysSaved to set
	 */
	public void setDaysSaved(Integer daysSaved) {
		this.daysSaved = daysSaved;
	}
	/**
	 * @return the daysSavedAtCurrentRate
	 */
	public Integer getDaysSavedAtCurrentRate() {
		return daysSavedAtCurrentRate;
	}
	/**
	 * @param daysSavedAtCurrentRate the daysSavedAtCurrentRate to set
	 */
	public void setDaysSavedAtCurrentRate(Integer daysSavedAtCurrentRate) {
		this.daysSavedAtCurrentRate = daysSavedAtCurrentRate;
	}
	
	/**
	 * @return the originalAmortization
	 */
	public List<AmortizationRecord> getOriginalAmortization() {
		if(originalAmortization == null) originalAmortization = new ArrayList<>();
		return originalAmortization;
	}
	/**
	 * @param originalAmortization the originalAmortization to set
	 */
	public void setOriginalAmortization(List<AmortizationRecord> originalAmortization) {
		this.originalAmortization = originalAmortization;
	}
	
	/**
	 * @return the currentProjectedAmortization
	 */
	public List<AmortizationRecord> getCurrentProjectedAmortization() {
		return currentProjectedAmortization;
	}
	/**
	 * @param currentProjectedAmortization the currentProjectedAmortization to set
	 */
	public void setCurrentProjectedAmortization(List<AmortizationRecord> currentProjectedAmortization) {
		this.currentProjectedAmortization = currentProjectedAmortization;
	}
	
	/**
	 * @return the payments
	 */
	public List<Payment> getPayments() {
		return payments;
	}
	/**
	 * @param payments the payments to set
	 */
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
	
	/**
	 * @return the firstDayAtNibble
	 */
	public Date getFirstDayAtNibble() {
		return firstDayAtNibble;
	}
	/**
	 * @param firstDayAtNibble the firstDayAtNibble to set
	 */
	public void setFirstDayAtNibble(Date firstDayAtNibble) {
		this.firstDayAtNibble = firstDayAtNibble;
	}
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
