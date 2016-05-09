/**
 * Copyright 2015-2017. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nibbledebt.core.data.dao.INibblerDao;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.Nibbler;

/**
 * @author Rocky Alam
 *
 */
@Component
public class BillingProcessor extends AbstractProcessor {
	@Autowired
	private INibblerDao nibblerDao;
	

	@Scheduled(cron="0 0 * * 1 *")
	public void processWeeklyPayment() throws RepositoryException{
		List<Nibbler> nibblers = nibblerDao.findAll();
		for(Nibbler nibbler : nibblers){
			
		}
	}
	
	public void processWeeklyPaymentForUser(Nibbler nibbler){
		
	}
	
}
