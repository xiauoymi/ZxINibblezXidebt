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
@XmlType(name="ChallengeResponses", namespace="http://schema.intuit.com/platform/fdatafeed/challenge/v1", propOrder={"responses"})
public class ChallengeResponses
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  @XmlElement(name="response", required=true)
  protected List<String> responses;
  
  public List<String> getResponses()
  {
    if (this.responses == null) {
      this.responses = new ArrayList();
    }
    return this.responses;
  }
  
  public void setResponses(List<String> responses)
  {
    this.responses = responses;
  }
  
  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy)
  {
    if (!(object instanceof ChallengeResponses)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    ChallengeResponses that = (ChallengeResponses)object;
    
    List<String> lhsResponses = (this.responses != null) && (!this.responses.isEmpty()) ? getResponses() : null;
    
    List<String> rhsResponses = (that.responses != null) && (!that.responses.isEmpty()) ? that.getResponses() : null;
    if (!strategy.equals(LocatorUtils.property(thisLocator, "responses", lhsResponses), LocatorUtils.property(thatLocator, "responses", rhsResponses), lhsResponses, rhsResponses)) {
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
    
    List<String> theResponses = (this.responses != null) && (!this.responses.isEmpty()) ? getResponses() : null;
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "responses", theResponses), currentHashCode, theResponses);
    
    return currentHashCode;
  }
  
  public int hashCode()
  {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}
