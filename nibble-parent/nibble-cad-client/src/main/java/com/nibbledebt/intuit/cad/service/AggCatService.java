package com.nibbledebt.intuit.cad.service;

import java.io.InputStream;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import com.nibbledebt.intuit.cad.core.ContentType;
import com.nibbledebt.intuit.cad.core.Context;
import com.nibbledebt.intuit.cad.core.EntityName;
import com.nibbledebt.intuit.cad.core.MethodType;
import com.nibbledebt.intuit.cad.data.Account;
import com.nibbledebt.intuit.cad.data.AccountList;
import com.nibbledebt.intuit.cad.data.ChallengeResponses;
import com.nibbledebt.intuit.cad.data.Challenges;
import com.nibbledebt.intuit.cad.data.Files;
import com.nibbledebt.intuit.cad.data.InstitutionDetail;
import com.nibbledebt.intuit.cad.data.InstitutionLogin;
import com.nibbledebt.intuit.cad.data.Institutions;
import com.nibbledebt.intuit.cad.data.InvestmentPositions;
import com.nibbledebt.intuit.cad.data.TransactionList;
import com.nibbledebt.intuit.cad.exception.AggCatException;
import com.nibbledebt.intuit.cad.interceptor.InterceptorProvider;
import com.nibbledebt.intuit.cad.interceptor.IntuitMessage;
import com.nibbledebt.intuit.cad.interceptor.RequestElements;
import com.nibbledebt.intuit.cad.interceptor.ResponseElements;
import com.nibbledebt.intuit.cad.util.StringUtils;

public class AggCatService {
	private transient Context context = null;

