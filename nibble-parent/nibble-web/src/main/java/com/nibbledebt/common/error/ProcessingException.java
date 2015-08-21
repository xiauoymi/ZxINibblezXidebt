/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.error;

/**
 * This exception wraps all exceptions that originate from the processing layer
 * 
 * @author Rocky Alam
 *
 */
public class ProcessingException extends Exception {
	private static final long serialVersionUID = -248831579040468211L;
	public ProcessingException(String message, Throwable e){
		super(message, e);
	}
	
	public ProcessingException(String message){
		super(message);
	}
	
	public ProcessingException(Throwable e){
		super( e);
	}
}
