package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="ErrorType", namespace="http://schema.intuit.com/platform/fdatafeed/common/v1")
@XmlEnum
public enum ErrorType
{
  SYSTEM_ERROR,  APP_ERROR,  USER_ERROR,  WARNING;
  
  private ErrorType() {}
  
  public String value()
  {
    return name();
  }
  
  public static ErrorType fromValue(String v)
  {
    return valueOf(v);
  }
}
