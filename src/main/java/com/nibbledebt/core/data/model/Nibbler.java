/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author ralam1
 *
 */
@NamedQueries({
	@NamedQuery(name="findNibblerByUsername", query="from Nibbler n where n.nibblerDir.username = :username")
})
@Entity()
@Table(	name="nibbler"
//		uniqueConstraints = {
//			@UniqueConstraint(columnNames = {"activation_code", ""})
//		}
		)
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="nibbler_id"))
})
public class Nibbler extends AbstractModel {	
	
	@Column(name="first_name", nullable=false, length=50)
	private String firstName;
	
	@Column(name="last_name", nullable=false, length=50)
	private String lastName;
	
	@Column(name="email", nullable=false, length=50)
	private String email;
	
	@Column(name="phone", nullable=true, length=11)
	private String phone;
	
	@Column(name="address_line_1", nullable=true, length=100)
	private String addressLine1;
	
	@Column(name="address_line_2", nullable=true, length=100)
	private String addressLine2;
	
	@Column(name="city", nullable=true, length=100)
	private String city;
	
	@Column(name="state", nullable=true, length=100)
	private String state;
	
	@Column(name="zip", nullable=true, length=100)
	private Integer zip;

	@Column(name="ext_acct_access_token", nullable=false, length=50)
	private String extAccessToken;
	
	@OneToOne(mappedBy="nibbler", orphanRemoval=true, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private NibblerDirectory nibblerDir;
	
	@OneToOne(mappedBy="nibbler", orphanRemoval=true, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private NibblerPreference nibblerPreferences;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="nibbler", orphanRemoval=true)
	private List<NibblerAccount> accounts;
	
	public Nibbler(){
		super();
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public Integer getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(Integer zip) {
		this.zip = zip;
	}

	/**
	 * @return the extAccessToken
	 */
	public String getExtAccessToken() {
		return extAccessToken;
	}

	/**
	 * @param extAccessToken the extAccessToken to set
	 */
	public void setExtAccessToken(String extAccessToken) {
		this.extAccessToken = extAccessToken;
	}

	/**
	 * @return the nibblerDir
	 */
	public NibblerDirectory getNibblerDir() {
		return nibblerDir;
	}

	/**
	 * @param nibblerDir the nibblerDir to set
	 */
	public void setNibblerDir(NibblerDirectory nibblerDir) {
		this.nibblerDir = nibblerDir;
	}

	/**
	 * @return the nibblerPreferences
	 */
	public NibblerPreference getNibblerPreferences() {
		return nibblerPreferences;
	}

	/**
	 * @param nibblerPreferences the nibblerPreferences to set
	 */
	public void setNibblerPreferences(NibblerPreference nibblerPreferences) {
		this.nibblerPreferences = nibblerPreferences;
	}

	/**
	 * @return the accounts
	 */
	public List<NibblerAccount> getAccounts() {
		if(accounts == null) accounts = new ArrayList<>();
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<NibblerAccount> accounts) {
		this.accounts = accounts;
	}

		
}
