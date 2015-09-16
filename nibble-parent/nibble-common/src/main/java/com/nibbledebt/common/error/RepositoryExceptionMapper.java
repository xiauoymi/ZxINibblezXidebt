/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.error;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.nibbledebt.core.data.error.RepositoryException;

/**
 * @author ralam
 *
 */
public class RepositoryExceptionMapper implements
		ExceptionMapper<RepositoryException> {

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(RepositoryException arg0) {
		return Response.status(503).entity(arg0.getMessage()).build();
	}

}
