/**
 * 
 */
package com.nibbledebt.integration.finicity;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nibbledebt.integration.finicity.error.FinicityAccessException;
import com.nibbledebt.integration.finicity.error.PartnerAuthenticationException;
import com.nibbledebt.integration.finicity.model.Institution;
import com.nibbledebt.integration.finicity.model.Institutions;

/**
 * @author alam_home
 *
 */
@Component
public class FinicityClient {
	
		
	@Autowired
	private SecurityContext finicitySecurityContext;
	
//	public Institutions getInstitutions() throws FinicityAccessException{
//		try {
//			return  getConfiguredClient().get(Institutions.class);
//		} catch (PartnerAuthenticationException e) {
//			throw new FinicityAccessException(e);
//		}
//	}
//
//    public Institution getInstitution(String institutionId) throws FinicityAccessException{
//        try {
//            return getConfiguredClient().path("/" + institutionId).get(Institution.class);
//        } catch (PartnerAuthenticationException e) {
//            throw new FinicityAccessException(e);
//        }
//    }

}