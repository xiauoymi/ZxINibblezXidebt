package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="OtherInfo", propOrder={"typeDesc"})
public class OtherInfo extends SecurityInfo
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  protected String typeDesc;

  public String getTypeDesc()
  {
    return this.typeDesc;
  }

  public void setTypeDesc(String value)
  {
    this.typeDesc = value;
  }

  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
    if (!(object instanceof OtherInfo)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    if (!super.equals(thisLocator, thatLocator, object, strategy)) {
      return false;
    }
    OtherInfo that = (OtherInfo)object;

    String lhsTypeDesc = getTypeDesc();

    String rhsTypeDesc = that.getTypeDesc();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "typeDesc", lhsTypeDesc), LocatorUtils.property(thatLocator, "typeDesc", rhsTypeDesc), lhsTypeDesc, rhsTypeDesc)) {
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

    String theTypeDesc = getTypeDesc();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "typeDesc", theTypeDesc), currentHashCode, theTypeDesc);

    return currentHashCode;
  }

  public int hashCode() {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}