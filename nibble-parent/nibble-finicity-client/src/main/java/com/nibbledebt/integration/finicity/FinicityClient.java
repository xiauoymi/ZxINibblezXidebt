/**
 * 
 */
package com.nibbledebt.integration.finicity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.nibbledebt.integration.finicity.model.*;
import com.nibbledebt.integration.finicity.model.accounts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.nibbledebt.integration.finicity.error.FinicityAccessException;
import com.nibbledebt.integration.finicity.model.Customer;
import com.nibbledebt.integration.finicity.model.Institution;
import com.nibbledebt.integration.finicity.model.Institutions;
import com.nibbledebt.integration.finicity.model.LoginField;
import com.nibbledebt.integration.finicity.model.LoginForm;
import com.nibbledebt.integration.finicity.model.accounts.Accounts;
import com.nibbledebt.integration.finicity.model.accounts.AddAccountsResponse;
import com.nibbledebt.integration.finicity.model.accounts.CustomerAccountsRequest;
import com.nibbledebt.integration.finicity.model.accounts.DiscoverAccountsResponse;
import com.nibbledebt.integration.finicity.model.accounts.ImageChoiceMfaChallenges;
import com.nibbledebt.integration.finicity.model.accounts.ImageMfaChallenges;
import com.nibbledebt.integration.finicity.model.accounts.MfaChallenges;
import com.nibbledebt.integration.finicity.model.accounts.MfaType;
import com.nibbledebt.integration.finicity.model.accounts.TextMfaChallenges;
import com.nibbledebt.integration.finicity.model.trxs.Transactions;

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
	
	@Value("${finicity.cust.acct.trxs.url}")
	private String finicityCustAcctTrxsUrl;	
	
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
    public Customers getCustomers() throws FinicityAccessException {
        return restClient.getForEntity(finicityCustUrl, Customers.class).getBody();
    }

    /**
     * Discover customer accounts. (Need to activate accounts)
     * @param customerId    - customer Id
     * @param institutionId - institution Id
     * @param fields        - login fields
     * @return Response Entity.
     * @throws FinicityAccessException
     */
    @NeedsToken
    public ResponseEntity<String> addCustomerAccountsString(String customerId, String institutionId,
                                                                 LoginField[] fields) throws FinicityAccessException {
        CustomerAccountsRequest request = new CustomerAccountsRequest();
        request.setFields(fields);
        ResponseEntity<String> entity = restClient.postForEntity(
                finicityCustUrl + customerId + "/institutions/" + institutionId + "/accounts/addall",
                request, String.class);
        return entity;
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


    /**
     * Add Accounts
     * @param customerId - customerId
     * @param institutionId - institutionId
     * @param fields login fields
     * @return - Discover Account Response
     * @throws FinicityAccessException
     */
    @NeedsToken
    public AddAccountsResponse addAccounts(String customerId, String institutionId,
                                                     LoginField[] fields) throws FinicityAccessException {
        XmlMapper mapper = new XmlMapper();
        AddAccountsResponse response = new AddAccountsResponse();
        ResponseEntity<String> entity = addCustomerAccountsString(customerId, institutionId, fields);
        if (entity.getStatusCode() == HttpStatus.OK) {
            response.setMfaType(MfaType.NON_MFA);
            try {
                response.setAccounts(mapper.readValue(entity.getBody(), Accounts.class));
            } catch (Exception e) {
                throw new FinicityAccessException(e);
            }
        } else if (entity.getStatusCode() == HttpStatus.NON_AUTHORITATIVE_INFORMATION) {
            MfaChallenges challenges;
            try {
                challenges = mapper.readValue(entity.getBody(), TextMfaChallenges.class);
                response.setMfaType(MfaType.TEXT);
            } catch (Exception e) {
                try {
                    challenges = mapper.readValue(entity.getBody(), ImageMfaChallenges.class);
                    response.setMfaType(MfaType.IMAGE);
                } catch (Exception e1) {
                    try {
                        challenges = mapper.readValue(entity.getBody(), ImageChoiceMfaChallenges.class);
                        response.setMfaType(MfaType.IMAGE_CHOOSE);
                    } catch (Exception e2) {
                        throw new FinicityAccessException(e2);
                    }
                }
            }
            response.setMfaChallenges(challenges);
        }
        return response;
    }
	
	@NeedsToken
	public Transactions getCustomerAccountTransactions(String customerId, String accountId, Date fromDate, Date toDate, int start, int limit, String sort) throws FinicityAccessException{
		Map<String, String> urlVariables = new HashMap<>();
		if(StringUtils.isNotBlank(sort)) urlVariables.put("sort", sort);
		if(fromDate != null ) urlVariables.put("fromDate", String.valueOf(fromDate.getTime()));
		if(toDate != null ) urlVariables.put("toDate", String.valueOf(toDate.getTime()));
		return urlVariables.isEmpty() ? restClient.getForObject(finicityCustAcctTrxsUrl+customerId+"/accounts/"+accountId+"/transactions", Transactions.class) : 
			restClient.getForObject(finicityCustAcctTrxsUrl+customerId+"/accounts/"+accountId+"/transactions", Transactions.class, urlVariables);
	}
}