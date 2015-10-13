package com.nibbledebt.nibble.integration.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "institution",
        "loginForm"
})
public class Bank {

    @JsonProperty("institution")
    private Institution institution;
    @JsonProperty("loginForm")
    private LoginForm loginForm;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The institution
     */
    @JsonProperty("institution")
    public Institution getInstitution() {
        return institution;
    }

    /**
     *
     * @param institution
     * The institution
     */
    @JsonProperty("institution")
    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    /**
     *
     * @return
     * The loginForm
     */
    @JsonProperty("loginForm")
    public LoginForm getLoginForm() {
        return loginForm;
    }

    /**
     *
     * @param loginForm
     * The loginForm
     */
    @JsonProperty("loginForm")
    public void setLoginForm(LoginForm loginForm) {
        this.loginForm = loginForm;
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
