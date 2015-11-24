package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="CreditAccountType", namespace="http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
@XmlEnum
public enum CreditAccountType
{
  CREDITCARD,  LINEOFCREDIT,  OTHER;
  
  private CreditAccountType() {}
  
  public String value()
  {
    return name();
  }
  
  public static CreditAccountType fromValue(String v)
  {
    return valueOf(v);
  }
}
