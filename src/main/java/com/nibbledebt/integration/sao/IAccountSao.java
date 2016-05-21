/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.sao;

import java.util.List;

import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.integration.model.plaid.Balance;
import com.nibbledebt.integration.model.plaid.Institution;
import com.nibbledebt.integration.model.plaid.LinkResponse;
import com.nibbledebt.integration.model.plaid.MfaResponse;
import com.nibbledebt.integration.model.plaid.TransactionsResponse;

/**
 * @author ralam
 *
 */
public interface IAccountSao {
	public List<Institution> getSupportedInstitutions() throws ServiceException;

	public Institution getInstitution(String institutionId) throws ServiceException;
	
	public LinkResponse linkAccount(String username, String password, String pin, Institution inst) throws ServiceException;
	
	public MfaResponse linkAccountMfa(String username, String password, String pin, Institution inst) throws ServiceException;
	
	public TransactionsResponse retrieveTransactions(String accessToken, String externalAccountId, String since) throws ServiceException;
	
	public List<Balance> retrieveBalances(String accessToken) throws ServiceException;
	
	public LinkResponse submitMfaResponse(String accessToken, String mfa, Institution inst) throws ServiceException;
	
	public MfaResponse initiateMfaSend(String accessToken, String sendMethod) throws ServiceException;
}
