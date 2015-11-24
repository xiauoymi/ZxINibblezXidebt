package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="PaymentType", namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
@XmlEnum
public enum PaymentType
{
  INT_ONLY,  PRN_AND_INT,  PRN_PLUS_INT;
  
  private PaymentType() {}
  
  public String value()
  {
    return name();
  }
  
  public static PaymentType fromValue(String v)
  {
    return valueOf(v);
  }
}
