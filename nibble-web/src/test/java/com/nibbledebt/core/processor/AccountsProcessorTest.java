/**
 * Copyright 2015-2017. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.nibbledebt.domain.model.AmortizationRecord;
import com.nibbledebt.domain.model.Loan;
import com.nibbledebt.domain.model.LoanSummary;
import com.nibbledebt.domain.model.Payment;

/**
 * @author Rocky Alam
 *
 */
public class AccountsProcessorTest {
	private LoanSummary lsummary;
	
	@Before
	public void init(){
		LocalDate date = LocalDate.now(ZoneId.systemDefault());
		
		lsummary = new LoanSummary();
		Loan loan1 = new Loan();
		loan1.setInterestRate(BigDecimal.valueOf(6.00));
		loan1.setMinimumPayment(BigDecimal.valueOf(111.02));
		loan1.setPrincipalBalance(BigDecimal.valueOf(10000));
		loan1.setPayments(Arrays.asList(new Payment[]{	new Payment(BigDecimal.valueOf(100d), Date.from(date.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(date.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()), ""), 
														new Payment(BigDecimal.valueOf(100d), Date.from(date.plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(date.plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant()), "")}));
		loan1.setFirstDayAtNibble(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		lsummary.getLoans().add(loan1);
	}

	@Test
	public void testLoanSummary() {
		BigDecimal totalPaid = new BigDecimal(100.00);
		BigDecimal weeklyAverage = new BigDecimal(10.00);
		
				
		for(Loan loan : lsummary.getLoans()){
			Map<String, BigDecimal> paymentMap = new HashMap<>();
			// convert all payments to a map of month-year and value
			for(Payment payment : loan.getPayments()){
				String paymentKey = new StringBuffer(payment.getInitiatedTs().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth().getValue())
										.append(payment.getInitiatedTs().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear()).toString();
				
				if(paymentMap.get(paymentKey) == null) paymentMap.put(paymentKey, payment.getAmount());
				else paymentMap.put(paymentKey, paymentMap.get(paymentKey).add(payment.getAmount()));
			}
			
			
			int monthIteration = 1;
			BigDecimal originalAccruedMonthlyInterest = BigDecimal.ZERO;
			BigDecimal currentAccruedMonthlyInterest = BigDecimal.ZERO;
			
			BigDecimal originalPrincipalBalance = loan.getPrincipalBalance();
			BigDecimal currentPrincipalBalance = loan.getPrincipalBalance();
			
			if(loan.getPrincipalBalance().doubleValue() > loan.getMinimumPayment().doubleValue()) {
				for (LocalDate date = loan.getFirstDayAtNibble().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;  date = date.plusDays(1)) {
					if(date.getDayOfMonth() == date.lengthOfMonth()){
						originalAccruedMonthlyInterest = originalAccruedMonthlyInterest.add((originalPrincipalBalance.multiply((loan.getInterestRate().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)))).divide(BigDecimal.valueOf(date.lengthOfYear()), 2, RoundingMode.HALF_UP));
						if(originalPrincipalBalance.add(originalAccruedMonthlyInterest).doubleValue() > loan.getMinimumPayment().doubleValue()){
							originalPrincipalBalance = originalPrincipalBalance.subtract(loan.getMinimumPayment().subtract(originalAccruedMonthlyInterest));
							loan.getOriginalAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), originalAccruedMonthlyInterest, loan.getMinimumPayment().subtract(originalAccruedMonthlyInterest), originalPrincipalBalance));
						}else{
							loan.getOriginalAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), originalAccruedMonthlyInterest, originalPrincipalBalance, BigDecimal.ZERO));
							break;
						}
						
						if(currentPrincipalBalance.add(currentAccruedMonthlyInterest).doubleValue() > loan.getMinimumPayment().doubleValue()){
							currentPrincipalBalance = currentPrincipalBalance.subtract(loan.getMinimumPayment().subtract(currentAccruedMonthlyInterest));
							loan.getCurrentProjectedAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), currentAccruedMonthlyInterest, loan.getMinimumPayment().subtract(currentAccruedMonthlyInterest), currentPrincipalBalance));
						}else{
							loan.getCurrentProjectedAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), currentAccruedMonthlyInterest, currentPrincipalBalance, BigDecimal.ZERO));
						}
						
						currentAccruedMonthlyInterest = originalAccruedMonthlyInterest = BigDecimal.ZERO;
						monthIteration++;
						
					}else{
						currentAccruedMonthlyInterest = originalAccruedMonthlyInterest = originalAccruedMonthlyInterest.add((originalPrincipalBalance.multiply((loan.getInterestRate().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)))).divide(BigDecimal.valueOf(date.lengthOfYear()), 2, RoundingMode.HALF_UP));
					}
				
				}
			}
		}
		
		System.out.println(lsummary);
		
	}
	
}