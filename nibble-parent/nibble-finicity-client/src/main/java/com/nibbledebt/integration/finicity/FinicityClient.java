/**
 * 
 */
package com.nibbledebt.integration.finicity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nibbledebt.integration.finicity.error.FinicityAccessException;
import com.nibbledebt.integration.finicity.model.Institution;
import com.nibbledebt.integration.finicity.model.Institutions;

/**
 * @author alam_home
 *
 */
@Component
public class FinicityClient {
	
	@Autowired
	private RestClient restClient;
			
	@Value("${finicity.inst.url}")
	private String finicityInstUrl;	
	
	@NeedsToken
	public Institutions getInstitutions() throws FinicityAccessException{
		return  restClient.getForObject(finicityInstUrl, Institutions.class);
	}

	@NeedsToken
	public Institution getInstitution(String institutionId) throws FinicityAccessException{
		return  restClient.getForObject(finicityInstUrl+institutionId, Institution.class);
	}

}