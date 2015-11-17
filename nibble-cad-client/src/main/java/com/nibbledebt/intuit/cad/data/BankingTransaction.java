package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="BankingTransaction", namespace="http://schema.intuit.com/platform/fdatafeed/bnktransaction/v1")
@XmlRootElement(name="BankingTransaction", namespace="http://schema.intuit.com/platform/fdatafeed/bnktransaction/v1")
public class BankingTransaction extends Transaction
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;

  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy)
  {
    if (!(object instanceof BankingTransaction)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    if (!super.equals(thisLocator, thatLocator, object, strategy)) {
      return false;
    }
    return true;
  }

  public boolean equals(Object object) {
    EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
    return equals(null, null, object, strategy);
  }

  public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
    int currentHashCode = super.hashCode(locator, strategy);
    return currentHashCode;
  }

  public int hashCode() {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}