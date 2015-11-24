package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="accountTypeEnumeration", namespace="http://schema.intuit.com/platform/fdatafeed/common/v1")
@XmlEnum
public enum AccountTypeEnumeration
{
  CHECKING("checking"),  SAVINGS("savings"),  CD("cd"),  MONEY_MARKET("money market"),  RECURRING_DEPOSIT("recurring deposit"),  CASH_MANAGEMENT("cash management"),  OVERDRAFT("overdraft"),  CREDIT_CARD("credit card"),  LINE_OF_CREDIT("line of credit"),  TAXABLE_INVESTMENT("taxable investment"),  TAX_DEFERRED_INVESTMENT("tax-deferred investment"),  MORTGAGE("mortgage"),  LOAN("loan"),  ANNUITY("annuity"),  WHOLE_LIFE("whole life"),  REWARDS("rewards"),  EMAIL("email"),  BILLER("biller"),  TERM_INSURANCE("term insurance"),  ASSET("asset"),  LIABILITY("liability"),  OTHER("other");
  
  private final String value;
  
  private AccountTypeEnumeration(String v)
  {
    this.value = v;
  }
  
  public String value()
  {
    return this.value;
  }
  
  public static AccountTypeEnumeration fromValue(String v)
  {
    for (AccountTypeEnumeration c : values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }
}
