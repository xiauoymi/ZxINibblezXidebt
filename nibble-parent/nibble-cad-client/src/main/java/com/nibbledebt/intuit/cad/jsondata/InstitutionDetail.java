package com.nibbledebt.intuit.cad.jsondata;


public class InstitutionDetail extends Institution {
	protected String status;
	protected Address address;
	protected String emailAddress;
	protected String specialText;
	protected CurrencyCode currencyCode;
	protected Keys keys;

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String value) {
		this.status = value;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address value) {
		this.address = value;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String value) {
		this.emailAddress = value;
	}

	public String getSpecialText() {
		return this.specialText;
	}

	public void setSpecialText(String value) {
		this.specialText = value;
	}

	public CurrencyCode getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(CurrencyCode value) {
		this.currencyCode = value;
	}

	public Keys getKeys() {
		return this.keys;
	}

	public void setKeys(Keys value) {
		this.keys = value;
	}

}