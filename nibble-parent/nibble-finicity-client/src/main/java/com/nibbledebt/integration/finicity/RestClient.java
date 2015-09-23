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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author ralam
 *
 */
@Component
public class RestClient extends RestTemplate {

	/**
	 * Constructor for a custom RestTemplate for Finicity. It does the following:
	 * 	- Creates credentials provider for Proxy Authentication
	 * 	- Creates a Commons HttpClient object with the proxy and credentials
	 * 	- Creates a route plan to run the calls through a proxy
	 * 	- Sets a request factory with this client into this RestTemplate
	 * 	- Configures the MappingJackson2XmlHttpMessageConverter to read root element name from @JsonRootName
	 *  - Add the app key to the header of the http request
	 *  
	 * @param proxyUrl
	 * @param proxyPort
	 * @param proxyUsername
	 * @param proxyPassword
	 */
	@Autowired
    public RestClient(	@Value("${proxy.url}") String proxyUrl, 
    					@Value("${proxy.port}") Integer proxyPort, 
    					@Value("${proxy.username}") String proxyUsername, 
    					@Value("${proxy.password}") String proxyPassword,
    					@Value("${finicity.appkey}") String appKey,
    					HeaderInterceptor headerInterceptor)  {
    	// create the credentials 
    	CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(proxyUrl, proxyPort),
                new UsernamePasswordCredentials(proxyUsername, proxyPassword));
          
        // create the client
    	HttpClientBuilder builder = HttpClientBuilder.create();
    	HttpHost proxy = new HttpHost(proxyUrl, proxyPort);
    	builder.setProxy(proxy);
    	builder.setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());
    	builder.setDefaultCredentialsProvider(credsProvider);
    	
    	// create the route plan
    	HttpRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
    	builder.setRoutePlanner(routePlanner);    	
    	CloseableHttpClient client = builder.build();
    	
    	// set the client into the request factory and set it to the rest template
    	setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));
    	
    	// modify the message converter to read root element name from the annotations
		for(int i=0;i<getMessageConverters().size();i++){
			if (getMessageConverters().get(i) instanceof MappingJackson2XmlHttpMessageConverter){
				((MappingJackson2XmlHttpMessageConverter)getMessageConverters().get(i)).getObjectMapper().enable(SerializationFeature.WRAP_ROOT_VALUE);
				break;
			}
		}
    	
		// add the header interceptor to add the app key
		getInterceptors().add(headerInterceptor);
    }    
}