package com.nibbledebt.nibble.common;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nibbledebt.nibble.security.SecurityContext;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;

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
        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
//        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZZZ"));

        for(HttpMessageConverter<?> conv : template.getMessageConverters()){
            if(conv instanceof MappingJackson2HttpMessageConverter){
                ((MappingJackson2HttpMessageConverter)conv).setObjectMapper(mapper);
            }
        }
        return template;
    }

    public class CookieInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(
                HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                throws IOException {

            HttpHeaders headers = request.getHeaders();
            if(SecurityContext.getCurrentContext().getCookie()!=null)
                headers.add("Cookie", SecurityContext.getCurrentContext().getCookie());
            return execution.execute(request, body);
        }
    }
}