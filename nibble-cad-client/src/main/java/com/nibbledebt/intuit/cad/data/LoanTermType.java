package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="LoanTermType", namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
@XmlEnum
public enum LoanTermType
{
  COMBO,  FIXED,  REVOLVE,  OPEN;
  
  private LoanTermType() {}
  
  public String value()
  {
    return name();
  }
  
  public static LoanTermType fromValue(String v)
  {
    return valueOf(v);
  }
}
