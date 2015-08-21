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
@XmlType(name="LoanTermType", namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
@XmlEnum
public enum LoanTermType
{
  COMBO, 
  FIXED, 
  REVOLVE, 
  OPEN;

  public String value() {
    return name();
  }

  public static LoanTermType fromValue(String v) {
    return valueOf(v);
  }
}
