/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Generated object representation of all recepient metadata that can be added
 * to the send request of the Mandrill connector.
 * 
 * @author ralam1
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "rcpt",
    "values"
})
public class RecipientMetadatum {

    @JsonProperty("rcpt")
    private String rcpt;
    @JsonProperty("values")
    private Values values;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The rcpt
     */
    @JsonProperty("rcpt")
    public String getRcpt() {
        return rcpt;
    }

    /**
     * 
     * @param rcpt
     *     The rcpt
     */
    @JsonProperty("rcpt")
    public void setRcpt(String rcpt) {
        this.rcpt = rcpt;
    }

    /**
     * 
     * @return
     *     The values
     */
    @JsonProperty("values")
    public Values getValues() {
        return values;
    }

    /**
     * 
     * @param values
     *     The values
     */
    @JsonProperty("values")
    public void setValues(Values values) {
        this.values = values;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
