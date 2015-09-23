/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.finicity;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author ralam
 *
 */
public class HeaderInterceptor implements ClientHttpRequestInterceptor {
	private String finAppKey;
	private String finAppKeyLabel;
	
	public HeaderInterceptor(String finAppKeyLabel, String finAppKey){
		this.finAppKey = finAppKey;
		this.finAppKeyLabel = finAppKeyLabel;
	}
	
    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        HttpHeaders headers = request.getHeaders();
        headers.add(finAppKeyLabel, finAppKey);
        headers.add("Content-Type", "application/xml");
        return execution.execute(request, body);
    }
}
