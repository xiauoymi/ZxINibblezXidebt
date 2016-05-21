/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.error;

/**
 * @author ralam
 *
 */
public class RepositoryException extends Exception {
	private static final long serialVersionUID = 9045471533358748154L;

	/**
	 * @param arg0
	 */
	public RepositoryException(String message) {
		super(message);
	}

	/**
	 * @param arg0
	 */
	public RepositoryException(Throwable ex) {
		super(ex);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public RepositoryException(String message, Throwable ex) {
		super(message, ex);
	}

}