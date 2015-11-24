package com.nibbledebt.intuit.cad.exception;

public class SamlAssertionException
  extends AggCatException
{
  private static final long serialVersionUID = -8507597897919155361L;
  
  public SamlAssertionException(String errorMessage, Throwable throwable)
  {
    super(errorMessage, throwable);
  }
  
  public SamlAssertionException(String errorMessage)
  {
    super(errorMessage);
  }
  
  public SamlAssertionException(Throwable throwable)
  {
    super(throwable);
  }
}
