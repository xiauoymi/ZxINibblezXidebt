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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.nibbledebt.domain.model.account.AccountDetail;

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
	public LoanSummary getLoanSummary(String username) throws ProcessingException, RepositoryException{
		LoanSummary summary = new LoanSummary();
		List<Account> accts = getLoanAccounts(username);
		
		for(Account acct : accts){
			Loan loan = new Loan();
			loan.setInterestRate(new BigDecimal(acct.getDetail().getInterestRate()));
			loan.setMinimumPayment(new BigDecimal(acct.getDetail().getPaymentMinAmount()));
			loan.setPrincipalBalance(new BigDecimal(acct.getDetail().getPrincipalBalance()));
			loan.setPayments(acct.getCredits());
			loan.setFirstDayAtNibble(acct.getCreatedTs());
			loan.setCurrentCumulativeInterest(new BigDecimal(acct.getDetail().getYtdInterestPaid()));
			summary.getLoans().add(loan);
			

			Map<LocalDate, BigDecimal> paymentMap = new HashMap<>();
			Double totalPayments = 0d;
			// convert all payments to a map of month-year and value
			if(loan.getPayments()!=null){
				for(Payment payment : loan.getPayments()){					
					LocalDate paymentDate = payment.getInitiatedTs().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					if(paymentMap.get(paymentDate) == null) paymentMap.put(paymentDate, payment.getAmount());
					else paymentMap.put(paymentDate, paymentMap.get(paymentDate).add(payment.getAmount()));
					totalPayments += payment.getAmount().doubleValue();
				}
				
			}
			Double averagePayment = totalPayments/(loan.getPayments().size()/4);
			
			int monthIteration = 1;
			Double originalAccruedMonthlyInterest = 0d;
			Double currentAccruedMonthlyInterest = 0d;
			Double projectedAccruedMonthlyInterest = 0d;
			
			Double originalPrincipalBalance = loan.getPrincipalBalance().doubleValue();
			Double currentPrincipalBalance = loan.getPrincipalBalance().doubleValue();
			Double projectedPrincipalBalance = loan.getPrincipalBalance().doubleValue();
			Double originalInterest = loan.getOriginalCumulativeInterest()!=null ? loan.getOriginalCumulativeInterest().doubleValue() : 0d;
			Double currentInterest = loan.getCurrentCumulativeInterest()!=null ? loan.getCurrentCumulativeInterest().doubleValue() : 0d;
			Double projectedInterest = loan.getProjectedCumulativeInterest()!=null ? loan.getProjectedCumulativeInterest().doubleValue() : 0d;
			
			if(loan.getPrincipalBalance().doubleValue() > loan.getMinimumPayment().doubleValue()) {
				for (LocalDate date = loan.getFirstDayAtNibble().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;  date = date.plusDays(1)) {
					originalAccruedMonthlyInterest += (originalPrincipalBalance*((loan.getInterestRate().doubleValue()/date.lengthOfYear())/100d));
					
					if(paymentMap.get(date) != null) currentPrincipalBalance -= paymentMap.get(date).doubleValue();
					currentAccruedMonthlyInterest += (currentPrincipalBalance*((loan.getInterestRate().doubleValue()/date.lengthOfYear())/100d));
					
					if(averagePayment > 0){
						projectedPrincipalBalance -= averagePayment;
					}else{
						projectedPrincipalBalance -= 30;
					}

					projectedAccruedMonthlyInterest += (projectedPrincipalBalance*((loan.getInterestRate().doubleValue()/date.lengthOfYear())/100d));
					
					if(date.getDayOfMonth() == date.lengthOfMonth()){
						
						if(originalPrincipalBalance+originalAccruedMonthlyInterest > loan.getMinimumPayment().doubleValue()){
							originalPrincipalBalance -= (loan.getMinimumPayment().doubleValue()-originalAccruedMonthlyInterest);
							loan.getOriginalAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), BigDecimal.valueOf(originalAccruedMonthlyInterest), BigDecimal.valueOf(loan.getMinimumPayment().doubleValue()-originalAccruedMonthlyInterest), BigDecimal.valueOf(originalPrincipalBalance)));
						}else{
							loan.getOriginalAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), BigDecimal.valueOf(originalAccruedMonthlyInterest), BigDecimal.valueOf(originalPrincipalBalance), BigDecimal.ZERO));
							originalPrincipalBalance = 0d;
						}
						
						if(currentPrincipalBalance+currentAccruedMonthlyInterest > loan.getMinimumPayment().doubleValue()){
							currentPrincipalBalance -= (loan.getMinimumPayment().doubleValue()-currentAccruedMonthlyInterest);
							loan.getCurrentProjectedAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), BigDecimal.valueOf(currentAccruedMonthlyInterest), BigDecimal.valueOf(loan.getMinimumPayment().doubleValue()-currentAccruedMonthlyInterest), BigDecimal.valueOf(currentPrincipalBalance)));
						}else{
							if(currentPrincipalBalance!=0){
								loan.getCurrentProjectedAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), BigDecimal.valueOf(currentAccruedMonthlyInterest), BigDecimal.valueOf(currentPrincipalBalance), BigDecimal.ZERO));
								currentPrincipalBalance = 0d;
							}
						}
						
						if(projectedPrincipalBalance+projectedAccruedMonthlyInterest > loan.getMinimumPayment().doubleValue()){
							projectedPrincipalBalance -= (loan.getMinimumPayment().doubleValue()-projectedAccruedMonthlyInterest);
							loan.getProjectedAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), BigDecimal.valueOf(projectedAccruedMonthlyInterest), BigDecimal.valueOf(loan.getMinimumPayment().doubleValue()-projectedAccruedMonthlyInterest), BigDecimal.valueOf(projectedPrincipalBalance)));
						}else{
							if(projectedPrincipalBalance!=0){
								loan.getProjectedAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), BigDecimal.valueOf(projectedAccruedMonthlyInterest), BigDecimal.valueOf(projectedPrincipalBalance), BigDecimal.ZERO));
								projectedPrincipalBalance = 0d;
							}
						}
						
						if(originalPrincipalBalance == 0 && currentPrincipalBalance == 0) break;
						
						originalInterest += originalAccruedMonthlyInterest;
						currentInterest += currentAccruedMonthlyInterest;
						projectedInterest += projectedAccruedMonthlyInterest;
						currentAccruedMonthlyInterest = originalAccruedMonthlyInterest = projectedAccruedMonthlyInterest = 0d;
						monthIteration++;
						
					}
				
				}
			}

			loan.setOriginalCumulativeInterest(BigDecimal.valueOf(originalInterest));
			loan.setCurrentCumulativeInterest(BigDecimal.valueOf(currentInterest));
			loan.setProjectedCumulativeInterest(BigDecimal.valueOf(currentInterest));
			loan.setOriginalPayoffDuration(loan.getOriginalAmortization().size());
			loan.setCurrentPayoffDuration(loan.getCurrentProjectedAmortization().size());
			loan.setProjectedPayoffDuration(loan.getCurrentProjectedAmortization().size());
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
				wacct.setAvailable((acct.getBalances()!=null && !acct.getBalances().isEmpty() && acct.getBalances().get(0).getAvailable()!=null) ? acct.getBalances().get(0).getAvailable().toString() : BigDecimal.ZERO.toString());
				wacct.setBalance((acct.getBalances()!=null && !acct.getBalances().isEmpty() && acct.getBalances().get(0).getCurrent()!=null) ? acct.getBalances().get(0).getCurrent().toString() : BigDecimal.ZERO.toString());
				wacct.setInstitutionName(acct.getInstitution().getSupportedInstitution().getDisplayName());
				wacct.setAccountExternalId(acct.getExternalId());
				wacct.setUseForPayoff(acct.getUseForpayoff());
				wacct.setAccountName(acct.getName());
				wacct.setCreatedTs(acct.getCreatedTs());
				
				AccountDetail detail = new AccountDetail();
				detail.setPayoffAmount((acct.getBalances()!=null && !acct.getBalances().isEmpty() && acct.getBalances().get(0).getPayoffAmount()!=null) ? acct.getBalances().get(0).getPayoffAmount().toString() : BigDecimal.ZERO.toString());
				detail.setInterestRate((acct.getBalances()!=null && !acct.getBalances().isEmpty() && acct.getBalances().get(0).getInterestRate()!=null) ? acct.getBalances().get(0).getInterestRate().toString() : BigDecimal.ZERO.toString());
				detail.setPrincipalBalance((acct.getBalances()!=null && !acct.getBalances().isEmpty() && acct.getBalances().get(0).getPrincipalBalance()!=null) ? acct.getBalances().get(0).getPrincipalBalance().toString() : BigDecimal.ZERO.toString());
				detail.setYtdInterestPaid((acct.getBalances()!=null && !acct.getBalances().isEmpty() && acct.getBalances().get(0).getYtdInterestPaid()!=null) ? acct.getBalances().get(0).getYtdInterestPaid().toString() : BigDecimal.ZERO.toString());
				detail.setYtdPrincipalPaid((acct.getBalances()!=null && !acct.getBalances().isEmpty() && acct.getBalances().get(0).getYtdPrincipalPaid()!=null) ? acct.getBalances().get(0).getYtdPrincipalPaid().toString() : BigDecimal.ZERO.toString());
				detail.setPaymentMinAmount((acct.getBalances()!=null && !acct.getBalances().isEmpty() && acct.getBalances().get(0).getPaymentMinAmount()!=null) ? acct.getBalances().get(0).getPaymentMinAmount().toString() : BigDecimal.ZERO.toString());
				wacct.setDetail(detail);
				
				for(PaymentActivity pa : acct.getCreditActivity()){
					wacct.getCredits().add(new Payment(pa.getAmount(), pa.getInitiatedTs(), pa.getCompletedTs(), pa.getAuthorizationCode()));
				}
				
				for(PaymentActivity pa : acct.getDebitActivity()){
					wacct.getDebits().add(new Payment(pa.getAmount(), pa.getInitiatedTs(), pa.getCompletedTs(), pa.getAuthorizationCode()));
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
