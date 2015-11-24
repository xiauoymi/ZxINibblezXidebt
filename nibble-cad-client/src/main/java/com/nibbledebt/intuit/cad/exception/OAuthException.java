package com.nibbledebt.intuit.cad.exception;

public class OAuthException
  extends AggCatException
{
  private static final long serialVersionUID = -2603418224402010418L;
  
  public OAuthException(String errorMessage)
  {
    super(errorMessage);
  }
  
  public OAuthException(Throwable throwable)
  {
    super(throwable);
  }
  
  public OAuthException(String errorMessage, Throwable throwable)
  {
    super(errorMessage, throwable);
  }
}
