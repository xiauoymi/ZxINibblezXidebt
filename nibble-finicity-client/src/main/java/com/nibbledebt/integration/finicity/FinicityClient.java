/**
 * 
 */
package com.nibbledebt.integration.finicity;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.nibbledebt.integration.finicity.error.FinicityAccessException;
import com.nibbledebt.integration.finicity.model.Customer;
import com.nibbledebt.integration.finicity.model.Customers;
import com.nibbledebt.integration.finicity.model.Institution;
import com.nibbledebt.integration.finicity.model.Institutions;
import com.nibbledebt.integration.finicity.model.LoginField;
import com.nibbledebt.integration.finicity.model.LoginForm;
import com.nibbledebt.integration.finicity.model.TransactionTest;
import com.nibbledebt.integration.finicity.model.accounts.Accounts;
import com.nibbledebt.integration.finicity.model.accounts.AddAccountsResponse;
import com.nibbledebt.integration.finicity.model.accounts.ChallengesRequest;
import com.nibbledebt.integration.finicity.model.accounts.CustomerAccountMfaRequest;
import com.nibbledebt.integration.finicity.model.accounts.CustomerAccountsRequest;
import com.nibbledebt.integration.finicity.model.accounts.DiscoverAccountsResponse;
import com.nibbledebt.integration.finicity.model.accounts.ImageChoiceMfaChallenges;
import com.nibbledebt.integration.finicity.model.accounts.ImageMfaChallenges;
import com.nibbledebt.integration.finicity.model.accounts.MfaChallenges;
import com.nibbledebt.integration.finicity.model.accounts.MfaType;
import com.nibbledebt.integration.finicity.model.accounts.QuestionRequest;
import com.nibbledebt.integration.finicity.model.accounts.TextChoiceMfaChallenges;
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

	@Autowired
	private SecurityContext securityContext;

	private final String MFA_SESSION_HEADER = "MFA-Session";

	@NeedsToken
	public Institutions getInstitutions() throws FinicityAccessException {
		return restClient.getForObject(finicityInstUrl, Institutions.class);
	}

	@NeedsToken
	public Institutions getInstitutions(String search, Integer start, Integer limit)
			throws FinicityAccessException, RestClientException, UnsupportedEncodingException {
		if (start == null) {
			start = 1;
		}
		if (limit == null) {
			limit = 10;
		}
		if (search == null || "".equals(search)) {
			search = "*";
		}
		URI targetUrl = UriComponentsBuilder.fromUriString(finicityInstUrl).queryParam("search", search)
				.queryParam("start", start).queryParam("limit", limit).build().toUri();
		System.out.println(targetUrl);
		return restClient.getForObject(targetUrl, Institutions.class);
	}

	@NeedsToken
	public Institution getInstitution(String institutionId) throws FinicityAccessException {
		return restClient.getForObject(finicityInstUrl + institutionId, Institution.class);
	}

	@NeedsToken
	public LoginForm getInstitutionLoginForm(String institutionId) throws FinicityAccessException {
		return restClient.getForObject(finicityInstUrl + institutionId + "/loginForm", LoginForm.class);
	}

	@NeedsToken
	public Customer addTestCustomer(String username, String firstName, String lastName) throws FinicityAccessException {
		return restClient.postForObject(finicityCustUrl + "testing", new Customer(username, firstName, lastName),
				Customer.class);
	}

	@NeedsToken
	public Customer addCustomer(String username, String firstName, String lastName) throws FinicityAccessException {
		return restClient.postForObject(finicityCustUrl + "active", new Customer(username, firstName, lastName),
				Customer.class);
	}

	@NeedsToken
	public void deleteCustomer(String customerId) throws FinicityAccessException {
		restClient.delete(finicityCustUrl + customerId);
	}

	@NeedsToken
	public Customers getCustomers() throws FinicityAccessException {
		return restClient.getForEntity(finicityCustUrl, Customers.class).getBody();
	}

	/**
	 * Discover customer accounts. (Need to activate accounts)
	 * 
	 * @param customerId
	 *            - customer Id
	 * @param institutionId
	 *            - institution Id
	 * @param fields
	 *            - login fields
	 * @return Response Entity.
	 * @throws FinicityAccessException
	 */
	@NeedsToken
	public ResponseEntity<String> addCustomerAccountsString(String customerId, String institutionId,
			LoginField[] fields) throws FinicityAccessException {
		CustomerAccountsRequest request = new CustomerAccountsRequest();
		request.setFields(fields);
		ResponseEntity<String> entity = restClient.postForEntity(
				finicityCustUrl + customerId + "/institutions/" + institutionId + "/accounts/addall", request,
				String.class);
		return entity;
	}

	@NeedsToken
	public TransactionTest addTestTx(String customerId, String accountId)
			throws FinicityAccessException {
		String description = "customerId = "+customerId+" accountId="+accountId+" "+new Date();
		return restClient.postForObject(finicityCustUrl +customerId+ "/accounts/"+accountId+"/transactions", new TransactionTest(description),
				TransactionTest.class);
	}
	
	/**
	 *
	 * @param customerId
	 *            - customer Id
	 * @param institutionId
	 *            - institution Id
	 * @return Response Entity. If
	 * @throws FinicityAccessException
	 */
	@NeedsToken
	public ResponseEntity<String> addCustomerAccountsMfaString(String customerId, String institutionId, String text,
			String answer) throws FinicityAccessException {

		CustomerAccountMfaRequest request = new CustomerAccountMfaRequest();
		ChallengesRequest challenges = new ChallengesRequest();
		QuestionRequest question = new QuestionRequest();

		question.setText(text);
		question.setAnswer(answer);

		challenges.setQuestion(new QuestionRequest[] { question });

		request.setMfaChallenges(challenges);

		HttpHeaders headers = new HttpHeaders();
		headers.add(MFA_SESSION_HEADER, securityContext.getMfaToken());
		HttpEntity<CustomerAccountMfaRequest> requestEntity = new HttpEntity<CustomerAccountMfaRequest>(request,
				headers);

		ResponseEntity<String> entity = restClient.exchange(
				finicityCustUrl + customerId + "/institutions/" + institutionId + "/accounts/addall/mfa",
				HttpMethod.POST, requestEntity, String.class);
		return entity;
	}
	
	
	@NeedsToken
	public ResponseEntity<String> addCustomerAccountsMfaString(String customerId, String institutionId, QuestionRequest[] questions) throws FinicityAccessException {

		CustomerAccountMfaRequest request = new CustomerAccountMfaRequest();
		ChallengesRequest challenges = new ChallengesRequest();

		challenges.setQuestion(questions);

		request.setMfaChallenges(challenges);

		HttpHeaders headers = new HttpHeaders();
		headers.add(MFA_SESSION_HEADER, securityContext.getMfaToken());
		HttpEntity<CustomerAccountMfaRequest> requestEntity = new HttpEntity<CustomerAccountMfaRequest>(request,
				headers);

		ResponseEntity<String> entity = restClient.exchange(
				finicityCustUrl + customerId + "/institutions/" + institutionId + "/accounts/addall/mfa",
				HttpMethod.POST, requestEntity, String.class);
		return entity;
	}

	/**
	 * Discover customer accounts
	 * 
	 * @param customerId
	 *            - customer Id
	 * @param institutionId
	 *            - institution Id
	 * @param fields
	 *            - login fields
	 * @return Response Entity.
	 * @throws FinicityAccessException
	 */
	@NeedsToken
	public ResponseEntity<String> discoverCustomerAccountsString(String customerId, String institutionId,
			LoginField[] fields) throws FinicityAccessException {
		CustomerAccountsRequest request = new CustomerAccountsRequest();
		request.setFields(fields);
		ResponseEntity<String> entity = restClient.postForEntity(
				finicityCustUrl + customerId + "/institutions/" + institutionId + "/accounts", request, String.class);
		return entity;
	}

	/**
	 * Discover Accounts
	 * 
	 * @param customerId
	 *            - customerId
	 * @param institutionId
	 *            - institutionId
	 * @param fields
	 *            login fields
	 * @return - Discover Account Response
	 * @throws FinicityAccessException
	 */
	@NeedsToken
	public DiscoverAccountsResponse discoverAccounts(String customerId, String institutionId, LoginField[] fields)
			throws FinicityAccessException {
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
	 * 
	 * @param customerId
	 *            - customerId
	 * @param institutionId
	 *            - institutionId
	 * @param fields
	 *            login fields
	 * @return - Add Account Response
	 * @throws FinicityAccessException
	 */
	@NeedsToken
	public AddAccountsResponse addAccounts(String customerId, String institutionId, LoginField[] fields)
			throws FinicityAccessException {
		ResponseEntity<String> entity = addCustomerAccountsString(customerId, institutionId, fields);
		return getAddAccountResponse(entity);
	}

	/**
	 * Add Accounts with WFA answer
	 * 
	 * @param customerId
	 * @param institutionId
	 * @param fields
	 * @param text
	 * @param answer
	 * @return - Add Account Response
	 * @throws FinicityAccessException
	 */
	@NeedsToken
	public AddAccountsResponse addAccountsWithMfaAnswers(String customerId, String institutionId, String text,
			String answer) throws FinicityAccessException {
		ResponseEntity<String> entity = addCustomerAccountsMfaString(customerId, institutionId, text, answer);
		return getAddAccountResponse(entity);
	};

	private AddAccountsResponse getAddAccountResponse(ResponseEntity<String> entity) throws FinicityAccessException {
		XmlMapper mapper = new XmlMapper();
		AddAccountsResponse response = new AddAccountsResponse();
		if (entity.getStatusCode() == HttpStatus.OK) {
			response.setMfaType(MfaType.NON_MFA);
			try {
				response.setAccounts(mapper.readValue(entity.getBody(), Accounts.class));
			} catch (Exception e) {
				throw new FinicityAccessException(e);
			}
		} else if (entity.getStatusCode() == HttpStatus.NON_AUTHORITATIVE_INFORMATION) {
			MfaChallenges challenges;
			securityContext.setMfaToken(entity.getHeaders().get(MFA_SESSION_HEADER).get(0));
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
						try {
							challenges = mapper.readValue(entity.getBody(), TextChoiceMfaChallenges.class);
							response.setMfaType(MfaType.TEXT_CHOOSE);
						} catch (Exception e3) {
							throw new FinicityAccessException(e3);
						}
					}
				}
			}
			response.setMfaChallenges(challenges);
		}
		return response;
	}

	@NeedsToken
	public Transactions getCustomerAccountTransactions(String customerId, String accountId, Date fromDate, Date toDate,
			int start, int limit, String sort) throws FinicityAccessException {
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(finicityCustAcctTrxsUrl + customerId + "/accounts/" + accountId + "/transactions");
		if (StringUtils.isNotBlank(sort))
			builder.queryParam("sort", sort);
		if (fromDate != null)
			builder.queryParam("fromDate", String.valueOf(fromDate.getTime() / 1000));
		if (toDate != null)
			builder.queryParam("toDate", String.valueOf(toDate.getTime() / 1000));
		return restClient.getForEntity(builder.build().encode().toUriString(), Transactions.class).getBody();
	}
}