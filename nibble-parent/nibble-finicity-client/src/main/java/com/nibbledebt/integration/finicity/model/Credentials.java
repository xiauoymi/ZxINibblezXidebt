/**
 * 
 */
package com.nibbledebt.integration.finicity.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author alam_home
 *
 */
@XmlRootElement(name="credentials")
@XmlType(name="credentials", propOrder = {"partnerId", "partnerSecret"})
@XmlAccessorType ( XmlAccessType.FIELD )
public class Credentials {

    @XmlElement(name="partnerId", required=true)
	private String partnerId;

    @XmlElement(name="partnerSecret", required=true)
	private String partnerSecret;

	/**
	 * @return the partnerId
	 */
	public String getPartnerId() {
		return partnerId;
	}

	/**
	 * @param partnerId the partnerId to set
	 */
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * @return the partnerSecret
	 */
	public String getPartnerSecret() {
		return partnerSecret;
	}

	/**
	 * @param partnerSecret the partnerSecret to set
	 */
	public void setPartnerSecret(String partnerSecret) {
		this.partnerSecret = partnerSecret;
	}
	
	
}
