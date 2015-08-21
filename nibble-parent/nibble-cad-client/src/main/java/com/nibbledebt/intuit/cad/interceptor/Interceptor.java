package com.nibbledebt.intuit.cad.interceptor;

import com.nibbledebt.intuit.cad.exception.AggCatException;

public abstract interface Interceptor {
	public abstract void execute(IntuitMessage paramIntuitMessage)
			throws AggCatException;
}