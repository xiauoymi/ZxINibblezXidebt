package com.nibbledebt.common.validator;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class JacksonJsonProviderFactory {

    public JacksonJsonProvider create() {
        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
//        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZZZ"));
        return new JacksonJsonProvider(mapper);        
    }
}
