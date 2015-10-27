/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.web.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.logging.LogLevel;
import com.nibbledebt.common.logging.Loggable;
import com.nibbledebt.common.security.MemberDetails;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.processor.TransactionProcessor;
import com.nibbledebt.integration.finicity.model.hooks.Event;

/**
 * @author ralam
 *
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
public class SyncHook {
	@Autowired
	private TransactionProcessor trxsProcessor;
	
	@POST
	@Path("/event")
	@Loggable(logLevel=LogLevel.INFO)
	public Boolean receiveEvent(Event event) throws ProcessingException, RepositoryException{
		return ((MemberDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIsFirstLogin();
	}
}
