/**
 * 
 */
package com.nibbledebt.integration.finicity.model.trxs;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author alam_home
 *
 */
@JsonRootName("transaction")
public class Transaction {
	private BigDecimal amount;
	private BigDecimal bonusAmount;
	private BigDecimal escrowAmount;
	private BigDecimal feeAmount;
	private BigDecimal interestAmount;
	private BigDecimal principalAmount;
	private String status;
	private String accountId;
	private String customerId;
	private String description;
	private String id;
	private String institutionTransactionId;
	private Long transactionDate;
	private Long postedDate;
	private BigDecimal unitQuantity;
	private BigDecimal unitValue;

	private String type;
	
	private SubAccount subaccount;

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the bonusAmount
	 */
	public BigDecimal getBonusAmount() {
		return bonusAmount;
	}

	/**
	 * @param bonusAmount the bonusAmount to set
	 */
	public void setBonusAmount(BigDecimal bonusAmount) {
		this.bonusAmount = bonusAmount;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the institutionTransactionId
	 */
	public String getInstitutionTransactionId() {
		return institutionTransactionId;
	}

	/**
	 * @param institutionTransactionId the institutionTransactionId to set
	 */
	public void setInstitutionTransactionId(String institutionTransactionId) {
		this.institutionTransactionId = institutionTransactionId;
	}

	/**
	 * @return the transactionDate
	 */
	public Long getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Long transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the postedDate
	 */
	public Long getPostedDate() {
		return postedDate;
	}

	/**
	 * @param postedDate the postedDate to set
	 */
	public void setPostedDate(Long postedDate) {
		this.postedDate = postedDate;
	}

	/**
	 * @return the unitQuantity
	 */
	public BigDecimal getUnitQuantity() {
		return unitQuantity;
	}

	/**
	 * @param unitQuantity the unitQuantity to set
	 */
	public void setUnitQuantity(BigDecimal unitQuantity) {
		this.unitQuantity = unitQuantity;
	}

	/**
	 * @return the unitValue
	 */
	public BigDecimal getUnitValue() {
		return unitValue;
	}

	/**
	 * @param unitValue the unitValue to set
	 */
	public void setUnitValue(BigDecimal unitValue) {
		this.unitValue = unitValue;
	}

	/**
	 * @return the subaccount
	 */
	public SubAccount getSubaccount() {
		return subaccount;
	}

	/**
	 * @param subaccount the subaccount to set
	 */
	public void setSubaccount(SubAccount subaccount) {
		this.subaccount = subaccount;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
}
