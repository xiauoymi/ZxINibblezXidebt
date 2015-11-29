package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Institutions", namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1")
public class Institutions
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  
  @JacksonXmlElementWrapper(useWrapping=false)
  protected List<Institution> institution;
  
  public List<Institution> getInstitution()
  {
    if (this.institution == null) {
      this.institution = new ArrayList<>();
    }
    return this.institution;
  }
  
  public void setInstitution(List<Institution> institution)
  {
    this.institution = institution;
  }
  
  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy)
  {
    if (!(object instanceof Institutions)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    Institutions that = (Institutions)object;
    
    List<Institution> lhsInstitutions = (this.institution != null) && (!this.institution.isEmpty()) ? getInstitution() : null;
    
    List<Institution> rhsInstitutions = (that.institution != null) && (!that.institution.isEmpty()) ? that.getInstitution() : null;
    if (!strategy.equals(LocatorUtils.property(thisLocator, "institutions", lhsInstitutions), LocatorUtils.property(thatLocator, "institutions", rhsInstitutions), lhsInstitutions, rhsInstitutions)) {
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
    
    List<Institution> theInstitutions = (this.institution != null) && (!this.institution.isEmpty()) ? getInstitution() : null;
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "institutions", theInstitutions), currentHashCode, theInstitutions);
    
    return currentHashCode;
  }
  
  public int hashCode()
  {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}
