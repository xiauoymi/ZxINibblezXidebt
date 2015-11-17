/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.error;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author ralam
 *
 */
public class NotificationExceptionMapper implements
		ExceptionMapper<NotificationException> {

	@Override
	public Response toResponse(NotificationException arg0) {
		return Response.status(501).entity(arg0.getMessage()).build();
	}

}
