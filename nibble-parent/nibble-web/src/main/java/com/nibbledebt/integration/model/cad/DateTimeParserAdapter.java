/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.model.cad;

/**
 * @author ralam
 *
 */
import java.util.Calendar;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateTimeParserAdapter extends XmlAdapter<String, Calendar>
{
  public Calendar unmarshal(String value)
  {
    return DatatypeConverter.parseDateTime(value);
  }

  public String marshal(Calendar value) {
    if (value == null) {
      return null;
    }
    return DatatypeConverter.printDateTime(value);
  }
}