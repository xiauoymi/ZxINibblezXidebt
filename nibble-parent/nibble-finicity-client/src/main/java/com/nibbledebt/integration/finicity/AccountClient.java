package com.nibbledebt.integration.finicity;

import com.nibbledebt.integration.finicity.error.FinicityAccessException;
import com.nibbledebt.integration.finicity.model.LoginField;
import com.nibbledebt.integration.finicity.model.accounts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author a.salachyonok
 */
@Component
public class AccountClient {

    @Autowired
    private RestClient restClient;

    @Value("${finicity.cust.url}")
    private String accountsBaseUrl;

    public static final String MFA_SESSION_HEADER = "MFA-Session";

    /**
     * Discover customer accounts
     * @param customerId - customer ID
     * @param institutionId - institution ID
     * @param fields - login fields
     * @return Accounts object or null if MFA
     * @throws FinicityAccessException
     */

    @NeedsToken
    public Accounts discoverCustomerAccounts(String customerId, String institutionId,
                                         LoginField[] fields) throws FinicityAccessException {
        CustomerAccountsRequest request = new CustomerAccountsRequest();
        request.setFields(fields);
        Accounts accounts = restClient.postForObject(
                accountsBaseUrl + customerId + "/institutions/" + institutionId + "/accounts",
                request, Accounts.class);
        return accounts;
    }

    /**
     *
     * @param customerId - customer Id
     * @param institutionId - institution Id
     * @param fields - login fields
     * @return Response Entity. If
     * @throws FinicityAccessException
     */
    @NeedsToken
    public ResponseEntity<String> discoverCustomerAccountsString(String customerId, String institutionId,
                                             LoginField[] fields) throws FinicityAccessException {
        CustomerAccountsRequest request = new CustomerAccountsRequest();
        request.setFields(fields);
        ResponseEntity<String> entity = restClient.postForEntity(
                accountsBaseUrl + customerId + "/institutions/" + institutionId + "/accounts",
                request, String.class);
        return entity;
    }


    /**
     *
     * @param customerId - customer Id
     * @param institutionId - institution Id
     * @param fields - login fields
     * @return Response Entity. If
     * @throws FinicityAccessException
     */
    @NeedsToken
    public ResponseEntity<String> addAllCustomerAccountsString(String customerId, String institutionId,
                                                                 LoginField[] fields) throws FinicityAccessException {
        CustomerAccountsRequest request = new CustomerAccountsRequest();
        request.setFields(fields);
        ResponseEntity<String> entity = restClient.postForEntity(
                accountsBaseUrl + customerId + "/institutions/" + institutionId + "/accounts/addall",
                request, String.class);
        return entity;
    }

    /**
     *
     * @param customerId - customer Id
     * @param institutionId - institution Id
     * @return Response Entity. If
     * @throws FinicityAccessException
     */
    @NeedsToken
    public ResponseEntity<String> addAllCustomerAccountsMfaString(
            String customerId, String institutionId,
            String mfaSession, String text, String answer) throws FinicityAccessException {
        CustomerAccountMfaRequest request = new CustomerAccountMfaRequest();
        ChallengesRequest challenges = new ChallengesRequest();
        QuestionRequest question = new QuestionRequest();

        question.setText(text);
        question.setAnswer(answer);

        challenges.setQuestion(new QuestionRequest[]{question});

        request.setMfaChallenges(challenges);

        HttpHeaders headers = new HttpHeaders();
        headers.add(MFA_SESSION_HEADER, mfaSession);
        HttpEntity<CustomerAccountMfaRequest> requestEntity =
                new HttpEntity<CustomerAccountMfaRequest>(request, headers);

        ResponseEntity<String> entity = restClient.exchange(
                accountsBaseUrl + customerId + "/institutions/" + institutionId + "/accounts/addall/mfa",
                HttpMethod.POST, requestEntity, String.class);
        return entity;
    }

    /**
     *
     * @param customerId - customer Id
     * @param institutionId - institution Id
     * @return Response Entity. If
     * @throws FinicityAccessException
     */
    @NeedsToken
    public ResponseEntity<String> discoverCustomerAccountsMfaString(
            String customerId, String institutionId, LoginField[] fields,
            String mfaSession, String text, String answer) throws FinicityAccessException {

        CustomerAccountMfaRequest request = new CustomerAccountMfaRequest();
        ChallengesRequest challenges = new ChallengesRequest();
        QuestionRequest question = new QuestionRequest();

        question.setText(text);
        question.setAnswer(answer);

        challenges.setQuestion(new QuestionRequest[]{question});

        request.setMfaChallenges(challenges);
        request.setFields(fields);

        HttpHeaders headers = new HttpHeaders();
        headers.add(MFA_SESSION_HEADER, mfaSession);
        HttpEntity<CustomerAccountMfaRequest> requestEntity =
                new HttpEntity<CustomerAccountMfaRequest>(request, headers);

        ResponseEntity<String> entity = restClient.exchange(
                accountsBaseUrl + customerId + "/institutions/" + institutionId + "/accounts/mfa",
                HttpMethod.POST, requestEntity, String.class);
        return entity;
    }

    /**
     *
     * @param customerId - customer Id
     * @param institutionId - institution Id
     * @return Response Entity. If
     * @throws FinicityAccessException
     */
    @NeedsToken
    public ResponseEntity<String> activationCustomerAccountsAggregationString(
            String customerId, String institutionId, Account[] accounts) throws FinicityAccessException {
        Accounts request = new Accounts();
        request.setAccount(accounts);
        HttpEntity<Accounts> requestEntity = new HttpEntity<Accounts>(request);
        ResponseEntity<String> entity = restClient.exchange(
                accountsBaseUrl + customerId + "/institutions/" + institutionId + "/accounts",
                HttpMethod.PUT, requestEntity, String.class);
        return entity;
    }


    /**
     *
     * @param customerId - customer Id
     * @param institutionId - institution Id
     * @return Response Entity. If
     * @throws FinicityAccessException
     */
    @NeedsToken
    public ResponseEntity<String> activationCustomerAccountsMfaAggregationString(
            String customerId, String institutionId, Account[] accounts) throws FinicityAccessException {
        AccountsMfa request = new AccountsMfa();
        request.setAccount(accounts);
        HttpEntity<AccountsMfa> requestEntity = new HttpEntity<AccountsMfa>(request);
        ResponseEntity<String> entity = restClient.exchange(
                accountsBaseUrl + customerId + "/institutions/" + institutionId + "/accounts/mfa",
                HttpMethod.PUT, requestEntity, String.class);
        return entity;
    }

}
