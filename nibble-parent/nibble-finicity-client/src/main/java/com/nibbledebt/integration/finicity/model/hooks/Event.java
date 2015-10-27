/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.finicity.model.hooks;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author ralam
 *
 */
@JsonTypeInfo(  
	    use = JsonTypeInfo.Id.NAME,  
	    include = JsonTypeInfo.As.PROPERTY,  
	    property = "class")  
@JsonSubTypes({  
	    @Type(value = AccountEvent.class, name = "account"),  
	    @Type(value = TransactionEvent.class, name = "transaction") }) 
@JsonRootName("event")
public abstract class Event {
	@JsonProperty("class")
	private String eventClass;
	private String type;
	
	/**
	 * @return the eventClass
	 */
	public String getEventClass() {
		return eventClass;
	}
	/**
	 * @param eventClass the eventClass to set
	 */
	public void setEventClass(String eventClass) {
		this.eventClass = eventClass;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
