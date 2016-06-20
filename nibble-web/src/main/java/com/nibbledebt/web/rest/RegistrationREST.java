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
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.common.error.ValidationException;
import com.nibbledebt.common.logging.LogLevel;
import com.nibbledebt.common.logging.Loggable;
import com.nibbledebt.common.validator.Validatable;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.processor.InstitutionProcessor;
import com.nibbledebt.core.processor.RegistrationProcessor;
import com.nibbledebt.domain.model.Bank;
import com.nibbledebt.domain.model.Institution;
import com.nibbledebt.domain.model.NibblerData;
import com.nibbledebt.domain.model.account.AddAccountsResponse;

/**
 * @author Rocky Alam
 *
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class RegistrationREST extends AbstractREST {
	@Autowired
	private RegistrationProcessor regService;
	
	@Autowired
	private InstitutionProcessor instService;
	
	@POST
	@Path("/register/activate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@Validatable() //TODO - write custom validator
	public NibblerData activate(NibblerData nibblerData) throws ProcessingException, ServiceException,
            RepositoryException, ValidationException{
		regService.activateNibbler(nibblerData);
		return nibblerData;
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@Validatable() //TODO - write custom validator
	public Long startRegistration(NibblerData nibblerData) throws ProcessingException, ServiceException,
            RepositoryException, ValidationException{
		return regService.startRegistration(nibblerData);
	}
	
	@POST
	@Path("/updateRegister")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@Validatable() //TODO - write custom validator
	public Long updateRegistration(NibblerData nibblerData) throws ProcessingException, ServiceException,
            RepositoryException, ValidationException{
		return regService.updateCustomerData(nibblerData);
	}
	
	@POST
	@Path("/customerToken")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@Validatable() //TODO - write custom validator
	public NibblerData customeToken(NibblerData nibblerData) throws ProcessingException, ServiceException,
            RepositoryException, ValidationException{
		regService.getIavToken(nibblerData);
		return nibblerData;
	}
	
	
	@POST
	@Path("/register/loanaccount")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@Validatable() //TODO - write custom validator
	public AddAccountsResponse addLoanAccount(NibblerData nibblerData) throws ProcessingException, ServiceException,
            RepositoryException, ValidationException{
			return regService.addLoanAccount(nibblerData);
	}
	
	@POST
	@Path("/register/loanaccountByReferral")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@Validatable() //TODO - write custom validator
	public AddAccountsResponse addLoanAccountByReferral(NibblerData nibblerData) throws ProcessingException, ServiceException,
            RepositoryException, ValidationException{
			return regService.addLoanAccountByReferral(nibblerData);
	}
	
	@POST
	@Path("/register/roundupaccount")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	@Validatable() //TODO - write custom validator
	public AddAccountsResponse addRoudnupAccount(NibblerData nibblerData) throws ProcessingException, ServiceException,
            RepositoryException, ValidationException{
			return regService.addRoundupAccount(nibblerData, nibblerData.getEmail());
	}
	
    @POST
    @Path("/submitRoundupMfa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable(logLevel=LogLevel.INFO)
    @Validatable() //TODO - write custom validator
    public AddAccountsResponse submitRoundupMfa(NibblerData nibblerData) throws  ProcessingException, ServiceException, RepositoryException, ValidationException {
        return regService.submitRoundupAccountMfaAnswer(nibblerData);

    }
    
    @POST
    @Path("/submitLoanMfa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable(logLevel=LogLevel.INFO)
    @Validatable() //TODO - write custom validator
    public AddAccountsResponse submitLoanMfa(NibblerData nibblerData) throws  ProcessingException, ServiceException, RepositoryException, ValidationException {
        return regService.submitRoundupAccountMfaAnswer(nibblerData);

    }
	
//	@POST
//	@Path("/registermfa")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	@Loggable(logLevel=LogLevel.INFO)
//	@Validatable() //TODO - write custom validator
//	public MfaResponse registerMfa(NibblerData nibblerData) throws ProcessingException, ServiceException, RepositoryException{
//		return regService.registerNibblerWithMfa(nibblerData);
//	}
//	
//	@POST
//	@Path("/mfacode")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	@Loggable(logLevel=LogLevel.INFO)
//	@Validatable() //TODO - write custom validator
//	public void sendCode(MfaRequest request) throws ProcessingException, ServiceException{
//		regService.sendMfaCode(request.getAccessToken(), request.getSendMethod());
//	}
	
//	@POST
//	@Path("/mfa")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	@Loggable(logLevel=LogLevel.INFO)
//	@Validatable() //TODO - write custom validator
//	public void mfaResponse(NibblerData data) throws ProcessingException, ServiceException, RepositoryException{
//		regService.submitMfa(data);
//	}
//	
//	@POST
//	@Path("/mfaques")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	@Loggable(logLevel=LogLevel.INFO)
//	@Validatable() //TODO - write custom validator
//	public void mfaQuesResponse(NibblerData data) throws ProcessingException, ServiceException, RepositoryException{
//		regService.submitQuesMfa(data);
//	}
	
	@GET
	@Path("/banks")
	@Consumes(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	public List<Bank> getSupportedBanks() throws ProcessingException, ServiceException{
		return instService.getSupportedInstitutions();
	}
	
	@GET
	@Path("/institutions")
	@Consumes(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	public List<Institution> getApiBanks() throws ProcessingException, ServiceException{
		return instService.getApiFinicityInstitutions();
	}
	
	@GET
	@Path("/searchInstitutions/{search}")
	@Loggable(logLevel=LogLevel.INFO)
	public List<Bank> getApiBanksByName(@PathParam("search") String search) throws ProcessingException, ServiceException{
		return instService.getApiBanks(search);
	}
	
	@GET
	@Path("/loans")
	@Consumes(MediaType.APPLICATION_JSON)
	@Loggable(logLevel=LogLevel.INFO)
	public List<Bank> getSupportedLoans() throws ProcessingException, ServiceException{
		return instService.getSupportedLoanInstitutions();
	}
	
	@GET
	@Path("/pop")
	@Loggable(logLevel=LogLevel.INFO)
	public void populate() throws ProcessingException, ServiceException{
		instService.populateInstitutions();
	}
	
	@GET
	@Path("/logo/{id}")
	@Produces("image/*")
	@Loggable(logLevel=LogLevel.INFO)
	public Response getBankLogo(@PathParam("id") String id) throws ProcessingException{
		ResponseBuilder responseBuilder;
		if(instService.getLogoById(id) != null){
			responseBuilder = Response.ok(instService.getLogoById(id));
	        responseBuilder.header("Content-Disposition", "attachment; filename=\""+id+"\"");
	        return responseBuilder.build();
		}else{
			responseBuilder = Response.noContent();
			responseBuilder.status(Status.NOT_FOUND);
			return responseBuilder.build();
		}
	}
}