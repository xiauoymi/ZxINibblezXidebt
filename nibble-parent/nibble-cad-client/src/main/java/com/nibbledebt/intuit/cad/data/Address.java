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
@XmlType(name="Address", namespace="http://schema.intuit.com/platform/fdatafeed/common/v1", propOrder={"type", "address1", "address2", "address3", "city", "state", "postalCode", "country"})
public class Address
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  protected AddressType type;
  protected String address1;
  protected String address2;
  protected String address3;
  protected String city;
  protected String state;
  protected String postalCode;
  protected String country;

  public AddressType getType()
  {
    return this.type;
  }

  public void setType(AddressType value)
  {
    this.type = value;
  }

  public String getAddress1()
  {
    return this.address1;
  }

  public void setAddress1(String value)
  {
    this.address1 = value;
  }

  public String getAddress2()
  {
    return this.address2;
  }

  public void setAddress2(String value)
  {
    this.address2 = value;
  }

  public String getAddress3()
  {
    return this.address3;
  }

  public void setAddress3(String value)
  {
    this.address3 = value;
  }

  public String getCity()
  {
    return this.city;
  }

  public void setCity(String value)
  {
    this.city = value;
  }

  public String getState()
  {
    return this.state;
  }

  public void setState(String value)
  {
    this.state = value;
  }

  public String getPostalCode()
  {
    return this.postalCode;
  }

  public void setPostalCode(String value)
  {
    this.postalCode = value;
  }

  public String getCountry()
  {
    return this.country;
  }

  public void setCountry(String value)
  {
    this.country = value;
  }

  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
    if (!(object instanceof Address)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    Address that = (Address)object;

    AddressType lhsType = getType();

    AddressType rhsType = that.getType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "type", lhsType), LocatorUtils.property(thatLocator, "type", rhsType), lhsType, rhsType)) {
      return false;
    }

    String lhsAddress1 = getAddress1();

    String rhsAddress1 = that.getAddress1();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "address1", lhsAddress1), LocatorUtils.property(thatLocator, "address1", rhsAddress1), lhsAddress1, rhsAddress1)) {
      return false;
    }

    String lhsAddress2 = getAddress2();

    String rhsAddress2 = that.getAddress2();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "address2", lhsAddress2), LocatorUtils.property(thatLocator, "address2", rhsAddress2), lhsAddress2, rhsAddress2)) {
      return false;
    }

    String lhsAddress3 = getAddress3();

    String rhsAddress3 = that.getAddress3();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "address3", lhsAddress3), LocatorUtils.property(thatLocator, "address3", rhsAddress3), lhsAddress3, rhsAddress3)) {
      return false;
    }

    String lhsCity = getCity();

    String rhsCity = that.getCity();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "city", lhsCity), LocatorUtils.property(thatLocator, "city", rhsCity), lhsCity, rhsCity)) {
      return false;
    }

    String lhsState = getState();

    String rhsState = that.getState();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "state", lhsState), LocatorUtils.property(thatLocator, "state", rhsState), lhsState, rhsState)) {
      return false;
    }

    String lhsPostalCode = getPostalCode();

    String rhsPostalCode = that.getPostalCode();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "postalCode", lhsPostalCode), LocatorUtils.property(thatLocator, "postalCode", rhsPostalCode), lhsPostalCode, rhsPostalCode)) {
      return false;
    }

    String lhsCountry = getCountry();

    String rhsCountry = that.getCountry();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "country", lhsCountry), LocatorUtils.property(thatLocator, "country", rhsCountry), lhsCountry, rhsCountry)) {
      return false;
    }

    return true;
  }

  public boolean equals(Object object) {
    EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
    return equals(null, null, object, strategy);
  }

  public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
    int currentHashCode = 1;

    AddressType theType = getType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "type", theType), currentHashCode, theType);

    String theAddress1 = getAddress1();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "address1", theAddress1), currentHashCode, theAddress1);

    String theAddress2 = getAddress2();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "address2", theAddress2), currentHashCode, theAddress2);

    String theAddress3 = getAddress3();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "address3", theAddress3), currentHashCode, theAddress3);

    String theCity = getCity();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "city", theCity), currentHashCode, theCity);

    String theState = getState();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "state", theState), currentHashCode, theState);

    String thePostalCode = getPostalCode();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "postalCode", thePostalCode), currentHashCode, thePostalCode);

    String theCountry = getCountry();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "country", theCountry), currentHashCode, theCountry);

    return currentHashCode;
  }

  public int hashCode() {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}