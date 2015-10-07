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
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * @author ralam
 *
 */
@NamedQueries({
	@NamedQuery(name="findInstitutionByName", query="from Institution i where i.name = :name"),
	@NamedQuery(name="findInstitutionByNameAndId", query="from Institution i where i.name = :name and i.externalId = :external_id"),
	@NamedQuery(name="findInstitutionByType", query="from Institution i where i.type = :type"),
	@NamedQuery(name="listPrimaryInstitutions", query="from Institution i where i.isPrimary = true")
})
@Entity()
@Table(	name="institution",
		uniqueConstraints = {
			@UniqueConstraint(columnNames = {"external_id"})
		},
		indexes= {
			@Index(columnList="name", unique=false),
			@Index(columnList="external_id", unique=false)
			
		}
	)
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="institution_id"))
})
public class Institution extends AbstractModel {
	@Column(name="external_id", nullable=false, length=50)
	private String externalId;
	
	@Column(name="name", nullable=false, length=256)
	private String name;
	
	@Column(name="phone", nullable=true, length=256)
	private String phone;
	
	@Column(name="home_url", nullable=true, length=256)
	private String homeUrl;
	
	@Column(name="type", nullable=true, length=256)
	private String type;
	
	@Column(name="isPrimary", nullable=false)
	private Boolean isPrimary;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_synced_ts", nullable = true)
	private Date lastSyncedTs;
		
	@Column(name="aggregator_name", nullable=false, length=50)
	private String aggregatorName;
	
	@Column(name="priority", nullable=true)
	private Integer priority;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="nibbler", orphanRemoval=false)
	private List<NibblerAccount> accounts;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="institution", orphanRemoval=true)
	private List<Field> fields;

	/**
	 * @return the externalId
	 */
	public String getExternalId() {
		return externalId;
	}

	/**
	 * @param externalId the externalId to set
	 */
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the isPrimary
	 */
	public Boolean getIsPrimary() {
		return isPrimary;
	}

	/**
	 * @param isPrimary the isPrimary to set
	 */
	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
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
	 * @return the aggregatorName
	 */
	public String getAggregatorName() {
		return aggregatorName;
	}

	/**
	 * @param aggregatorName the aggregatorName to set
	 */
	public void setAggregatorName(String aggregatorName) {
		this.aggregatorName = aggregatorName;
	}

	/**
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
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
	
}