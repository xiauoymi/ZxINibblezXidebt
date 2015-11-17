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
@XmlType(name="", propOrder={"institutions"})
@XmlRootElement(name="Institutions", namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1")
public class Institutions
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;

  @XmlElement(name="institution", namespace="http://schema.intuit.com/platform/fdatafeed/institution/v1")
  protected List<Institution> institutions;

  public List<Institution> getInstitutions()
  {
    if (this.institutions == null) {
      this.institutions = new ArrayList();
    }
    return this.institutions;
  }

  public void setInstitutions(List<Institution> institutions)
  {
    this.institutions = institutions;
  }

  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
    if (!(object instanceof Institutions)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    Institutions that = (Institutions)object;

    List lhsInstitutions = (this.institutions != null) && (!this.institutions.isEmpty()) ? getInstitutions() : null;

    List rhsInstitutions = (that.institutions != null) && (!that.institutions.isEmpty()) ? that.getInstitutions() : null;
    if (!strategy.equals(LocatorUtils.property(thisLocator, "institutions", lhsInstitutions), LocatorUtils.property(thatLocator, "institutions", rhsInstitutions), lhsInstitutions, rhsInstitutions)) {
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

    List theInstitutions = (this.institutions != null) && (!this.institutions.isEmpty()) ? getInstitutions() : null;
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "institutions", theInstitutions), currentHashCode, theInstitutions);

    return currentHashCode;
  }

  public int hashCode() {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}