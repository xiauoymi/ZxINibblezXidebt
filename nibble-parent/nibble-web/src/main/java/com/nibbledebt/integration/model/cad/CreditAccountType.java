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
@XmlType(name="CreditAccountType", namespace="http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
@XmlEnum
public enum CreditAccountType
{
  CREDITCARD, 
  LINEOFCREDIT, 
  OTHER;

  public String value() {
    return name();
  }

  public static CreditAccountType fromValue(String v) {
    return valueOf(v);
  }
}
