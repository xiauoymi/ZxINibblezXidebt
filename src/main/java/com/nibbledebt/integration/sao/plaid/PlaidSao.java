/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.sao.plaid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.common.logging.LogLevel;
import com.nibbledebt.common.logging.Loggable;
import com.nibbledebt.integration.model.plaid.Balance;
import com.nibbledebt.integration.model.plaid.Institution;
import com.nibbledebt.integration.model.plaid.LinkResponse;
import com.nibbledebt.integration.model.plaid.MfaResponse;
import com.nibbledebt.integration.model.plaid.Options;
import com.nibbledebt.integration.model.plaid.TransactionsResponse;
import com.nibbledebt.integration.model.plaid.UpgradeResponse;
import com.nibbledebt.integration.sao.IAccountSao;

/**
 * @author ralam1
 *
 */
@Component
public class PlaidSao implements IAccountSao{
	@Value("${plaid.url}")
	private String plaidUrl;
		
	@Value("${plaid.client.id}")
	private String cliendId;
	
	@Value("${plaid.secret}")
	private String secret;
	
	@Autowired
	private WebClient plaidConnectClient;
	
	@Autowired
	private WebClient plaidConnectStepClient;
	
	@Autowired
	private WebClient plaidAuthClient;
	
	@Autowired
	private WebClient plaidAuthStepClient;
	
	@Autowired
	private WebClient plaidBalanceClient;
	
	@Autowired
	private WebClient plaidUpgradeClient;
	
	@Autowired
	private WebClient plaidInstClient;
	
	@Autowired
	private WebClient plaidConnectGetClient;
	
	
	private ObjectMapper jsonMapper = new ObjectMapper();
	
	
	@Loggable(logLevel=LogLevel.DEBUG)
	public List<Institution> getSupportedInstitutions(){
		WebClient client = WebClient.fromClient(plaidInstClient);
	     client = client.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON);
	   
