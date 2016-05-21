/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.error;

/**
 * This exception is thrown when there is an issue with the Notifications
 * module.
 * 
 * @author ralam1
 *
 */
public class DefaultException extends Exception {
	private static final long serialVersionUID = 5626202940534478912L;
	
	/**
	 * Constructor with message and exception to wrap.
	 * 
	 * @param message
	 * @param t
	 */
	public DefaultException(String message, Throwable t){
		super(message,t);
	}
	
	/**
	 * Constructor with message.
	 * 
	 * @param message
	 */
	public DefaultException(String message){
		super(message);
	}
}
