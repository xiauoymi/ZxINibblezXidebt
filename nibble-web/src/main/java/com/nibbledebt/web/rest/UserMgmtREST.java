/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.web.rest;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.nibbledebt.common.error.DefaultException;
import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.common.logging.LogLevel;
import com.nibbledebt.common.logging.Loggable;
import com.nibbledebt.common.security.MemberDetails;
import com.nibbledebt.common.validator.Validatable;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.Nibbler;
import com.nibbledebt.core.processor.BillingProcessor;
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
	
	@Autowired
	private BillingProcessor billingProcessor;
	
	@POST
	@Path("/reset")
	@Consumes(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@Validatable() //TODO - write custom validator
	public void register(NibblerData nibblerData) throws ProcessingException, RepositoryException{
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
	public MemberDetails getProfile() throws ProcessingException{
		try {
			return ((MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		} catch (Exception e) {
			throw new ProcessingException("Nibble's web app is coming soon. Please check your email for weekly updates or contact us at info@nibbledebt.com");
		}
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
	
	@POST
	@Path("/users")
	@Consumes(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	public List<NibblerData> loadUsers(Nibbler nibbler) throws DefaultException, RepositoryException{
		return usersProcessor.loadUsers(nibbler);
	}
	
	@POST
	@Path("/update")
	@Loggable(logLevel=LogLevel.INFO)
	public void update(NibblerData nibblerData) throws DefaultException, RepositoryException{
		usersProcessor.update(nibblerData);
	}
	
	@POST
	@Path("/suspend")
	@Loggable(logLevel=LogLevel.INFO)
	public void suspend(NibblerData nibblerData) throws DefaultException, RepositoryException{
		usersProcessor.suspend(nibblerData);
	}
	
	@POST
	@Path("/activate")
	@Loggable(logLevel=LogLevel.INFO)
	public void activate(NibblerData nibblerData) throws DefaultException, RepositoryException{
		usersProcessor.activateSuspendedUser(nibblerData);
	}
	
	@POST
	@Path("/loginAs")
	@Loggable(logLevel=LogLevel.INFO)
	public void loginAs(NibblerData nibblerData) throws Exception{
		SecurityContextHolder.getContext().setAuthentication(usersProcessor.loginAs(nibblerData));
	}
	
	
	@GET
	@Path("/setsuspendeddate/{username}/{date}")
	@Loggable(logLevel=LogLevel.INFO)
	@PreAuthorize("hasRole('nibbler_level_1')")
	public void setsuspendeddate(@PathParam("username") String username,@PathParam("date") String date) throws ProcessingException, ServiceException, DefaultException, RepositoryException, ParseException{
		DateTimeFormatter dTF = DateTimeFormatter.ofPattern("uuuuddMM");
		Date d=Date.from(LocalDate.parse(date, dTF).atStartOfDay(ZoneId.systemDefault()).toInstant());
		usersProcessor.setsuspendeddate(username, d);
	}
	
	@GET
	@Path("/bill")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	public void bill() throws ProcessingException, RepositoryException{
		billingProcessor.processPayment();
	}
}
