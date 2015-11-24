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
@XmlType(name="", propOrder={"investmentAccountType", "interestMarginBalance", "shortBalance", "availableCashBalance", "currentBalance", "maturityValueAmount", "unvestedBalance", "vestedBalance", "empMatchDeferAmount", "empMatchDeferAmountYtd", "empMatchAmount", "empMatchAmountYtd", "empPretaxContribAmount", "empPretaxContribAmountYtd", "rolloverAmount", "contribTotalYtd", "empMatchDeferAmountItd", "empMatchAmountItd", "rolloverItd", "cashBalanceAmount", "initialLoanBalance", "loanStartDate", "currentLoanBalance", "loanRate"})
@XmlRootElement(name="InvestmentAccount", namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
public class InvestmentAccount
  extends Account
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected String investmentAccountType;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal interestMarginBalance;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal shortBalance;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal availableCashBalance;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal currentBalance;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal maturityValueAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal unvestedBalance;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal vestedBalance;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal empMatchDeferAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal empMatchDeferAmountYtd;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal empMatchAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal empMatchAmountYtd;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal empPretaxContribAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal empPretaxContribAmountYtd;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal rolloverAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal contribTotalYtd;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal empMatchDeferAmountItd;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal empMatchAmountItd;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal rolloverItd;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal cashBalanceAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal initialLoanBalance;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1", type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar loanStartDate;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal currentLoanBalance;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1")
  protected BigDecimal loanRate;
  
  public String getInvestmentAccountType()
  {
    return this.investmentAccountType;
  }
  
  public void setInvestmentAccountType(String value)
  {
    this.investmentAccountType = value;
  }
  
  public BigDecimal getInterestMarginBalance()
  {
    return this.interestMarginBalance;
  }
  
  public void setInterestMarginBalance(BigDecimal value)
  {
    this.interestMarginBalance = value;
  }
  
  public BigDecimal getShortBalance()
  {
    return this.shortBalance;
  }
  
  public void setShortBalance(BigDecimal value)
  {
    this.shortBalance = value;
  }
  
  public BigDecimal getAvailableCashBalance()
  {
    return this.availableCashBalance;
  }
  
  public void setAvailableCashBalance(BigDecimal value)
  {
    this.availableCashBalance = value;
  }
  
  public BigDecimal getCurrentBalance()
  {
    return this.currentBalance;
  }
  
  public void setCurrentBalance(BigDecimal value)
  {
    this.currentBalance = value;
  }
  
  public BigDecimal getMaturityValueAmount()
  {
    return this.maturityValueAmount;
  }
  
  public void setMaturityValueAmount(BigDecimal value)
  {
    this.maturityValueAmount = value;
  }
  
  public BigDecimal getUnvestedBalance()
  {
    return this.unvestedBalance;
  }
  
  public void setUnvestedBalance(BigDecimal value)
  {
    this.unvestedBalance = value;
  }
  
  public BigDecimal getVestedBalance()
  {
    return this.vestedBalance;
  }
  
  public void setVestedBalance(BigDecimal value)
  {
    this.vestedBalance = value;
  }
  
  public BigDecimal getEmpMatchDeferAmount()
  {
    return this.empMatchDeferAmount;
  }
  
  public void setEmpMatchDeferAmount(BigDecimal value)
  {
    this.empMatchDeferAmount = value;
  }
  
  public BigDecimal getEmpMatchDeferAmountYtd()
  {
    return this.empMatchDeferAmountYtd;
  }
  
  public void setEmpMatchDeferAmountYtd(BigDecimal value)
  {
    this.empMatchDeferAmountYtd = value;
  }
  
  public BigDecimal getEmpMatchAmount()
  {
    return this.empMatchAmount;
  }
  
  public void setEmpMatchAmount(BigDecimal value)
  {
    this.empMatchAmount = value;
  }
  
  public BigDecimal getEmpMatchAmountYtd()
  {
    return this.empMatchAmountYtd;
  }
  
  public void setEmpMatchAmountYtd(BigDecimal value)
  {
    this.empMatchAmountYtd = value;
  }
  
  public BigDecimal getEmpPretaxContribAmount()
  {
    return this.empPretaxContribAmount;
  }
  
  public void setEmpPretaxContribAmount(BigDecimal value)
  {
    this.empPretaxContribAmount = value;
  }
  
  public BigDecimal getEmpPretaxContribAmountYtd()
  {
    return this.empPretaxContribAmountYtd;
  }
  
  public void setEmpPretaxContribAmountYtd(BigDecimal value)
  {
    this.empPretaxContribAmountYtd = value;
  }
  
  public BigDecimal getRolloverAmount()
  {
    return this.rolloverAmount;
  }
  
  public void setRolloverAmount(BigDecimal value)
  {
    this.rolloverAmount = value;
  }
  
  public BigDecimal getContribTotalYtd()
  {
    return this.contribTotalYtd;
  }
  
  public void setContribTotalYtd(BigDecimal value)
  {
    this.contribTotalYtd = value;
  }
  
  public BigDecimal getEmpMatchDeferAmountItd()
  {
    return this.empMatchDeferAmountItd;
  }
  
  public void setEmpMatchDeferAmountItd(BigDecimal value)
  {
    this.empMatchDeferAmountItd = value;
  }
  
  public BigDecimal getEmpMatchAmountItd()
  {
    return this.empMatchAmountItd;
  }
  
  public void setEmpMatchAmountItd(BigDecimal value)
  {
    this.empMatchAmountItd = value;
  }
  
  public BigDecimal getRolloverItd()
  {
    return this.rolloverItd;
  }
  
  public void setRolloverItd(BigDecimal value)
  {
    this.rolloverItd = value;
  }
  
  public BigDecimal getCashBalanceAmount()
  {
    return this.cashBalanceAmount;
  }
  
  public void setCashBalanceAmount(BigDecimal value)
  {
    this.cashBalanceAmount = value;
  }
  
  public BigDecimal getInitialLoanBalance()
  {
    return this.initialLoanBalance;
  }
  
  public void setInitialLoanBalance(BigDecimal value)
  {
    this.initialLoanBalance = value;
  }
  
  public Calendar getLoanStartDate()
  {
    return this.loanStartDate;
  }
  
  public void setLoanStartDate(Calendar value)
  {
    this.loanStartDate = value;
  }
  
  public BigDecimal getCurrentLoanBalance()
  {
    return this.currentLoanBalance;
  }
  
  public void setCurrentLoanBalance(BigDecimal value)
  {
    this.currentLoanBalance = value;
  }
  
  public BigDecimal getLoanRate()
  {
    return this.loanRate;
  }
  
  public void setLoanRate(BigDecimal value)
  {
    this.loanRate = value;
  }
  
  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy)
  {
    if (!(object instanceof InvestmentAccount)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    if (!super.equals(thisLocator, thatLocator, object, strategy)) {
      return false;
    }
    InvestmentAccount that = (InvestmentAccount)object;
    
    String lhsInvestmentAccountType = getInvestmentAccountType();
    
    String rhsInvestmentAccountType = that.getInvestmentAccountType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "investmentAccountType", lhsInvestmentAccountType), LocatorUtils.property(thatLocator, "investmentAccountType", rhsInvestmentAccountType), lhsInvestmentAccountType, rhsInvestmentAccountType)) {
      return false;
    }
    BigDecimal lhsInterestMarginBalance = getInterestMarginBalance();
    
    BigDecimal rhsInterestMarginBalance = that.getInterestMarginBalance();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "interestMarginBalance", lhsInterestMarginBalance), LocatorUtils.property(thatLocator, "interestMarginBalance", rhsInterestMarginBalance), lhsInterestMarginBalance, rhsInterestMarginBalance)) {
      return false;
    }
    BigDecimal lhsShortBalance = getShortBalance();
    
    BigDecimal rhsShortBalance = that.getShortBalance();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "shortBalance", lhsShortBalance), LocatorUtils.property(thatLocator, "shortBalance", rhsShortBalance), lhsShortBalance, rhsShortBalance)) {
      return false;
    }
    BigDecimal lhsAvailableCashBalance = getAvailableCashBalance();
    
    BigDecimal rhsAvailableCashBalance = that.getAvailableCashBalance();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "availableCashBalance", lhsAvailableCashBalance), LocatorUtils.property(thatLocator, "availableCashBalance", rhsAvailableCashBalance), lhsAvailableCashBalance, rhsAvailableCashBalance)) {
      return false;
    }
    BigDecimal lhsCurrentBalance = getCurrentBalance();
    
    BigDecimal rhsCurrentBalance = that.getCurrentBalance();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "currentBalance", lhsCurrentBalance), LocatorUtils.property(thatLocator, "currentBalance", rhsCurrentBalance), lhsCurrentBalance, rhsCurrentBalance)) {
      return false;
    }
    BigDecimal lhsMaturityValueAmount = getMaturityValueAmount();
    
    BigDecimal rhsMaturityValueAmount = that.getMaturityValueAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "maturityValueAmount", lhsMaturityValueAmount), LocatorUtils.property(thatLocator, "maturityValueAmount", rhsMaturityValueAmount), lhsMaturityValueAmount, rhsMaturityValueAmount)) {
      return false;
    }
    BigDecimal lhsUnvestedBalance = getUnvestedBalance();
    
    BigDecimal rhsUnvestedBalance = that.getUnvestedBalance();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "unvestedBalance", lhsUnvestedBalance), LocatorUtils.property(thatLocator, "unvestedBalance", rhsUnvestedBalance), lhsUnvestedBalance, rhsUnvestedBalance)) {
      return false;
    }
    BigDecimal lhsVestedBalance = getVestedBalance();
    
    BigDecimal rhsVestedBalance = that.getVestedBalance();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "vestedBalance", lhsVestedBalance), LocatorUtils.property(thatLocator, "vestedBalance", rhsVestedBalance), lhsVestedBalance, rhsVestedBalance)) {
      return false;
    }
    BigDecimal lhsEmpMatchDeferAmount = getEmpMatchDeferAmount();
    
    BigDecimal rhsEmpMatchDeferAmount = that.getEmpMatchDeferAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "empMatchDeferAmount", lhsEmpMatchDeferAmount), LocatorUtils.property(thatLocator, "empMatchDeferAmount", rhsEmpMatchDeferAmount), lhsEmpMatchDeferAmount, rhsEmpMatchDeferAmount)) {
      return false;
    }
    BigDecimal lhsEmpMatchDeferAmountYtd = getEmpMatchDeferAmountYtd();
    
    BigDecimal rhsEmpMatchDeferAmountYtd = that.getEmpMatchDeferAmountYtd();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "empMatchDeferAmountYtd", lhsEmpMatchDeferAmountYtd), LocatorUtils.property(thatLocator, "empMatchDeferAmountYtd", rhsEmpMatchDeferAmountYtd), lhsEmpMatchDeferAmountYtd, rhsEmpMatchDeferAmountYtd)) {
      return false;
    }
    BigDecimal lhsEmpMatchAmount = getEmpMatchAmount();
    
    BigDecimal rhsEmpMatchAmount = that.getEmpMatchAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "empMatchAmount", lhsEmpMatchAmount), LocatorUtils.property(thatLocator, "empMatchAmount", rhsEmpMatchAmount), lhsEmpMatchAmount, rhsEmpMatchAmount)) {
      return false;
    }
    BigDecimal lhsEmpMatchAmountYtd = getEmpMatchAmountYtd();
    
    BigDecimal rhsEmpMatchAmountYtd = that.getEmpMatchAmountYtd();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "empMatchAmountYtd", lhsEmpMatchAmountYtd), LocatorUtils.property(thatLocator, "empMatchAmountYtd", rhsEmpMatchAmountYtd), lhsEmpMatchAmountYtd, rhsEmpMatchAmountYtd)) {
      return false;
    }
    BigDecimal lhsEmpPretaxContribAmount = getEmpPretaxContribAmount();
    
    BigDecimal rhsEmpPretaxContribAmount = that.getEmpPretaxContribAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "empPretaxContribAmount", lhsEmpPretaxContribAmount), LocatorUtils.property(thatLocator, "empPretaxContribAmount", rhsEmpPretaxContribAmount), lhsEmpPretaxContribAmount, rhsEmpPretaxContribAmount)) {
      return false;
    }
    BigDecimal lhsEmpPretaxContribAmountYtd = getEmpPretaxContribAmountYtd();
    
    BigDecimal rhsEmpPretaxContribAmountYtd = that.getEmpPretaxContribAmountYtd();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "empPretaxContribAmountYtd", lhsEmpPretaxContribAmountYtd), LocatorUtils.property(thatLocator, "empPretaxContribAmountYtd", rhsEmpPretaxContribAmountYtd), lhsEmpPretaxContribAmountYtd, rhsEmpPretaxContribAmountYtd)) {
      return false;
    }
    BigDecimal lhsRolloverAmount = getRolloverAmount();
    
    BigDecimal rhsRolloverAmount = that.getRolloverAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "rolloverAmount", lhsRolloverAmount), LocatorUtils.property(thatLocator, "rolloverAmount", rhsRolloverAmount), lhsRolloverAmount, rhsRolloverAmount)) {
      return false;
    }
    BigDecimal lhsContribTotalYtd = getContribTotalYtd();
    
    BigDecimal rhsContribTotalYtd = that.getContribTotalYtd();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "contribTotalYtd", lhsContribTotalYtd), LocatorUtils.property(thatLocator, "contribTotalYtd", rhsContribTotalYtd), lhsContribTotalYtd, rhsContribTotalYtd)) {
      return false;
    }
    BigDecimal lhsEmpMatchDeferAmountItd = getEmpMatchDeferAmountItd();
    
    BigDecimal rhsEmpMatchDeferAmountItd = that.getEmpMatchDeferAmountItd();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "empMatchDeferAmountItd", lhsEmpMatchDeferAmountItd), LocatorUtils.property(thatLocator, "empMatchDeferAmountItd", rhsEmpMatchDeferAmountItd), lhsEmpMatchDeferAmountItd, rhsEmpMatchDeferAmountItd)) {
      return false;
    }
    BigDecimal lhsEmpMatchAmountItd = getEmpMatchAmountItd();
    
    BigDecimal rhsEmpMatchAmountItd = that.getEmpMatchAmountItd();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "empMatchAmountItd", lhsEmpMatchAmountItd), LocatorUtils.property(thatLocator, "empMatchAmountItd", rhsEmpMatchAmountItd), lhsEmpMatchAmountItd, rhsEmpMatchAmountItd)) {
      return false;
    }
    BigDecimal lhsRolloverItd = getRolloverItd();
    
    BigDecimal rhsRolloverItd = that.getRolloverItd();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "rolloverItd", lhsRolloverItd), LocatorUtils.property(thatLocator, "rolloverItd", rhsRolloverItd), lhsRolloverItd, rhsRolloverItd)) {
      return false;
    }
    BigDecimal lhsCashBalanceAmount = getCashBalanceAmount();
    
    BigDecimal rhsCashBalanceAmount = that.getCashBalanceAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "cashBalanceAmount", lhsCashBalanceAmount), LocatorUtils.property(thatLocator, "cashBalanceAmount", rhsCashBalanceAmount), lhsCashBalanceAmount, rhsCashBalanceAmount)) {
      return false;
    }
    BigDecimal lhsInitialLoanBalance = getInitialLoanBalance();
    
    BigDecimal rhsInitialLoanBalance = that.getInitialLoanBalance();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "initialLoanBalance", lhsInitialLoanBalance), LocatorUtils.property(thatLocator, "initialLoanBalance", rhsInitialLoanBalance), lhsInitialLoanBalance, rhsInitialLoanBalance)) {
      return false;
    }
    Calendar lhsLoanStartDate = getLoanStartDate();
    
    Calendar rhsLoanStartDate = that.getLoanStartDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "loanStartDate", lhsLoanStartDate), LocatorUtils.property(thatLocator, "loanStartDate", rhsLoanStartDate), lhsLoanStartDate, rhsLoanStartDate)) {
      return false;
    }
    BigDecimal lhsCurrentLoanBalance = getCurrentLoanBalance();
    
    BigDecimal rhsCurrentLoanBalance = that.getCurrentLoanBalance();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "currentLoanBalance", lhsCurrentLoanBalance), LocatorUtils.property(thatLocator, "currentLoanBalance", rhsCurrentLoanBalance), lhsCurrentLoanBalance, rhsCurrentLoanBalance)) {
      return false;
    }
    BigDecimal lhsLoanRate = getLoanRate();
    
    BigDecimal rhsLoanRate = that.getLoanRate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "loanRate", lhsLoanRate), LocatorUtils.property(thatLocator, "loanRate", rhsLoanRate), lhsLoanRate, rhsLoanRate)) {
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
    
    String theInvestmentAccountType = getInvestmentAccountType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "investmentAccountType", theInvestmentAccountType), currentHashCode, theInvestmentAccountType);
    
    BigDecimal theInterestMarginBalance = getInterestMarginBalance();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "interestMarginBalance", theInterestMarginBalance), currentHashCode, theInterestMarginBalance);
    
    BigDecimal theShortBalance = getShortBalance();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "shortBalance", theShortBalance), currentHashCode, theShortBalance);
    
    BigDecimal theAvailableCashBalance = getAvailableCashBalance();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "availableCashBalance", theAvailableCashBalance), currentHashCode, theAvailableCashBalance);
    
    BigDecimal theCurrentBalance = getCurrentBalance();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "currentBalance", theCurrentBalance), currentHashCode, theCurrentBalance);
    
    BigDecimal theMaturityValueAmount = getMaturityValueAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maturityValueAmount", theMaturityValueAmount), currentHashCode, theMaturityValueAmount);
    
    BigDecimal theUnvestedBalance = getUnvestedBalance();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "unvestedBalance", theUnvestedBalance), currentHashCode, theUnvestedBalance);
    
    BigDecimal theVestedBalance = getVestedBalance();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "vestedBalance", theVestedBalance), currentHashCode, theVestedBalance);
    
    BigDecimal theEmpMatchDeferAmount = getEmpMatchDeferAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "empMatchDeferAmount", theEmpMatchDeferAmount), currentHashCode, theEmpMatchDeferAmount);
    
    BigDecimal theEmpMatchDeferAmountYtd = getEmpMatchDeferAmountYtd();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "empMatchDeferAmountYtd", theEmpMatchDeferAmountYtd), currentHashCode, theEmpMatchDeferAmountYtd);
    
    BigDecimal theEmpMatchAmount = getEmpMatchAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "empMatchAmount", theEmpMatchAmount), currentHashCode, theEmpMatchAmount);
    
    BigDecimal theEmpMatchAmountYtd = getEmpMatchAmountYtd();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "empMatchAmountYtd", theEmpMatchAmountYtd), currentHashCode, theEmpMatchAmountYtd);
    
    BigDecimal theEmpPretaxContribAmount = getEmpPretaxContribAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "empPretaxContribAmount", theEmpPretaxContribAmount), currentHashCode, theEmpPretaxContribAmount);
    
    BigDecimal theEmpPretaxContribAmountYtd = getEmpPretaxContribAmountYtd();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "empPretaxContribAmountYtd", theEmpPretaxContribAmountYtd), currentHashCode, theEmpPretaxContribAmountYtd);
    
    BigDecimal theRolloverAmount = getRolloverAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rolloverAmount", theRolloverAmount), currentHashCode, theRolloverAmount);
    
    BigDecimal theContribTotalYtd = getContribTotalYtd();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "contribTotalYtd", theContribTotalYtd), currentHashCode, theContribTotalYtd);
    
    BigDecimal theEmpMatchDeferAmountItd = getEmpMatchDeferAmountItd();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "empMatchDeferAmountItd", theEmpMatchDeferAmountItd), currentHashCode, theEmpMatchDeferAmountItd);
    
    BigDecimal theEmpMatchAmountItd = getEmpMatchAmountItd();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "empMatchAmountItd", theEmpMatchAmountItd), currentHashCode, theEmpMatchAmountItd);
    
    BigDecimal theRolloverItd = getRolloverItd();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rolloverItd", theRolloverItd), currentHashCode, theRolloverItd);
    
    BigDecimal theCashBalanceAmount = getCashBalanceAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "cashBalanceAmount", theCashBalanceAmount), currentHashCode, theCashBalanceAmount);
    
    BigDecimal theInitialLoanBalance = getInitialLoanBalance();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "initialLoanBalance", theInitialLoanBalance), currentHashCode, theInitialLoanBalance);
    
    Calendar theLoanStartDate = getLoanStartDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "loanStartDate", theLoanStartDate), currentHashCode, theLoanStartDate);
    
    BigDecimal theCurrentLoanBalance = getCurrentLoanBalance();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "currentLoanBalance", theCurrentLoanBalance), currentHashCode, theCurrentLoanBalance);
    
    BigDecimal theLoanRate = getLoanRate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "loanRate", theLoanRate), currentHashCode, theLoanRate);
    
    return currentHashCode;
  }
  
  public int hashCode()
  {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}
