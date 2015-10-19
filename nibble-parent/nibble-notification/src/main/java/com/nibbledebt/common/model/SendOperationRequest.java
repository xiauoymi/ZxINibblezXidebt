/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * A generated object representation of the JSON request that needs to be
 * sent to the send operation of Mandrill.
 * 
 * @author ralam1
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "message",
    "async",
    "ip_pool",
    "send_at"
})
public class SendOperationRequest {

    @JsonProperty("key")
    private String key;
    @JsonProperty("message")
    private Message message;
    @JsonProperty("async")
    private Boolean async;
    @JsonProperty("ip_pool")
    private String ipPool;
    @JsonProperty("send_at")
    private String sendAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The key
     */
    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    /**
     * 
     * @param key
     *     The key
     */
    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 
     * @return
     *     The message
     */
    @JsonProperty("message")
    public Message getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    @JsonProperty("message")
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     The async
     */
    @JsonProperty("async")
    public Boolean getAsync() {
        return async;
    }

    /**
     * 
     * @param async
     *     The async
     */
    @JsonProperty("async")
    public void setAsync(Boolean async) {
        this.async = async;
    }

    /**
     * 
     * @return
     *     The ipPool
     */
    @JsonProperty("ip_pool")
    public String getIpPool() {
        return ipPool;
    }

    /**
     * 
     * @param ipPool
     *     The ip_pool
     */
    @JsonProperty("ip_pool")
    public void setIpPool(String ipPool) {
        this.ipPool = ipPool;
    }

    /**
     * 
     * @return
     *     The sendAt
     */
    @JsonProperty("send_at")
    public String getSendAt() {
        return sendAt;
    }

    /**
     * 
     * @param sendAt
     *     The send_at
     */
    @JsonProperty("send_at")
    public void setSendAt(String sendAt) {
        this.sendAt = sendAt;
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
