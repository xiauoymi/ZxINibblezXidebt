package com.nibbledebt.intuit.cad.exception;

public class ConfigurationException extends AggCatException
{
  private static final long serialVersionUID = -8415364890369303956L;

  public ConfigurationException(String errorMessage)
  {
    super(errorMessage);
  }

  public ConfigurationException(Throwable throwable)
  {
    super(throwable);
  }

  public ConfigurationException(String errorMessage, Throwable throwable)
  {
    super(errorMessage, throwable);
  }
}