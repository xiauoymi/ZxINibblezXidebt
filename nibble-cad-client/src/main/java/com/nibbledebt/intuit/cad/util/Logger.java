package com.nibbledebt.intuit.cad.util;

import org.slf4j.LoggerFactory;

public final class Logger
{
  private static org.slf4j.Logger sInstance;
  
  public static org.slf4j.Logger getLogger()
  {
    if (null == sInstance) {
      sInstance = LoggerFactory.getLogger("com.intuit.aggcat.logger");
    }
    return sInstance;
  }
}
