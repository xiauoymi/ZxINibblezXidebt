package com.nibbledebt.dwolla;

import java.math.BigDecimal;
import java.net.UnknownHostException;

import com.nibbledebt.core.data.model.Nibbler;
import com.nibbledebt.core.data.model.NibblerAccount;

import io.swagger.client.ApiException;
import io.swagger.client.model.TransferListResponse;

public interface IDwollaClient {
	public void createCustomer(Nibbler nibbler) throws UnknownHostException, ApiException;
	
	public String createCustomer(NibblerAccount nibblerAccount) throws UnknownHostException, ApiException;

	public String createAccount(NibblerAccount nibblerAccount, String id) throws ApiException;
	
	public String transfer(String from,String to,String value,NibblerAccount nibblerAccount) throws ApiException;
	
	public BigDecimal getBalance(String id) throws ApiException;
	
//	public String payFee(String from,String value,NibblerAccount nibblerAccount) throws ApiException;
	
	public String getIAVToken(String id) throws ApiException;
	
	String getFundingSource();
	TransferListResponse statusTransfer(String id) throws ApiException;
}