package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
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
@XmlType(name="Account", namespace="http://schema.intuit.com/platform/fdatafeed/account/v1", propOrder={"accountId", "status", "accountNumber", "accountNumberReal", "accountNickname", "displayPosition", "institutionId", "description", "registeredUserName", "balanceAmount", "balanceDate", "balancePreviousAmount", "lastTxnDate", "aggrSuccessDate", "aggrAttemptDate", "aggrStatusCode", "currencyCode", "bankId", "institutionLoginId"})
@XmlSeeAlso({LoanAccount.class, CreditAccount.class, BankingAccount.class, OtherAccount.class, InvestmentAccount.class, RewardsAccount.class})
public abstract class Account
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  protected long accountId;
  @XmlElement(required=true)
  protected String status;
  @XmlElement(required=true)
  protected String accountNumber;
  protected String accountNumberReal;
  protected String accountNickname;
  protected Integer displayPosition;
  protected long institutionId;
  protected String description;
  protected String registeredUserName;
  protected BigDecimal balanceAmount;
  @XmlElement(type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar balanceDate;
  protected BigDecimal balancePreviousAmount;
  @XmlElement(type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar lastTxnDate;
  @XmlElement(type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar aggrSuccessDate;
  @XmlElement(type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar aggrAttemptDate;
  protected String aggrStatusCode;
  protected CurrencyCode currencyCode;
  protected String bankId;
  protected long institutionLoginId;
  
  public long getAccountId()
  {
    return this.accountId;
  }
  
  public void setAccountId(long value)
  {
    this.accountId = value;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String value)
  {
    this.status = value;
  }
  
  public String getAccountNumber()
  {
    return this.accountNumber;
  }
  
  public void setAccountNumber(String value)
  {
    this.accountNumber = value;
  }
  
  public String getAccountNumberReal()
  {
    return this.accountNumberReal;
  }
  
  public void setAccountNumberReal(String value)
  {
    this.accountNumberReal = value;
  }
  
  public String getAccountNickname()
  {
    return this.accountNickname;
  }
  
  public void setAccountNickname(String value)
  {
    this.accountNickname = value;
  }
  
  public Integer getDisplayPosition()
  {
    return this.displayPosition;
  }
  
  public void setDisplayPosition(Integer value)
  {
    this.displayPosition = value;
  }
  
  public long getInstitutionId()
  {
    return this.institutionId;
  }
  
  public void setInstitutionId(long value)
  {
    this.institutionId = value;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String value)
  {
    this.description = value;
  }
  
  public String getRegisteredUserName()
  {
    return this.registeredUserName;
  }
  
  public void setRegisteredUserName(String value)
  {
    this.registeredUserName = value;
  }
  
  public BigDecimal getBalanceAmount()
  {
    return this.balanceAmount;
  }
  
  public void setBalanceAmount(BigDecimal value)
  {
    this.balanceAmount = value;
  }
  
  public Calendar getBalanceDate()
  {
    return this.balanceDate;
  }
  
  public void setBalanceDate(Calendar value)
  {
    this.balanceDate = value;
  }
  
  public BigDecimal getBalancePreviousAmount()
  {
    return this.balancePreviousAmount;
  }
  
  public void setBalancePreviousAmount(BigDecimal value)
  {
    this.balancePreviousAmount = value;
  }
  
  public Calendar getLastTxnDate()
  {
    return this.lastTxnDate;
  }
  
  public void setLastTxnDate(Calendar value)
  {
    this.lastTxnDate = value;
  }
  
  public Calendar getAggrSuccessDate()
  {
    return this.aggrSuccessDate;
  }
  
  public void setAggrSuccessDate(Calendar value)
  {
    this.aggrSuccessDate = value;
  }
  
  public Calendar getAggrAttemptDate()
  {
    return this.aggrAttemptDate;
  }
  
  public void setAggrAttemptDate(Calendar value)
  {
    this.aggrAttemptDate = value;
  }
  
  public String getAggrStatusCode()
  {
    return this.aggrStatusCode;
  }
  
  public void setAggrStatusCode(String value)
  {
    this.aggrStatusCode = value;
  }
  
  public CurrencyCode getCurrencyCode()
  {
    return this.currencyCode;
  }
  
  public void setCurrencyCode(CurrencyCode value)
  {
    this.currencyCode = value;
  }
  
  public String getBankId()
  {
    return this.bankId;
  }
  
  public void setBankId(String value)
  {
    this.bankId = value;
  }
  
  public long getInstitutionLoginId()
  {
    return this.institutionLoginId;
  }
  
  public void setInstitutionLoginId(long value)
  {
    this.institutionLoginId = value;
  }
  
  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy)
  {
    if (!(object instanceof Account)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    Account that = (Account)object;
    
    long lhsAccountId = getAccountId();
    
    long rhsAccountId = that.getAccountId();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "accountId", lhsAccountId), LocatorUtils.property(thatLocator, "accountId", rhsAccountId), lhsAccountId, rhsAccountId)) {
      return false;
    }
    String lhsStatus = getStatus();
    
    String rhsStatus = that.getStatus();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "status", lhsStatus), LocatorUtils.property(thatLocator, "status", rhsStatus), lhsStatus, rhsStatus)) {
      return false;
    }
    String lhsAccountNumber = getAccountNumber();
    
    String rhsAccountNumber = that.getAccountNumber();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "accountNumber", lhsAccountNumber), LocatorUtils.property(thatLocator, "accountNumber", rhsAccountNumber), lhsAccountNumber, rhsAccountNumber)) {
      return false;
    }
    String lhsAccountNumberReal = getAccountNumberReal();
    
    String rhsAccountNumberReal = that.getAccountNumberReal();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "accountNumberReal", lhsAccountNumberReal), LocatorUtils.property(thatLocator, "accountNumberReal", rhsAccountNumberReal), lhsAccountNumberReal, rhsAccountNumberReal)) {
      return false;
    }
    String lhsAccountNickname = getAccountNickname();
    
    String rhsAccountNickname = that.getAccountNickname();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "accountNickname", lhsAccountNickname), LocatorUtils.property(thatLocator, "accountNickname", rhsAccountNickname), lhsAccountNickname, rhsAccountNickname)) {
      return false;
    }
    Integer lhsDisplayPosition = getDisplayPosition();
    
    Integer rhsDisplayPosition = that.getDisplayPosition();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "displayPosition", lhsDisplayPosition), LocatorUtils.property(thatLocator, "displayPosition", rhsDisplayPosition), lhsDisplayPosition, rhsDisplayPosition)) {
      return false;
    }
    long lhsInstitutionId = getInstitutionId();
    
    long rhsInstitutionId = that.getInstitutionId();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "institutionId", lhsInstitutionId), LocatorUtils.property(thatLocator, "institutionId", rhsInstitutionId), lhsInstitutionId, rhsInstitutionId)) {
      return false;
    }
    String lhsDescription = getDescription();
    
    String rhsDescription = that.getDescription();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription)) {
      return false;
    }
    String lhsRegisteredUserName = getRegisteredUserName();
    
    String rhsRegisteredUserName = that.getRegisteredUserName();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "registeredUserName", lhsRegisteredUserName), LocatorUtils.property(thatLocator, "registeredUserName", rhsRegisteredUserName), lhsRegisteredUserName, rhsRegisteredUserName)) {
      return false;
    }
    BigDecimal lhsBalanceAmount = getBalanceAmount();
    
    BigDecimal rhsBalanceAmount = that.getBalanceAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "balanceAmount", lhsBalanceAmount), LocatorUtils.property(thatLocator, "balanceAmount", rhsBalanceAmount), lhsBalanceAmount, rhsBalanceAmount)) {
      return false;
    }
    Calendar lhsBalanceDate = getBalanceDate();
    
    Calendar rhsBalanceDate = that.getBalanceDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "balanceDate", lhsBalanceDate), LocatorUtils.property(thatLocator, "balanceDate", rhsBalanceDate), lhsBalanceDate, rhsBalanceDate)) {
      return false;
    }
    BigDecimal lhsBalancePreviousAmount = getBalancePreviousAmount();
    
    BigDecimal rhsBalancePreviousAmount = that.getBalancePreviousAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "balancePreviousAmount", lhsBalancePreviousAmount), LocatorUtils.property(thatLocator, "balancePreviousAmount", rhsBalancePreviousAmount), lhsBalancePreviousAmount, rhsBalancePreviousAmount)) {
      return false;
    }
    Calendar lhsLastTxnDate = getLastTxnDate();
    
    Calendar rhsLastTxnDate = that.getLastTxnDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "lastTxnDate", lhsLastTxnDate), LocatorUtils.property(thatLocator, "lastTxnDate", rhsLastTxnDate), lhsLastTxnDate, rhsLastTxnDate)) {
      return false;
    }
    Calendar lhsAggrSuccessDate = getAggrSuccessDate();
    
    Calendar rhsAggrSuccessDate = that.getAggrSuccessDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "aggrSuccessDate", lhsAggrSuccessDate), LocatorUtils.property(thatLocator, "aggrSuccessDate", rhsAggrSuccessDate), lhsAggrSuccessDate, rhsAggrSuccessDate)) {
      return false;
    }
    Calendar lhsAggrAttemptDate = getAggrAttemptDate();
    
    Calendar rhsAggrAttemptDate = that.getAggrAttemptDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "aggrAttemptDate", lhsAggrAttemptDate), LocatorUtils.property(thatLocator, "aggrAttemptDate", rhsAggrAttemptDate), lhsAggrAttemptDate, rhsAggrAttemptDate)) {
      return false;
    }
    String lhsAggrStatusCode = getAggrStatusCode();
    
    String rhsAggrStatusCode = that.getAggrStatusCode();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "aggrStatusCode", lhsAggrStatusCode), LocatorUtils.property(thatLocator, "aggrStatusCode", rhsAggrStatusCode), lhsAggrStatusCode, rhsAggrStatusCode)) {
      return false;
    }
    CurrencyCode lhsCurrencyCode = getCurrencyCode();
    
    CurrencyCode rhsCurrencyCode = that.getCurrencyCode();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "currencyCode", lhsCurrencyCode), LocatorUtils.property(thatLocator, "currencyCode", rhsCurrencyCode), lhsCurrencyCode, rhsCurrencyCode)) {
      return false;
    }
    String lhsBankId = getBankId();
    
    String rhsBankId = that.getBankId();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "bankId", lhsBankId), LocatorUtils.property(thatLocator, "bankId", rhsBankId), lhsBankId, rhsBankId)) {
      return false;
    }
    long lhsInstitutionLoginId = getInstitutionLoginId();
    
    long rhsInstitutionLoginId = that.getInstitutionLoginId();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "institutionLoginId", lhsInstitutionLoginId), LocatorUtils.property(thatLocator, "institutionLoginId", rhsInstitutionLoginId), lhsInstitutionLoginId, rhsInstitutionLoginId)) {
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
    
    long theAccountId = getAccountId();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "accountId", theAccountId), currentHashCode, theAccountId);
    
    String theStatus = getStatus();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "status", theStatus), currentHashCode, theStatus);
    
    String theAccountNumber = getAccountNumber();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "accountNumber", theAccountNumber), currentHashCode, theAccountNumber);
    
    String theAccountNumberReal = getAccountNumberReal();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "accountNumberReal", theAccountNumberReal), currentHashCode, theAccountNumberReal);
    
    String theAccountNickname = getAccountNickname();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "accountNickname", theAccountNickname), currentHashCode, theAccountNickname);
    
    Integer theDisplayPosition = getDisplayPosition();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "displayPosition", theDisplayPosition), currentHashCode, theDisplayPosition);
    
    long theInstitutionId = getInstitutionId();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "institutionId", theInstitutionId), currentHashCode, theInstitutionId);
    
    String theDescription = getDescription();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription);
    
    String theRegisteredUserName = getRegisteredUserName();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "registeredUserName", theRegisteredUserName), currentHashCode, theRegisteredUserName);
    
    BigDecimal theBalanceAmount = getBalanceAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "balanceAmount", theBalanceAmount), currentHashCode, theBalanceAmount);
    
    Calendar theBalanceDate = getBalanceDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "balanceDate", theBalanceDate), currentHashCode, theBalanceDate);
    
    BigDecimal theBalancePreviousAmount = getBalancePreviousAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "balancePreviousAmount", theBalancePreviousAmount), currentHashCode, theBalancePreviousAmount);
    
    Calendar theLastTxnDate = getLastTxnDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lastTxnDate", theLastTxnDate), currentHashCode, theLastTxnDate);
    
    Calendar theAggrSuccessDate = getAggrSuccessDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "aggrSuccessDate", theAggrSuccessDate), currentHashCode, theAggrSuccessDate);
    
    Calendar theAggrAttemptDate = getAggrAttemptDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "aggrAttemptDate", theAggrAttemptDate), currentHashCode, theAggrAttemptDate);
    
    String theAggrStatusCode = getAggrStatusCode();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "aggrStatusCode", theAggrStatusCode), currentHashCode, theAggrStatusCode);
    
    CurrencyCode theCurrencyCode = getCurrencyCode();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "currencyCode", theCurrencyCode), currentHashCode, theCurrencyCode);
    
    String theBankId = getBankId();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "bankId", theBankId), currentHashCode, theBankId);
    
    long theInstitutionLoginId = getInstitutionLoginId();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "institutionLoginId", theInstitutionLoginId), currentHashCode, theInstitutionLoginId);
    
    return currentHashCode;
  }
  
  public int hashCode()
  {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}
