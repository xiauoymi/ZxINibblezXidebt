package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
@XmlType(name="", propOrder={"status", "address", "emailAddress", "specialText", "currencyCode", "keys"})
@XmlRootElement(name="InstitutionDetail", namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1")
public class InstitutionDetail extends Institution
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;

  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1", required=true)
  protected String status;

  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1", required=true)
  protected Address address;

  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1")
  protected String emailAddress;

  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1")
  protected String specialText;

  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1")
  protected CurrencyCode currencyCode;

  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1", required=true)
  protected Keys keys;

  public String getStatus()
  {
    return this.status;
  }

  public void setStatus(String value)
  {
    this.status = value;
  }

  public Address getAddress()
  {
    return this.address;
  }

  public void setAddress(Address value)
  {
    this.address = value;
  }

  public String getEmailAddress()
  {
    return this.emailAddress;
  }

  public void setEmailAddress(String value)
  {
    this.emailAddress = value;
  }

  public String getSpecialText()
  {
    return this.specialText;
  }

  public void setSpecialText(String value)
  {
    this.specialText = value;
  }

  public CurrencyCode getCurrencyCode()
  {
    return this.currencyCode;
  }

  public void setCurrencyCode(CurrencyCode value)
  {
    this.currencyCode = value;
  }

  public Keys getKeys()
  {
    return this.keys;
  }

  public void setKeys(Keys value)
  {
    this.keys = value;
  }

  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
    if (!(object instanceof InstitutionDetail)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    if (!super.equals(thisLocator, thatLocator, object, strategy)) {
      return false;
    }
    InstitutionDetail that = (InstitutionDetail)object;

    String lhsStatus = getStatus();

    String rhsStatus = that.getStatus();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "status", lhsStatus), LocatorUtils.property(thatLocator, "status", rhsStatus), lhsStatus, rhsStatus)) {
      return false;
    }

    Address lhsAddress = getAddress();

    Address rhsAddress = that.getAddress();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "address", lhsAddress), LocatorUtils.property(thatLocator, "address", rhsAddress), lhsAddress, rhsAddress)) {
      return false;
    }

    String lhsEmailAddress = getEmailAddress();

    String rhsEmailAddress = that.getEmailAddress();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "emailAddress", lhsEmailAddress), LocatorUtils.property(thatLocator, "emailAddress", rhsEmailAddress), lhsEmailAddress, rhsEmailAddress)) {
      return false;
    }

    String lhsSpecialText = getSpecialText();

    String rhsSpecialText = that.getSpecialText();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "specialText", lhsSpecialText), LocatorUtils.property(thatLocator, "specialText", rhsSpecialText), lhsSpecialText, rhsSpecialText)) {
      return false;
    }

    CurrencyCode lhsCurrencyCode = getCurrencyCode();

    CurrencyCode rhsCurrencyCode = that.getCurrencyCode();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "currencyCode", lhsCurrencyCode), LocatorUtils.property(thatLocator, "currencyCode", rhsCurrencyCode), lhsCurrencyCode, rhsCurrencyCode)) {
      return false;
    }

    Keys lhsKeys = getKeys();

    Keys rhsKeys = that.getKeys();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "keys", lhsKeys), LocatorUtils.property(thatLocator, "keys", rhsKeys), lhsKeys, rhsKeys)) {
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

    String theStatus = getStatus();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "status", theStatus), currentHashCode, theStatus);

    Address theAddress = getAddress();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "address", theAddress), currentHashCode, theAddress);

    String theEmailAddress = getEmailAddress();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "emailAddress", theEmailAddress), currentHashCode, theEmailAddress);

    String theSpecialText = getSpecialText();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "specialText", theSpecialText), currentHashCode, theSpecialText);

    CurrencyCode theCurrencyCode = getCurrencyCode();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "currencyCode", theCurrencyCode), currentHashCode, theCurrencyCode);

    Keys theKeys = getKeys();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "keys", theKeys), currentHashCode, theKeys);

    return currentHashCode;
  }

  public int hashCode() {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"keies"})
  public static class Keys
    implements Serializable, Equals, HashCode
  {
    private static final long serialVersionUID = 1L;

    @XmlElement(name="key", namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1")
    protected List<Key> keies;

    public List<Key> getKeies()
    {
      if (this.keies == null) {
        this.keies = new ArrayList();
      }
      return this.keies;
    }

    public void setKeies(List<Key> keies)
    {
      this.keies = keies;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof Keys)) {
        return false;
      }
      if (this == object) {
        return true;
      }
      Keys that = (Keys)object;

      List lhsKeies = (this.keies != null) && (!this.keies.isEmpty()) ? getKeies() : null;

      List rhsKeies = (that.keies != null) && (!that.keies.isEmpty()) ? that.getKeies() : null;
      if (!strategy.equals(LocatorUtils.property(thisLocator, "keies", lhsKeies), LocatorUtils.property(thatLocator, "keies", rhsKeies), lhsKeies, rhsKeies)) {
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

      List theKeies = (this.keies != null) && (!this.keies.isEmpty()) ? getKeies() : null;
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "keies", theKeies), currentHashCode, theKeies);

      return currentHashCode;
    }

    public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return hashCode(null, strategy);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"name", "val", "status", "valueLengthMin", "valueLengthMax", "displayFlag", "displayOrder", "mask", "instructions", "description"})
    public static class Key
      implements Serializable, Equals, HashCode
    {
      private static final long serialVersionUID = 1L;

      @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1", required=true)
      protected String name;

      @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1", required=true)
      protected String val;

      @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1", required=true)
      protected String status;

      @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1")
      protected Integer valueLengthMin;

      @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1")
      protected Integer valueLengthMax;

      @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1")
      protected boolean displayFlag;

      @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1")
      protected int displayOrder;

      @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1")
      protected boolean mask;

      @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1")
      protected String instructions;

      @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1")
      protected String description;

      public String getName()
      {
        return this.name;
      }

      public void setName(String value)
      {
        this.name = value;
      }

      public String getVal()
      {
        return this.val;
      }

      public void setVal(String value)
      {
        this.val = value;
      }

      public String getStatus()
      {
        return this.status;
      }

      public void setStatus(String value)
      {
        this.status = value;
      }

      public Integer getValueLengthMin()
      {
        return this.valueLengthMin;
      }

      public void setValueLengthMin(Integer value)
      {
        this.valueLengthMin = value;
      }

      public Integer getValueLengthMax()
      {
        return this.valueLengthMax;
      }

      public void setValueLengthMax(Integer value)
      {
        this.valueLengthMax = value;
      }

      public boolean isDisplayFlag()
      {
        return this.displayFlag;
      }

      public void setDisplayFlag(boolean value)
      {
        this.displayFlag = value;
      }

      public int getDisplayOrder()
      {
        return this.displayOrder;
      }

      public void setDisplayOrder(int value)
      {
        this.displayOrder = value;
      }

      public boolean isMask()
      {
        return this.mask;
      }

      public void setMask(boolean value)
      {
        this.mask = value;
      }

      public String getInstructions()
      {
        return this.instructions;
      }

      public void setInstructions(String value)
      {
        this.instructions = value;
      }

      public String getDescription()
      {
        return this.description;
      }

      public void setDescription(String value)
      {
        this.description = value;
      }

      public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Key)) {
          return false;
        }
        if (this == object) {
          return true;
        }
        Key that = (Key)object;

        String lhsName = getName();

        String rhsName = that.getName();
        if (!strategy.equals(LocatorUtils.property(thisLocator, "name", lhsName), LocatorUtils.property(thatLocator, "name", rhsName), lhsName, rhsName)) {
          return false;
        }

        String lhsVal = getVal();

        String rhsVal = that.getVal();
        if (!strategy.equals(LocatorUtils.property(thisLocator, "val", lhsVal), LocatorUtils.property(thatLocator, "val", rhsVal), lhsVal, rhsVal)) {
          return false;
        }

        String lhsStatus = getStatus();

        String rhsStatus = that.getStatus();
        if (!strategy.equals(LocatorUtils.property(thisLocator, "status", lhsStatus), LocatorUtils.property(thatLocator, "status", rhsStatus), lhsStatus, rhsStatus)) {
          return false;
        }

        Integer lhsValueLengthMin = getValueLengthMin();

        Integer rhsValueLengthMin = that.getValueLengthMin();
        if (!strategy.equals(LocatorUtils.property(thisLocator, "valueLengthMin", lhsValueLengthMin), LocatorUtils.property(thatLocator, "valueLengthMin", rhsValueLengthMin), lhsValueLengthMin, rhsValueLengthMin)) {
          return false;
        }

        Integer lhsValueLengthMax = getValueLengthMax();

        Integer rhsValueLengthMax = that.getValueLengthMax();
        if (!strategy.equals(LocatorUtils.property(thisLocator, "valueLengthMax", lhsValueLengthMax), LocatorUtils.property(thatLocator, "valueLengthMax", rhsValueLengthMax), lhsValueLengthMax, rhsValueLengthMax)) {
          return false;
        }

        boolean lhsDisplayFlag = isDisplayFlag();

        boolean rhsDisplayFlag = that.isDisplayFlag();
        if (!strategy.equals(LocatorUtils.property(thisLocator, "displayFlag", lhsDisplayFlag), LocatorUtils.property(thatLocator, "displayFlag", rhsDisplayFlag), lhsDisplayFlag, rhsDisplayFlag)) {
          return false;
        }

        int lhsDisplayOrder = getDisplayOrder();

        int rhsDisplayOrder = that.getDisplayOrder();
        if (!strategy.equals(LocatorUtils.property(thisLocator, "displayOrder", lhsDisplayOrder), LocatorUtils.property(thatLocator, "displayOrder", rhsDisplayOrder), lhsDisplayOrder, rhsDisplayOrder)) {
          return false;
        }

        boolean lhsMask = isMask();

        boolean rhsMask = that.isMask();
        if (!strategy.equals(LocatorUtils.property(thisLocator, "mask", lhsMask), LocatorUtils.property(thatLocator, "mask", rhsMask), lhsMask, rhsMask)) {
          return false;
        }

        String lhsInstructions = getInstructions();

        String rhsInstructions = that.getInstructions();
        if (!strategy.equals(LocatorUtils.property(thisLocator, "instructions", lhsInstructions), LocatorUtils.property(thatLocator, "instructions", rhsInstructions), lhsInstructions, rhsInstructions)) {
          return false;
        }

        String lhsDescription = getDescription();

        String rhsDescription = that.getDescription();
        if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription)) {
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

        String theName = getName();
        currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "name", theName), currentHashCode, theName);

        String theVal = getVal();
        currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "val", theVal), currentHashCode, theVal);

        String theStatus = getStatus();
        currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "status", theStatus), currentHashCode, theStatus);

        Integer theValueLengthMin = getValueLengthMin();
        currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "valueLengthMin", theValueLengthMin), currentHashCode, theValueLengthMin);

        Integer theValueLengthMax = getValueLengthMax();
        currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "valueLengthMax", theValueLengthMax), currentHashCode, theValueLengthMax);

        boolean theDisplayFlag = isDisplayFlag();
        currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "displayFlag", theDisplayFlag), currentHashCode, theDisplayFlag);

        int theDisplayOrder = getDisplayOrder();
        currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "displayOrder", theDisplayOrder), currentHashCode, theDisplayOrder);

        boolean theMask = isMask();
        currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "mask", theMask), currentHashCode, theMask);

        String theInstructions = getInstructions();
        currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "instructions", theInstructions), currentHashCode, theInstructions);

        String theDescription = getDescription();
        currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription);

        return currentHashCode;
      }

      public int hashCode() {
        HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return hashCode(null, strategy);
      }
    }
  }
}