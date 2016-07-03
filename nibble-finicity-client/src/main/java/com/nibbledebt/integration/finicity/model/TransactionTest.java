/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.finicity.model;

import java.util.Date;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author ust3000
 *
 */
@JsonRootName("transaction")
public class TransactionTest {
	private String amount;
	private String description;
	private String posted_date;
	private String transaction_date;
	
	public TransactionTest(){}
	
	
	public TransactionTest(String description) {
		super();
		Random randomGenerator = new Random();
		this.amount =(randomGenerator.nextInt(100)+ 0.01)+"";
		this.description = description;
		this.posted_date = new Date().getTime()+"";
		this.transaction_date = this.posted_date;
	}


	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPosted_date() {
		return posted_date;
	}
	public void setPosted_date(String posted_date) {
		this.posted_date = posted_date;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
    
	
}
