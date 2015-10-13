package com.nibbledebt.nibble.integration.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"loginField"
})
public class LoginForm {

	@JsonProperty("loginField")
	private List<LoginField> loginField = new ArrayList<LoginField>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 *
	 * @return
	 * The loginField
	 */
	@JsonProperty("loginField")
	public List<LoginField> getLoginField() {
		return loginField;
	}

	/**
	 *
	 * @param loginField
	 * The loginField
	 */
	@JsonProperty("loginField")
	public void setLoginField(List<LoginField> loginField) {
		this.loginField = loginField;
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