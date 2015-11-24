package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="Banking401KSourceType", namespace="http://schema.intuit.com/platform/fdatafeed/ibnktransaction/v1")
@XmlEnum
public enum Banking401KSourceType
{
  PRETAX,  AFTERTAX,  MATCH,  PROFITSHARING,  ROLLOVER,  OTHERVEST,  OTHERNONVEST;
  
  private Banking401KSourceType() {}
  
  public String value()
  {
    return name();
  }
  
  public static Banking401KSourceType fromValue(String v)
  {
    return valueOf(v);
  }
}
