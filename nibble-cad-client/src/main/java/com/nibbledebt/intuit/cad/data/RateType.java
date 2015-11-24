package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="RateType", namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
@XmlEnum
public enum RateType
{
  FIXED,  FLOATING,  ARM;
  
  private RateType() {}
  
  public String value()
  {
    return name();
  }
  
  public static RateType fromValue(String v)
  {
    return valueOf(v);
  }
}
