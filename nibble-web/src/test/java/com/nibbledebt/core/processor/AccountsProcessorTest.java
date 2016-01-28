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
			Double weeklyAveragePayment = totalPayments/(loan.getPayments().size());
			
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
			
			Double originalMonthExtraPayment = 0d;
			Double currentMonthExtraPayment = 0d;
			Double projectedMonthExtraPayment = 0d;
			
			if(loan.getPrincipalBalance().doubleValue() > loan.getMinimumPayment().doubleValue()) {
				for (LocalDate date = loan.getFirstDayAtNibble().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;  date = date.plusDays(1)) {
					originalAccruedMonthlyInterest += (originalPrincipalBalance*((loan.getInterestRate().doubleValue()/date.lengthOfYear())/100d));
					
					if(paymentMap.get(date) != null) {
						currentMonthExtraPayment = paymentMap.get(date).doubleValue();
						currentPrincipalBalance -= currentMonthExtraPayment;
					}
					currentAccruedMonthlyInterest += (currentPrincipalBalance*((loan.getInterestRate().doubleValue()/date.lengthOfYear())/100d));
					
					
					if(date.getDayOfWeek().getValue()==7 && weeklyAveragePayment > 0){
						if(projectedPrincipalBalance > (loan.getMinimumPayment().doubleValue()+weeklyAveragePayment)) {
							projectedMonthExtraPayment += weeklyAveragePayment;
							projectedPrincipalBalance -= weeklyAveragePayment;
						}
					}else if(date.getDayOfWeek().getValue()==7 && weeklyAveragePayment == 0){
						if(projectedPrincipalBalance > (loan.getMinimumPayment().doubleValue()+10)) {
							projectedMonthExtraPayment += 10;
							projectedPrincipalBalance -= 10;
						}
					}

					projectedAccruedMonthlyInterest += (projectedPrincipalBalance*((loan.getInterestRate().doubleValue()/date.lengthOfYear())/100d));
					
					if(date.getDayOfMonth() == date.lengthOfMonth()){
						
						if(originalPrincipalBalance+originalAccruedMonthlyInterest > loan.getMinimumPayment().doubleValue()){
							originalPrincipalBalance -= (loan.getMinimumPayment().doubleValue()-originalAccruedMonthlyInterest);
							loan.getOriginalAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), BigDecimal.valueOf(originalAccruedMonthlyInterest), BigDecimal.valueOf(loan.getMinimumPayment().doubleValue()-originalAccruedMonthlyInterest), BigDecimal.valueOf(originalPrincipalBalance), BigDecimal.valueOf(originalMonthExtraPayment)));
						}else{
							loan.getOriginalAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), BigDecimal.valueOf(originalAccruedMonthlyInterest), BigDecimal.valueOf(originalPrincipalBalance), BigDecimal.ZERO, BigDecimal.valueOf(originalMonthExtraPayment)));
							originalPrincipalBalance = 0d;
						}
						
						if(currentPrincipalBalance+currentAccruedMonthlyInterest > loan.getMinimumPayment().doubleValue()){
							currentPrincipalBalance -= (loan.getMinimumPayment().doubleValue()-currentAccruedMonthlyInterest);
							loan.getCurrentProjectedAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), BigDecimal.valueOf(currentAccruedMonthlyInterest), BigDecimal.valueOf(loan.getMinimumPayment().doubleValue()-currentAccruedMonthlyInterest), BigDecimal.valueOf(currentPrincipalBalance), BigDecimal.valueOf(currentMonthExtraPayment)));
						}else{
							if(currentPrincipalBalance!=0){
								loan.getCurrentProjectedAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), BigDecimal.valueOf(currentAccruedMonthlyInterest), BigDecimal.valueOf(currentPrincipalBalance), BigDecimal.ZERO, BigDecimal.valueOf(currentMonthExtraPayment)));
								currentPrincipalBalance = 0d;
								currentMonthExtraPayment = 0d;
							}
						}
						
						if(projectedPrincipalBalance+projectedAccruedMonthlyInterest > loan.getMinimumPayment().doubleValue()){
							projectedPrincipalBalance -= (loan.getMinimumPayment().doubleValue()-projectedAccruedMonthlyInterest);
							loan.getProjectedAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), BigDecimal.valueOf(projectedAccruedMonthlyInterest), BigDecimal.valueOf(loan.getMinimumPayment().doubleValue()-projectedAccruedMonthlyInterest), BigDecimal.valueOf(projectedPrincipalBalance), BigDecimal.valueOf(projectedMonthExtraPayment)));
						}else{
							if(projectedPrincipalBalance!=0){
								loan.getProjectedAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), BigDecimal.valueOf(projectedAccruedMonthlyInterest), BigDecimal.valueOf(projectedPrincipalBalance), BigDecimal.ZERO, BigDecimal.valueOf(projectedMonthExtraPayment)));
								projectedPrincipalBalance = 0d;
								projectedMonthExtraPayment = 0d;
							}
						}
						
						if(projectedPrincipalBalance == 0 && originalPrincipalBalance == 0 && currentPrincipalBalance == 0) break;
						
						originalInterest += originalAccruedMonthlyInterest;
						currentInterest += currentAccruedMonthlyInterest;
						projectedInterest += projectedAccruedMonthlyInterest;
						currentAccruedMonthlyInterest = originalAccruedMonthlyInterest = projectedAccruedMonthlyInterest = 0d;
						originalMonthExtraPayment = currentMonthExtraPayment = projectedMonthExtraPayment = 0d;
						monthIteration++;
						
					}
				
				}
			}

			loan.setOriginalCumulativeInterest(BigDecimal.valueOf(originalInterest));
			loan.setCurrentCumulativeInterest(BigDecimal.valueOf(currentInterest));
			loan.setProjectedCumulativeInterest(BigDecimal.valueOf(projectedInterest));
			loan.setOriginalPayoffDuration(loan.getOriginalAmortization().size());
			loan.setCurrentPayoffDuration(loan.getCurrentProjectedAmortization().size());
			loan.setProjectedPayoffDuration(loan.getProjectedAmortization().size());
			loan.setWeeklyAverage(BigDecimal.valueOf(weeklyAveragePayment));
		}
		
		System.out.println(lsummary);
		
	}
	
}