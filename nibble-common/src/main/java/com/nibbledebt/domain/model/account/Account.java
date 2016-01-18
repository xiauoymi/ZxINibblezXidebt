/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.domain.model.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nibbledebt.domain.model.Payment;

/**
 * @author ralam
 *
 */
public class Account {
	private Long accountId;
	private String accountNumber;
    private String accountName;
	private String institutionName;
    private String institutionExternalId;
	private String accountType;
	private String balance;
	private String available;
    private String accountExternalId;
    
    private AccountDetail detail;

	private Boolean useForRounding;
	private Boolean useForPayoff;
	
	private List<Payment> credits;
	private List<Payment> debits;
	
	private Date createdTs;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getInstitutionExternalId() {
        return institutionExternalId;
    }

    public void setInstitutionExternalId(String institutionExternalId) {
        this.institutionExternalId = institutionExternalId;
    }

    public String getAccountExternalId() {
        return accountExternalId;
    }

    public void setAccountExternalId(String accountExternalId) {
        this.accountExternalId = accountExternalId;
    }

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
	public String getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(String balance) {
		this.balance = balance;
	}
	/**
	 * @return the available
	 */
	public String getAvailable() {
		return available;
	}
	/**
	 * @param available the available to set
	 */
	public void setAvailable(String available) {
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

	/**
	 * @return the detail
	 */
	public AccountDetail getDetail() {
		return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(AccountDetail detail) {
		this.detail = detail;
	}

	/**
	 * @return the useForPayoff
	 */
	public Boolean getUseForPayoff() {
		return useForPayoff;
	}

	/**
	 * @param useForPayoff the useForPayoff to set
	 */
	public void setUseForPayoff(Boolean useForPayoff) {
		this.useForPayoff = useForPayoff;
	}

	/**
	 * @return the credits
	 */
	public List<Payment> getCredits() {
		if(this.credits == null) this.credits = new ArrayList<>();
		return credits;
	}

	/**
	 * @param credits the credits to set
	 */
	public void setCredits(List<Payment> credits) {
		this.credits = credits;
	}

	/**
	 * @return the debits
	 */
	public List<Payment> getDebits() {
		if(this.debits == null) this.debits = new ArrayList<>();
		return debits;
	}

	/**
	 * @param debit the debits to set
	 */
	public void setDebits(List<Payment> debits) {
		this.debits = debits;
	}

	/**
	 * @return the createdTs
	 */
	public Date getCreatedTs() {
		return createdTs;
	}

	/**
	 * @param createdTs the createdTs to set
	 */
	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}
}
