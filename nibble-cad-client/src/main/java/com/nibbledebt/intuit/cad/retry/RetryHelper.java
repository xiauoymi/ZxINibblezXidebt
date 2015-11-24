package com.nibbledebt.intuit.cad.retry;

import com.nibbledebt.intuit.cad.exception.AggCatException;

public final class RetryHelper
{
  public static void argumentNotNegativeValue(long argumentValue, String argumentName)
    throws AggCatException
  {
    if (argumentValue < 0L) {
      throw new AggCatException("The value for argument name [" + argumentName + "] is negative [" + argumentValue + "]. Please check the configuration.");
    }
  }
}
