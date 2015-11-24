package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="TransferAction", namespace="http://schema.intuit.com/platform/fdatafeed/invtransaction/v1")
@XmlEnum
public enum TransferAction
{
  IN,  OUT;
  
  private TransferAction() {}
  
  public String value()
  {
    return name();
  }
  
  public static TransferAction fromValue(String v)
  {
    return valueOf(v);
  }
}
