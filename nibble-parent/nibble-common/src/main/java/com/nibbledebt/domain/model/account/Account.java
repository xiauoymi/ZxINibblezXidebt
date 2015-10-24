/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.domain.model.account;

import java.math.BigDecimal;

/**
 * @author ralam
 *
 */
public class Account {
	private Long accountId;
	private String accountNumber;
	private String institutionName;
	private String accountType;
	private BigDecimal balance;
	private BigDecimal available;

	private Boolean useForRounding;
	
	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}
	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
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
	 * @return the useForRounding
	 */
	public Boolean getUseForRounding() {
		return useForRounding;
	}
	/**
	 * @param useForRounding the useForRounding to set
	 */
	public void setUseForRounding(Boolean useForRounding) {
		this.useForRounding = useForRounding;
	}
	
	
}
