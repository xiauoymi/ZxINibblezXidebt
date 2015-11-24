package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="SellReason", namespace="http://schema.intuit.com/platform/fdatafeed/invtransaction/v1")
@XmlEnum
public enum SellReason
{
  CALL,  SELL,  MATURITY;
  
  private SellReason() {}
  
  public String value()
  {
    return name();
  }
  
  public static SellReason fromValue(String v)
  {
    return valueOf(v);
  }
}
