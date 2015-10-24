package com.nibbledebt.nibble.security;

import com.nibbledebt.nibble.integration.model.CustomerData;

/**
 * Created by ralam on 10/13/15.
 */
public class RegisterObject implements SessionObject<CustomerData>{
    private CustomerData customerData;
    public RegisterObject(CustomerData customerData){
        this.customerData = customerData;
    }

    @Override
    public CustomerData getData(String key) {
        if(this.customerData==null) this.customerData = new CustomerData();
        return customerData;
    }
}
