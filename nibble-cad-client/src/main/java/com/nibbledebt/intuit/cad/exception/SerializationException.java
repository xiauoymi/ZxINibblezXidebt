package com.nibbledebt.intuit.cad.exception;

public class SerializationException
  extends AggCatException
{
  private static final long serialVersionUID = 1L;
  
  public SerializationException(String errorMessage)
  {
    super(errorMessage);
  }
  
  public SerializationException(Throwable throwable)
  {
    super(throwable);
  }
  
  public SerializationException(String errorMessage, Throwable throwable)
  {
    super(errorMessage, throwable);
  }
}
