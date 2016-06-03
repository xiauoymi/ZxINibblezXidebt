/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.error;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.springframework.security.core.AuthenticationException;

/**
 * @author ralam
 *
 */
public class AuthenticationExceptionMapper implements
		ExceptionMapper<AuthenticationException> {

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(AuthenticationException arg0) {
		return Response.status(502).entity(arg0).build();
	}

}
