/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * @author ralam1
 *
 */
@NamedQueries({
	@NamedQuery(name="findAcctByLastPull", query="from NibblerAccount na where na.lastTransactionPull < :lastTransactionPull"),
	@NamedQuery(name="findAcctByUser", query="from NibblerAccount na where na.nibbler.nibblerDir.username = :username"),
	@NamedQuery(name="findAcctByExtId", query="from NibblerAccount na where na.externalId = :externalId"),
	@NamedQuery(name="findAcctByUseForPayoff", query="from NibblerAccount na where na.useForpayoff = true and na.nibbler.nibblerDir.username = :username"),
    @NamedQuery(name="findAcctByUserAndId", query="from NibblerAccount na where na.id = :id and na.nibbler.nibblerDir.username = :username")
	
})
@Entity()
@Table(	name="nibbler_account",
		uniqueConstraints={@UniqueConstraint(columnNames={"nibbler_id","number","institution_id","account_type_id"}), 
							@UniqueConstraint(columnNames={"nibbler_id","external_id"})}
		)
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="nibbler_account_id"))
})
public class NibblerAccount extends AbstractModel{
	@Column(name="name", nullable=false, length=50)
	private String name;
	
	@Column(name="external_id", nullable=false, length=50)
	private String externalId;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="institution_id", updatable=true, nullable=false)
	private Institution institution;
		
	@Column(name="number", nullable=true, length=50)
	private String number;
	
	@Column(name="user_for_rounding", nullable=false)
	private Boolean useForRounding = true;
	
	@Column(name="user_for_payoff", nullable=false)
	private Boolean useForpayoff = false;
	
	@Column(name="number_mask", nullable=true, length=50)
	private String numberMask;
	
	@Column(name="last_transaction_pull", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastTransactionPull;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="account_type_id", updatable=true, nullable=false)
	private AccountType accountType;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="nibbler_id", updatable=true, nullable=false)
	private Nibbler nibbler;
	
	@Column(name="cumulative_roundups_amount", nullable=true, scale=2, precision=10)
	private BigDecimal cumulativeRoundupsAmount;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="account")
	@OrderBy("created_ts DESC")
	private List<AccountBalance> balances;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="account")
	private List<AccountTransaction> transactions;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="account")
	private List<AccountLimit> limits;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="fromAccount")
	@OrderBy("initiatedTs asc")
	private List<PaymentActivity> debitActivity;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="toAccount")
	@OrderBy("initiatedTs asc")
	private List<PaymentActivity> creditActivity;
	
	public NibblerAccount(){
		super();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the institutionName
	 */
	public Institution getInstitution() {
		return institution;
	}

	/**
	 * @param institutionName the institutionName to set
	 */
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the numberMask
	 */
	public String getNumberMask() {
		return numberMask;
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
	 * @param numberMask the numberMask to set
	 */
	public void setNumberMask(String numberMask) {
		this.numberMask = numberMask;
	}

	/**
	 * @return the lastTransactionPull
	 */
	public Date getLastTransactionPull() {
		return lastTransactionPull;
	}

	/**
	 * @param lastTransactionPull the lastTransactionPull to set
	 */
	public void setLastTransactionPull(Date lastTransactionPull) {
		this.lastTransactionPull = lastTransactionPull;
	}

	/**
	 * @return the accountType
	 */
	public AccountType getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the nibbler
	 */
	public Nibbler getNibbler() {
		return nibbler;
	}

	/**
	 * @param nibbler the nibbler to set
	 */
	public void setNibbler(Nibbler nibbler) {
		this.nibbler = nibbler;
	}

	/**
	 * @return the balances
	 */
	public List<AccountBalance> getBalances() {
		if(balances == null) balances = new ArrayList<>();
		return balances;
	}

	/**
	 * @param balances the balances to set
	 */
	public void setBalances(List<AccountBalance> balances) {
		this.balances = balances;
	}

	/**
	 * @return the limits
	 */
	public List<AccountLimit> getLimits() {
		if(limits == null) limits = new ArrayList<>();
		return limits;
	}

	/**
	 * @param limits the limits to set
	 */
	public void setLimits(List<AccountLimit> limits) {
		this.limits = limits;
	}

	/**
	 * @return the externalId
	 */
	public String getExternalId() {
		return externalId;
	}

	/**
	 * @param externalId the externalId to set
	 */
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	/**
	 * @return the transactions
	 */
	public List<AccountTransaction> getTransactions() {
		if(transactions == null) transactions = new ArrayList<>();
		return transactions;
	}

	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(List<AccountTransaction> transactions) {
		this.transactions = transactions;
	}

	/**
	 * @return the cumulativeRoundupsAmount
	 */
	public BigDecimal getCumulativeRoundupsAmount() {
		return cumulativeRoundupsAmount;
	}

	/**
	 * @param cumulativeRoundupsAmount the cumulativeRoundupsAmount to set
	 */
	public void setCumulativeRoundupsAmount(BigDecimal cumulativeRoundupsAmount) {
		this.cumulativeRoundupsAmount = cumulativeRoundupsAmount;
	}

	/**
	 * @return the useForpayoff
	 */
	public Boolean getUseForpayoff() {
		return useForpayoff;
	}

	/**
	 * @param useForpayoff the useForpayoff to set
	 */
	public void setUseForpayoff(Boolean useForpayoff) {
		this.useForpayoff = useForpayoff;
	}

	/**
	 * @return the debitActivity
	 */
	public List<PaymentActivity> getDebitActivity() {
		return debitActivity;
	}

	/**
	 * @param debitActivity the debitActivity to set
	 */
	public void setDebitActivity(List<PaymentActivity> debitActivity) {
		this.debitActivity = debitActivity;
	}

	/**
	 * @return the creditActivity
	 */
	public List<PaymentActivity> getCreditActivity() {
		return creditActivity;
	}

	/**
	 * @param creditActivity the creditActivity to set
	 */
	public void setCreditActivity(List<PaymentActivity> creditActivity) {
		this.creditActivity = creditActivity;
	}	
	
}