/**
 * 
 */
package com.nibbledebt.core.data.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author ralam
 *
 */
@NamedQueries({
	@NamedQuery(name="getActivityForToAccount", query="from PaymentActivity pa where pa.toAccount.id = :toAccountId and completedTs is not null order by completedTs asc")	
})
@Entity()
@Table(	name="payment_activity"	)
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="payment_activity_id"))
})
public class PaymentActivity extends AbstractModel {
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="from_account_id", updatable=true, nullable=false)
	private NibblerAccount fromAccount;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="to_account_id", updatable=true, nullable=false)
	private NibblerAccount toAccount;
	
	@Column(name="amount", nullable=false, scale=2, precision=10)
	private BigDecimal amount;
	
	@Column(name="initiated_ts", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date initiatedTs;
	
	@Column(name="completed_ts", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date completedTs;
	
	@Column(name="authorization")
	private String authorization;

	/**
	 * @return the fromAccount
	 */
	public NibblerAccount getFromAccount() {
		return fromAccount;
	}

	/**
	 * @param fromAccount the fromAccount to set
	 */
	public void setFromAccount(NibblerAccount fromAccount) {
		this.fromAccount = fromAccount;
	}

	/**
	 * @return the toAccount
	 */
	public NibblerAccount getToAccount() {
		return toAccount;
	}

	/**
	 * @param toAccount the toAccount to set
	 */
	public void setToAccount(NibblerAccount toAccount) {
		this.toAccount = toAccount;
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
