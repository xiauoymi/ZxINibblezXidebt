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
import com.nibbledebt.core.processor.UsersProcessor;
import com.nibbledebt.domain.model.Contributor;
import com.nibbledebt.domain.model.Transaction;
import com.nibbledebt.domain.model.TransactionSummary;
import com.nibbledebt.domain.model.account.Account;

/**
 * @author ralam
 *
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class AccountMgmtREST extends AbstractREST {
	@Autowired
	private AccountsProcessor accountsProcessor;
	
	@Autowired
	private UsersProcessor usersProcessor;
	
	@Autowired
	private TransactionProcessor trxsProcessor;
	
	@GET
	@Path("/contributors")
	@Loggable(logLevel=LogLevel.INFO)
	@PreAuthorize("hasRole('receiver')")
	public List<Contributor> getContributors() throws ProcessingException, RepositoryException{
		List<Contributor> contributors = usersProcessor.retrieveContributors(getCurrentUser());
		for(Contributor contr : contributors){
			contr.setSummary(trxsProcessor.getWeeklyTrxSummary(contr.getUsername()));
		}
		return contributors;
	}
	
	@GET
	@Path("/isfirstlogin")
	@Loggable(logLevel=LogLevel.INFO)
	@PreAuthorize("hasRole('nibbler_level_1')")
	public Boolean isFirstLogin() throws ProcessingException, RepositoryException{
		return ((MemberDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIsFirstLogin();
	}
	
	@GET
	@Path("/roundupaccounts")
	@Loggable(logLevel=LogLevel.INFO)
	@PreAuthorize("hasRole('nibbler_level_1')")
	public List<Account> getRoundupAccounts() throws ProcessingException, RepositoryException{
		return accountsProcessor.getRoundupAccounts(getCurrentUser());
	}
	
	@GET
	@Path("/loanaccounts")
	@Loggable(logLevel=LogLevel.INFO)
	@PreAuthorize("hasRole('nibbler_level_1')")
	public List<Account> getLoanAccounts() throws ProcessingException, RepositoryException{
		return accountsProcessor.getLoanAccounts(getCurrentUser());
	}
	
	@GET
	@Path("/acctrxs/{accountId}")
	@Loggable(logLevel=LogLevel.INFO)
	@PreAuthorize("hasRole('nibbler_level_1')")
	public List<Transaction> getAccountTransactions(@PathParam("accountId") Long accountId) throws ProcessingException, RepositoryException{
		return trxsProcessor.retrieveTransactions(accountId);
	}
	
	@GET
	@Path("/weeksummary")
	@Loggable(logLevel=LogLevel.INFO)
	@PreAuthorize("hasRole('nibbler_level_1')")
	public TransactionSummary getWeeklySummary() throws ProcessingException, RepositoryException{
		return trxsProcessor.getWeeklyTrxSummary(getCurrentUser());
	}
	
	@POST
	@Path("/roundupaccounts")
	@Consumes(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@PreAuthorize("hasRole('nibbler_level_1')")
	public void updateRoundingAccts(List<Long> acctountIds) throws ProcessingException, RepositoryException{
		accountsProcessor.updateRoundingAccounts(getCurrentUser(), acctountIds);
	}
	
	@POST
	@Path("/loanaccounts")
	@Consumes(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@PreAuthorize("hasRole('nibbler_level_1')")
	public void updateLoanAcct(Long acctountId) throws ProcessingException, RepositoryException{
		accountsProcessor.updateLoanAccount(getCurrentUser(), acctountId);
	}
}
