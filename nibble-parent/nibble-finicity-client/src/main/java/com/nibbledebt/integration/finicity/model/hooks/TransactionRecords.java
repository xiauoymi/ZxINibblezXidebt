/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.finicity.model.hooks;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.nibbledebt.integration.finicity.model.trxs.Transaction;

/**
 * @author ralam
 *
 */
@JsonRootName("records")
public class TransactionRecords{
	
	@JacksonXmlProperty(localName = "transaction")
    @JacksonXmlElementWrapper(useWrapping = false)
    private Transaction[] transaction;

	public Transaction[] getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction[] transaction) {
		this.transaction = transaction;		
	}
}