/**
 * 
 */
package com.nibbledebt.integration.finicity.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author alam_home
 *
 */
@XmlRootElement
@XmlType(propOrder = {"partnerId", "partnerSecret"})
public class Credentials {

	private String partnerId;
	private String partnerSecret;

	/**
	 * @return the partnerId
	 */
    @XmlElement
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
    @XmlElement
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
