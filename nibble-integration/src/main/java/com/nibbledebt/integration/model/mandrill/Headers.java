/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.model.mandrill;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Generated object representation of the headers component of the 
 * send operation request that can be sent to the Mandrill connector.
 * 
 * @author ralam1
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Reply-To"
})
public class Headers {

    @JsonProperty("Reply-To")
    private String ReplyTo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The ReplyTo
     */
    @JsonProperty("Reply-To")
    public String getReplyTo() {
        return ReplyTo;
    }

    /**
     * 
     * @param ReplyTo
     *     The Reply-To
     */
    @JsonProperty("Reply-To")
    public void setReplyTo(String ReplyTo) {
        this.ReplyTo = ReplyTo;
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
