package com.nibbledebt.integration.finicity.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author a.salachyonok
 */
@JsonRootName("customers")
public class Customers {


    @JacksonXmlProperty(localName = "customer")
    @JacksonXmlElementWrapper(useWrapping = false)
    private Customer[] customers;

    public Customer[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }
}
