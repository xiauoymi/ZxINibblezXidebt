package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
@XmlType(name="", propOrder={"bankingAccountType", "postedDate", "availableBalanceAmount", "interestType", "originationDate", "openDate", "periodInterestRate", "periodDepositAmount", "periodInterestAmount", "interestAmountYtd", "interestPriorAmountYtd", "maturityDate", "maturityAmount"})
@XmlRootElement(name="BankingAccount", namespace="http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
public class BankingAccount
  extends Account
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
  protected BankingAccountType bankingAccountType;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1", type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar postedDate;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
  protected BigDecimal availableBalanceAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
  protected String interestType;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1", type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar originationDate;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1", type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar openDate;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
  protected BigDecimal periodInterestRate;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
  protected BigDecimal periodDepositAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
  protected BigDecimal periodInterestAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
  protected BigDecimal interestAmountYtd;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
  protected BigDecimal interestPriorAmountYtd;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1", type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar maturityDate;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
  protected BigDecimal maturityAmount;
  
  public BankingAccountType getBankingAccountType()
  {
    return this.bankingAccountType;
  }
  
  public void setBankingAccountType(BankingAccountType value)
  {
    this.bankingAccountType = value;
  }
  
  public Calendar getPostedDate()
  {
    return this.postedDate;
  }
  
  public void setPostedDate(Calendar value)
  {
    this.postedDate = value;
  }
  
  public BigDecimal getAvailableBalanceAmount()
  {
    return this.availableBalanceAmount;
  }
  
  public void setAvailableBalanceAmount(BigDecimal value)
  {
    this.availableBalanceAmount = value;
  }
  
  public String getInterestType()
  {
    return this.interestType;
  }
  
  public void setInterestType(String value)
  {
    this.interestType = value;
  }
  
  public Calendar getOriginationDate()
  {
    return this.originationDate;
  }
  
  public void setOriginationDate(Calendar value)
  {
    this.originationDate = value;
  }
  
  public Calendar getOpenDate()
  {
    return this.openDate;
  }
  
  public void setOpenDate(Calendar value)
  {
    this.openDate = value;
  }
  
  public BigDecimal getPeriodInterestRate()
  {
    return this.periodInterestRate;
  }
  
  public void setPeriodInterestRate(BigDecimal value)
  {
    this.periodInterestRate = value;
  }
  
  public BigDecimal getPeriodDepositAmount()
  {
    return this.periodDepositAmount;
  }
  
  public void setPeriodDepositAmount(BigDecimal value)
  {
    this.periodDepositAmount = value;
  }
  
  public BigDecimal getPeriodInterestAmount()
  {
    return this.periodInterestAmount;
  }
  
  public void setPeriodInterestAmount(BigDecimal value)
  {
    this.periodInterestAmount = value;
  }
  
  public BigDecimal getInterestAmountYtd()
  {
    return this.interestAmountYtd;
  }
  
  public void setInterestAmountYtd(BigDecimal value)
  {
    this.interestAmountYtd = value;
  }
  
  public BigDecimal getInterestPriorAmountYtd()
  {
    return this.interestPriorAmountYtd;
  }
  
  public void setInterestPriorAmountYtd(BigDecimal value)
  {
    this.interestPriorAmountYtd = value;
  }
  
  public Calendar getMaturityDate()
  {
    return this.maturityDate;
  }
  
  public void setMaturityDate(Calendar value)
  {
    this.maturityDate = value;
  }
  
  public BigDecimal getMaturityAmount()
  {
    return this.maturityAmount;
  }
  
  public void setMaturityAmount(BigDecimal value)
  {
    this.maturityAmount = value;
  }
  
  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy)
  {
    if (!(object instanceof BankingAccount)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    if (!super.equals(thisLocator, thatLocator, object, strategy)) {
      return false;
    }
    BankingAccount that = (BankingAccount)object;
    
    BankingAccountType lhsBankingAccountType = getBankingAccountType();
    
    BankingAccountType rhsBankingAccountType = that.getBankingAccountType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "bankingAccountType", lhsBankingAccountType), LocatorUtils.property(thatLocator, "bankingAccountType", rhsBankingAccountType), lhsBankingAccountType, rhsBankingAccountType)) {
      return false;
    }
    Calendar lhsPostedDate = getPostedDate();
    
    Calendar rhsPostedDate = that.getPostedDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "postedDate", lhsPostedDate), LocatorUtils.property(thatLocator, "postedDate", rhsPostedDate), lhsPostedDate, rhsPostedDate)) {
      return false;
    }
    BigDecimal lhsAvailableBalanceAmount = getAvailableBalanceAmount();
    
    BigDecimal rhsAvailableBalanceAmount = that.getAvailableBalanceAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "availableBalanceAmount", lhsAvailableBalanceAmount), LocatorUtils.property(thatLocator, "availableBalanceAmount", rhsAvailableBalanceAmount), lhsAvailableBalanceAmount, rhsAvailableBalanceAmount)) {
      return false;
    }
    String lhsInterestType = getInterestType();
    
    String rhsInterestType = that.getInterestType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "interestType", lhsInterestType), LocatorUtils.property(thatLocator, "interestType", rhsInterestType), lhsInterestType, rhsInterestType)) {
      return false;
    }
    Calendar lhsOriginationDate = getOriginationDate();
    
    Calendar rhsOriginationDate = that.getOriginationDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "originationDate", lhsOriginationDate), LocatorUtils.property(thatLocator, "originationDate", rhsOriginationDate), lhsOriginationDate, rhsOriginationDate)) {
      return false;
    }
    Calendar lhsOpenDate = getOpenDate();
    
    Calendar rhsOpenDate = that.getOpenDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "openDate", lhsOpenDate), LocatorUtils.property(thatLocator, "openDate", rhsOpenDate), lhsOpenDate, rhsOpenDate)) {
      return false;
    }
    BigDecimal lhsPeriodInterestRate = getPeriodInterestRate();
    
    BigDecimal rhsPeriodInterestRate = that.getPeriodInterestRate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "periodInterestRate", lhsPeriodInterestRate), LocatorUtils.property(thatLocator, "periodInterestRate", rhsPeriodInterestRate), lhsPeriodInterestRate, rhsPeriodInterestRate)) {
      return false;
    }
    BigDecimal lhsPeriodDepositAmount = getPeriodDepositAmount();
    
    BigDecimal rhsPeriodDepositAmount = that.getPeriodDepositAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "periodDepositAmount", lhsPeriodDepositAmount), LocatorUtils.property(thatLocator, "periodDepositAmount", rhsPeriodDepositAmount), lhsPeriodDepositAmount, rhsPeriodDepositAmount)) {
      return false;
    }
    BigDecimal lhsPeriodInterestAmount = getPeriodInterestAmount();
    
    BigDecimal rhsPeriodInterestAmount = that.getPeriodInterestAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "periodInterestAmount", lhsPeriodInterestAmount), LocatorUtils.property(thatLocator, "periodInterestAmount", rhsPeriodInterestAmount), lhsPeriodInterestAmount, rhsPeriodInterestAmount)) {
      return false;
    }
    BigDecimal lhsInterestAmountYtd = getInterestAmountYtd();
    
    BigDecimal rhsInterestAmountYtd = that.getInterestAmountYtd();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "interestAmountYtd", lhsInterestAmountYtd), LocatorUtils.property(thatLocator, "interestAmountYtd", rhsInterestAmountYtd), lhsInterestAmountYtd, rhsInterestAmountYtd)) {
      return false;
    }
    BigDecimal lhsInterestPriorAmountYtd = getInterestPriorAmountYtd();
    
    BigDecimal rhsInterestPriorAmountYtd = that.getInterestPriorAmountYtd();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "interestPriorAmountYtd", lhsInterestPriorAmountYtd), LocatorUtils.property(thatLocator, "interestPriorAmountYtd", rhsInterestPriorAmountYtd), lhsInterestPriorAmountYtd, rhsInterestPriorAmountYtd)) {
      return false;
    }
    Calendar lhsMaturityDate = getMaturityDate();
    
    Calendar rhsMaturityDate = that.getMaturityDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "maturityDate", lhsMaturityDate), LocatorUtils.property(thatLocator, "maturityDate", rhsMaturityDate), lhsMaturityDate, rhsMaturityDate)) {
      return false;
    }
    BigDecimal lhsMaturityAmount = getMaturityAmount();
    
    BigDecimal rhsMaturityAmount = that.getMaturityAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "maturityAmount", lhsMaturityAmount), LocatorUtils.property(thatLocator, "maturityAmount", rhsMaturityAmount), lhsMaturityAmount, rhsMaturityAmount)) {
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
    
    BankingAccountType theBankingAccountType = getBankingAccountType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "bankingAccountType", theBankingAccountType), currentHashCode, theBankingAccountType);
    
    Calendar thePostedDate = getPostedDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "postedDate", thePostedDate), currentHashCode, thePostedDate);
    
    BigDecimal theAvailableBalanceAmount = getAvailableBalanceAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "availableBalanceAmount", theAvailableBalanceAmount), currentHashCode, theAvailableBalanceAmount);
    
    String theInterestType = getInterestType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "interestType", theInterestType), currentHashCode, theInterestType);
    
    Calendar theOriginationDate = getOriginationDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "originationDate", theOriginationDate), currentHashCode, theOriginationDate);
    
    Calendar theOpenDate = getOpenDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "openDate", theOpenDate), currentHashCode, theOpenDate);
    
    BigDecimal thePeriodInterestRate = getPeriodInterestRate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "periodInterestRate", thePeriodInterestRate), currentHashCode, thePeriodInterestRate);
    
    BigDecimal thePeriodDepositAmount = getPeriodDepositAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "periodDepositAmount", thePeriodDepositAmount), currentHashCode, thePeriodDepositAmount);
    
    BigDecimal thePeriodInterestAmount = getPeriodInterestAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "periodInterestAmount", thePeriodInterestAmount), currentHashCode, thePeriodInterestAmount);
    
    BigDecimal theInterestAmountYtd = getInterestAmountYtd();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "interestAmountYtd", theInterestAmountYtd), currentHashCode, theInterestAmountYtd);
    
    BigDecimal theInterestPriorAmountYtd = getInterestPriorAmountYtd();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "interestPriorAmountYtd", theInterestPriorAmountYtd), currentHashCode, theInterestPriorAmountYtd);
    
    Calendar theMaturityDate = getMaturityDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maturityDate", theMaturityDate), currentHashCode, theMaturityDate);
    
    BigDecimal theMaturityAmount = getMaturityAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maturityAmount", theMaturityAmount), currentHashCode, theMaturityAmount);
    
    return currentHashCode;
  }
  
  public int hashCode()
  {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}
