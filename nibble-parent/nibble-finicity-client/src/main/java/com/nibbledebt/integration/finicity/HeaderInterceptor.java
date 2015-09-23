/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.finicity;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**
 * @author ralam
 *
 */
@Component
public class HeaderInterceptor implements ClientHttpRequestInterceptor {
	
	@Value("${finicity.app.key}")
	private String finAppKey;

	private String token;
	
	private static final String finAppKeyLabel = "Finicity-App-Key";
	
	private static final String finAppTokenLabel = "Finicity-App-Token";
	
    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        HttpHeaders headers = request.getHeaders();
        headers.add(finAppKeyLabel, finAppKey);
        headers.add("Content-Type", "application/xml");
        headers.add("Accept", "application/xml");
        if(token != null) headers.add(finAppTokenLabel, token);
        return execution.execute(request, body);
    }
    
    /**
     * Sets the token to the class variable for reuse.
     * 
     * @param token
     */
    public void setToken(String token){
    	this.token = token;
    }
}
