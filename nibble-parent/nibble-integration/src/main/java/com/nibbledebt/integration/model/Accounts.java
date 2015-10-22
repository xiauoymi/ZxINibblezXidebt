package com.nibbledebt.integration.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.nibbledebt.integration.model.Account;

/**
 * @author a.salachyonok
 */
@JsonRootName("accounts")
public class Accounts {

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
