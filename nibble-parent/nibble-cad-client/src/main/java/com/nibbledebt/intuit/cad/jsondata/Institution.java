package com.nibbledebt.intuit.cad.jsondata;


public class Institution {
	protected long institutionId;
	protected String institutionName;
	protected String homeUrl;
	protected String phoneNumber;
	protected Boolean virtual;

	public long getInstitutionId() {
		return this.institutionId;
	}

	public void setInstitutionId(long value) {
		this.institutionId = value;
	}

	public String getInstitutionName() {
		return this.institutionName;
	}

	public void setInstitutionName(String value) {
		this.institutionName = value;
	}

	public String getHomeUrl() {
		return this.homeUrl;
	}

	public void setHomeUrl(String value) {
		this.homeUrl = value;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String value) {
		this.phoneNumber = value;
	}

	public Boolean isVirtual() {
		return this.virtual;
	}

	public void setVirtual(Boolean value) {
		this.virtual = value;
	}
}