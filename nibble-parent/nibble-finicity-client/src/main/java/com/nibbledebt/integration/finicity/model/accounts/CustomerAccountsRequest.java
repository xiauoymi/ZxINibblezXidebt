package com.nibbledebt.integration.finicity.model.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.nibbledebt.integration.finicity.model.LoginField;

import java.util.List;

/**
 * @author a.salachyonok
 */
@JsonRootName("accounts")
public class CustomerAccountsRequest {

    @JacksonXmlElementWrapper(useWrapping = true, localName = "credentials")
    @JacksonXmlProperty(localName = "loginField")
    private LoginField[] fields;

    public LoginField[] getFields() {
        return fields;
    }

    public void setFields(LoginField[] fields) {
        this.fields = fields;
    }
}
