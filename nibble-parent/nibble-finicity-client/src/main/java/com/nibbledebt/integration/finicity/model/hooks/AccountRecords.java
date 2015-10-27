/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.finicity.model.hooks;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.nibbledebt.integration.finicity.model.accounts.Account;

/**
 * @author ralam
 *
 */
@JsonRootName("records")
public class AccountRecords{
	
	@JacksonXmlProperty(localName = "account")
    @JacksonXmlElementWrapper(useWrapping = false)
    private Account[] account;

	public Account[] getAccount() {
		return account;
	}

	public void setAccount(Account[] account) {
		this.account = account;		
	}

}
