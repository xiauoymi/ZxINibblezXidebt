package com.nibbledebt.intuit.cad.exception;

import com.nibbledebt.intuit.cad.data.Status;

public class InvalidProxyException
  extends AggCatException
{
  private static final long serialVersionUID = 2488689372746746885L;
  
  public InvalidProxyException(String errorMessage)
  {
    super(errorMessage);
  }
  
  public InvalidProxyException(String errorCode, String errorMessage)
  {
    super(errorCode, errorMessage);
  }
  
  public InvalidProxyException(String errorCode, String errorMessage, Status status, String errorResponseText)
  {
    super(errorCode, errorMessage, status, errorResponseText);
  }
  
  public InvalidProxyException(Throwable throwable)
  {
    super(throwable);
  }
  
  public InvalidProxyException(String errorMessage, Throwable throwable)
  {
    super(errorMessage, throwable);
  }
  
  public InvalidProxyException(String errorCode, String errorMessage, Throwable throwable)
  {
    super(errorCode, errorMessage, throwable);
  }
}
