/**
 * 
 */
package com.nibbledebt.domain.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ralam
 *
 */
public class Payment {	
	private BigDecimal amount;
	
	private Date initiatedTs;
	
	private Date completedTs;
	
	private String authorization;

	
	public Payment(BigDecimal amount, Date initiatedTs, Date completedTs,
			String authorization) {
		super();
		this.amount = amount;
		this.initiatedTs = initiatedTs;
		this.completedTs = completedTs;
		this.authorization = authorization;
	}

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
	 * @return the initiatedTs
	 */
	public Date getInitiatedTs() {
		return initiatedTs;
	}

	/**
	 * @param initiatedTs the initiatedTs to set
	 */
	public void setInitiatedTs(Date initiatedTs) {
		this.initiatedTs = initiatedTs;
	}

	/**
	 * @return the completedTs
	 */
	public Date getCompletedTs() {
		return completedTs;
	}

	/**
	 * @param completedTs the completedTs to set
	 */
	public void setCompletedTs(Date completedTs) {
		this.completedTs = completedTs;
	}

	/**
	 * @return the authorization
	 */
	public String getAuthorization() {
		return authorization;
	}

	/**
	 * @param authorization the authorization to set
	 */
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	
	
}
