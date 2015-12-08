/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.web.sys.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.impl.ResponseBuilderImpl;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.logging.LogLevel;
import com.nibbledebt.common.logging.Loggable;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.processor.AccountsProcessor;
import com.nibbledebt.core.processor.TransactionProcessor;
import com.nibbledebt.integration.finicity.model.hooks.AccountEvent;
import com.nibbledebt.integration.finicity.model.hooks.Event;
import com.nibbledebt.integration.finicity.model.hooks.TransactionEvent;
import com.nibbledebt.integration.finicity.model.trxs.Transaction;

/**
 * @author ralam
 *
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Component
public class SyncHook {
	@Autowired
	private TransactionProcessor trxsProcessor;
	@Autowired
	private AccountsProcessor acctsProcessor;
	@Autowired
	private Mapper integrationMapper;
	
	@POST
	@Path("/event")
	@Loggable(logLevel=LogLevel.INFO)
	public Response receiveEvent(Event event) throws ProcessingException, RepositoryException{
		if(event instanceof TransactionEvent){
			TransactionEvent trxEvent = (TransactionEvent)event;
			List<com.nibbledebt.domain.model.Transaction> trxs = new ArrayList<>();
			for(Transaction extTrx : trxEvent.getRecords().getTransaction()){
				trxs.add(integrationMapper.map(extTrx, com.nibbledebt.domain.model.Transaction.class));
			}
			
		}else if(event instanceof AccountEvent){
			AccountEvent acctEvent = (AccountEvent)event;
			
		}
		return (new ResponseBuilderImpl()).status(Status.OK).build();
	}
}
