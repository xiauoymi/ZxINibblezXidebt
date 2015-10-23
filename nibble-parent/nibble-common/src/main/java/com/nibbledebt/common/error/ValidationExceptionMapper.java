/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.error;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author ralam
 *
 */
public class ValidationExceptionMapper implements
		ExceptionMapper<ValidationException> {

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(ValidationException arg0) {
		return Response.status(Status.BAD_REQUEST).entity(arg0.getMessage()).build();
	}

}
