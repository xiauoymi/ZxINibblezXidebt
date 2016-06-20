/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * @author ralam
 *
 */
public class AjaxAwareEntryPoint extends LoginUrlAuthenticationEntryPoint {

	public AjaxAwareEntryPoint(String loginUrl) {
		super(loginUrl);
	}

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {

		boolean isAjax = request.getRequestURI().startsWith("/services");
		authException.printStackTrace();
		if (isAjax) {
			 response.setContentType("application/json");
			    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			    response.getOutputStream().println("{ \"error\": \"" + authException.getMessage() + "\" }");
		} else {
			super.commence(request, response, authException);
		}
	}
}