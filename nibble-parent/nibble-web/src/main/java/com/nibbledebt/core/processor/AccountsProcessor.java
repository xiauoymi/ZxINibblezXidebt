/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nibbledebt.core.data.dao.INibblerAccountDao;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.NibblerAccount;
import com.nibbledebt.domain.model.account.Account;

/**
 * @author ralam
 *
 */
@Component
public class AccountsProcessor extends AbstractProcessor {
	@Autowired
	private INibblerAccountDao nibblerAcctDao;
		
	@Transactional(readOnly=true, isolation=Isolation.REPEATABLE_READ)
	public List<Account> getRoundupAccounts(String username) throws RepositoryException{
		List<NibblerAccount> accts = nibblerAcctDao.find(username);
		List<Account> webAccts = new ArrayList<>();
		for(NibblerAccount acct : accts){
			if(!StringUtils.equalsIgnoreCase(acct.getAccountType().getCode(), "student_loan")){
				Account wacct = new Account();
				wacct.setAccountId(acct.getId());
				wacct.setAccountNumber(acct.getNumberMask());
				wacct.setAccountType(acct.getAccountType().getCode());
				wacct.setAvailable(acct.getBalances()!=null ? acct.getBalances().get(0).getAvailable().toString() : BigDecimal.ZERO.toString());
				wacct.setBalance(acct.getBalances()!=null ? acct.getBalances().get(0).getCurrent().toString() : BigDecimal.ZERO.toString());
				wacct.setInstitutionName(acct.getInstitution().getName());
				wacct.setAccountExternalId(acct.getExternalId());
				webAccts.add(wacct);
			}
		}
		
		return webAccts;
	}
	
	@Transactional(readOnly=true, isolation=Isolation.REPEATABLE_READ)
	public List<Account> getLoanAccounts(String username) throws RepositoryException{
		List<NibblerAccount> accts = nibblerAcctDao.find(username);
		List<Account> webAccts = new ArrayList<>();
		for(NibblerAccount acct : accts){
			if(StringUtils.equalsIgnoreCase(acct.getAccountType().getCode(), "student_loan")){
				Account wacct = new Account();
				wacct.setAccountId(acct.getId());
				wacct.setAccountNumber(acct.getNumberMask());
				wacct.setAccountType(acct.getAccountType().getCode());
				wacct.setAvailable(acct.getBalances()!=null ? acct.getBalances().get(0).getAvailable().toString() : BigDecimal.ZERO.toString());
				wacct.setBalance(acct.getBalances()!=null ? acct.getBalances().get(0).getCurrent().toString() : BigDecimal.ZERO.toString());
				wacct.setInstitutionName(acct.getInstitution().getName());
				wacct.setAccountExternalId(acct.getExternalId());
				webAccts.add(wacct);
			}
		}
		
		return webAccts;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	public void updateRoundingAccounts(String username, List<Long> accountIds) throws RepositoryException{
		List<NibblerAccount> accts = nibblerAcctDao.find(username);
		for(NibblerAccount acct : accts){
			if(accountIds.contains(acct.getId())){
				acct.setUseForRounding(true);
			}else{
				acct.setUseForRounding(false);
			}
			setUpdated(acct, username);
			nibblerAcctDao.update(acct);
		}
	}
}
