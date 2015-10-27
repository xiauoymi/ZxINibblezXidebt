/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.finicity.model.hooks;


/**
 * @author ralam
 *
 */
public class TransactionEvent extends Event{
	private TransactionRecords records;

	/**
	 * @return the records
	 */
	public TransactionRecords getRecords() {
		return records;
	}
	/**
	 * @param records the records to set
	 */
	public void setRecords(TransactionRecords records) {
		this.records = records;
	}
	
}
