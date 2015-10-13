package com.nibbledebt.nibble.security;

import com.nibbledebt.nibble.integration.model.Bank;

import java.util.List;

/**
 * Created by ralam on 10/13/15.
 */
public class BanksObject implements SessionObject<List<Bank>> {

    private List<Bank> banks;

    public BanksObject(List<Bank> banks) {
        this.banks = banks;
    }


    @Override
    public List<Bank> getData(String key) {
        return banks;
    }
}