	     return new ArrayList<Institution>(Arrays.asList(client.get(Institution[].class)));
	}
	
	@Loggable(logLevel=LogLevel.DEBUG)
	public Institution getInstitution(String id){
		 WebClient client = WebClient.fromClient(plaidInstClient);
	     client = client.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).path("/"+id);
	   
	     return client.get(Institution.class);
	}
	
	@Loggable(logLevel=LogLevel.DEBUG)
	private UpgradeResponse upgrade(String accessToken) throws ServiceException{
		WebClient client = WebClient.fromClient(plaidUpgradeClient);
	     client = client.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
	    		 	.query("client_id", cliendId)
					.query("secret", secret)
					.query("access_token", accessToken)
					.query("upgrade_to", "connect");
	     
	     return client.post(null, UpgradeResponse.class);
	    
	}
	
	
	
	@Loggable(logLevel=LogLevel.DEBUG)
	public LinkResponse linkAccount(String username, String password, String pin, Institution inst) throws ServiceException{
		if(inst.getProducts().contains("auth")){
	    	LinkResponse response = auth(username, password, pin, inst);
	    	if(inst.getProducts().contains("connect")){
				upgrade(response.getAccessToken());	    		
	    	}
	    	return response;
	    }else if(inst.getProducts().contains("connect")){
	    	return connect(username, password, pin, inst);
	    }else{
	    	throw new ServiceException("One of Auth or Connect must be supported.");
	    }
	    
	}
	
	@Loggable(logLevel=LogLevel.DEBUG)
	public MfaResponse linkAccountMfa(String username, String password, String pin, Institution inst) throws ServiceException{
		if(inst.getProducts().contains("auth")){
	    	MfaResponse response = authMfa(username, password, pin, inst);
	    	if(inst.getProducts().contains("connect")){
				upgrade(response.getAccessToken());	    		
	    	}
	    	return response;
	    }else if(inst.getProducts().contains("connect")){
	    	return connectMfa(username, password, pin, inst);
	    }else{
	    	throw new ServiceException("One of Auth or Connect must be supported.");
	    }	 	 
	    
	}
	
	@Loggable(logLevel=LogLevel.DEBUG)
	public LinkResponse submitMfaResponse(String accessToken, String mfa, Institution inst) throws ServiceException{
		if(inst.getProducts().contains("auth")){
	    	WebClient client = WebClient.fromClient(plaidAuthStepClient);
		     try {
				client = client.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
						 	.query("client_id", cliendId)
							.query("secret", secret)
							.query("access_token", accessToken)
							.query("mfa", mfa)
							.query("options", jsonMapper.writeValueAsString(new Options(true)));
				return client.post(null, LinkResponse.class);	
			} catch (JsonProcessingException e) {
				throw new ServiceException(e);
			}
	    }else if(inst.getProducts().contains("connect")){
	    	WebClient client = WebClient.fromClient(plaidConnectStepClient);
		     try {
				client = client.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
						 	.query("client_id", cliendId)
							.query("secret", secret)
							.query("access_token", accessToken)
							.query("mfa", mfa)
							.query("options", jsonMapper.writeValueAsString(new Options(true)));
				return client.post(null, LinkResponse.class);	
			} catch (JsonProcessingException e) {
				throw new ServiceException(e);
			}
	    }else{
	    	throw new ServiceException("One of Auth or Connect must be supported.");
	    }
	}
	
	@Loggable(logLevel=LogLevel.DEBUG)
	public MfaResponse initiateMfaSend(String accessToken, String sendMethod) throws ServiceException{
		WebClient client = WebClient.fromClient(plaidAuthStepClient);
	     client = client.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
	    		 	.query("client_id", cliendId)
					.query("secret", secret)
					.query("access_token", accessToken)
					.query("options", sendMethod);
	     
	     return client.post(null, MfaResponse.class);		 
	    
	}

	@Override
	public TransactionsResponse retrieveTransactions(String accessToken, String externalAccountId, String since) throws ServiceException {
		 WebClient client = WebClient.fromClient(plaidConnectGetClient);		 
		 client = client.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
	    		 	.query("client_id", cliendId)
					.query("secret", secret)
					.query("access_token", accessToken)
					.query("account", externalAccountId);
	     if(since != null) client.query("options", "{\"gte\":\""+since+"\"}");
	     return client.post(null, TransactionsResponse.class);	
	}

	@Override
	public List<Balance> retrieveBalances(String accessToken) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Loggable(logLevel=LogLevel.DEBUG)
	private LinkResponse connect(String username, String password, String pin, Institution inst) throws ServiceException{
		WebClient client = WebClient.fromClient(plaidConnectClient);
	     try {
			client = client.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
					 	.query("client_id", cliendId)
						.query("secret", secret)
						.query("username", username)
						.query("password", password)
						.query("type", inst.getType())
						.query("options", jsonMapper.writeValueAsString(new Options(null, true)));
			if(pin!=null) client.query("pin", pin);
		} catch (JsonProcessingException e) {
			throw new ServiceException(e);
		}
	     
	    return client.post(null, LinkResponse.class);
	    
	}
	
	@Loggable(logLevel=LogLevel.DEBUG)
	private LinkResponse auth(String username, String password, String pin, Institution inst) throws ServiceException{
		WebClient client = WebClient.fromClient(plaidAuthClient);
	     try {
			client = client.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
					 	.query("client_id", cliendId)
						.query("secret", secret)
						.query("username", username)
						.query("password", password)
						.query("type", inst.getType())
						.query("options", jsonMapper.writeValueAsString(new Options(null, true)));
			if(pin!=null) client.query("pin", pin);
		} catch (JsonProcessingException e) {
			throw new ServiceException(e);
		}
	     
	    return client.post(null, LinkResponse.class);
	    
	}
	
	@Loggable(logLevel=LogLevel.DEBUG)
	private MfaResponse connectMfa(String username, String password, String pin, Institution inst) throws ServiceException{
		 WebClient client = WebClient.fromClient(plaidConnectClient);
	     try {
			client = client.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
					 	.query("client_id", cliendId)
						.query("secret", secret)
						.query("username", username)
						.query("password", password)
						.query("type", inst.getType())
						.query("options", jsonMapper.writeValueAsString(new Options(null, true)));
			if(pin!=null) client.query("pin", pin);
		} catch (JsonProcessingException e) {
			throw new ServiceException(e);
		}
	     
	    return client.post(null, MfaResponse.class);
	    
	}
	
	@Loggable(logLevel=LogLevel.DEBUG)
	private MfaResponse authMfa(String username, String password, String pin, Institution inst) throws ServiceException{
		WebClient client = WebClient.fromClient(plaidAuthClient);
	     try {
			client = client.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
					 	.query("client_id", cliendId)
						.query("secret", secret)
						.query("username", username)
						.query("password", password)
						.query("type", inst.getType())
						.query("options", jsonMapper.writeValueAsString(new Options(null, true)));
			if(pin!=null) client.query("pin", pin);
		} catch (JsonProcessingException e) {
			throw new ServiceException(e);
		}
	     
	    return client.post(null, MfaResponse.class);
	    
	}
}
