/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.web.rest.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nibbledebt.integration.model.Institution;

/**
 * @author ralam1
 *
 */
public class NibblerData {
	private String username;		
	private String password;	
	private String firstName;	
	private String lastName;
	private String email;	
	private String phone;	
	private String address1;	
	private String address2;	
	private String city;	
	private String state;	
	private Integer zip;
	private Institution institution;
	private String activationCode;
	private String url;
	
	private String status;
	private String resetCode;
	
	private Boolean isFirstLogin;

	/**
	 * @return the isFirstLogin
	 */
	public Boolean getIsFirstLogin() {
		return isFirstLogin;
	}


	/**
	 * @param isFirstLogin the isFirstLogin to set
	 */
	public void setIsFirstLogin(Boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}


	private String instUsername;		
	private String instPassword;		
	private String instPin;
	
	private String[] mfa;
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("send_method")
	private String sendMethod;
	
	private List<Account> accounts = new ArrayList<>();
	
	private List<String> roles = new ArrayList<>();	 
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}


	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}


	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
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
	 * @return the roles
	 */
	public List<String> getRoles() {
		if(roles == null) roles = new ArrayList<>();
		return roles;
	}


	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	/**
	 * @return the institution
	 */
	public Institution getInstitution() {
		return institution;
	}


	/**
	 * @param institution the institution to set
	 */
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}


	/**
	 * @return the activationCode
	 */
	public String getActivationCode() {
		return activationCode;
	}


	/**
	 * @param activationCode the activationCode to set
	 */
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}


	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * @return the instUsername
	 */
	public String getInstUsername() {
		return instUsername;
	}


	/**
	 * @param instUsername the instUsername to set
	 */
	public void setInstUsername(String instUsername) {
		this.instUsername = instUsername;
	}


	/**
	 * @return the instPassword
	 */
	public String getInstPassword() {
		return instPassword;
	}


	/**
	 * @param instPassword the instPassword to set
	 */
	public void setInstPassword(String instPassword) {
		this.instPassword = instPassword;
	}


	/**
	 * @return the instPin
	 */
	public String getInstPin() {
		return instPin;
	}


	/**
	 * @param instPin the instPin to set
	 */
	public void setInstPin(String instPin) {
		this.instPin = instPin;
	}



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the resetCode
	 */
	public String getResetCode() {
		return resetCode;
	}


	/**
	 * @param resetCode the resetCode to set
	 */
	public void setResetCode(String resetCode) {
		this.resetCode = resetCode;
	}

	/**
	 * @return the mfa
	 */
	public String[] getMfa() {
		return mfa;
	}


	/**
	 * @param mfa the mfa to set
	 */
	public void setMfa(String[] mfa) {
		this.mfa = mfa;
	}


	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}


	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}


	/**
	 * @return the sendMethod
	 */
	public String getSendMethod() {
		return sendMethod;
	}


	/**
	 * @param sendMethod the sendMethod to set
	 */
	public void setSendMethod(String sendMethod) {
		this.sendMethod = sendMethod;
	}


	/**
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}


	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}


	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
