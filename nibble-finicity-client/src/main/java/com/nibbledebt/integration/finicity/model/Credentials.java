/**
 * 
 */
package com.nibbledebt.integration.finicity.model;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author alam_home
 *
 */
@JsonRootName("credentials")
public class Credentials {

	private String partnerId;

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
