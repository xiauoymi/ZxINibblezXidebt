package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="BuyType", namespace="http://schema.intuit.com/platform/fdatafeed/invtransaction/v1")
@XmlEnum
public enum BuyType
{
  BUY,  BUYTOCOVER;
  
  private BuyType() {}
  
  public String value()
  {
    return name();
  }
  
  public static BuyType fromValue(String v)
  {
    return valueOf(v);
  }
}
