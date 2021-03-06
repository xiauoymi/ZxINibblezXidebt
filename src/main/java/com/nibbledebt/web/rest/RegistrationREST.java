/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.web.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.error.RepositoryException;
import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.common.logging.LogLevel;
import com.nibbledebt.common.logging.Loggable;
import com.nibbledebt.common.validator.Validatable;
import com.nibbledebt.core.processor.InstitutionProcessor;
import com.nibbledebt.core.processor.RegistrationProcessor;
import com.nibbledebt.integration.model.plaid.Institution;
import com.nibbledebt.integration.model.plaid.LinkResponse;
import com.nibbledebt.integration.model.plaid.MfaRequest;
import com.nibbledebt.integration.model.plaid.MfaResponse;
import com.nibbledebt.web.rest.model.JsonListWrapper;
import com.nibbledebt.web.rest.model.NibblerData;

/**
 * @author Rocky Alam
 *
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class RegistrationREST {
	@Autowired
	private RegistrationProcessor regService;
	
	@Autowired
	private InstitutionProcessor instService;
	
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@Validatable() //TODO - write custom validator
	public LinkResponse register(NibblerData nibblerData) throws ProcessingException, ServiceException, RepositoryException{
		return regService.registerNibbler(nibblerData);
	}
	
	@POST
	@Path("/registermfa")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@Validatable() //TODO - write custom validator
	public MfaResponse registerMfa(NibblerData nibblerData) throws ProcessingException, ServiceException, RepositoryException{
		return regService.registerNibblerWithMfa(nibblerData);
	}
	
	@POST
	@Path("/mfacode")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@Validatable() //TODO - write custom validator
	public void sendCode(MfaRequest request) throws ProcessingException, ServiceException{
		regService.sendMfaCode(request.getAccessToken(), request.getSendMethod());
	}
	
	
	@POST
	@Path("/mfa")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@Validatable() //TODO - write custom validator
	public void mfaResponse(NibblerData data) throws ProcessingException, ServiceException, RepositoryException{
		regService.submitMfa(data);
	}
	
	@POST
	@Path("/mfaques")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@Validatable() //TODO - write custom validator
	public void mfaQuesResponse(NibblerData data) throws ProcessingException, ServiceException, RepositoryException{
		regService.submitQuesMfa(data);
	}
	
	@POST
	@Path("/activate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@Validatable() //TODO - write custom validator
	public void activate(NibblerData nibblerData) throws ProcessingException, RepositoryException{
		regService.activateNibbler(nibblerData.getUsername(), nibblerData.getPassword(), nibblerData.getActivationCode());
	}
	
	
	@GET
	@Path("/banks")
	@Consumes(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	public JsonListWrapper<Institution> getSupportedBanks() throws ProcessingException, ServiceException{
		JsonListWrapper<Institution> wrapper = new JsonListWrapper<>();
		wrapper.getItems().addAll(instService.getSupportedInstitutions());
		return wrapper;
	}
	
	@GET
	@Path("/logo/{type}")
	@Produces("image/*")
	@Loggable(logLevel=LogLevel.INFO)
	public Response getBankLogo(@PathParam("type") String type) throws ProcessingException{
		ResponseBuilder responseBuilder;
		responseBuilder = Response.ok(instService.getLogo(type));
        responseBuilder.header("Content-Disposition", "attachment; filename=\""+type+"\"");
        return responseBuilder.build();
	}
}