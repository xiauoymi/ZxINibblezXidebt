/**
 * 
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
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author alam_home
 *
 */
@NamedQueries({
	@NamedQuery(name="findSupportedInstitutionByDisplayName", query="from SupportedInstitution i where i.displayName = :displayName"),
	@NamedQuery(name="findSupportedInstitutionByType", query="from SupportedInstitution i where i.type = :type"),
	@NamedQuery(name="findSupportedInstitutionByTypeAndTestModeSupport", query="from SupportedInstitution i where i.supportsTestMode = true and i.type= :type"),
	@NamedQuery(name="findSupportedInstitutionByExternalId", query="from SupportedInstitution i where i.externalId= :externalId")
})
@Entity()
@Table(	name="supported_institution",
		uniqueConstraints = {
			@UniqueConstraint(columnNames = {"external_id", "aggregator_name"})
		},
		indexes= {
			@Index(columnList="name", unique=false),
			@Index(columnList="external_id", unique=false),
			@Index(columnList="aggregator_name", unique=false),
			@Index(columnList="type", unique=false)
		}
	)
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="supported_institution_id"))
})
public class SupportedInstitution extends AbstractModel {
	@Column(name="name", nullable=false, length=256)
	private String name;
	
	@Column(name="display_name", nullable=false, length=256)
	private String displayName;
		
	@Column(name="external_id", nullable=false, length=50)
	private String externalId;
	
	@Column(name="aggregator_name", nullable=false)
	private String aggegatorName;
	
	@Column(name="aggregator_qualifier", nullable=false)
	private String aggregatorQualifier;
	
	@Column(name="priority", nullable=false)
	private Short priority;
	
	@Column(name="logo_code", nullable=false, length=20)
	private String logoCode;
		
	@Column(name="type", nullable=false, length=256)
	private String type;
	
	@Column(name="supports_test_mode", nullable=false)
	private Boolean supportsTestMode;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="supportedInstitution", orphanRemoval=true)
	private List<RoutingInformation> routingInformation = new ArrayList<RoutingInformation>(); 
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="supportedInstitution", orphanRemoval=true)
	private List<Institution> institutions;

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
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

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
	 * @return the aggegatorName
	 */
	public String getAggegatorName() {
		return aggegatorName;
	}

	/**
	 * @param aggegatorName the aggegatorName to set
	 */
	public void setAggegatorName(String aggegatorName) {
		this.aggegatorName = aggegatorName;
	}

	/**
	 * @return the aggregatorQualifier
	 */
	public String getAggregatorQualifier() {
		return aggregatorQualifier;
	}

	/**
	 * @param aggregatorQualifier the aggregatorQualifier to set
	 */
	public void setAggregatorQualifier(String aggregatorQualifier) {
		this.aggregatorQualifier = aggregatorQualifier;
	}

	/**
	 * @return the priority
	 */
	public Short getPriority() {
		if(priority==null){
			return 1;
		}
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Short priority) {
		this.priority = priority;
	}

	/**
	 * @return the logoCode
	 */
	public String getLogoCode() {
		return logoCode;
	}

	/**
	 * @param logoCode the logoCode to set
	 */
	public void setLogoCode(String logoCode) {
		this.logoCode = logoCode;
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
	 * @return the institutions
	 */
	public List<Institution> getInstitutions() {
		return institutions;
	}

	/**
	 * @param institutions the institutions to set
	 */
	public void setInstitutions(List<Institution> institutions) {
		this.institutions = institutions;
	}

	/**
	 * @return the supportsTestMode
	 */
	public Boolean getSupportsTestMode() {
		return supportsTestMode;
	}

	/**
	 * @param supportsTestMode the supportsTestMode to set
	 */
	public void setSupportsTestMode(Boolean supportsTestMode) {
		this.supportsTestMode = supportsTestMode;
	}

	/**
	 * @return the routingInformation
	 */
	public List<RoutingInformation> getRoutingInformation() {
		return routingInformation;
	}

	/**
	 * @param routingInformation the routingInformation to set
	 */
	public void setRoutingInformation(List<RoutingInformation> routingInformation) {
		this.routingInformation = routingInformation;
	}
	
	
}
