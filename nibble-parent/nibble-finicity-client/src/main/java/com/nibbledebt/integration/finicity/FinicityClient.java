/**
 * 
 */
package com.nibbledebt.integration.finicity;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.nibbledebt.integration.finicity.model.accounts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.nibbledebt.integration.finicity.error.FinicityAccessException;
import com.nibbledebt.integration.finicity.model.Customer;
import com.nibbledebt.integration.finicity.model.Institution;
import com.nibbledebt.integration.finicity.model.Institutions;
import com.nibbledebt.integration.finicity.model.LoginField;
import com.nibbledebt.integration.finicity.model.LoginForm;

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

    /**
     * get list of institutions
     * @return com.nibbledebt.integration.finicity.model.Institutions
     * @throws FinicityAccessException
     */
	@NeedsToken
	public Institutions getInstitutions() throws FinicityAccessException{
		return  restClient.getForObject(finicityInstUrl, Institutions.class);
	}

    /**
     * get institution by specific id
     * @param institutionId institution id
     * @return com.nibbledebt.integration.finicity.model.Institution
     * @throws FinicityAccessException
     */
	@NeedsToken
	public Institution getInstitution(String institutionId) throws FinicityAccessException{
		return  restClient.getForObject(finicityInstUrl+institutionId, Institution.class);
	}

    /**
     * get institution login form by institution id
     * @param institutionId institution is
     * @return com.nibbledebt.integration.finicity.model.LoginForm
     * @throws FinicityAccessException
     */
	@NeedsToken
	public LoginForm getInstitutionLoginForm(String institutionId) throws FinicityAccessException{
		return  restClient.getForObject(finicityInstUrl+institutionId+"/loginForm", LoginForm.class);
	}
	
	@NeedsToken
	public Customer addTestCustomer(String username, String firstName, String lastName) throws FinicityAccessException{
		return  restClient.postForObject(finicityCustUrl+"testing", new Customer(username, firstName, lastName), Customer.class);
	}

    /**
     * Add customer
     * @param username - customer login
     * @param firstName - customer first name
     * @param lastName - customer last name
     * @return com.nibbledebt.integration.finicity.model.Customer
     * @throws FinicityAccessException
     */
	@NeedsToken
	public Customer addCustomer(String username, String firstName, String lastName) throws FinicityAccessException{
		return  restClient.postForObject(finicityCustUrl+"active", new Customer(username, firstName, lastName), Customer.class);
	}

    /**
     * delete customer by id
     * @param customerId - customer id
     * @throws FinicityAccessException
     */
	@NeedsToken
	public void deleteCustomer(String customerId) throws FinicityAccessException{
		restClient.delete(finicityCustUrl+customerId);
	}

    @Deprecated
	@NeedsToken
	public Accounts discoverAccounts(String customerId, String institutionId, List<LoginField> fields) throws FinicityAccessException{
		CustomerAccountsRequest req = new CustomerAccountsRequest();
		LoginField[] fieldArray = fields.toArray(new LoginField[fields.size()]);
		req.setFields(fieldArray);
		return restClient.postForObject(finicityCustUrl+customerId+"/institutions/"+institutionId+"/accounts", req, Accounts.class);
	}

    /**
     * Discover customer accounts
     * @param customerId    - customer Id
     * @param institutionId - institution Id
     * @param fields        - login fields
     * @return Response Entity.
     * @throws FinicityAccessException
     */
    @NeedsToken
    public ResponseEntity<String> discoverCustomerAccountsString(String customerId, String institutionId,
                                                                 LoginField[] fields) throws FinicityAccessException {
        CustomerAccountsRequest request = new CustomerAccountsRequest();
        request.setFields(fields);
        ResponseEntity<String> entity = restClient.postForEntity(
                finicityCustUrl + customerId + "/institutions/" + institutionId + "/accounts",
                request, String.class);
        return entity;
    }

    /**
     * Discover Accounts
     * @param customerId - customerId
     * @param institutionId - institutionId
     * @param fields login fields
     * @return - Discover Account Response
     * @throws FinicityAccessException
     */
    @NeedsToken
    public DiscoverAccountsResponse discoverAccounts(String customerId, String institutionId,
                                                     LoginField[] fields) throws FinicityAccessException {
        XmlMapper mapper = new XmlMapper();
        DiscoverAccountsResponse response = new DiscoverAccountsResponse();
        ResponseEntity<String> entity = discoverCustomerAccountsString(customerId, institutionId, fields);
        if (entity.getStatusCode() == HttpStatus.OK) {
            response.setType(MfaType.NON_MFA);
            try {
                response.setAccounts(mapper.readValue(entity.getBody(), Accounts.class));
            } catch (Exception e) {
                throw new FinicityAccessException(e);
            }
        } else if (entity.getStatusCode() == HttpStatus.NON_AUTHORITATIVE_INFORMATION) {
            MfaChallenges challenges;
            try {
                challenges = mapper.readValue(entity.getBody(), TextMfaChallenges.class);
                response.setType(MfaType.TEXT);
            } catch (Exception e) {
                try {
                    challenges = mapper.readValue(entity.getBody(), ImageMfaChallenges.class);
                    response.setType(MfaType.IMAGE);
                } catch (Exception e1) {
                    try {
                        challenges = mapper.readValue(entity.getBody(), ImageChoiceMfaChallenges.class);
                        response.setType(MfaType.IMAGE_CHOOSE);
                    } catch (Exception e2) {
                        throw new FinicityAccessException(e2);
                    }
                }
            }
            response.setMfaChallenges(challenges);
        }
        return response;
    }
	
}