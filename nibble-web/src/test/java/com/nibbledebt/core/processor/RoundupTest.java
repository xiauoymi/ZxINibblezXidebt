package com.nibbledebt.core.processor;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
		TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();

		System.out.println(Date.from(Instant.from((java.time.LocalDate.now().with(fieldUS, 1).atStartOfDay().atZone(ZoneId.systemDefault())))));
		System.out.println(Date.from(Instant.from((java.time.LocalDate.now().with(fieldUS, 2).atStartOfDay().atZone(ZoneId.systemDefault()))))); 
		System.out.println(Date.from(Instant.from((java.time.LocalDate.now().with(fieldUS, 3).atStartOfDay().atZone(ZoneId.systemDefault()))))); 
		System.out.println(Date.from(Instant.from((java.time.LocalDate.now().with(fieldUS, 4).atStartOfDay().atZone(ZoneId.systemDefault()))))); 
		System.out.println(Date.from(Instant.from((java.time.LocalDate.now().with(fieldUS, 5).atStartOfDay().atZone(ZoneId.systemDefault()))))); 
		System.out.println(Date.from(Instant.from((java.time.LocalDate.now().with(fieldUS, 6).atStartOfDay().atZone(ZoneId.systemDefault()))))); 
		System.out.println(Date.from(Instant.from((java.time.LocalDate.now().with(fieldUS, 7).atStartOfDay().atZone(ZoneId.systemDefault()))))); 
	}
}
