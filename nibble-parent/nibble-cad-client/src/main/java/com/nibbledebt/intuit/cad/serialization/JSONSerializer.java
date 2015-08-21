/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.intuit.cad.serialization;

import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.nibbledebt.intuit.cad.exception.SerializationException;

/**
 * @author ralam
 *
 */
public class JSONSerializer implements ISerializer {
	private static final Logger LOG = LoggerFactory.getLogger(JSONSerializer.class);
	
	/* (non-Javadoc)
	 * @see com.nibbledebt.intuit.cad.serialization.ISerializer#serialize(java.lang.Object)
	 */
	@Override
	public <T> String serialize(T paramT) throws SerializationException {
		try {
			StringWriter writer = new StringWriter();
			ObjectMapper mapper = new ObjectMapper();
			JaxbAnnotationModule module = new JaxbAnnotationModule();
			mapper.registerModule(module);
			mapper.writeValue(writer, paramT);
			return writer.toString();
		} catch (Exception e) {
			LOG.error("unable to marshall in JSON Serializer", e);
			throw new SerializationException(e);
		} 
	}

	/* (non-Javadoc)
	 * @see com.nibbledebt.intuit.cad.serialization.ISerializer#deserialize(java.lang.String, java.lang.Class)
	 */
	@Override
	public Object deserialize(String paramString, Class<?> paramClass) throws SerializationException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JaxbAnnotationModule module = new JaxbAnnotationModule();
			mapper.registerModule(module);
			return mapper.readValue(paramString , paramClass);
		} catch (Exception e) {
			LOG.error("unable to marshall in JSON DeSerializer", e);
			throw new SerializationException(e);
		} 
	}
	
	

}
