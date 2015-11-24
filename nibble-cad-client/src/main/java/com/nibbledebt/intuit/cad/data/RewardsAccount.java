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
@XmlType(name="", propOrder={"rewardsAccountType", "postedDate", "programType", "originalBalance", "currentBalance", "rewardQualifyAmountYtd", "rewardLifetimeEarned", "segmentYtd"})
@XmlRootElement(name="RewardsAccount", namespace="http://schema.intuit.com/platform/fdatafeed/rewardsaccount/v1")
public class RewardsAccount
  extends Account
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/rewardsaccount/v1")
  protected RewardsAccountType rewardsAccountType;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/rewardsaccount/v1", type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar postedDate;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/rewardsaccount/v1")
  protected String programType;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/rewardsaccount/v1")
  protected BigDecimal originalBalance;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/rewardsaccount/v1")
  protected BigDecimal currentBalance;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/rewardsaccount/v1")
  protected BigDecimal rewardQualifyAmountYtd;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/rewardsaccount/v1")
  protected BigDecimal rewardLifetimeEarned;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/rewardsaccount/v1")
  protected BigDecimal segmentYtd;
  
  public RewardsAccountType getRewardsAccountType()
  {
    return this.rewardsAccountType;
  }
  
  public void setRewardsAccountType(RewardsAccountType value)
  {
    this.rewardsAccountType = value;
  }
  
  public Calendar getPostedDate()
  {
    return this.postedDate;
  }
  
  public void setPostedDate(Calendar value)
  {
    this.postedDate = value;
  }
  
  public String getProgramType()
  {
    return this.programType;
  }
  
  public void setProgramType(String value)
  {
    this.programType = value;
  }
  
  public BigDecimal getOriginalBalance()
  {
    return this.originalBalance;
  }
  
  public void setOriginalBalance(BigDecimal value)
  {
    this.originalBalance = value;
  }
  
  public BigDecimal getCurrentBalance()
  {
    return this.currentBalance;
  }
  
  public void setCurrentBalance(BigDecimal value)
  {
    this.currentBalance = value;
  }
  
  public BigDecimal getRewardQualifyAmountYtd()
  {
    return this.rewardQualifyAmountYtd;
  }
  
  public void setRewardQualifyAmountYtd(BigDecimal value)
  {
    this.rewardQualifyAmountYtd = value;
  }
  
  public BigDecimal getRewardLifetimeEarned()
  {
    return this.rewardLifetimeEarned;
  }
  
  public void setRewardLifetimeEarned(BigDecimal value)
  {
    this.rewardLifetimeEarned = value;
  }
  
  public BigDecimal getSegmentYtd()
  {
    return this.segmentYtd;
  }
  
  public void setSegmentYtd(BigDecimal value)
  {
    this.segmentYtd = value;
  }
  
  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy)
  {
    if (!(object instanceof RewardsAccount)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    if (!super.equals(thisLocator, thatLocator, object, strategy)) {
      return false;
    }
    RewardsAccount that = (RewardsAccount)object;
    
    RewardsAccountType lhsRewardsAccountType = getRewardsAccountType();
    
    RewardsAccountType rhsRewardsAccountType = that.getRewardsAccountType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "rewardsAccountType", lhsRewardsAccountType), LocatorUtils.property(thatLocator, "rewardsAccountType", rhsRewardsAccountType), lhsRewardsAccountType, rhsRewardsAccountType)) {
      return false;
    }
    Calendar lhsPostedDate = getPostedDate();
    
    Calendar rhsPostedDate = that.getPostedDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "postedDate", lhsPostedDate), LocatorUtils.property(thatLocator, "postedDate", rhsPostedDate), lhsPostedDate, rhsPostedDate)) {
      return false;
    }
    String lhsProgramType = getProgramType();
    
    String rhsProgramType = that.getProgramType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "programType", lhsProgramType), LocatorUtils.property(thatLocator, "programType", rhsProgramType), lhsProgramType, rhsProgramType)) {
      return false;
    }
    BigDecimal lhsOriginalBalance = getOriginalBalance();
    
    BigDecimal rhsOriginalBalance = that.getOriginalBalance();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "originalBalance", lhsOriginalBalance), LocatorUtils.property(thatLocator, "originalBalance", rhsOriginalBalance), lhsOriginalBalance, rhsOriginalBalance)) {
      return false;
    }
    BigDecimal lhsCurrentBalance = getCurrentBalance();
    
    BigDecimal rhsCurrentBalance = that.getCurrentBalance();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "currentBalance", lhsCurrentBalance), LocatorUtils.property(thatLocator, "currentBalance", rhsCurrentBalance), lhsCurrentBalance, rhsCurrentBalance)) {
      return false;
    }
    BigDecimal lhsRewardQualifyAmountYtd = getRewardQualifyAmountYtd();
    
    BigDecimal rhsRewardQualifyAmountYtd = that.getRewardQualifyAmountYtd();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "rewardQualifyAmountYtd", lhsRewardQualifyAmountYtd), LocatorUtils.property(thatLocator, "rewardQualifyAmountYtd", rhsRewardQualifyAmountYtd), lhsRewardQualifyAmountYtd, rhsRewardQualifyAmountYtd)) {
      return false;
    }
    BigDecimal lhsRewardLifetimeEarned = getRewardLifetimeEarned();
    
    BigDecimal rhsRewardLifetimeEarned = that.getRewardLifetimeEarned();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "rewardLifetimeEarned", lhsRewardLifetimeEarned), LocatorUtils.property(thatLocator, "rewardLifetimeEarned", rhsRewardLifetimeEarned), lhsRewardLifetimeEarned, rhsRewardLifetimeEarned)) {
      return false;
    }
    BigDecimal lhsSegmentYtd = getSegmentYtd();
    
    BigDecimal rhsSegmentYtd = that.getSegmentYtd();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "segmentYtd", lhsSegmentYtd), LocatorUtils.property(thatLocator, "segmentYtd", rhsSegmentYtd), lhsSegmentYtd, rhsSegmentYtd)) {
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
    
    RewardsAccountType theRewardsAccountType = getRewardsAccountType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rewardsAccountType", theRewardsAccountType), currentHashCode, theRewardsAccountType);
    
    Calendar thePostedDate = getPostedDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "postedDate", thePostedDate), currentHashCode, thePostedDate);
    
    String theProgramType = getProgramType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "programType", theProgramType), currentHashCode, theProgramType);
    
    BigDecimal theOriginalBalance = getOriginalBalance();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "originalBalance", theOriginalBalance), currentHashCode, theOriginalBalance);
    
    BigDecimal theCurrentBalance = getCurrentBalance();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "currentBalance", theCurrentBalance), currentHashCode, theCurrentBalance);
    
    BigDecimal theRewardQualifyAmountYtd = getRewardQualifyAmountYtd();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rewardQualifyAmountYtd", theRewardQualifyAmountYtd), currentHashCode, theRewardQualifyAmountYtd);
    
    BigDecimal theRewardLifetimeEarned = getRewardLifetimeEarned();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rewardLifetimeEarned", theRewardLifetimeEarned), currentHashCode, theRewardLifetimeEarned);
    
    BigDecimal theSegmentYtd = getSegmentYtd();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "segmentYtd", theSegmentYtd), currentHashCode, theSegmentYtd);
    
    return currentHashCode;
  }
  
  public int hashCode()
  {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}
