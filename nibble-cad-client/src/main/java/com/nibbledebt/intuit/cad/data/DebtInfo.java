package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="DebtInfo", propOrder={"callDate", "yieldToCall", "callPrice", "callType", "couponFreq", "couponMaturityDate", "couponRate", "debtClass", "debtType", "maturityDate", "yieldToMaturity", "parValue"})
public class DebtInfo
  extends SecurityInfo
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  @XmlElement(type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar callDate;
  protected BigDecimal yieldToCall;
  protected BigDecimal callPrice;
  protected String callType;
  protected String couponFreq;
  @XmlElement(type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar couponMaturityDate;
  protected BigDecimal couponRate;
  protected String debtClass;
  protected String debtType;
  @XmlElement(type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar maturityDate;
  protected BigDecimal yieldToMaturity;
  protected BigDecimal parValue;
  
  public Calendar getCallDate()
  {
    return this.callDate;
  }
  
  public void setCallDate(Calendar value)
  {
    this.callDate = value;
  }
  
  public BigDecimal getYieldToCall()
  {
    return this.yieldToCall;
  }
  
  public void setYieldToCall(BigDecimal value)
  {
    this.yieldToCall = value;
  }
  
  public BigDecimal getCallPrice()
  {
    return this.callPrice;
  }
  
  public void setCallPrice(BigDecimal value)
  {
    this.callPrice = value;
  }
  
  public String getCallType()
  {
    return this.callType;
  }
  
  public void setCallType(String value)
  {
    this.callType = value;
  }
  
  public String getCouponFreq()
  {
    return this.couponFreq;
  }
  
  public void setCouponFreq(String value)
  {
    this.couponFreq = value;
  }
  
  public Calendar getCouponMaturityDate()
  {
    return this.couponMaturityDate;
  }
  
  public void setCouponMaturityDate(Calendar value)
  {
    this.couponMaturityDate = value;
  }
  
  public BigDecimal getCouponRate()
  {
    return this.couponRate;
  }
  
  public void setCouponRate(BigDecimal value)
  {
    this.couponRate = value;
  }
  
  public String getDebtClass()
  {
    return this.debtClass;
  }
  
  public void setDebtClass(String value)
  {
    this.debtClass = value;
  }
  
  public String getDebtType()
  {
    return this.debtType;
  }
  
  public void setDebtType(String value)
  {
    this.debtType = value;
  }
  
  public Calendar getMaturityDate()
  {
    return this.maturityDate;
  }
  
  public void setMaturityDate(Calendar value)
  {
    this.maturityDate = value;
  }
  
  public BigDecimal getYieldToMaturity()
  {
    return this.yieldToMaturity;
  }
  
  public void setYieldToMaturity(BigDecimal value)
  {
    this.yieldToMaturity = value;
  }
  
  public BigDecimal getParValue()
  {
    return this.parValue;
  }
  
  public void setParValue(BigDecimal value)
  {
    this.parValue = value;
  }
  
  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy)
  {
    if (!(object instanceof DebtInfo)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    if (!super.equals(thisLocator, thatLocator, object, strategy)) {
      return false;
    }
    DebtInfo that = (DebtInfo)object;
    
    Calendar lhsCallDate = getCallDate();
    
    Calendar rhsCallDate = that.getCallDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "callDate", lhsCallDate), LocatorUtils.property(thatLocator, "callDate", rhsCallDate), lhsCallDate, rhsCallDate)) {
      return false;
    }
    BigDecimal lhsYieldToCall = getYieldToCall();
    
    BigDecimal rhsYieldToCall = that.getYieldToCall();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "yieldToCall", lhsYieldToCall), LocatorUtils.property(thatLocator, "yieldToCall", rhsYieldToCall), lhsYieldToCall, rhsYieldToCall)) {
      return false;
    }
    BigDecimal lhsCallPrice = getCallPrice();
    
    BigDecimal rhsCallPrice = that.getCallPrice();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "callPrice", lhsCallPrice), LocatorUtils.property(thatLocator, "callPrice", rhsCallPrice), lhsCallPrice, rhsCallPrice)) {
      return false;
    }
    String lhsCallType = getCallType();
    
    String rhsCallType = that.getCallType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "callType", lhsCallType), LocatorUtils.property(thatLocator, "callType", rhsCallType), lhsCallType, rhsCallType)) {
      return false;
    }
    String lhsCouponFreq = getCouponFreq();
    
    String rhsCouponFreq = that.getCouponFreq();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "couponFreq", lhsCouponFreq), LocatorUtils.property(thatLocator, "couponFreq", rhsCouponFreq), lhsCouponFreq, rhsCouponFreq)) {
      return false;
    }
    Calendar lhsCouponMaturityDate = getCouponMaturityDate();
    
    Calendar rhsCouponMaturityDate = that.getCouponMaturityDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "couponMaturityDate", lhsCouponMaturityDate), LocatorUtils.property(thatLocator, "couponMaturityDate", rhsCouponMaturityDate), lhsCouponMaturityDate, rhsCouponMaturityDate)) {
      return false;
    }
    BigDecimal lhsCouponRate = getCouponRate();
    
    BigDecimal rhsCouponRate = that.getCouponRate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "couponRate", lhsCouponRate), LocatorUtils.property(thatLocator, "couponRate", rhsCouponRate), lhsCouponRate, rhsCouponRate)) {
      return false;
    }
    String lhsDebtClass = getDebtClass();
    
    String rhsDebtClass = that.getDebtClass();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "debtClass", lhsDebtClass), LocatorUtils.property(thatLocator, "debtClass", rhsDebtClass), lhsDebtClass, rhsDebtClass)) {
      return false;
    }
    String lhsDebtType = getDebtType();
    
    String rhsDebtType = that.getDebtType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "debtType", lhsDebtType), LocatorUtils.property(thatLocator, "debtType", rhsDebtType), lhsDebtType, rhsDebtType)) {
      return false;
    }
    Calendar lhsMaturityDate = getMaturityDate();
    
    Calendar rhsMaturityDate = that.getMaturityDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "maturityDate", lhsMaturityDate), LocatorUtils.property(thatLocator, "maturityDate", rhsMaturityDate), lhsMaturityDate, rhsMaturityDate)) {
      return false;
    }
    BigDecimal lhsYieldToMaturity = getYieldToMaturity();
    
    BigDecimal rhsYieldToMaturity = that.getYieldToMaturity();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "yieldToMaturity", lhsYieldToMaturity), LocatorUtils.property(thatLocator, "yieldToMaturity", rhsYieldToMaturity), lhsYieldToMaturity, rhsYieldToMaturity)) {
      return false;
    }
    BigDecimal lhsParValue = getParValue();
    
    BigDecimal rhsParValue = that.getParValue();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "parValue", lhsParValue), LocatorUtils.property(thatLocator, "parValue", rhsParValue), lhsParValue, rhsParValue)) {
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
    
    Calendar theCallDate = getCallDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "callDate", theCallDate), currentHashCode, theCallDate);
    
    BigDecimal theYieldToCall = getYieldToCall();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "yieldToCall", theYieldToCall), currentHashCode, theYieldToCall);
    
    BigDecimal theCallPrice = getCallPrice();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "callPrice", theCallPrice), currentHashCode, theCallPrice);
    
    String theCallType = getCallType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "callType", theCallType), currentHashCode, theCallType);
    
    String theCouponFreq = getCouponFreq();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "couponFreq", theCouponFreq), currentHashCode, theCouponFreq);
    
    Calendar theCouponMaturityDate = getCouponMaturityDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "couponMaturityDate", theCouponMaturityDate), currentHashCode, theCouponMaturityDate);
    
    BigDecimal theCouponRate = getCouponRate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "couponRate", theCouponRate), currentHashCode, theCouponRate);
    
    String theDebtClass = getDebtClass();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "debtClass", theDebtClass), currentHashCode, theDebtClass);
    
    String theDebtType = getDebtType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "debtType", theDebtType), currentHashCode, theDebtType);
    
    Calendar theMaturityDate = getMaturityDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maturityDate", theMaturityDate), currentHashCode, theMaturityDate);
    
    BigDecimal theYieldToMaturity = getYieldToMaturity();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "yieldToMaturity", theYieldToMaturity), currentHashCode, theYieldToMaturity);
    
    BigDecimal theParValue = getParValue();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "parValue", theParValue), currentHashCode, theParValue);
    
    return currentHashCode;
  }
  
  public int hashCode()
  {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}
