/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.error;

/**
 * @author Rocky Alam
 *
 */
public class ServiceException extends Exception {
	private static final long serialVersionUID = -6670272310872652968L;

	/**
	 * @param arg0
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * @param arg0
	 */
	public ServiceException(Throwable ex) {
		super(ex);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ServiceException(String message, Throwable ex) {
		super(message, ex);
	}

}
