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
@XmlType(name="LoanType", namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
@XmlEnum
public enum LoanType
{
  LOAN, 
  AUTO, 
  COMMERCIAL, 
  CONSTR, 
  CONSUMER, 
  HOMEEQUITY, 
  MILITARY, 
  MORTGAGE, 
  SMB, 
  STUDENT;

  public String value() {
    return name();
  }

  public static LoanType fromValue(String v) {
    return valueOf(v);
  }
}
