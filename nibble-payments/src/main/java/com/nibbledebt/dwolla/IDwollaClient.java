package com.nibbledebt.dwolla;

import java.math.BigDecimal;
import java.net.UnknownHostException;

import com.nibbledebt.core.data.model.NibblerAccount;

import io.swagger.client.ApiException;

public interface IDwollaClient {
	public String createCustomer(NibblerAccount nibblerAccount) throws UnknownHostException, ApiException;

	public String createAccount(NibblerAccount nibblerAccount, String id) throws ApiException;
	
	public boolean transfer(String from,String to,String value,NibblerAccount nibblerAccount) throws ApiException;
	
	public BigDecimal getBalance(String id) throws ApiException;
	
	public boolean transfer(String from,String value,NibblerAccount nibblerAccount) throws ApiException;
}