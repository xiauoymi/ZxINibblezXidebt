package com.nibbledebt.core.processor;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nibbledebt.core.data.model.AccountTransaction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class RoundupTest {
	private List<BigDecimal> testFigures = new ArrayList<>();
	private List<BigDecimal> exFigures = new ArrayList<>();
	
	@Before
	public void init(){
		testFigures.add(new BigDecimal("2307.15"));
		testFigures.add(new BigDecimal("3.19"));
		testFigures.add(new BigDecimal("-240.00"));
		testFigures.add(new BigDecimal("-3042.44"));
		

		exFigures.add(new BigDecimal("0.85"));
		exFigures.add(new BigDecimal("0.81"));
		exFigures.add(new BigDecimal("0.00"));
		exFigures.add(new BigDecimal("0.56"));
	}

	@Test
	public void testRounding() {
		List<BigDecimal> resultFigures = new ArrayList<>();
		for(BigDecimal figure : testFigures){
			BigDecimal nearestDollar = BigDecimal.ZERO;
			BigDecimal result;
			if(figure.doubleValue() < 0){
				nearestDollar = figure.multiply(new BigDecimal(-1)).setScale(0, RoundingMode.UP);
				result = nearestDollar.subtract(figure.multiply(new BigDecimal(-1)));
			}else{
				nearestDollar = figure.setScale(0, RoundingMode.UP);
				result = nearestDollar.subtract(figure);
			}
			resultFigures.add(result);
		}
		
		for(int i=0; i<resultFigures.size(); i++){
			assertEquals(exFigures.get(i), resultFigures.get(i));
		}
	
	}
	
	@Test
	public void testDayOfWeek(){
		LocalDate now = new LocalDate();
		System.out.println(now.withDayOfWeek(DateTimeConstants.SATURDAY)); 
		System.out.println(now.withDayOfWeek(DateTimeConstants.SATURDAY).minusDays(6)); 
	}
}
