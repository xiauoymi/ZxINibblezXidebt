package com.nibbledebt.dwolla;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.nibbledebt.Config;
import com.nibbledebt.core.data.model.NibblerAccount;
import com.nibbledebt.domain.model.account.Account;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.CustomersApi;
import io.swagger.client.api.FundingsourcesApi;
import io.swagger.client.api.TransfersApi;
import io.swagger.client.model.Amount;
import io.swagger.client.model.CreateCustomer;
import io.swagger.client.model.CreateFundingSourceRequest;
import io.swagger.client.model.FundingSource;
import io.swagger.client.model.HalLink;
import io.swagger.client.model.TransferRequestBody;
import io.swagger.client.model.Unit$;

@Component
public class DwollaClient implements IDwollaClient {
	public final static String SCOPES = "Send|AccountInfoFull|Funding";
	public final static String REDIRECT_URI = "http://localhost:4567/callback";
	public final static String CLIENT_ID = "vWy5Jd5MG9taM6mQ5p6pOMfXSsoMJUU4IweFeoBbZIICp8rlaU";
	public final static String CLIENT_SECRET = "Fzo8i4fFtRueFZB8t3YN1Vdr74VdeRGZm6zozHVmBPOixGCesS";
	public final static String SENDER_PIN = "1234";
	public final static String TOKEN = "QNNHiXEGqMUaPuS4MW9GptUCKcIVFinHxHx1gstOYvkZYdkxvb";
	public final static String FUNDING_SOURCE="";
	private static ApiClient client;
	@Resource
	private Environment env;
	
	public String createCustomer(NibblerAccount nibblerAccount) throws UnknownHostException, ApiException {
		CustomersApi api = new CustomersApi(getApiClient());
		CreateCustomer customer = new CreateCustomer();
		customer.setFirstName(nibblerAccount.getNibbler().getFirstName());
		customer.setLastName(nibblerAccount.getNibbler().getLastName());
		customer.setEmail(nibblerAccount.getNibbler().getEmail());
		customer.setType("personal");
		customer.setAddress1(nibblerAccount.getNibbler().getAddressLine1());
		customer.setAddress2(nibblerAccount.getNibbler().getAddressLine2());
		customer.setState(nibblerAccount.getNibbler().getState().toUpperCase().substring(0, 2));
		customer.setCity(nibblerAccount.getNibbler().getCity());
		customer.setPhone(nibblerAccount.getNibbler().getPhone());
		customer.setDateOfBirth(nibblerAccount.getNibbler().getDateOfBirth());
		customer.setPostalCode(nibblerAccount.getNibbler().getZip().toString());
		customer.setSsn(nibblerAccount.getNibbler().getSsn());
		customer.setIpAddress(InetAddress.getLocalHost().getHostAddress());
		Unit$ r = api.create(customer);
		return r.getLocationHeader();
	}

	public String createAccount(NibblerAccount nibblerAccount, String id) throws ApiException {
		FundingsourcesApi api = new FundingsourcesApi(getApiClient());
		CreateFundingSourceRequest fundingSourceRequest = new CreateFundingSourceRequest();
		fundingSourceRequest.setAccountNumber(nibblerAccount.getNumber());
		if(!StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod")){
			fundingSourceRequest.setRoutingNumber("222222226");
		}else{
			fundingSourceRequest.setRoutingNumber(nibblerAccount.getNumber().substring(0, 9));
		}
		fundingSourceRequest.setType("checking".equals(nibblerAccount.getAccountType().getCode())
				? nibblerAccount.getAccountType().getCode() : "savings");
		fundingSourceRequest.setName(nibblerAccount.getName());
		FundingSource fundingSource = api.createCustomerFundingSource(fundingSourceRequest, id);
		return fundingSource.getId();
	}

	private ApiClient getApiClient() {
		if (client == null) {
			client = new ApiClient();
			client.setBasePath(Config.BASE_URL);
			client.setAccessToken("vlXesFMo3xLIsgyTlLDaWQsgWYrVWfSxpT77XOtlRfYCYXFsoJ");
		}
		return client;
	}
	
	public static void main(String[] a) throws ApiException{
//		DwollaClient d=new DwollaClient();
//		FundingsourcesApi api = new FundingsourcesApi(d.getApiClient());
//		CreateFundingSourceRequest fundingSourceRequest = new CreateFundingSourceRequest();
//		fundingSourceRequest.setAccountNumber("4100007777");
//		fundingSourceRequest.setRoutingNumber("222222226");
//		System.out.println(fundingSourceRequest.getRoutingNumber());
//		System.out.println(fundingSourceRequest.getRoutingNumber().length());
//		fundingSourceRequest.setType("checking");
//		fundingSourceRequest.setName("MM");
//		FundingSource fundingSource = api.createCustomerFundingSource(fundingSourceRequest, "a7c0e646-fded-4926-8e2f-1e6f5038c866");
	}

	public boolean transfer(String from,String to,String value,NibblerAccount nibblerAccount) throws ApiException{
		FundingsourcesApi fundingsourcesApi=new FundingsourcesApi(getApiClient());
		FundingSource fundingSource=fundingsourcesApi.id(from);
		if(fundingSource.getBalance().compareTo(new BigDecimal(value))<=0){
			return false;
		}
		TransfersApi api=new TransfersApi(getApiClient());
		TransferRequestBody transfertRequestBody=new TransferRequestBody();
		Amount amount=new Amount();
		amount.setCurrency("USD");
		amount.setValue(value);
		transfertRequestBody.setAmount(amount);
		Map<String,HalLink> links=new HashMap<String,HalLink>();
		HalLink destination=new HalLink();
		destination.setHref(to);
		HalLink source=new HalLink();
		source.setHref(from);
		links.put("destination", destination);
		links.put("source", source);
		transfertRequestBody.setLinks(links);
		api.create(transfertRequestBody);
		return true;
	}
	
	public boolean transfer(String from,String value,NibblerAccount nibblerAccount) throws ApiException{
		FundingsourcesApi fundingsourcesApi=new FundingsourcesApi(getApiClient());
		FundingSource fundingSource=fundingsourcesApi.id(from);
		if(fundingSource.getBalance().compareTo(new BigDecimal(value))<=0){
			return false;
		}
		TransfersApi api=new TransfersApi(getApiClient());
		TransferRequestBody transfertRequestBody=new TransferRequestBody();
		Amount amount=new Amount();
		amount.setCurrency("USD");
		amount.setValue(value);
		transfertRequestBody.setAmount(amount);
		Map<String,HalLink> links=new HashMap<String,HalLink>();
		HalLink destination=new HalLink();
		destination.setHref(FUNDING_SOURCE);
		HalLink source=new HalLink();
		source.setHref(from);
		links.put("destination", destination);
		links.put("source", source);
		transfertRequestBody.setLinks(links);
		api.create(transfertRequestBody);
		return true;
	}
	
	
	public BigDecimal getBalance(String id) throws ApiException{
		FundingsourcesApi fundingsourcesApi=new FundingsourcesApi(getApiClient());
		FundingSource fundingSource=fundingsourcesApi.id(id);
		return fundingSource.getBalance();
		}
}