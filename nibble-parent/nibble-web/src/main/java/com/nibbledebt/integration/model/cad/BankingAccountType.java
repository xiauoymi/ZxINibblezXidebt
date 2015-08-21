/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.model.cad;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * @author ralam
 *
 */
@XmlType(name="BankingAccountType", namespace="http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
@XmlEnum
public enum BankingAccountType
{
  CHECKING, 
  SAVINGS, 
  MONEYMRKT, 
  RECURRINGDEPOSIT, 
  CD, 
  CASHMANAGEMENT, 
  OVERDRAFT;

  public String value() {
    return name();
  }

  public static BankingAccountType fromValue(String v) {
    return valueOf(v);
  }
}