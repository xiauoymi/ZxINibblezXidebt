/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;


/**
 * @author ralam
 *
 */
public interface RunnableAsync<T> extends Runnable {
	public abstract void setEntity(T entity);
}
