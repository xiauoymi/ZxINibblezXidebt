package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="InvSecurityType")
@XmlEnum
public enum InvSecurityType
{
  MFINFO,  STOCKINFO,  OPTINFO,  DEBTINFO,  OTHERINFO;
  
  private InvSecurityType() {}
  
  public String value()
  {
    return name();
  }
  
  public static InvSecurityType fromValue(String v)
  {
    return valueOf(v);
  }
}
