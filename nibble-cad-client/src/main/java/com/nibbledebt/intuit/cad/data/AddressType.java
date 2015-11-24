package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="AddressType", namespace="http://schema.intuit.com/platform/fdatafeed/common/v1")
@XmlEnum
public enum AddressType
{
  HOME,  BUSINESS,  MAILING;
  
  private AddressType() {}
  
  public String value()
  {
    return name();
  }
  
  public static AddressType fromValue(String v)
  {
    return valueOf(v);
  }
}
