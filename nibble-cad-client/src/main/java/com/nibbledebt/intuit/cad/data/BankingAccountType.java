package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="BankingAccountType", namespace="http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
@XmlEnum
public enum BankingAccountType
{
  CHECKING,  SAVINGS,  MONEYMRKT,  RECURRINGDEPOSIT,  CD,  CASHMANAGEMENT,  OVERDRAFT;
  
  private BankingAccountType() {}
  
  public String value()
  {
    return name();
  }
  
  public static BankingAccountType fromValue(String v)
  {
    return valueOf(v);
  }
}
