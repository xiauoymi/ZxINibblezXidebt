package com.nibbledebt.intuit.cad.exception;

import com.nibbledebt.intuit.cad.data.Status;

public class ServiceUnavailableException
  extends AggCatException
{
  private static final long serialVersionUID = 2488689372746746885L;
  
  public ServiceUnavailableException(String errorMessage)
  {
    super(errorMessage);
  }
  
  public ServiceUnavailableException(String errorCode, String errorMessage)
  {
    super(errorCode, errorMessage);
  }
  
  public ServiceUnavailableException(String errorCode, String errorMessage, Status status, String errorResponseText)
  {
    super(errorCode, errorMessage, status, errorResponseText);
  }
  
  public ServiceUnavailableException(Throwable throwable)
  {
    super(throwable);
  }
  
  public ServiceUnavailableException(String errorMessage, Throwable throwable)
  {
    super(errorMessage, throwable);
  }
  
  public ServiceUnavailableException(String errorCode, String errorMessage, Throwable throwable)
  {
    super(errorCode, errorMessage, throwable);
  }
}
