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
@JsonPropertyOrder({ "_account", "_id", "amount", "date", "name", "meta",
		"pending", "type", "category", "category_id", "score" })
public class Transaction {

	@JsonProperty("_account")
	private String Account;
	@JsonProperty("_id")
	private String Id;
	@JsonProperty("amount")
	private Double amount;
	@JsonProperty("date")
	private String date;
	@JsonProperty("name")
	private String name;
	@JsonProperty("meta")
	private Meta_ meta;
	@JsonProperty("pending")
	private Boolean pending;
	@JsonProperty("type")
	private Type type;
	@JsonProperty("category")
	private List<String> category = new ArrayList<String>();
	@JsonProperty("category_id")
	private String categoryId;
	@JsonProperty("score")
	private Score score;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The Account
	 */
	@JsonProperty("_account")
	public String getAccount() {
		return Account;
	}

	/**
	 * 
	 * @param Account
	 *            The _account
	 */
	@JsonProperty("_account")
	public void setAccount(String Account) {
		this.Account = Account;
	}

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
	 * @return The amount
	 */
	@JsonProperty("amount")
	public Double getAmount() {
		return amount;
	}

	/**
	 * 
	 * @param amount
	 *            The amount
	 */
	@JsonProperty("amount")
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * 
	 * @return The date
	 */
	@JsonProperty("date")
	public String getDate() {
		return date;
	}

	/**
	 * 
	 * @param date
	 *            The date
	 */
	@JsonProperty("date")
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return The meta
	 */
	@JsonProperty("meta")
	public Meta_ getMeta() {
		return meta;
	}

	/**
	 * 
	 * @param meta
	 *            The meta
	 */
	@JsonProperty("meta")
	public void setMeta(Meta_ meta) {
		this.meta = meta;
	}

	/**
	 * 
	 * @return The pending
	 */
	@JsonProperty("pending")
	public Boolean getPending() {
		return pending;
	}

	/**
	 * 
	 * @param pending
	 *            The pending
	 */
	@JsonProperty("pending")
	public void setPending(Boolean pending) {
		this.pending = pending;
	}

	/**
	 * 
	 * @return The type
	 */
	@JsonProperty("type")
	public Type getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            The type
	 */
	@JsonProperty("type")
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * 
	 * @return The category
	 */
	@JsonProperty("category")
	public List<String> getCategory() {
		return category;
	}

	/**
	 * 
	 * @param category
	 *            The category
	 */
	@JsonProperty("category")
	public void setCategory(List<String> category) {
		this.category = category;
	}

	/**
	 * 
	 * @return The categoryId
	 */
	@JsonProperty("category_id")
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * 
	 * @param categoryId
	 *            The category_id
	 */
	@JsonProperty("category_id")
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 
	 * @return The score
	 */
	@JsonProperty("score")
	public Score getScore() {
		return score;
	}

	/**
	 * 
	 * @param score
	 *            The score
	 */
	@JsonProperty("score")
	public void setScore(Score score) {
		this.score = score;
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