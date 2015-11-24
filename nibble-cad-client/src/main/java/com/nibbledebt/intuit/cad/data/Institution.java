package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

import com.fasterxml.jackson.annotation.JsonRootName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Institution", namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1", propOrder={"institutionId", "institutionName", "homeUrl", "phoneNumber", "virtual"})
@XmlSeeAlso({InstitutionDetail.class})
@JsonRootName("institution")
public class Institution
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 15234544L;
  @XmlElement()
  protected long institutionId;
  @XmlElement(required=true)
  protected String institutionName;
  @XmlElement()
  protected String homeUrl;
  @XmlElement()
  protected String phoneNumber;
  @XmlElement()
  protected Boolean virtual;
  
  public Institution(){}
  
  public Institution(long institutionId, String institutionName, String homeUrl, String phoneNumber, Boolean virtual) {
	super();
	this.institutionId = institutionId;
	this.institutionName = institutionName;
	this.homeUrl = homeUrl;
	this.phoneNumber = phoneNumber;
	this.virtual = virtual;
}

public long getInstitutionId()
  {
    return this.institutionId;
  }
  
  public void setInstitutionId(long value)
  {
    this.institutionId = value;
  }
  
  public String getInstitutionName()
  {
    return this.institutionName;
  }
  
  public void setInstitutionName(String value)
  {
    this.institutionName = value;
  }
  
  public String getHomeUrl()
  {
    return this.homeUrl;
  }
  
  public void setHomeUrl(String value)
  {
    this.homeUrl = value;
  }
  
  public String getPhoneNumber()
  {
    return this.phoneNumber;
  }
  
  public void setPhoneNumber(String value)
  {
    this.phoneNumber = value;
  }
  
  public Boolean isVirtual()
  {
    return this.virtual;
  }
  
  public void setVirtual(Boolean value)
  {
    this.virtual = value;
  }
  
  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy)
  {
    if (!(object instanceof Institution)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    Institution that = (Institution)object;
    
    long lhsInstitutionId = getInstitutionId();
    
    long rhsInstitutionId = that.getInstitutionId();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "institutionId", lhsInstitutionId), LocatorUtils.property(thatLocator, "institutionId", rhsInstitutionId), lhsInstitutionId, rhsInstitutionId)) {
      return false;
    }
    String lhsInstitutionName = getInstitutionName();
    
    String rhsInstitutionName = that.getInstitutionName();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "institutionName", lhsInstitutionName), LocatorUtils.property(thatLocator, "institutionName", rhsInstitutionName), lhsInstitutionName, rhsInstitutionName)) {
      return false;
    }
    String lhsHomeUrl = getHomeUrl();
    
    String rhsHomeUrl = that.getHomeUrl();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "homeUrl", lhsHomeUrl), LocatorUtils.property(thatLocator, "homeUrl", rhsHomeUrl), lhsHomeUrl, rhsHomeUrl)) {
      return false;
    }
    String lhsPhoneNumber = getPhoneNumber();
    
    String rhsPhoneNumber = that.getPhoneNumber();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "phoneNumber", lhsPhoneNumber), LocatorUtils.property(thatLocator, "phoneNumber", rhsPhoneNumber), lhsPhoneNumber, rhsPhoneNumber)) {
      return false;
    }
    Boolean lhsVirtual = isVirtual();
    
    Boolean rhsVirtual = that.isVirtual();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "virtual", lhsVirtual), LocatorUtils.property(thatLocator, "virtual", rhsVirtual), lhsVirtual, rhsVirtual)) {
      return false;
    }
    return true;
  }
  
  public boolean equals(Object object)
  {
    EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
    return equals(null, null, object, strategy);
  }
  
  public int hashCode(ObjectLocator locator, HashCodeStrategy strategy)
  {
    int currentHashCode = 1;
    
    long theInstitutionId = getInstitutionId();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "institutionId", theInstitutionId), currentHashCode, theInstitutionId);
    
    String theInstitutionName = getInstitutionName();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "institutionName", theInstitutionName), currentHashCode, theInstitutionName);
    
    String theHomeUrl = getHomeUrl();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "homeUrl", theHomeUrl), currentHashCode, theHomeUrl);
    
    String thePhoneNumber = getPhoneNumber();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "phoneNumber", thePhoneNumber), currentHashCode, thePhoneNumber);
    
    Boolean theVirtual = isVirtual();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "virtual", theVirtual), currentHashCode, theVirtual);
    
    return currentHashCode;
  }
  
  public int hashCode()
  {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}
