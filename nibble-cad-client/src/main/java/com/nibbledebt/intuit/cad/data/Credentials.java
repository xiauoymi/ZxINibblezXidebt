package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
@XmlType(name="Credentials", namespace="http://schema.intuit.com/platform/fdatafeed/institutionlogin/v1", propOrder={"credentials"})
public class Credentials
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  @XmlElement(name="credential")
  protected List<Credential> credentials;
  
  public List<Credential> getCredentials()
  {
    if (this.credentials == null) {
      this.credentials = new ArrayList();
    }
    return this.credentials;
  }
  
  public void setCredentials(List<Credential> credentials)
  {
    this.credentials = credentials;
  }
  
  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy)
  {
    if (!(object instanceof Credentials)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    Credentials that = (Credentials)object;
    
    List<Credential> lhsCredentials = (this.credentials != null) && (!this.credentials.isEmpty()) ? getCredentials() : null;
    
    List<Credential> rhsCredentials = (that.credentials != null) && (!that.credentials.isEmpty()) ? that.getCredentials() : null;
    if (!strategy.equals(LocatorUtils.property(thisLocator, "credentials", lhsCredentials), LocatorUtils.property(thatLocator, "credentials", rhsCredentials), lhsCredentials, rhsCredentials)) {
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
    
    List<Credential> theCredentials = (this.credentials != null) && (!this.credentials.isEmpty()) ? getCredentials() : null;
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "credentials", theCredentials), currentHashCode, theCredentials);
    
    return currentHashCode;
  }
  
  public int hashCode()
  {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}
