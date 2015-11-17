package com.nibbledebt.intuit.cad.core;

import org.apache.http.client.methods.HttpRequestBase;

import com.nibbledebt.intuit.cad.exception.AggCatException;

public abstract interface IAuthorizer
{
  public abstract void authorize(HttpRequestBase paramHttpRequestBase)
    throws AggCatException;
}