/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.nibbledebt.integration.model.DiscoverAccountsResponse;
import com.nibbledebt.integration.model.LoginField;
import com.nibbledebt.integration.sao.IIntegrationSao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nibbledebt.core.data.dao.INibblerAccountDao;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.NibblerAccount;
import com.nibbledebt.web.rest.model.Account;

/**
 * @author ralam
 *
 */
@Component
public class AccountsProcessor extends AbstractProcessor {
	@Autowired
	private INibblerAccountDao nibblerAcctDao;

    @Autowired
    @Qualifier("finicitySao")
    private IIntegrationSao integrationSao;


	
	@Transactional(readOnly=true, isolation=Isolation.REPEATABLE_READ)
	public List<Account> getAccounts() throws RepositoryException{
		List<NibblerAccount> accts = nibblerAcctDao.find(getCurrentUser());
		List<Account> webAccts = new ArrayList<>();
		for(NibblerAccount acct : accts){
			Account wacct = new Account();
			wacct.setAccountId(acct.getId());
			wacct.setAccountNumber(acct.getNumberMask());
			wacct.setAccountType(acct.getAccountType().getCode());
			wacct.setAvailable(acct.getBalances()!=null ? acct.getBalances().get(0).getAvailable() : BigDecimal.ZERO);
			wacct.setBalance(acct.getBalances()!=null ? acct.getBalances().get(0).getCurrent() : BigDecimal.ZERO);
			wacct.setInstitutionName(acct.getInstitution().getName());
			webAccts.add(wacct);
		}
		
		return webAccts;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	public void updateRoundingAccounts(List<Long> accountIds) throws RepositoryException{
		List<NibblerAccount> accts = nibblerAcctDao.find(getCurrentUser());
		for(NibblerAccount acct : accts){
			if(accountIds.contains(acct.getId())){
				acct.setUseForRounding(true);
			}else{
				acct.setUseForRounding(false);
			}
			setUpdated(acct, getCurrentUser());
			nibblerAcctDao.update(acct);
		}
	}

//    public DiscoverAccountsResponse getAccountsForLink(String institutionId,List<LoginField> fields) {
//        integrationSao.getAccounts(getCurrentUser())
//    }
}
