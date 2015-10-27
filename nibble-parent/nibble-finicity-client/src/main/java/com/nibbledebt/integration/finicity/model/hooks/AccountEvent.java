/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.finicity.model.hooks;


/**
 * @author ralam
 *
 */
public class AccountEvent extends Event{
	private AccountRecords records;
	
	/**
	 * @return the records
	 */
	public AccountRecords getRecords() {
		return records;
	}
	/**
	 * @param records the records to set
	 */
	public void setRecords(AccountRecords records) {
		this.records = records;
	}
	
}