	public AggCatService(Context context) {
		this.context = context;
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");

		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");

		System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "debug");

		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");
	}

	public Institutions getInstitutions(String requestType) throws AggCatException {
		IntuitMessage intuitMessage = new IntuitMessage();
		intuitMessage.setRequestType(requestType);
		RequestElements requestElements = intuitMessage.getRequestElements();

		Map requestParameters = requestElements.getRequestParameters();

		requestParameters
				.put("entity-name", EntityName.INSTITUTIONS.toString());
		requestParameters.put("method-type", MethodType.GET.toString());

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("GetInstitutions");

		new InterceptorProvider().executeInterceptors(intuitMessage);

		return (Institutions) intuitMessage.getResponseElements().getResponse();
	}

	public InstitutionDetail getInstitutionDetails(long institutionId, String requestType)
			throws AggCatException {
		IntuitMessage intuitMessage = new IntuitMessage();
		intuitMessage.setRequestType(requestType);
		RequestElements requestElements = intuitMessage.getRequestElements();

		Map requestParameters = requestElements.getRequestParameters();

		requestParameters
				.put("entity-name", EntityName.INSTITUTIONS.toString());
		requestParameters.put("id", String.valueOf(institutionId));
		requestParameters.put("method-type", MethodType.GET.toString());

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("GetInstitutionDetails");

		new InterceptorProvider().executeInterceptors(intuitMessage);

		return (InstitutionDetail) intuitMessage.getResponseElements()
				.getResponse();
	}

	public DiscoverAndAddAccountsResponse discoverAndAddAccounts(
			long institutionId, InstitutionLogin institutionLogin, String requestType)
			throws AggCatException {
		IntuitMessage intuitMessage = new IntuitMessage();
		intuitMessage.setRequestType(requestType);
		RequestElements requestElements = intuitMessage.getRequestElements();

		Map requestParameters = requestElements.getRequestParameters();

		requestParameters
				.put("entity-name", EntityName.INSTITUTIONS.toString());
		requestParameters.put("id", String.valueOf(institutionId));
		requestParameters.put("action", EntityName.LOGINS.toString());
		requestParameters.put("method-type", MethodType.POST.toString());

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("DiscoverAndAddAccounts");

		requestElements.setObjectToSerialize(institutionLogin);

		new InterceptorProvider().executeInterceptors(intuitMessage);

		return getDiscoverAndAddAccountsResponse(String.valueOf(institutionId),
				intuitMessage);
	}
	

	public DiscoverAndAddAccountsResponse discoverAndAddAccounts(
			ChallengeResponses challengeResponses,
			ChallengeSession challengeSession) throws AggCatException {
		IntuitMessage intuitMessage = new IntuitMessage();
		RequestElements requestElements = intuitMessage.getRequestElements();

		Map requestParameters = requestElements.getRequestParameters();

		requestParameters
				.put("entity-name", EntityName.INSTITUTIONS.toString());
		requestParameters.put("id", challengeSession.getId());
		requestParameters.put("action", EntityName.LOGINS.toString());
		requestParameters.put("method-type", MethodType.POST.toString());

		Map requestHeaders = requestElements.getRequestHeaders();
		requestHeaders.put("challengeNodeId",
				challengeSession.getChallengeNodeId());
		requestHeaders.put("challengeSessionId",
				challengeSession.getChallengeSessionId());

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("DiscoverAndAddAccounts");

		InstitutionLogin institutionLogin = new InstitutionLogin();
		institutionLogin.setChallengeResponses(challengeResponses);

		requestElements.setObjectToSerialize(institutionLogin);

		new InterceptorProvider().executeInterceptors(intuitMessage);

		return getDiscoverAndAddAccountsResponse(challengeSession.getId(),
				intuitMessage);
	}

	private DiscoverAndAddAccountsResponse getDiscoverAndAddAccountsResponse(
			String institutionId, IntuitMessage intuitMessage) {
		DiscoverAndAddAccountsResponse response = new DiscoverAndAddAccountsResponse();
		RequestElements requestElements = intuitMessage.getRequestElements();
		String identifier = requestElements.getResourceTypeIdentifier();

		ResponseElements responseElements = intuitMessage.getResponseElements();
		if (identifier.equals("DiscoverAndAddAccounts")) {
			AccountList accountList = (AccountList) responseElements
					.getResponse();
			response.setAccountList(accountList);
		} else {
			Challenges challenges = (Challenges) responseElements.getResponse();
			response.setChallenges(challenges);

			ChallengeSession challengeSession = new ChallengeSession();
			HttpResponse httpResponse = responseElements.getHttpResponse();
			Header challengeSessionIdHeader = httpResponse
					.getLastHeader("challengeSessionId");
			Header challengeNodeIdHeader = httpResponse
					.getLastHeader("challengeNodeId");

			challengeSession.setChallengeSessionId(challengeSessionIdHeader
					.getValue());
			challengeSession.setChallengeNodeId(challengeNodeIdHeader
					.getValue());
			challengeSession.setId(institutionId);

			response.setChallengeSession(challengeSession);
		}
		return response;
	}

	public AccountList getCustomerAccounts() throws AggCatException {
		IntuitMessage intuitMessage = new IntuitMessage();
		RequestElements requestElements = intuitMessage.getRequestElements();
		Map requestParameters = requestElements.getRequestParameters();

		requestParameters.put("entity-name", EntityName.ACCOUNTS.toString());
		requestParameters.put("method-type", MethodType.GET.toString());

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("GetCustomerAccounts");

		new InterceptorProvider().executeInterceptors(intuitMessage);

		return (AccountList) intuitMessage.getResponseElements().getResponse();
	}

	public AccountList getLoginAccounts(long loginId) throws AggCatException {
		IntuitMessage intuitMessage = new IntuitMessage();
		RequestElements requestElements = intuitMessage.getRequestElements();
		Map requestParameters = requestElements.getRequestParameters();

		requestParameters.put("entity-name", EntityName.LOGINS.toString());
		requestParameters.put("id", String.valueOf(loginId));
		requestParameters.put("action", EntityName.ACCOUNTS.toString());
		requestParameters.put("method-type", MethodType.GET.toString());

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("GetLoginAccounts");

		new InterceptorProvider().executeInterceptors(intuitMessage);

		return (AccountList) intuitMessage.getResponseElements().getResponse();
	}

	public AccountList getAccount(long accountId) throws AggCatException {
		IntuitMessage intuitMessage = new IntuitMessage();
		RequestElements requestElements = intuitMessage.getRequestElements();
		Map requestParameters = requestElements.getRequestParameters();

		requestParameters.put("entity-name", EntityName.ACCOUNTS.toString());
		requestParameters.put("id", String.valueOf(accountId));
		requestParameters.put("method-type", MethodType.GET.toString());

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("GetAccount");

		new InterceptorProvider().executeInterceptors(intuitMessage);

		return (AccountList) intuitMessage.getResponseElements().getResponse();
	}

	public TransactionList getAccountTransactions(long accountId,
			String txnStartDate, String txnEndDate) throws AggCatException {
		if (!StringUtils.hasText(txnStartDate)) {
			throw new AggCatException("Transaction start date is missing");
		}
		IntuitMessage intuitMessage = new IntuitMessage();
		RequestElements requestElements = intuitMessage.getRequestElements();
		Map requestParameters = requestElements.getRequestParameters();

		requestParameters.put("entity-name", EntityName.ACCOUNTS.toString());
		requestParameters.put("id", String.valueOf(accountId));
		requestParameters.put("action", EntityName.TRANSACTIONS.toString());
		requestParameters.put("method-type", MethodType.GET.toString());

		Map queryParameters = requestElements.getQueryParameters();
		queryParameters.put("txnStartDate", txnStartDate);

		if (StringUtils.hasText(txnEndDate)) {
			queryParameters.put("txnEndDate", txnEndDate);
		}

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("GetAccountTransactions");

		new InterceptorProvider().executeInterceptors(intuitMessage);

		return (TransactionList) intuitMessage.getResponseElements()
				.getResponse();
	}

	public UpdateInstitutionLoginResponse updateInstitutionLogin(long loginId,
			boolean isRefreshReqd, InstitutionLogin institutionLogin)
			throws AggCatException {
		IntuitMessage intuitMessage = new IntuitMessage();
		RequestElements requestElements = intuitMessage.getRequestElements();

		Map requestParameters = requestElements.getRequestParameters();

		requestParameters.put("entity-name", EntityName.LOGINS.toString());
		requestParameters.put("id", String.valueOf(loginId));
		requestParameters.put("method-type", MethodType.PUT.toString());

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("UpdateInstitutionLogin");

		Map queryParams = requestElements.getQueryParameters();
		queryParams.put("refresh", Boolean.toString(isRefreshReqd));

		if (null != institutionLogin) {
			requestElements.setObjectToSerialize(institutionLogin);
		}

		new InterceptorProvider().executeInterceptors(intuitMessage);

		UpdateInstitutionLoginResponse response = getUpdateInstitutionLoginResponse(
				String.valueOf(loginId), intuitMessage);
		return response;
	}

	public UpdateInstitutionLoginResponse updateInstitutionLogin(
			ChallengeResponses challengeResponses,
			ChallengeSession challengeSession) throws AggCatException {
		IntuitMessage intuitMessage = new IntuitMessage();
		RequestElements requestElements = intuitMessage.getRequestElements();

		Map requestParameters = requestElements.getRequestParameters();

		requestParameters.put("entity-name", EntityName.LOGINS.toString());
		requestParameters.put("id", challengeSession.getId());
		requestParameters.put("method-type", MethodType.PUT.toString());

		Map requestHeaders = requestElements.getRequestHeaders();
		requestHeaders.put("challengeNodeId",
				challengeSession.getChallengeNodeId());
		requestHeaders.put("challengeSessionId",
				challengeSession.getChallengeSessionId());

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("UpdateInstitutionLogin");

		InstitutionLogin institutionLogin = new InstitutionLogin();
		institutionLogin.setChallengeResponses(challengeResponses);

		requestElements.setObjectToSerialize(institutionLogin);

		new InterceptorProvider().executeInterceptors(intuitMessage);

		UpdateInstitutionLoginResponse response = getUpdateInstitutionLoginResponse(
				challengeSession.getId(), intuitMessage);
		return response;
	}

	private UpdateInstitutionLoginResponse getUpdateInstitutionLoginResponse(
			String loginId, IntuitMessage intuitMessage) {
		UpdateInstitutionLoginResponse response = new UpdateInstitutionLoginResponse();
		RequestElements requestElements = intuitMessage.getRequestElements();
		String identifier = requestElements.getResourceTypeIdentifier();

		if (identifier.equals("UpdateInstitutionLogin")) {
			response.setUpdated(true);
		} else {
			ResponseElements responseElements = intuitMessage
					.getResponseElements();
			Challenges challenges = (Challenges) responseElements.getResponse();
			response.setChallenges(challenges);

			ChallengeSession challengeSession = new ChallengeSession();
			HttpResponse httpResponse = responseElements.getHttpResponse();
			Header challengeSessionIdHeader = httpResponse
					.getLastHeader("challengeSessionId");
			Header challengeNodeIdHeader = httpResponse
					.getLastHeader("challengeNodeId");
			challengeSession.setChallengeSessionId(challengeSessionIdHeader
					.getValue());
			challengeSession.setChallengeNodeId(challengeNodeIdHeader
					.getValue());
			challengeSession.setId(loginId);

			response.setChallengeSession(challengeSession);
		}
		return response;
	}

	public void updateAccountType(Account account) throws AggCatException {
		IntuitMessage intuitMessage = new IntuitMessage();
		RequestElements requestElements = intuitMessage.getRequestElements();

		Map requestParameters = requestElements.getRequestParameters();

		requestParameters.put("entity-name", EntityName.ACCOUNTS.toString());
		requestParameters.put("id", Long.toString(account.getAccountId()));
		requestParameters.put("method-type", MethodType.PUT.toString());

		requestElements.setObjectToSerialize(account);

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("UpdateAccountType");

		new InterceptorProvider().executeInterceptors(intuitMessage);
	}

	public void deleteAccount(long accountId) throws AggCatException {
		IntuitMessage intuitMessage = new IntuitMessage();
		RequestElements requestElements = intuitMessage.getRequestElements();

		Map requestParameters = requestElements.getRequestParameters();

		requestParameters.put("entity-name", EntityName.ACCOUNTS.toString());
		requestParameters.put("id", String.valueOf(accountId));
		requestParameters.put("method-type", MethodType.DELETE.toString());

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("DeleteAccount");

		new InterceptorProvider().executeInterceptors(intuitMessage);
	}

	public void deleteCustomer() throws AggCatException {
		IntuitMessage intuitMessage = new IntuitMessage();
		RequestElements requestElements = intuitMessage.getRequestElements();

		Map requestParameters = requestElements.getRequestParameters();

		requestParameters.put("entity-name", EntityName.CUSTOMERS.toString());
		requestParameters.put("method-type", MethodType.DELETE.toString());

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("DeleteCustomer");

		new InterceptorProvider().executeInterceptors(intuitMessage);
	}

	public InvestmentPositions getInvestmentPositions(long accountId)
			throws AggCatException {
		IntuitMessage intuitMessage = new IntuitMessage();
		RequestElements requestElements = intuitMessage.getRequestElements();
		Map requestParameters = requestElements.getRequestParameters();

		requestParameters.put("entity-name", EntityName.ACCOUNTS.toString());
		requestParameters.put("id", String.valueOf(accountId));
		requestParameters.put("action", EntityName.POSITIONS.toString());
		requestParameters.put("method-type", MethodType.GET.toString());

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("GetInvestmentPositions");

		new InterceptorProvider().executeInterceptors(intuitMessage);

		return (InvestmentPositions) intuitMessage.getResponseElements()
				.getResponse();
	}

	public Files listFiles() throws AggCatException {
		IntuitMessage intuitMessage = new IntuitMessage();
		RequestElements requestElements = intuitMessage.getRequestElements();
		requestElements.setApiType("Batch");
		Map requestParameters = requestElements.getRequestParameters();

		requestParameters.put("entity-name", EntityName.FILES.toString());
		requestParameters.put("method-type", MethodType.GET.toString());

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("ListFiles");

		new InterceptorProvider().executeInterceptors(intuitMessage);

		return (Files) intuitMessage.getResponseElements().getResponse();
	}

	public void deleteFile(String fileName) throws AggCatException {
		IntuitMessage intuitMessage = new IntuitMessage();
		RequestElements requestElements = intuitMessage.getRequestElements();
		requestElements.setApiType("Batch");

		Map requestParameters = requestElements.getRequestParameters();

		requestParameters.put("entity-name", EntityName.FILES.toString());
		requestParameters.put("id", fileName);
		requestParameters.put("method-type", MethodType.DELETE.toString());

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("DeleteFile");

		new InterceptorProvider().executeInterceptors(intuitMessage);
	}

	public InputStream getFileData(String fileName) throws AggCatException {
		return getFileData(fileName, null);
	}

	public InputStream getFileData(String fileName, long lowerRange,
			long upperRange) throws AggCatException {
		String range = Long.toString(lowerRange) + "-"
				+ Long.toString(upperRange);
		return getFileData(fileName, range);
	}

	private InputStream getFileData(String fileName, String range)
			throws AggCatException {
		IntuitMessage intuitMessage = new IntuitMessage();
		RequestElements requestElements = intuitMessage.getRequestElements();
		requestElements.setApiType("Batch");

		Map requestParameters = requestElements.getRequestParameters();

		requestParameters.put("entity-name", EntityName.FILES.toString());
		requestParameters.put("id", fileName);
		requestParameters.put("method-type", MethodType.GET.toString());

		Map requestHeaders = requestElements.getRequestHeaders();
		if (null != range) {
			requestHeaders.put("Range", range);
		}
		requestHeaders.put("content-type", ContentType.OCTETSTREAM.toString());

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("GetFileData");

		new InterceptorProvider().executeInterceptors(intuitMessage);
		return (InputStream) intuitMessage.getResponseElements().getResponse();
	}
}