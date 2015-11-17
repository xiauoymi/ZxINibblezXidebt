package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="SellType", namespace="http://schema.intuit.com/platform/fdatafeed/invtransaction/v1")
@XmlEnum
public enum SellType
{
  SELL, 
  SELLSHORT;

  public String value() {
    return name();
  }

  public static SellType fromValue(String v) {
    return valueOf(v);
  }
}