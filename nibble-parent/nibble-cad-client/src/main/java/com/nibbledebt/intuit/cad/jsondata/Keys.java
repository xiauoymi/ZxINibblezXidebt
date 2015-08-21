/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.intuit.cad.jsondata;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ralam
 *
 */
public class Keys {
	@JsonProperty("key")
	protected List<Key> keies;

	public List<Key> getKeies() {
		if (this.keies == null) {
			this.keies = new ArrayList<>();
		}
		return this.keies;
	}

	/**
	 * @param keies the keies to set
	 */
	public void setKeies(List<Key> keies) {
		this.keies = keies;
	}

}
