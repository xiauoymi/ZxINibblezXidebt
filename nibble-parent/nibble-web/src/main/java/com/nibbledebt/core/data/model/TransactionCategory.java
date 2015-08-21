/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Rocky Alam
 *
 */
@NamedQueries({
	@NamedQuery(name="findCatByName", query="from TransactionCategory tc where tc.name = :name")
})
@Entity()
@Table(	name="transaction_category",
		uniqueConstraints=@UniqueConstraint(columnNames={"name"})
)
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="transaction_category_id"))
})
public class TransactionCategory extends AbstractModel {
	@Column(name="name", nullable=false, length=256)
	private String name;
	
	@Column(name="description", nullable=true, length=256)
	private String description;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="categories")
	private List<AccountTransaction> transactions;	
	
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
	
}
