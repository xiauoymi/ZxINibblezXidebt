package com.nibbledebt.intuit.cad.exception;

import com.nibbledebt.intuit.cad.data.Status;

public class InvalidRequestException extends AggCatException
{
  private static final long serialVersionUID = -2152752316496012401L;

  public InvalidRequestException(String errorMessage)
  {
    super(errorMessage);
  }

  public InvalidRequestException(String errorCode, String errorMessage)
  {
    super(errorCode, errorMessage);
  }

  public InvalidRequestException(String errorCode, String errorMessage, Status status, String errorResponseText)
  {
    super(errorCode, errorMessage, status, errorResponseText);
  }

  public InvalidRequestException(Throwable throwable)
  {
    super(throwable);
  }

  public InvalidRequestException(String errorMessage, Throwable throwable)
  {
    super(errorMessage, throwable);
  }

  public InvalidRequestException(String errorCode, String errorMessage, Throwable throwable)
  {
    super(errorCode, errorMessage, throwable);
  }
}