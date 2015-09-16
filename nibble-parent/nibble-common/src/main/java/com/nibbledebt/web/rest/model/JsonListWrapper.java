/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.web.rest.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ralam1
 *
 */
public class JsonListWrapper<T> {
	private List<T> listItems;

	public JsonListWrapper(){}
	
	public JsonListWrapper (List<T> listItems){
		this.listItems = listItems;
	}
	
	/**
	 * @return the listItems
	 */
	public List<T> getItems() {
		if (listItems == null) listItems = new ArrayList<T>();
		return listItems;
	}

	/**
	 * @param majors the listItems to set
	 */
	public void setItems(List<T> listItems) {
		this.listItems = listItems;
	}
	
}
