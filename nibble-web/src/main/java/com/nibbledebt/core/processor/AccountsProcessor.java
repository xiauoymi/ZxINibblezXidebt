/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.core.data.dao.INibblerAccountDao;
import com.nibbledebt.core.data.dao.IPaymentActivityDao;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.NibblerAccount;
import com.nibbledebt.core.data.model.PaymentActivity;
import com.nibbledebt.domain.model.AmortizationRecord;
import com.nibbledebt.domain.model.Loan;
import com.nibbledebt.domain.model.LoanSummary;
import com.nibbledebt.domain.model.Payment;
import com.nibbledebt.domain.model.account.Account;

/**
 * @author ralam
 *
 */
@Component
public class AccountsProcessor extends AbstractProcessor {
	@Resource
	private Environment env;
	
	@Autowired
	private INibblerAccountDao nibblerAcctDao;
	
	@Autowired
	private IPaymentActivityDao paymentActivityDao;
	
	@Transactional(readOnly=true)
	public LoanSummary getWeeklyLoanSummary(String username) throws ProcessingException, RepositoryException{
		LoanSummary summary = new LoanSummary();
		List<Account> accts = getLoanAccounts(username);
		
		for(Account acct : accts){
			Loan loan = new Loan();
			loan.setInterestRate(new BigDecimal(acct.getDetail().getInterestRate()));
			loan.setMinimumPayment(new BigDecimal(acct.getDetail().getPaymentMinAmount()));
			loan.setPrincipalBalance(new BigDecimal(acct.getDetail().getPrincipalBalance()));
			loan.setPayments(acct.getCredits());
			loan.setFirstDayAtNibble(acct.getCreatedTs());
			summary.getLoans().add(loan);
		}
		
		for(Loan loan : summary.getLoans()){
			int monthIteration = 1;
			// create payment map
			
			BigDecimal originalAccruedMonthlyInterest = BigDecimal.ZERO;
			BigDecimal currentAccruedMonthlyInterest = BigDecimal.ZERO;
			
			BigDecimal principalBalanceOriginal = loan.getPrincipalBalance();
			BigDecimal principalBalanceCurrent = loan.getPrincipalBalance();
			
			for (LocalDate date = LocalDate.now(ZoneId.systemDefault());  ; date = date.plusDays(1)) {
				if(loan.getPrincipalBalance().doubleValue() == 0) {
					break;
				}else{
					if(date.getDayOfMonth() == date.lengthOfMonth()){
						originalAccruedMonthlyInterest = originalAccruedMonthlyInterest.add((loan.getPrincipalBalance().multiply((loan.getInterestRate().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)))).divide(BigDecimal.valueOf(date.lengthOfYear()), 2, RoundingMode.HALF_UP));
						if(loan.getPrincipalBalance().add(originalAccruedMonthlyInterest).doubleValue() > loan.getMinimumPayment().doubleValue()){
							loan.setPrincipalBalance(loan.getPrincipalBalance().subtract(loan.getMinimumPayment().subtract(originalAccruedMonthlyInterest)));
							loan.getOriginalAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), originalAccruedMonthlyInterest, loan.getMinimumPayment().subtract(originalAccruedMonthlyInterest), loan.getPrincipalBalance()));
						}else{
							loan.getOriginalAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), originalAccruedMonthlyInterest, loan.getPrincipalBalance(), BigDecimal.ZERO));
							loan.setPrincipalBalance(BigDecimal.ZERO);
							
						}
						originalAccruedMonthlyInterest = BigDecimal.ZERO;
						monthIteration++;
					}else{
						originalAccruedMonthlyInterest = originalAccruedMonthlyInterest.add((loan.getPrincipalBalance().multiply((loan.getInterestRate().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)))).divide(BigDecimal.valueOf(date.lengthOfYear()), 2, RoundingMode.HALF_UP));
					}
				}
			}
		}
		
		return summary;
	}
		
	@Transactional(readOnly=true, isolation=Isolation.REPEATABLE_READ)
	public List<Account> getRoundupAccounts(String username) throws RepositoryException{
		List<NibblerAccount> accts = nibblerAcctDao.find(username);
		List<Account> webAccts = new ArrayList<>();
		for(NibblerAccount acct : accts){
			if(!StringUtils.equalsIgnoreCase(acct.getAccountType().getCode(), "student_loan") &&
					(StringUtils.equalsIgnoreCase(acct.getAccountType().getCode(), "checking") ||
							(StringUtils.containsIgnoreCase(acct.getAccountType().getCode(), "credit") && (StringUtils.containsIgnoreCase(acct.getAccountType().getCode(), "card"))))){
				Account wacct = new Account();
				wacct.setAccountId(acct.getId());
				wacct.setAccountNumber(acct.getNumberMask());
				wacct.setAccountType(acct.getAccountType().getCode());
				wacct.setAvailable((acct.getBalances()!=null && !acct.getBalances().isEmpty()) ? acct.getBalances().get(0).getAvailable().toString() : BigDecimal.ZERO.toString());
				wacct.setBalance((acct.getBalances()!=null && !acct.getBalances().isEmpty()) ? acct.getBalances().get(0).getCurrent().toString() : BigDecimal.ZERO.toString());
				wacct.setInstitutionName(acct.getInstitution().getSupportedInstitution().getDisplayName());
				wacct.setAccountExternalId(acct.getExternalId());
				wacct.setUseForRounding(acct.getUseForRounding());
				wacct.setAccountName(acct.getName());
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
			if(!StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod") ? (StringUtils.equalsIgnoreCase(acct.getAccountType().getCode(), "student_loan") || StringUtils.equalsIgnoreCase(acct.getAccountType().getCode(), "loan")) : StringUtils.equalsIgnoreCase(acct.getAccountType().getCode(), "student_loan")){
				Account wacct = new Account();
				wacct.setAccountId(acct.getId());
				wacct.setAccountNumber(acct.getNumberMask());
				wacct.setAccountType(acct.getAccountType().getCode());
				wacct.setAvailable(acct.getBalances()!=null ? acct.getBalances().get(0).getAvailable().toString() : BigDecimal.ZERO.toString());
				wacct.setBalance(acct.getBalances()!=null ? acct.getBalances().get(0).getCurrent().toString() : BigDecimal.ZERO.toString());
				wacct.setInstitutionName(acct.getInstitution().getSupportedInstitution().getDisplayName());
				wacct.setAccountExternalId(acct.getExternalId());
				wacct.setUseForPayoff(acct.getUseForpayoff());
				wacct.setAccountName(acct.getName());
				wacct.setCreatedTs(acct.getCreatedTs());
				for(PaymentActivity pa : acct.getCreditActivity()){
					wacct.getCredits().add(new Payment(pa.getAmount(), pa.getInitiatedTs(), pa.getCompletedTs(), pa.getAuthorization()));
				}
				
				for(PaymentActivity pa : acct.getDebitActivity()){
					wacct.getDebits().add(new Payment(pa.getAmount(), pa.getInitiatedTs(), pa.getCompletedTs(), pa.getAuthorization()));
				}
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

    @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
    public void updateRoundingAccount(String username, Long accountId, Boolean useForRoundup) throws RepositoryException {
        NibblerAccount acct = nibblerAcctDao.findByUserNameAndId(username, accountId);
        if (acct == null) {
            throw new RepositoryException("Account not found");
        }
        acct.setUseForRounding(useForRoundup);
        setUpdated(acct, username);
        nibblerAcctDao.update(acct);
    }
	
	
	
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor=RepositoryException.class)
	public void updateLoanAccount(String username, Long accountId) throws RepositoryException{
		NibblerAccount oldPayoffAccount = nibblerAcctDao.findByUseForPayoff(username);
		if(oldPayoffAccount!=null){
			oldPayoffAccount.setUseForpayoff(false);
			nibblerAcctDao.update(oldPayoffAccount);
		}
		NibblerAccount account = nibblerAcctDao.findOne(accountId);
		account.setUseForpayoff(true);
		nibblerAcctDao.update(account);
	}

}
