package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;
import java.math.BigDecimal;

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
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="LoanTransaction", namespace="http://schema.intuit.com/platform/fdatafeed/loantransaction/v1", propOrder={"principalAmount", "interestAmount", "escrowTotalAmount", "escrowTaxAmount", "escrowInsuranceAmount", "escrowPmiAmount", "escrowFeesAmount", "escrowOtherAmount"})
@XmlRootElement(name="LoanTransaction", namespace="http://schema.intuit.com/platform/fdatafeed/loantransaction/v1")
public class LoanTransaction
  extends Transaction
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  protected BigDecimal principalAmount;
  protected BigDecimal interestAmount;
  protected BigDecimal escrowTotalAmount;
  protected BigDecimal escrowTaxAmount;
  protected BigDecimal escrowInsuranceAmount;
  protected BigDecimal escrowPmiAmount;
  protected BigDecimal escrowFeesAmount;
  protected BigDecimal escrowOtherAmount;
  
  public BigDecimal getPrincipalAmount()
  {
    return this.principalAmount;
  }
  
  public void setPrincipalAmount(BigDecimal value)
  {
    this.principalAmount = value;
  }
  
  public BigDecimal getInterestAmount()
  {
    return this.interestAmount;
  }
  
  public void setInterestAmount(BigDecimal value)
  {
    this.interestAmount = value;
  }
  
  public BigDecimal getEscrowTotalAmount()
  {
    return this.escrowTotalAmount;
  }
  
  public void setEscrowTotalAmount(BigDecimal value)
  {
    this.escrowTotalAmount = value;
  }
  
  public BigDecimal getEscrowTaxAmount()
  {
    return this.escrowTaxAmount;
  }
  
  public void setEscrowTaxAmount(BigDecimal value)
  {
    this.escrowTaxAmount = value;
  }
  
  public BigDecimal getEscrowInsuranceAmount()
  {
    return this.escrowInsuranceAmount;
  }
  
  public void setEscrowInsuranceAmount(BigDecimal value)
  {
    this.escrowInsuranceAmount = value;
  }
  
  public BigDecimal getEscrowPmiAmount()
  {
    return this.escrowPmiAmount;
  }
  
  public void setEscrowPmiAmount(BigDecimal value)
  {
    this.escrowPmiAmount = value;
  }
  
  public BigDecimal getEscrowFeesAmount()
  {
    return this.escrowFeesAmount;
  }
  
  public void setEscrowFeesAmount(BigDecimal value)
  {
    this.escrowFeesAmount = value;
  }
  
  public BigDecimal getEscrowOtherAmount()
  {
    return this.escrowOtherAmount;
  }
  
  public void setEscrowOtherAmount(BigDecimal value)
  {
    this.escrowOtherAmount = value;
  }
  
  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy)
  {
    if (!(object instanceof LoanTransaction)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    if (!super.equals(thisLocator, thatLocator, object, strategy)) {
      return false;
    }
    LoanTransaction that = (LoanTransaction)object;
    
    BigDecimal lhsPrincipalAmount = getPrincipalAmount();
    
    BigDecimal rhsPrincipalAmount = that.getPrincipalAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "principalAmount", lhsPrincipalAmount), LocatorUtils.property(thatLocator, "principalAmount", rhsPrincipalAmount), lhsPrincipalAmount, rhsPrincipalAmount)) {
      return false;
    }
    BigDecimal lhsInterestAmount = getInterestAmount();
    
    BigDecimal rhsInterestAmount = that.getInterestAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "interestAmount", lhsInterestAmount), LocatorUtils.property(thatLocator, "interestAmount", rhsInterestAmount), lhsInterestAmount, rhsInterestAmount)) {
      return false;
    }
    BigDecimal lhsEscrowTotalAmount = getEscrowTotalAmount();
    
    BigDecimal rhsEscrowTotalAmount = that.getEscrowTotalAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "escrowTotalAmount", lhsEscrowTotalAmount), LocatorUtils.property(thatLocator, "escrowTotalAmount", rhsEscrowTotalAmount), lhsEscrowTotalAmount, rhsEscrowTotalAmount)) {
      return false;
    }
    BigDecimal lhsEscrowTaxAmount = getEscrowTaxAmount();
    
    BigDecimal rhsEscrowTaxAmount = that.getEscrowTaxAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "escrowTaxAmount", lhsEscrowTaxAmount), LocatorUtils.property(thatLocator, "escrowTaxAmount", rhsEscrowTaxAmount), lhsEscrowTaxAmount, rhsEscrowTaxAmount)) {
      return false;
    }
    BigDecimal lhsEscrowInsuranceAmount = getEscrowInsuranceAmount();
    
    BigDecimal rhsEscrowInsuranceAmount = that.getEscrowInsuranceAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "escrowInsuranceAmount", lhsEscrowInsuranceAmount), LocatorUtils.property(thatLocator, "escrowInsuranceAmount", rhsEscrowInsuranceAmount), lhsEscrowInsuranceAmount, rhsEscrowInsuranceAmount)) {
      return false;
    }
    BigDecimal lhsEscrowPmiAmount = getEscrowPmiAmount();
    
    BigDecimal rhsEscrowPmiAmount = that.getEscrowPmiAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "escrowPmiAmount", lhsEscrowPmiAmount), LocatorUtils.property(thatLocator, "escrowPmiAmount", rhsEscrowPmiAmount), lhsEscrowPmiAmount, rhsEscrowPmiAmount)) {
      return false;
    }
    BigDecimal lhsEscrowFeesAmount = getEscrowFeesAmount();
    
    BigDecimal rhsEscrowFeesAmount = that.getEscrowFeesAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "escrowFeesAmount", lhsEscrowFeesAmount), LocatorUtils.property(thatLocator, "escrowFeesAmount", rhsEscrowFeesAmount), lhsEscrowFeesAmount, rhsEscrowFeesAmount)) {
      return false;
    }
    BigDecimal lhsEscrowOtherAmount = getEscrowOtherAmount();
    
    BigDecimal rhsEscrowOtherAmount = that.getEscrowOtherAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "escrowOtherAmount", lhsEscrowOtherAmount), LocatorUtils.property(thatLocator, "escrowOtherAmount", rhsEscrowOtherAmount), lhsEscrowOtherAmount, rhsEscrowOtherAmount)) {
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
    int currentHashCode = super.hashCode(locator, strategy);
    
    BigDecimal thePrincipalAmount = getPrincipalAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "principalAmount", thePrincipalAmount), currentHashCode, thePrincipalAmount);
    
    BigDecimal theInterestAmount = getInterestAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "interestAmount", theInterestAmount), currentHashCode, theInterestAmount);
    
    BigDecimal theEscrowTotalAmount = getEscrowTotalAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "escrowTotalAmount", theEscrowTotalAmount), currentHashCode, theEscrowTotalAmount);
    
    BigDecimal theEscrowTaxAmount = getEscrowTaxAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "escrowTaxAmount", theEscrowTaxAmount), currentHashCode, theEscrowTaxAmount);
    
    BigDecimal theEscrowInsuranceAmount = getEscrowInsuranceAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "escrowInsuranceAmount", theEscrowInsuranceAmount), currentHashCode, theEscrowInsuranceAmount);
    
    BigDecimal theEscrowPmiAmount = getEscrowPmiAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "escrowPmiAmount", theEscrowPmiAmount), currentHashCode, theEscrowPmiAmount);
    
    BigDecimal theEscrowFeesAmount = getEscrowFeesAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "escrowFeesAmount", theEscrowFeesAmount), currentHashCode, theEscrowFeesAmount);
    
    BigDecimal theEscrowOtherAmount = getEscrowOtherAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "escrowOtherAmount", theEscrowOtherAmount), currentHashCode, theEscrowOtherAmount);
    
    return currentHashCode;
  }
  
  public int hashCode()
  {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}
