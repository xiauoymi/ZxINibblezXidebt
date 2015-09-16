/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.logging.LogLevel;
import com.nibbledebt.common.logging.Loggable;
import com.nibbledebt.common.security.MemberDetails;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.processor.AccountsProcessor;
import com.nibbledebt.core.processor.TransactionProcessor;
import com.nibbledebt.web.rest.model.Account;
import com.nibbledebt.web.rest.model.JsonListWrapper;
import com.nibbledebt.web.rest.model.TransactionSummary;

/**
 * @author ralam
 *
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class AccountMgmtREST {
	@Autowired
	private AccountsProcessor accountsProcessor;
	
	@Autowired
	private TransactionProcessor trxsProcessor;
	
	@GET
	@Path("/isfirstlogin")
	@Loggable(logLevel=LogLevel.INFO)
	@PreAuthorize("hasRole('nibbler_level_1')")
	public Boolean isFirstLogin() throws ProcessingException, RepositoryException{
		return ((MemberDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIsFirstLogin();
	}
	
	@GET
	@Path("/useraccounts")
	@Loggable(logLevel=LogLevel.INFO)
	@PreAuthorize("hasRole('nibbler_level_1')")
	public JsonListWrapper<Account> getUserAccounts() throws ProcessingException, RepositoryException{
		JsonListWrapper<Account> wrapper = new JsonListWrapper<>();
		wrapper.setItems(accountsProcessor.getAccounts());
		return wrapper;
	}
	
	@GET
	@Path("/acctrxs/{accountId}")
	@Loggable(logLevel=LogLevel.INFO)
	@PreAuthorize("hasRole('nibbler_level_1')")
	public JsonListWrapper<com.nibbledebt.web.rest.model.Transaction> getAccountTransactions(@PathParam("accountId") Long accountId) throws ProcessingException, RepositoryException{
		JsonListWrapper<com.nibbledebt.web.rest.model.Transaction> wrapper = new JsonListWrapper<>();
		wrapper.setItems(trxsProcessor.retrieveTransactions(accountId));
		return wrapper;
	}
	
	@GET
	@Path("/weeksummary")
	@Loggable(logLevel=LogLevel.INFO)
	@PreAuthorize("hasRole('nibbler_level_1')")
	public TransactionSummary getWeeklySummary() throws ProcessingException, RepositoryException{
		return trxsProcessor.getWeeklyTrxSummary();
	}
	
	@POST
	@Path("/updateroundaccts")
	@Consumes(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@PreAuthorize("hasRole('nibbler_level_1')")
	public void updateRoundingAccts(List<Long> acctountIds) throws ProcessingException, RepositoryException{
		accountsProcessor.updateRoundingAccounts(acctountIds);
	}
}
