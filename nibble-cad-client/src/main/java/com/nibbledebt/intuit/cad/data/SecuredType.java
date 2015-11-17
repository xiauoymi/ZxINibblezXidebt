package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="SecuredType", namespace="http://schema.intuit.com/platform/fdatafeed/invtransaction/v1")
@XmlEnum
public enum SecuredType
{
  NAKED, 
  COVERED;

  public String value() {
    return name();
  }

  public static SecuredType fromValue(String v) {
    return valueOf(v);
  }
}