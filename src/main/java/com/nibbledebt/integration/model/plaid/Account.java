/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.model.plaid;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "_id", "_item", "_user", "balance", "meta", "type",
		"institution_type" })
public class Account {

	@JsonProperty("_id")
	private String Id;
	@JsonProperty("_item")
	private String Item;
	@JsonProperty("_user")
	private String User;
	@JsonProperty("balance")
	private Balance balance;
	@JsonProperty("meta")
	private Meta meta;
	@JsonProperty("numbers")
	private Numbers numbers;
	@JsonProperty("type")
	private String type;
	@JsonProperty("institution_type")
	private String institutionType;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The Id
	 */
	@JsonProperty("_id")
	public String getId() {
		return Id;
	}

	/**
	 * 
	 * @param Id
	 *            The _id
	 */
	@JsonProperty("_id")
	public void setId(String Id) {
		this.Id = Id;
	}

	/**
	 * 
	 * @return The Item
	 */
	@JsonProperty("_item")
	public String getItem() {
		return Item;
	}

	/**
	 * 
	 * @param Item
	 *            The _item
	 */
	@JsonProperty("_item")
	public void setItem(String Item) {
		this.Item = Item;
	}

	/**
	 * 
	 * @return The User
	 */
	@JsonProperty("_user")
	public String getUser() {
		return User;
	}

	/**
	 * 
	 * @param User
	 *            The _user
	 */
	@JsonProperty("_user")
	public void setUser(String User) {
		this.User = User;
	}

	/**
	 * 
	 * @return The balance
	 */
	@JsonProperty("balance")
	public Balance getBalance() {
		return balance;
	}

	/**
	 * 
	 * @param balance
	 *            The balance
	 */
	@JsonProperty("balance")
	public void setBalance(Balance balance) {
		this.balance = balance;
	}

	/**
	 * 
	 * @return The meta
	 */
	@JsonProperty("meta")
	public Meta getMeta() {
		return meta;
	}

	/**
	 * 
	 * @param meta
	 *            The meta
	 */
	@JsonProperty("meta")
	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	/**
	 * 
	 * @return The type
	 */
	@JsonProperty("type")
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            The type
	 */
	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the numbers
	 */
	public Numbers getNumbers() {
		return numbers;
	}

	/**
	 * @param numbers the numbers to set
	 */
	public void setNumbers(Numbers numbers) {
		this.numbers = numbers;
	}

	/**
	 * @param additionalProperties the additionalProperties to set
	 */
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	/**
	 * 
	 * @return The institutionType
	 */
	@JsonProperty("institution_type")
	public String getInstitutionType() {
		return institutionType;
	}

	/**
	 * 
	 * @param institutionType
	 *            The institution_type
	 */
	@JsonProperty("institution_type")
	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
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
