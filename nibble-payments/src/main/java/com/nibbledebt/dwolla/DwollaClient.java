package com.nibbledebt.dwolla;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.nibbledebt.Config;
import com.nibbledebt.core.data.model.Nibbler;
import com.nibbledebt.core.data.model.NibblerAccount;

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
import io.swagger.client.model.Transfer;
import io.swagger.client.model.TransferListResponse;
import io.swagger.client.model.TransferRequestBody;
import io.swagger.client.model.Unit$;

@Component
public class DwollaClient implements IDwollaClient {
	@Value("${dwolla.fundingSource}")
	private String fundingSource;
	private final Logger logger = LoggerFactory.getLogger(getClass());
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
		nibblerAccount.getNibbler().getNibblerDir()
				.setIavToken(api.getCustomerIavToken(r.getLocationHeader()).getToken());
		return r.getLocationHeader();
	}

	public String getIAVToken(String id) throws ApiException {
		CustomersApi api = new CustomersApi(getApiClient());
		return api.getCustomerIavToken(id).getToken();
	}

	public void createCustomer(Nibbler nibbler) throws UnknownHostException, ApiException {
		CustomersApi api = new CustomersApi(getApiClient());
		CreateCustomer customer = new CreateCustomer();
		customer.setFirstName(nibbler.getFirstName());
		customer.setLastName(nibbler.getLastName());
		customer.setEmail(new Random().nextInt()+ nibbler.getEmail());
		customer.setType("personal");
		customer.setAddress1(nibbler.getAddressLine1());
		customer.setAddress2(nibbler.getAddressLine2());
		customer.setState(nibbler.getState().toUpperCase().substring(0, 2));
		customer.setCity(nibbler.getCity());
		customer.setPhone(nibbler.getPhone());
		customer.setDateOfBirth(nibbler.getDateOfBirth());
		customer.setPostalCode(nibbler.getZip().toString());
		customer.setSsn(nibbler.getSsn());
		customer.setIpAddress(InetAddress.getLocalHost().getHostAddress());
		Unit$ r = api.create(customer);
		nibbler.getNibblerDir().setIavToken(api.getCustomerIavToken(r.getLocationHeader()).getToken());
		nibbler.getNibblerDir().setLocationHeader(r.getLocationHeader());
	}


	public String createAccount(NibblerAccount nibblerAccount, String id) throws ApiException {
		FundingsourcesApi api = new FundingsourcesApi(getApiClient());
		CreateFundingSourceRequest fundingSourceRequest = new CreateFundingSourceRequest();
		fundingSourceRequest.setAccountNumber(nibblerAccount.getNumber());
		if (!StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod")) {
			fundingSourceRequest.setRoutingNumber("222222226");
		} else {
			fundingSourceRequest.setRoutingNumber(nibblerAccount.getNumber().substring(0, 9));
		}
		fundingSourceRequest.setType("checking".equals(nibblerAccount.getAccountType().getCode())
				? nibblerAccount.getAccountType().getCode() : "savings");
		fundingSourceRequest.setName(nibblerAccount.getName());
		FundingSource fundingSource = api.createCustomerFundingSource(fundingSourceRequest, id);
		return fundingSource.getId();
	}

	private static ApiClient getApiClient() throws ApiException {
		if (client == null) {
			client = new ApiClient();
			client.setBasePath(Config.BASE_URL);
			client.setAccessToken("vlXesFMo3xLIsgyTlLDaWQsgWYrVWfSxpT77XOtlRfYCYXFsoJ");
		}
		return client;
	}

	public static void main(String[] a) throws ApiException {
		DwollaClient dw=new DwollaClient();
		TransfersApi api=new TransfersApi(getApiClient());
//		Transfer transfer=api.byId("9f33f3b6-193f-e611-80e4-0aa34a9b2388");
//		System.err.println(transfer);
//		FundingSource fs= api.id("e795beea-ce5a-40e6-a0a2-cf9e48075070");
//		System.out.println(fs.getEmbedded());
		//"https://api-uat.dwolla.com/funding-sources/1c2245e2-a1fc-41d7-b668-a7a22e5d9361"
		//"https://api-uat.dwolla.com/funding-sources/560bb6e3-49fd-4b2b-a66f-ff838c529dfd"
		//dw.transfer("https://api-uat.dwolla.com/funding-sources/1c2245e2-a1fc-41d7-b668-a7a22e5d9361", "https://api-uat.dwolla.com/funding-sources/560bb6e3-49fd-4b2b-a66f-ff838c529dfd", "1000", null);
		dw.statusTransfer("1a810d90-47eb-4039-8277-4f0d501aeac3");
		//dw.payFee("https://api-uat.dwolla.com/funding-sources/e795beea-ce5a-40e6-a0a2-cf9e48075070", "10", null);
		//System.out.println(fundingSource);
	}

	public String transfer(String from, String to, String value, NibblerAccount nibblerAccount) throws ApiException {
//		logger.info("transfer ==> from " + from + " to =>" + to + " value =>" + value + " person=>"
//				+ nibblerAccount.getNibbler().getLastName());
		TransfersApi api = new TransfersApi(getApiClient());
		TransferRequestBody transfertRequestBody = new TransferRequestBody();
		Amount amount = new Amount();
		amount.setCurrency("USD");
		amount.setValue(value);
		transfertRequestBody.setAmount(amount);
		Map<String, HalLink> links = new HashMap<String, HalLink>();
		HalLink destination = new HalLink();
		FundingsourcesApi fundingsourcesApi=new FundingsourcesApi(getApiClient());
		FundingSource fs=fundingsourcesApi.id(to);
		destination.setHref(fs.getLinks().get("customer").getHref());
		HalLink source = new HalLink();
		source.setHref(from);
		links.put("destination", destination);
		links.put("source", source);
		transfertRequestBody.setLinks(links);
		Unit$ r=api.create(transfertRequestBody);
		logger.info("transfer success ==> "+r.getLocationHeader());
		return r.getLocationHeader();
	}
	
	public TransferListResponse statusTransfer(String id) throws ApiException {
//		logger.info("transfer ==> from " + from + " to =>" + to + " value =>" + value + " person=>"
//				+ nibblerAccount.getNibbler().getLastName());
		TransfersApi api = new TransfersApi(getApiClient());
		TransferListResponse list =api.getCustomerTransfers(id, 10, 0);
		System.out.println("detail transfer "+list);
		return list;
		//list.fo
		//logger.info("transfer success ==> "+transfer.getStatus()+" "+transfer.getAmount()+" "+transfer.getCreated());
		
	}

