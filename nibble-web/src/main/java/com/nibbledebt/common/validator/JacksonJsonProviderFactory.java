package com.nibbledebt.common.validator;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;

public class JacksonJsonProviderFactory {

    public JacksonJsonProvider createJson() {
        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
//        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZZZ"));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new JacksonJsonProvider(mapper);        
    }
    
    public JacksonXMLProvider createXml() {
        XmlMapper mapper = new XmlMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
//        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZZZ"));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new JacksonXMLProvider(mapper);        
    }
}
