/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.error;

/**
 * @author ralam
 *
 */
public class ValidationException extends Exception{
	private static final long serialVersionUID = 2617501567916376501L;
	
	public ValidationException(String msg){
		super(msg);
	}
}
