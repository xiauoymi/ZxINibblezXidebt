/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.notifier;

import com.nibbledebt.common.error.NotificationException;
import com.nibbledebt.common.model.Message;
import com.nibbledebt.common.model.SendOperationRequest;
import com.nibbledebt.common.model.SendOperationResponse;
import com.nibbledebt.common.model.To;
import com.nibbledebt.common.notifier.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is an extension of the common NetworkService interface to
 * encapsulate the connectivity logic required by Mandrill.
 * 
 * @author Rocky Alam
 *
 */
@Component()
public class MandrillSao {
    private static final String FROM_EMAIL = "admin@nibbledebt.com";

    private RestTemplate serviceClient;
        
    @Value("${mandrill.api_key}")
    private String apiKey;
    
    @Value("${mandrill.api_url}")
    private String apiUrl;
    
    /**
     * Constructor
     * 
     * @param converter
     */
    @Autowired
    public MandrillSao(JsonMessageConverter converter){
    	serviceClient = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

		// Add the Jackson Message converter
		messageConverters.add(converter);

		// Add the message converters to the restTemplate
		serviceClient.setMessageConverters(messageConverters);
		
    }
    
    /**
     * Send email using the Mandrill service.
     * 
     * @param subject
     * @param content
     * @param toEmails
     * @throws com.nibbledebt.common.error.NotificationException
     */
    public void sendEmail(String subject, String content, List<String> toEmails) throws NotificationException{
    	 SendOperationRequest request = new SendOperationRequest();
         Message message = new Message();
         message.setFromEmail(FROM_EMAIL);
         message.setHtml(content);
         
         List<To> tos = new ArrayList<To>();
         for(String email: toEmails) tos.add(new To(email, null, null));
         
         message.setTo(tos);
         message.setSubject(subject);
         request.setAsync(false);
         request.setKey(apiKey);
         request.setMessage(message);
         
         ResponseEntity<SendOperationResponse[]> response = serviceClient.postForEntity(apiUrl, request, SendOperationResponse[].class);
         
         if(!response.getBody()[0].getStatus().equalsIgnoreCase("sent") || response.getBody()[0].getId() == null){
             throw new NotificationException("Error while sending notification to : "+ toEmails);
         }    
    }   
}