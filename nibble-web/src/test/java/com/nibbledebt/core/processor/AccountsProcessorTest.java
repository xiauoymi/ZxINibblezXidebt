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
			int monthIteration = 1;
			BigDecimal originalAccruedMonthlyInterest = BigDecimal.ZERO;
			BigDecimal currentAccruedMonthlyInterest = BigDecimal.ZERO;
			
			BigDecimal originalPrincipalBalance = loan.getPrincipalBalance();
			BigDecimal currentPrincipalBalance = loan.getPrincipalBalance();
			
			for (LocalDate date = loan.getFirstDayAtNibble().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;  date = date.plusDays(1)) {
				if(loan.getPrincipalBalance().doubleValue() == 0) {
					break;
				}else{
					if(date.getDayOfMonth() == date.lengthOfMonth()){
						originalAccruedMonthlyInterest = originalAccruedMonthlyInterest.add((originalPrincipalBalance.multiply((loan.getInterestRate().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)))).divide(BigDecimal.valueOf(date.lengthOfYear()), 2, RoundingMode.HALF_UP));
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
		
		System.out.println(lsummary);
		
	}
}