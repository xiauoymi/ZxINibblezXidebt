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
@XmlType(name="", propOrder={"positions"})
@XmlRootElement(name="InvestmentPositions", namespace="http://schema.intuit.com/platform/fdatafeed/invposition/v1")
public class InvestmentPositions
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  @XmlElement(name="position", namespace="http://schema.intuit.com/platform/fdatafeed/invposition/v1")
  protected List<InvestmentPosition> positions;
  
  public List<InvestmentPosition> getPositions()
  {
    if (this.positions == null) {
      this.positions = new ArrayList();
    }
    return this.positions;
  }
  
  public void setPositions(List<InvestmentPosition> positions)
  {
    this.positions = positions;
  }
  
  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy)
  {
    if (!(object instanceof InvestmentPositions)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    InvestmentPositions that = (InvestmentPositions)object;
    
    List<InvestmentPosition> lhsPositions = (this.positions != null) && (!this.positions.isEmpty()) ? getPositions() : null;
    
    List<InvestmentPosition> rhsPositions = (that.positions != null) && (!that.positions.isEmpty()) ? that.getPositions() : null;
    if (!strategy.equals(LocatorUtils.property(thisLocator, "positions", lhsPositions), LocatorUtils.property(thatLocator, "positions", rhsPositions), lhsPositions, rhsPositions)) {
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
    
    List<InvestmentPosition> thePositions = (this.positions != null) && (!this.positions.isEmpty()) ? getPositions() : null;
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "positions", thePositions), currentHashCode, thePositions);
    
    return currentHashCode;
  }
  
  public int hashCode()
  {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}
