package com.nibbledebt.nibble.integration.model;

import android.graphics.Bitmap;

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
		"id",
		"mfa",
		"name",
		"products",
		"fields"
})
public class Institution {

	@JsonProperty("id")
	private String id;
	@JsonProperty("mfa")
	private List<Object> mfa = new ArrayList<Object>();
	@JsonProperty("name")
	private String name;
	@JsonProperty("logoCode")
	private String logoCode;
	@JsonProperty("products")
	private List<Object> products = new ArrayList<Object>();
	@JsonProperty("fields")
	private List<Object> fields = new ArrayList<Object>();

	@JsonIgnore
	private Bitmap logo;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 *
	 * @return
	 * The id
	 */
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	/**
	 *
	 * @param id
	 * The id
	 */
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * @return
	 * The mfa
	 */
	@JsonProperty("mfa")
	public List<Object> getMfa() {
		return mfa;
	}

	/**
	 *
	 * @param mfa
	 * The mfa
	 */
	@JsonProperty("mfa")
	public void setMfa(List<Object> mfa) {
		this.mfa = mfa;
	}

	/**
	 *
	 * @return
	 * The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 *
	 * @param name
	 * The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public String getLogoCode() {
		return logoCode;
	}

	public void setLogoCode(String logoCode) {
		this.logoCode = logoCode;
	}

	/**
	 *
	 * @return
	 * The products
	 */
	@JsonProperty("products")
	public List<Object> getProducts() {
		return products;
	}

	/**
	 *
	 * @param products
	 * The products
	 */
	@JsonProperty("products")
	public void setProducts(List<Object> products) {
		this.products = products;
	}

	/**
	 *
	 * @return
	 * The fields
	 */
	@JsonProperty("fields")
	public List<Object> getFields() {
		return fields;
	}

	public Bitmap getLogo() {
		return logo;
	}

	public void setLogo(Bitmap logo) {
		this.logo = logo;
	}

	/**
	 *
	 * @param fields
	 * The fields
	 */
	@JsonProperty("fields")
	public void setFields(List<Object> fields) {
		this.fields = fields;
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