/**
 * Copyright 2015-2017. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Rocky Alam
 *
 */
public class LoanSummary {
	private List<Loan> loans;
	private BigDecimal originalCumulativeInterest=BigDecimal.ZERO;
	private BigDecimal currentCumulativeInterest=BigDecimal.ZERO;
	private BigDecimal projectedCumulativeInterest=BigDecimal.ZERO;
	private Integer originalPayoffDuration=new Integer(0);
	private Integer currentPayoffDuration=new Integer(0);; 
	private Integer projectedPayoffDuration=new Integer(0);; 

	/**
	 * @return the loans
	 */
	public List<Loan> getLoans() {
		if(loans == null ) loans = new ArrayList<>();
		return loans;
	}
	/**
	 * @param loans the loans to set
	 */
	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public BigDecimal getOriginalCumulativeInterest() {
		return originalCumulativeInterest;
	}
	public void setOriginalCumulativeInterest(BigDecimal originalCumulativeInterest) {
		this.originalCumulativeInterest = originalCumulativeInterest;
	}
	public BigDecimal getCurrentCumulativeInterest() {
		return currentCumulativeInterest;
	}
	public void setCurrentCumulativeInterest(BigDecimal currentCumulativeInterest) {
		this.currentCumulativeInterest = currentCumulativeInterest;
	}
	public BigDecimal getProjectedCumulativeInterest() {
		return projectedCumulativeInterest;
	}
	public void setProjectedCumulativeInterest(BigDecimal projectedCumulativeInterest) {
		this.projectedCumulativeInterest = projectedCumulativeInterest;
	}
	
	public BigDecimal getTotalIterestSaved(){
		return originalCumulativeInterest.subtract(currentCumulativeInterest);
	}
	public Integer getOriginalPayoffDuration() {
		if(originalPayoffDuration==null){
			originalPayoffDuration=new Integer(0);
		}
		return originalPayoffDuration;
	}
	public void setOriginalPayoffDuration(Integer originalPayoffDuration) {
		this.originalPayoffDuration = originalPayoffDuration;
	}
	public Integer getCurrentPayoffDuration() {
		if(currentPayoffDuration==null){
			currentPayoffDuration=new Integer(0);
		}
		return currentPayoffDuration;
	}
	public void setCurrentPayoffDuration(Integer currentPayoffDuration) {
		this.currentPayoffDuration = currentPayoffDuration;
	}
	public Integer getProjectedPayoffDuration() {
		if(projectedPayoffDuration==null){
			projectedPayoffDuration=new Integer(0);
		}
		return projectedPayoffDuration;
	}
	public void setProjectedPayoffDuration(Integer projectedPayoffDuration) {
		this.projectedPayoffDuration = projectedPayoffDuration;
	}
	
	public Integer getNumberMonthSaved(){
		return originalPayoffDuration-currentPayoffDuration;
	}
	
}
