package com.nibbledebt.intuit.cad.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.nibbledebt.intuit.cad.exception.AggCatException;

public final class StringUtils
{
  private static final org.slf4j.Logger LOG = Logger.getLogger();

  public static StringUtils getInstance()
  {
    return new StringUtils();
  }

  public static boolean hasText(String text)
  {
    if ((text != null) && (!"".equals(text.trim()))) {
      return true;
    }
    return false;
  }

  public static String getContent(InputStream in)
    throws AggCatException
  {
    BufferedReader br = new BufferedReader(new InputStreamReader(in));
    StringBuilder sb = new StringBuilder();
    try
    {
      String line;
      while ((line = br.readLine()) != null)
        sb.append(line);
    }
    catch (IOException ioe) {
      LOG.error("IOException while read the content from input stream.", ioe);
      throw new AggCatException(ioe);
    }

    return sb.toString();
  }
}