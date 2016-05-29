/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author ralam
 *
 */
@NamedQueries({
	@NamedQuery(name="findInstitutionByName", query="from Institution i where i.supportedInstitution.displayName = :name"),
	@NamedQuery(name="findInstitutionBySupportedInstitution", query="from Institution i where i.supportedInstitution = :supportedInstitutionId"),
	@NamedQuery(name="findByExternalId", query="from Institution i where i.supportedInstitution.externalId = :externalId"),
	@NamedQuery(name="findInstitutionByType", query="from Institution i where i.supportedInstitution.type = :type "),
	@NamedQuery(name="findInstitutionByTypeAndTestModeSupport", query="from Institution i where i.supportedInstitution.supportsTestMode = true and i.supportedInstitution.type= :type")
})
@Entity()
@Table(	name="institution")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="institution_id"))
})
public class Institution extends AbstractModel {	
	@Column(name="phone", nullable=true, length=256)
	private String phone;
	
	@Column(name="home_url", nullable=true, length=256)
	private String homeUrl;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_synced_ts", nullable = true)
	private Date lastSyncedTs;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="nibbler", orphanRemoval=false)
	private List<NibblerAccount> accounts;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="institution", orphanRemoval=true)
	private List<Field> fields;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="supported_institution_id", updatable=true, nullable=false)
	private SupportedInstitution supportedInstitution;

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
	 * @return the homeUrl
	 */
	public String getHomeUrl() {
		return homeUrl;
	}

	/**
	 * @param homeUrl the homeUrl to set
	 */
	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}


	/**
	 * @return the lastSyncedTs
	 */
	public Date getLastSyncedTs() {
		return lastSyncedTs;
	}

	/**
	 * @param lastSyncedTs the lastSyncedTs to set
	 */
	public void setLastSyncedTs(Date lastSyncedTs) {
		this.lastSyncedTs = lastSyncedTs;
	}

	
	/**
	 * @return the accounts
	 */
	public List<NibblerAccount> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<NibblerAccount> accounts) {
		this.accounts = accounts;
	}

	/**
	 * @return the fields
	 */
	public List<Field> getFields() {
		if(fields == null) fields = new ArrayList<>();
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	/**
	 * @return the supportedInstitution
	 */
	public SupportedInstitution getSupportedInstitution() {
		return supportedInstitution;
	}

	/**
	 * @param supportedInstitution the supportedInstitution to set
	 */
	public void setSupportedInstitution(SupportedInstitution supportedInstitution) {
		this.supportedInstitution = supportedInstitution;
	}	
	
}