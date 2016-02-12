package com.nibbledebt.domain.model.account;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a.salachyonok
 */

public class Accounts {

    private List<Account> account;

    public List<Account> getAccount() {
    	if(account == null) account = new ArrayList<>();
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }
}
