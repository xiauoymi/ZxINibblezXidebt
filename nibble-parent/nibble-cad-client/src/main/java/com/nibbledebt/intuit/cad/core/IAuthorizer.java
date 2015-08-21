package com.nibbledebt.intuit.cad.core;

import com.nibbledebt.intuit.cad.exception.AggCatException;

import org.apache.http.client.methods.HttpRequestBase;

public abstract interface IAuthorizer {
	public abstract void authorize(HttpRequestBase paramHttpRequestBase)
			throws AggCatException;
}