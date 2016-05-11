/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.web.rest;

import java.util.Arrays;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.logging.LogLevel;
import com.nibbledebt.common.logging.Loggable;
import com.nibbledebt.common.security.MemberDetails;
import com.nibbledebt.common.validator.Validatable;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.processor.UsersProcessor;
import com.nibbledebt.domain.model.NibblerData;

/**
 * @author ralam
 *
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class UserMgmtREST  extends AbstractREST {
	
	@Autowired
	private UsersProcessor usersProcessor;
	
	@POST
	@Path("/reset")
	@Consumes(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@Validatable() //TODO - write custom validator
	public void reset(NibblerData nibblerData) throws ProcessingException, RepositoryException{
		usersProcessor.resetPassword(nibblerData.getEmail(), nibblerData.getPassword(), nibblerData.getResetCode());
	}
	
	@POST
	@Path("/sendResetCode")
	@Consumes(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@Validatable() //TODO - write custom validator
	public void sendResetCode(NibblerData nibblerData) throws ProcessingException, RepositoryException{
		usersProcessor.generateResetCode(nibblerData);
	}	

	@GET
	@Path("/useruq")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	public Boolean isUserUnique(@QueryParam("register_username") String username) throws ProcessingException, RepositoryException{
		return usersProcessor.retrieveNibbler(username)==null ? true : false;
	}
	
	@GET
	@Path("/profile")
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@PreAuthorize("hasRole('nibbler_level_1')")
	public MemberDetails getProfile(){
		return ((MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}
	
	@POST
	@Path("/invite")
	@Loggable(logLevel=LogLevel.INFO)
	@PreAuthorize("hasRole('receiver')")
	public void invite(String[] emails) throws ProcessingException, RepositoryException{
		NibblerData nibblerData = new NibblerData();
		nibblerData.setInviteEmails(Arrays.asList(emails));
		nibblerData.setEmail(getCurrentUser());
		this.usersProcessor.sendInvite(nibblerData);
	}
}
