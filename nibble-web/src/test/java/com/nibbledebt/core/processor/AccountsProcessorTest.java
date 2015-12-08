/**
 * Copyright 2015-2017. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.nibbledebt.domain.model.AmortizationRecord;
import com.nibbledebt.domain.model.Loan;
import com.nibbledebt.domain.model.LoanSummary;

/**
 * @author Rocky Alam
 *
 */
public class AccountsProcessorTest {
	private LoanSummary lsummary;
	
	@Before
	public void init(){
		lsummary = new LoanSummary();
		Loan loan1 = new Loan();
		loan1.setInterestRate(BigDecimal.valueOf(6.00));
		loan1.setMinimumPayment(BigDecimal.valueOf(193.33));
		loan1.setPrincipalBalance(BigDecimal.valueOf(10000));
		lsummary.getLoans().add(loan1);
	}

	@Test
	public void testLoanSummary() {
		BigDecimal totalPaid = new BigDecimal(100.00);
		BigDecimal weeklyAverage = new BigDecimal(10.00);
		
		for(Loan loan : lsummary.getLoans()){
			int monthIteration = 1;
			BigDecimal accruedMonthlyInterest = BigDecimal.ZERO;
			for (LocalDate date = LocalDate.now(ZoneId.systemDefault());  ; date = date.plusDays(1)) {
				if(loan.getPrincipalBalance().doubleValue() == 0) {
					break;
				}else{
					if(date.getDayOfMonth() == date.lengthOfMonth()){
						accruedMonthlyInterest = accruedMonthlyInterest.add((loan.getPrincipalBalance().multiply((loan.getInterestRate().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)))).divide(BigDecimal.valueOf(date.lengthOfYear()), 2, RoundingMode.HALF_UP));
						if(loan.getPrincipalBalance().add(accruedMonthlyInterest).doubleValue() > loan.getMinimumPayment().doubleValue()){
							loan.setPrincipalBalance(loan.getPrincipalBalance().subtract(loan.getMinimumPayment().subtract(accruedMonthlyInterest)));
							loan.getOriginalAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), accruedMonthlyInterest, loan.getMinimumPayment().subtract(accruedMonthlyInterest), loan.getPrincipalBalance()));
						}else{
							loan.getOriginalAmortization().add(new AmortizationRecord(monthIteration, date.getMonth().name(), Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), accruedMonthlyInterest, loan.getPrincipalBalance(), BigDecimal.ZERO));
							loan.setPrincipalBalance(BigDecimal.ZERO);
							
						}
						accruedMonthlyInterest = BigDecimal.ZERO;
						monthIteration++;
					}else{
						accruedMonthlyInterest = accruedMonthlyInterest.add((loan.getPrincipalBalance().multiply((loan.getInterestRate().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)))).divide(BigDecimal.valueOf(date.lengthOfYear()), 2, RoundingMode.HALF_UP));
					}
				}
			}
		}
		
		System.out.println(lsummary);
		
	}
}