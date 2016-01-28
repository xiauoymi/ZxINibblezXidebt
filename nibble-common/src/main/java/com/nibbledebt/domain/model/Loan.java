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
	private BigDecimal weeklyAverage;
	private BigDecimal originalCumulativeInterest;
	private BigDecimal currentCumulativeInterest;
	private BigDecimal projectedCumulativeInterest;
	private Integer originalPayoffDuration;
	private Integer currentPayoffDuration; 
	private Integer projectedPayoffDuration; 
	
	private List<AmortizationRecord> originalAmortization;
	private List<AmortizationRecord> currentProjectedAmortization;
	private List<AmortizationRecord> projectedAmortization;
	
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
	 * @return the originalCumulativeInterest
	 */
	public BigDecimal getOriginalCumulativeInterest() {
		return originalCumulativeInterest;
	}
	/**
	 * @param originalCumulativeInterest the originalCumulativeInterest to set
	 */
	public void setOriginalCumulativeInterest(BigDecimal originalCumulativeInterest) {
		this.originalCumulativeInterest = originalCumulativeInterest;
	}
	/**
	 * @return the currentCumulativeInterest
	 */
	public BigDecimal getCurrentCumulativeInterest() {
		return currentCumulativeInterest;
	}
	/**
	 * @param currentCumulativeInterest the currentCumulativeInterest to set
	 */
	public void setCurrentCumulativeInterest(BigDecimal currentCumulativeInterest) {
		this.currentCumulativeInterest = currentCumulativeInterest;
	}
	/**
	 * @return the originalPayoffDuration
	 */
	public Integer getOriginalPayoffDuration() {
		return originalPayoffDuration;
	}
	/**
	 * @param originalPayoffDuration the originalPayoffDuration to set
	 */
	public void setOriginalPayoffDuration(Integer originalPayoffDuration) {
		this.originalPayoffDuration = originalPayoffDuration;
	}
	/**
	 * @return the currentPayoffDuration
	 */
	public Integer getCurrentPayoffDuration() {
		return currentPayoffDuration;
	}
	/**
	 * @param currentPayoffDuration the currentPayoffDuration to set
	 */
	public void setCurrentPayoffDuration(Integer currentPayoffDuration) {
		this.currentPayoffDuration = currentPayoffDuration;
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
		if(currentProjectedAmortization == null) currentProjectedAmortization = new ArrayList<>();
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
	
	/**
	 * @return the projectedCumulativeInterest
	 */
	public BigDecimal getProjectedCumulativeInterest() {
		return projectedCumulativeInterest;
	}
	/**
	 * @param projectedCumulativeInterest the projectedCumulativeInterest to set
	 */
	public void setProjectedCumulativeInterest(
			BigDecimal projectedCumulativeInterest) {
		this.projectedCumulativeInterest = projectedCumulativeInterest;
	}
	/**
	 * @return the projectedPayoffDuration
	 */
	public Integer getProjectedPayoffDuration() {
		return projectedPayoffDuration;
	}
	/**
	 * @param projectedPayoffDuration the projectedPayoffDuration to set
	 */
	public void setProjectedPayoffDuration(Integer projectedPayoffDuration) {
		this.projectedPayoffDuration = projectedPayoffDuration;
	}
	/**
	 * @return the projectedAmortization
	 */
	public List<AmortizationRecord> getProjectedAmortization() {
		if(projectedAmortization == null) projectedAmortization = new ArrayList<>();
		return projectedAmortization;
	}
	/**
	 * @param projectedAmortization the projectedAmortization to set
	 */
	public void setProjectedAmortization(
			List<AmortizationRecord> projectedAmortization) {
		this.projectedAmortization = projectedAmortization;
	}
	/**
	 * @return the weeklyAverage
	 */
	public BigDecimal getWeeklyAverage() {
		return weeklyAverage;
	}
	/**
	 * @param weeklyAverage the weeklyAverage to set
	 */
	public void setWeeklyAverage(BigDecimal weeklyAverage) {
		this.weeklyAverage = weeklyAverage;
	}
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
