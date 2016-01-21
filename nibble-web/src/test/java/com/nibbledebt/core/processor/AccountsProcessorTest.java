/**
 * Copyright 2015-2017. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.math.BigDecimal;
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
		loan1.setMinimumPayment(BigDecimal.valueOf(193.33));
		loan1.setPrincipalBalance(BigDecimal.valueOf(10000));
		loan1.setPayments(Arrays.asList(new Payment[]{	new Payment(BigDecimal.valueOf(100d), Date.from(date.plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(date.plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()), ""), 
														new Payment(BigDecimal.valueOf(100d), Date.from(date.plusDays(14).atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(date.plusDays(14).atStartOfDay(ZoneId.systemDefault()).toInstant()), ""), 
														new Payment(BigDecimal.valueOf(100d), Date.from(date.plusDays(21).atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(date.plusDays(21).atStartOfDay(ZoneId.systemDefault()).toInstant()), ""), 
														new Payment(BigDecimal.valueOf(100d), Date.from(date.plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(date.plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant()), "")}));
		loan1.setFirstDayAtNibble(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		lsummary.getLoans().add(loan1);
	}

	@Test
	public void testLoanSummary() {
		BigDecimal totalPaid = new BigDecimal(100.00);
		BigDecimal weeklyAverage = new BigDecimal(10.00);
		
		
		for(Loan loan : lsummary.getLoans()){
			Map<LocalDate, BigDecimal> paymentMap = new HashMap<>();
			// convert all payments to a map of month-year and value
			if(loan.getPayments()!=null){
				for(Payment payment : loan.getPayments()){					
					LocalDate paymentDate = payment.getInitiatedTs().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					if(paymentMap.get(paymentDate) == null) paymentMap.put(paymentDate, payment.getAmount());
					else paymentMap.put(paymentDate, paymentMap.get(paymentDate).add(payment.getAmount()));
				}
				
			}
			
			
			int monthIteration = 1;
			Double originalAccruedMonthlyInterest = 0d;
			Double currentAccruedMonthlyInterest = 0d;
			
			Double originalPrincipalBalance = loan.getPrincipalBalance().doubleValue();
			Double currentPrincipalBalance = loan.getPrincipalBalance().doubleValue();
			Double originalInterest = loan.getOriginalCumulativeInterest()!=null ? loan.getOriginalCumulativeInterest().doubleValue() : 0d;
			Double currentInterest = loan.getCurrentCumulativeInterest()!=null ? loan.getCurrentCumulativeInterest().doubleValue() : 0d;
			
			if(loan.getPrincipalBalance().doubleValue() > loan.getMinimumPayment().doubleValue()) {
				for (LocalDate date = loan.getFirstDayAtNibble().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;  date = date.plusDays(1)) {
					originalAccruedMonthlyInterest += (originalPrincipalBalance*((loan.getInterestRate().doubleValue()/date.lengthOfYear())/100d));
//					originalAccruedMonthlyInterest = originalAccruedMonthlyInterest.add((originalPrincipalBalance.multiply((loan.getInterestRate().divide(BigDecimal.valueOf(date.lengthOfYear()), 6, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)))));
					if(paymentMap.get(date) != null) currentPrincipalBalance -= paymentMap.get(date).doubleValue();
//					currentAccruedMonthlyInterest = currentAccruedMonthlyInterest.add((currentPrincipalBalance.multiply((loan.getInterestRate().divide(BigDecimal.valueOf(date.lengthOfYear()), 6, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP))))); 
					currentAccruedMonthlyInterest += (currentPrincipalBalance*((loan.getInterestRate().doubleValue()/date.lengthOfYear())/100d));
					
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
						
						if(originalPrincipalBalance == 0 && currentPrincipalBalance == 0) break;
						
						originalInterest += originalAccruedMonthlyInterest;
						currentInterest += currentAccruedMonthlyInterest;
						currentAccruedMonthlyInterest = originalAccruedMonthlyInterest = 0d;
						monthIteration++;
						
					}
				
				}
			}

			loan.setOriginalCumulativeInterest(BigDecimal.valueOf(originalInterest));
			loan.setCurrentCumulativeInterest(BigDecimal.valueOf(currentInterest));
			loan.setOriginalPayoffDuration(loan.getOriginalAmortization().size());
			loan.setCurrentPayoffDuration(loan.getCurrentProjectedAmortization().size());
		}
		
		System.out.println(lsummary);
		
	}
	
}