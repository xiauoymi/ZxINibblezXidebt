/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.intuit.cad.service;

import java.util.Map;

import com.nibbledebt.intuit.cad.core.EntityName;
import com.nibbledebt.intuit.cad.core.MethodType;
import com.nibbledebt.intuit.cad.data.Institutions;
import com.nibbledebt.intuit.cad.exception.AggCatException;
import com.nibbledebt.intuit.cad.interceptor.InterceptorProvider;
import com.nibbledebt.intuit.cad.interceptor.IntuitMessage;
import com.nibbledebt.intuit.cad.interceptor.RequestElements;

/**
 * @author ralam
 *
 */
public class IntuitCADClient {
	public Institutions getInstitutions() throws AggCatException {
		IntuitMessage intuitMessage = new IntuitMessage();
		RequestElements requestElements = intuitMessage.getRequestElements();

		Map requestParameters = requestElements.getRequestParameters();

		requestParameters.put("entity-name", EntityName.INSTITUTIONS.toString());
		requestParameters.put("method-type", MethodType.GET.toString());

		requestElements.setContext(this.context);
		requestElements.setResourceTypeIdentifier("GetInstitutions");

		new InterceptorProvider().executeInterceptors(intuitMessage);

		return (Institutions) intuitMessage.getResponseElements().getResponse();
	}
}
