package com.nibbledebt.core.processor;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class LoginTest {
	
	@Test
	public void testLogin(){
		// The connection URL
        String url = "http://nibble-web.herokuapp.com/sslogin";

        MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
        values.add("nibbler_username", "testuser123");
        values.add("nibbler_password", "testuser123");

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
		restTemplate.getMessageConverters().clear();
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Make the HTTP GET request, marshaling the response to a String
        ResponseEntity<String> result = restTemplate.postForEntity(url, values, String.class);
        
        System.out.println(result.getHeaders().get("Set-Cookie").toArray()[0]);
	}
}
