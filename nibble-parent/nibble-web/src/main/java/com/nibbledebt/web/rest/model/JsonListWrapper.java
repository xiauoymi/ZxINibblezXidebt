/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.web.rest.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * @author ralam1
 *
 */
@Deprecated
@JsonRootName("response")
public class JsonListWrapper<T> {
	private List<T> items;

	public JsonListWrapper(){}
	
	public JsonListWrapper (List<T> items){
		this.items = items;
	}
	
	/**
	 * @return the items
	 */
	public List<T> getItems() {
		if (items == null) items = new ArrayList<T>();
		return items;
	}

	/**
	 * @param majors the items to set
	 */
	public void setItems(List<T> items) {
		this.items = items;
	}
	
}
