package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="RelatedOptionTransType", namespace="http://schema.intuit.com/platform/fdatafeed/invtransaction/v1")
@XmlEnum
public enum RelatedOptionTransType
{
  SPREAD, 
  STRADDLE, 
  NONE, 
  OTHER;

  public String value() {
    return name();
  }

  public static RelatedOptionTransType fromValue(String v) {
    return valueOf(v);
  }
}