/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.model;

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
@JsonPropertyOrder({ "accounts", "transactions", "access_token" })
public class LinkResponse {

	@JsonProperty("accounts")
	private List<Account> accounts = new ArrayList<Account>();
	@JsonProperty("transactions")
	private List<Transaction> transactions = new ArrayList<Transaction>();
	@JsonProperty("access_token")
	private String accessToken;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The accounts
	 */
	@JsonProperty("accounts")
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * 
	 * @param accounts
	 *            The accounts
	 */
	@JsonProperty("accounts")
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * 
	 * @return The transactions
	 */
	@JsonProperty("transactions")
	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * 
	 * @param transactions
	 *            The transactions
	 */
	@JsonProperty("transactions")
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	/**
	 * 
	 * @return The accessToken
	 */
	@JsonProperty("access_token")
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * 
	 * @param accessToken
	 *            The access_token
	 */
	@JsonProperty("access_token")
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
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