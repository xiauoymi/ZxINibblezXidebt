package com.nibbledebt.intuit.cad.core;

import com.nibbledebt.intuit.cad.exception.AggCatException;

public class Context
{
  private IAuthorizer authorizer = null;

  public Context(IAuthorizer authorizer)
    throws AggCatException
  {
    this.authorizer = authorizer;
  }

  public IAuthorizer getAuthorizer()
  {
    return this.authorizer;
  }

  public void setAuthorizer(OAuthAuthorizer authorizer)
  {
    this.authorizer = authorizer;
  }
}