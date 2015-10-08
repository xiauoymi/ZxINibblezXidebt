/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.common.logging.LogLevel;
import com.nibbledebt.common.logging.Loggable;
import com.nibbledebt.common.validator.Validatable;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.processor.InstitutionProcessor;
import com.nibbledebt.core.processor.RegistrationProcessor;
import com.nibbledebt.integration.model.LinkResponse;
import com.nibbledebt.integration.model.MfaRequest;
import com.nibbledebt.integration.model.MfaResponse;
import com.nibbledebt.web.rest.model.InstitutionDetail;
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
	public void register(NibblerData nibblerData) throws ProcessingException, ServiceException, RepositoryException{
		regService.registerNibbler(nibblerData);
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
	public JsonListWrapper<InstitutionDetail> getSupportedBanks() throws ProcessingException, ServiceException{
		JsonListWrapper<InstitutionDetail> wrapper = new JsonListWrapper<>();
		List<InstitutionDetail> insts = instService.getSupportedInstitutions();
		if(insts!=null) wrapper.getItems().addAll(insts);
		return wrapper;
	}
	
	@GET
	@Path("/pop")
	@Loggable(logLevel=LogLevel.INFO)
	public void populate() throws ProcessingException, ServiceException{
		instService.populateInstitutions();
	}
	
	@GET
	@Path("/logo/{type}")
	@Produces("image/*")
	@Loggable(logLevel=LogLevel.INFO)
	public Response getBankLogo(@PathParam("id") String id) throws ProcessingException{
		ResponseBuilder responseBuilder;
		responseBuilder = Response.ok(instService.getLogoById(id));
        responseBuilder.header("Content-Disposition", "attachment; filename=\""+id+"\"");
        return responseBuilder.build();
	}
}