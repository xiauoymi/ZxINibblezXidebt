/**
 * 
 */
package com.nibbledebt.core.data.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author alam_home
 *
 */
@Entity()
@Table(	name="routing_information",
		indexes= {
			@Index(columnList="routing_number", unique=false)
		}
	)
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="supported_institution_id"))
})
public class RoutingInformation extends AbstractModel {

	@Column(name="routing_number", nullable=false)
	private Long routingNumber;
	
	@Column(name="region", nullable=false)
	private String region = "all";
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="supported_institution_id", updatable=false, nullable=true, insertable=false)
	private SupportedInstitution supportedInstitution;

	/**
	 * @return the routingNumber
	 */
	public Long getRoutingNumber() {
		return routingNumber;
	}

	/**
	 * @param routingNumber the routingNumber to set
	 */
	public void setRoutingNumber(Long routingNumber) {
		this.routingNumber = routingNumber;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
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
