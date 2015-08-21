/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Rocky Alam
 *
 */
public class AbstractModel {
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
