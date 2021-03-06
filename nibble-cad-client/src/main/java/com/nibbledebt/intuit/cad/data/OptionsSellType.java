package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="OptionsSellType", namespace="http://schema.intuit.com/platform/fdatafeed/invtransaction/v1")
@XmlEnum
public enum OptionsSellType
{
  SELLTOOPEN,  SELLTOCLOSE;
  
  private OptionsSellType() {}
  
  public String value()
  {
    return name();
  }
  
  public static OptionsSellType fromValue(String v)
  {
    return valueOf(v);
  }
}
