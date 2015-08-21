package com.nibbledebt.integration.model.cad;

import java.util.Calendar;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateParseAdapter extends XmlAdapter<String, Calendar>
{
  public Calendar unmarshal(String value)
  {
    return DatatypeConverter.parseDate(value);
  }

  public String marshal(Calendar value) {
    if (value == null) {
      return null;
    }
    return DatatypeConverter.printDate(value);
  }
}