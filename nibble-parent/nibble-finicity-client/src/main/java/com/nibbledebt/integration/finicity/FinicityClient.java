/**
 * 
 */
package com.nibbledebt.integration.finicity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nibbledebt.integration.finicity.error.FinicityAccessException;
import com.nibbledebt.integration.finicity.model.Customer;
import com.nibbledebt.integration.finicity.model.Institution;
import com.nibbledebt.integration.finicity.model.Institutions;
import com.nibbledebt.integration.finicity.model.LoginField;
import com.nibbledebt.integration.finicity.model.LoginForm;
import com.nibbledebt.integration.finicity.model.accounts.Accounts;
import com.nibbledebt.integration.finicity.model.accounts.CustomerAccountsRequest;

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
	
	@Value("${finicity.cust.url}")
	private String finicityCustUrl;	
	
	
	@NeedsToken
	public Institutions getInstitutions() throws FinicityAccessException{
		return  restClient.getForObject(finicityInstUrl, Institutions.class);
	}
	
	@NeedsToken
	public Institution getInstitution(String institutionId) throws FinicityAccessException{
		return  restClient.getForObject(finicityInstUrl+institutionId, Institution.class);
	}
	
	@NeedsToken
	public LoginForm getInstitutionLoginForm(String institutionId) throws FinicityAccessException{
		return  restClient.getForObject(finicityInstUrl+institutionId+"/loginForm", LoginForm.class);
	}
	
	@NeedsToken
	public Customer addTestCustomer(String username, String firstName, String lastName) throws FinicityAccessException{
		return  restClient.postForObject(finicityCustUrl+"testing", new Customer(username, firstName, lastName), Customer.class);
	}
	
	@NeedsToken
	public Customer addCustomer(String username, String firstName, String lastName) throws FinicityAccessException{
		return  restClient.postForObject(finicityCustUrl+"active", new Customer(username, firstName, lastName), Customer.class);
	}
	
	@NeedsToken
	public void deleteCustomer(String customerId) throws FinicityAccessException{
		restClient.delete(finicityCustUrl+customerId);
	}
	
	@NeedsToken
	public Accounts discoverAccounts(String customerId, String institutionId, List<LoginField> fields) throws FinicityAccessException{
		CustomerAccountsRequest req = new CustomerAccountsRequest();
		LoginField[] fieldArray = fields.toArray(new LoginField[fields.size()]);
		req.setFields(fieldArray);
		return restClient.postForObject(finicityCustUrl+customerId+"/institutions/"+institutionId+"/accounts", req, Accounts.class);
	}
	
}