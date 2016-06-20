package com.nibbledebt.dwolla;

import java.math.BigDecimal;
import java.net.UnknownHostException;

import com.nibbledebt.core.data.model.Nibbler;
import com.nibbledebt.core.data.model.NibblerAccount;

import io.swagger.client.ApiException;

public interface IDwollaClient {
	public void createCustomer(Nibbler nibbler) throws UnknownHostException, ApiException;
	
	public String createCustomer(NibblerAccount nibblerAccount) throws UnknownHostException, ApiException;

	public String createAccount(NibblerAccount nibblerAccount, String id) throws ApiException;
	
	public boolean transfer(String from,String to,String value,NibblerAccount nibblerAccount) throws ApiException;
	
	public BigDecimal getBalance(String id) throws ApiException;
	
	public boolean payFee(String from,String value,NibblerAccount nibblerAccount) throws ApiException;
	
	public String getIAVToken(String id) throws ApiException;
}