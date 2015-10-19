/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.model;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated object representation of all merge variables that can be added
 * to the send request of the Mandrill connector.
 * 
 * @author ralam1
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "rcpt",
    "vars"
})
public class MergeVar {

    @JsonProperty("rcpt")
    private String rcpt;
    @JsonProperty("vars")
    private List<Var> vars = new ArrayList<Var>();
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
     *     The vars
     */
    @JsonProperty("vars")
    public List<Var> getVars() {
        return vars;
    }

    /**
     * 
     * @param vars
     *     The vars
     */
    @JsonProperty("vars")
    public void setVars(List<Var> vars) {
        this.vars = vars;
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
