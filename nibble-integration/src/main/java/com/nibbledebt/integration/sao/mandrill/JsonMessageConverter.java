/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.sao.mandrill;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * A message converter using Jackson 2.x that will convert incoming and
 * outgoing HTTP/JSON messages into POJOs using Jackson. This can be
 * used with Spring's RestTemplate.
 * 
 * @see RestTemplate
 * @author Rocky Alam
 *
 */
@Component
public class JsonMessageConverter extends AbstractHttpMessageConverter<Object> implements GenericHttpMessageConverter<Object> {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	private ObjectMapper objectMapper = new ObjectMapper();

	private boolean prefixJson = false;

	private Boolean prettyPrint;


	/**
	 * Construct a new {@code MappingJacksonHttpMessageConverter}.
	 */
	public JsonMessageConverter() {
		super(new MediaType("application", "json", DEFAULT_CHARSET), new MediaType("application", "*+json", DEFAULT_CHARSET));
	}

	/**
	 * Set the {@code ObjectMapper} for this view. If not set, a default
	 * {@link ObjectMapper#ObjectMapper() ObjectMapper} is used.
	 * <p>Setting a custom-configured {@code ObjectMapper} is one way to take further control of the JSON
	 * serialization process. For example, an extended {@link org.codehaus.jackson.map.SerializerFactory}
	 * can be configured that provides custom serializers for specific types. The other option for refining
	 * the serialization process is to use Jackson's provided annotations on the types to be serialized,
	 * in which case a custom-configured ObjectMapper is unnecessary.
	 */
	public void setObjectMapper(ObjectMapper objectMapper) {
		Assert.notNull(objectMapper, "ObjectMapper must not be null");
		this.objectMapper = objectMapper;
		configurePrettyPrint();
	}

	private void configurePrettyPrint() {
		if (this.prettyPrint != null) {
			this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		}
	}

	/**
	 * Return the underlying {@code ObjectMapper} for this view.
	 */
	public ObjectMapper getObjectMapper() {
		return this.objectMapper;
	}

	/**
	 * Indicate whether the JSON output by this view should be prefixed with "{} &&". Default is false.
	 * <p>Prefixing the JSON string in this manner is used to help prevent JSON Hijacking.
	 * The prefix renders the string syntactically invalid as a script so that it cannot be hijacked.
	 * This prefix does not affect the evaluation of JSON, but if JSON validation is performed on the
	 * string, the prefix would need to be ignored.
	 */
	public void setPrefixJson(boolean prefixJson) {
		this.prefixJson = prefixJson;
	}

	/**
	 * Whether to use the {@link org.codehaus.jackson.impl.DefaultPrettyPrinter} when writing JSON.
	 * This is a shortcut for setting up an {@code ObjectMapper} as follows:
	 * <pre>
	 * ObjectMapper mapper = new ObjectMapper();
	 * mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
	 * converter.setObjectMapper(mapper);
	 * </pre>
	 * <p>The default value is {@code false}.
	 */
	public void setPrettyPrint(boolean prettyPrint) {
		this.prettyPrint = prettyPrint;
		configurePrettyPrint();
	}

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return canRead(clazz, null, mediaType);
	}

	public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
		JavaType javaType = getJavaType(type, contextClass);
		return (this.objectMapper.canDeserialize(javaType) && canRead(mediaType));
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return (this.objectMapper.canSerialize(clazz) && canWrite(mediaType));
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		// should not be called, since we override canRead/Write instead
		throw new UnsupportedOperationException();
	}

	@Override
	protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		JavaType javaType = getJavaType(clazz, null);
		return readJavaType(javaType, inputMessage);
	}

	public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		JavaType javaType = getJavaType(type, contextClass);
		return readJavaType(javaType, inputMessage);
	}

	private Object readJavaType(JavaType javaType, HttpInputMessage inputMessage) {
		try {
			return this.objectMapper.readValue(inputMessage.getBody(), javaType);
		}
		catch (IOException ex) {
			throw new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
		}
	}

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
		JsonGenerator jsonGenerator =
				this.objectMapper.getFactory().createGenerator(outputMessage.getBody(), encoding);

		// A workaround for JsonGenerators not applying serialization features
		// https://github.com/FasterXML/jackson-databind/issues/12
		if (this.objectMapper.getSerializationConfig().isEnabled(SerializationFeature.INDENT_OUTPUT)) {
			jsonGenerator.useDefaultPrettyPrinter();
		}

		try {
			if (this.prefixJson) {
				jsonGenerator.writeRaw("{} && ");
			}
			this.objectMapper.writeValue(jsonGenerator, object);
		}
		catch (JsonProcessingException ex) {
			throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
		}
	}

	/**
	 * Return the Jackson {@link JavaType} for the specified type and context class.
	 * <p>The default implementation returns {@link TypeFactory#type(java.lang.reflect.Type)}
	 * or {@code TypeFactory.type(type, TypeFactory.type(contextClass))},
	 * but this can be overridden in subclasses, to allow for custom generic collection handling.
	 * For instance:
	 * <pre class="code">
	 * protected JavaType getJavaType(Type type) {
	 *   if (type instanceof Class && List.class.isAssignableFrom((Class)type)) {
	 *     return TypeFactory.collectionType(ArrayList.class, MyBean.class);
	 *   } else {
	 *     return super.getJavaType(type);
	 *   }
	 * }
	 * </pre>
	 * @param type the type to return the java type for
	 * @param contextClass a context class for the target type, for example a class
	 * in which the target type appears in a method signature, can be {@code null}
	 * @return the java type
	 */
	protected JavaType getJavaType(Type type, Class<?> contextClass) {
		return (contextClass != null) ?
				this.objectMapper.getTypeFactory().constructType(type, contextClass) : this.objectMapper.getTypeFactory().constructType(type);
	}

	/**
	 * Determine the JSON encoding to use for the given content type.
	 * @param contentType the media type as requested by the caller
	 * @return the JSON encoding to use (never {@code null})
	 */
	protected JsonEncoding getJsonEncoding(MediaType contentType) {
		if (contentType != null && contentType.getCharSet() != null) {
			Charset charset = contentType.getCharSet();
			for (JsonEncoding encoding : JsonEncoding.values()) {
				if (charset.name().equals(encoding.getJavaName())) {
					return encoding;
				}
			}
		}
		return JsonEncoding.UTF8;
	}

}