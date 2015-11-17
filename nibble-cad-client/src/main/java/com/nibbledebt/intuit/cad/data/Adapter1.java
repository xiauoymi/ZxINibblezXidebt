package com.nibbledebt.intuit.cad.data;

import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1 extends XmlAdapter<String, Calendar>
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