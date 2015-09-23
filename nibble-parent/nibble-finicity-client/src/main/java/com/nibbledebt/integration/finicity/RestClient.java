/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.finicity;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author ralam
 *
 */
public class RestClient extends RestTemplate {
    public RestClient(String proxyUrl, Integer proxyPort, String proxyUsername, String proxyPassword)  {
    	
    	CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(proxyUrl, proxyPort),
                new UsernamePasswordCredentials(proxyUsername, proxyPassword));
          
    	HttpClientBuilder builder = HttpClientBuilder.create();
    	HttpHost proxy = new HttpHost(proxyUrl, proxyPort);
    	builder.setProxy(proxy);
    	builder.setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());
    	builder.setDefaultCredentialsProvider(credsProvider);
    	
    	HttpRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
    	builder.setRoutePlanner(routePlanner);
    	
    	CloseableHttpClient client = builder.build();
    	
    	setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));
    	
    }
    
    
}