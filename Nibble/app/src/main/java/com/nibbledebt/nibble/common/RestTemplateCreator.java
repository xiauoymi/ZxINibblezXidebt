package com.nibbledebt.nibble.common;

import com.nibbledebt.nibble.security.SecurityContext;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by ralam on 10/7/15.
 */
public class RestTemplateCreator extends RestTemplate {

    private static RestTemplateCreator restTemplateCreator = new RestTemplateCreator();

    private RestTemplateCreator(){}

    public static RestTemplateCreator getTemplateCreator(){
        return restTemplateCreator;
    }

    public RestTemplate getNewTemplate(){
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add(new CookieInterceptor());
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return template;
    }


    public class CookieInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(
                HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                throws IOException {

            HttpHeaders headers = request.getHeaders();
            headers.add("Cookie", SecurityContext.getCurrentContext().getCookie());
            return execution.execute(request, body);
        }
    }
}