//	public String payFee(String from, String value, NibblerAccount nibblerAccount) throws ApiException {
//		logger.info("transfer ==> from " + from + " value =>" + value + " person=>"
//				+ nibblerAccount.getNibbler().getLastName());
//		TransfersApi api = new TransfersApi(getApiClient());
//		TransferRequestBody transfertRequestBody = new TransferRequestBody();
//		Amount amount = new Amount();
//		amount.setCurrency("USD");
//		amount.setValue(value);
//		transfertRequestBody.setAmount(amount);
//		Map<String, HalLink> links = new HashMap<String, HalLink>();
//		HalLink destination = new HalLink();
//		FundingsourcesApi fundingsourcesApi=new FundingsourcesApi(getApiClient());
//		FundingSource fs=fundingsourcesApi.id(FUNDING_SOURCE);
//		destination.setHref(fs.getLinks().get("account").getHref());
//		HalLink source = new HalLink();
//		source.setHref(from);
//		links.put("destination", destination);
//		links.put("source", source);
//		transfertRequestBody.setLinks(links);
//		Unit$ r=api.create(transfertRequestBody);
//		logger.info(r.getLocationHeader());
//		return r.getLocationHeader();
//	}

	public BigDecimal getBalance(String id) throws ApiException {
		FundingsourcesApi fundingsourcesApi = new FundingsourcesApi(getApiClient());
		FundingSource fundingSource = fundingsourcesApi.id(id);
		return fundingSource.getBalance();
	}

	public String getFundingSource() {
		return fundingSource;
	}
	
	

}