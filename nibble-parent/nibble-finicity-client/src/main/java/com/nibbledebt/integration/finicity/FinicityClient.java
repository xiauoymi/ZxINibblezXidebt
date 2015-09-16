/**
 * 
 */
package com.nibbledebt.integration.finicity;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nibbledebt.integration.finicity.error.FinicityAccessException;
import com.nibbledebt.integration.finicity.error.PartnerAuthenticationException;
import com.nibbledebt.integration.finicity.model.Institutions;

/**
 * @author alam_home
 *
 */
@Component
public class FinicityClient {
	@Autowired
	private WebClient fincityInstClient;
	
	@Autowired
	private SecurityContext finicitySecurityContext;
	
	public Institutions getInstitutions() throws FinicityAccessException{
		try {
			fincityInstClient.query("Finicity-App-Key", finicitySecurityContext.getAppKey())
								.query("Finicity-App-Token", finicitySecurityContext.getAppToken());
		
			return  fincityInstClient.get(Institutions.class);
		} catch (PartnerAuthenticationException e) {
			throw new FinicityAccessException(e);
		}
	}
}