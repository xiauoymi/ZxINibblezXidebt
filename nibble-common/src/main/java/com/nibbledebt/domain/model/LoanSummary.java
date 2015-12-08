/**
 * Copyright 2015-2017. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.domain.model;

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
}
