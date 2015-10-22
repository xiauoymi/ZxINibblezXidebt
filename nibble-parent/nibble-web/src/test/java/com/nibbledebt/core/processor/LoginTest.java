package com.nibbledebt.core.processor;

import com.google.gson.Gson;
import com.nibbledebt.integration.model.LoginField;
import com.nibbledebt.web.rest.model.LoginFormModel;
import com.nibbledebt.web.rest.model.RegisterNibblerRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

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

    @Test
    public void testRegister() {
        RegisterNibblerRequest request = new RegisterNibblerRequest();
        request.setAddress1("Address1");
        request.setAddress2("Address2");
        request.setCity("Austin");
        request.setState("TX");
        request.setEmail("email@email.com");
        request.setFirstName("first name");
        request.setLastName("last name");
        request.setUrl("url");
        LoginFormModel form = new LoginFormModel();
        form.setInstitutionId("institutionId");
        LoginField f1 = new LoginField();
        f1.setId("first id");
        f1.setName("first field name");
        LoginField f2 = new LoginField();
        f2.setId("first id");
        f2.setName("first field name");

        form.setLoginField(new ArrayList<LoginField>());
        form.getLoginField().add(f1);
        form.getLoginField().add(f2);
        request.setLoginForm(form);

        Gson gson = new Gson();

        String str = gson.toJson(request);

        Assert.assertNotNull(str);

    }
}
