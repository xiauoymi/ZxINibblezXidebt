package com.nibbledebt.integration.finicity.model.accounts;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

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
    
    public Account find(String number){
    	if(account ==null || number==null){
    		return null;
    	}else{
    		List<Account> accounts=Arrays.asList(account);
    		Optional<Account> account=accounts.stream().filter(a->number.equals(a.getNumber())).findAny();
    		return account.isPresent()? account.get():null;
    	}
    }
}
