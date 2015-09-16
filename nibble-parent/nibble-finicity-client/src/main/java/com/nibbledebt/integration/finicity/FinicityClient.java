/**
 * 
 */
package com.nibbledebt.integration.finicity;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nibbledebt.integration.finicity.model.Institutions;

/**
 * @author alam_home
 *
 */
@Component
public class FinicityClient {
	@Autowired
	private WebClient fincityWebClient;
	
	@Autowired
	private SecurityContext finicitySecurityContext;
	
	public Institutions getInstitutions(){
		fincityWebClient.query("Finicity-App-Key", "avalue")
					.query("Finicity-App-Token", "avalue");
		return  fincityWebClient.get(Institutions.class);
	}
}