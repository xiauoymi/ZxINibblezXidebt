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
@XmlType(name="Transaction", namespace="http://schema.intuit.com/platform/fdatafeed/transaction/v1", propOrder={"id", "currencyType", "institutionTransactionId", "correctInstitutionTransactionId", "correctAction", "serverTransactionId", "checkNumber", "refNumber", "confirmationNumber", "payeeId", "payeeName", "extendedPayeeName", "memo", "type", "valueType", "currencyRate", "originalCurrency", "postedDate", "userDate", "availableDate", "amount", "runningBalanceAmount", "pending", "categorization"})
@XmlSeeAlso({BankingTransaction.class, CreditCardTransaction.class, InvestmentBankingTransaction.class, LoanTransaction.class, InvestmentTransaction.class, RewardsTransaction.class})
public class Transaction
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  protected long id;
  protected CurrencyCode currencyType;
  protected String institutionTransactionId;
  protected String correctInstitutionTransactionId;
  protected TransactionCorrectedType correctAction;
  protected String serverTransactionId;
  protected String checkNumber;
  protected String refNumber;
  protected String confirmationNumber;
  protected String payeeId;
  protected String payeeName;
  protected String extendedPayeeName;
  protected String memo;
  protected String type;
  protected String valueType;
  protected BigDecimal currencyRate;
  protected Boolean originalCurrency;

  @XmlElement(required=true, type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar postedDate;

  @XmlElement(type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar userDate;

  @XmlElement(type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar availableDate;
  protected BigDecimal amount;
  protected BigDecimal runningBalanceAmount;
  protected boolean pending;
  protected Categorization categorization;

  public long getId()
  {
    return this.id;
  }

  public void setId(long value)
  {
    this.id = value;
  }

  public CurrencyCode getCurrencyType()
  {
    return this.currencyType;
  }

  public void setCurrencyType(CurrencyCode value)
  {
    this.currencyType = value;
  }

  public String getInstitutionTransactionId()
  {
    return this.institutionTransactionId;
  }

  public void setInstitutionTransactionId(String value)
  {
    this.institutionTransactionId = value;
  }

  public String getCorrectInstitutionTransactionId()
  {
    return this.correctInstitutionTransactionId;
  }

  public void setCorrectInstitutionTransactionId(String value)
  {
    this.correctInstitutionTransactionId = value;
  }

  public TransactionCorrectedType getCorrectAction()
  {
    return this.correctAction;
  }

  public void setCorrectAction(TransactionCorrectedType value)
  {
    this.correctAction = value;
  }

  public String getServerTransactionId()
  {
    return this.serverTransactionId;
  }

  public void setServerTransactionId(String value)
  {
    this.serverTransactionId = value;
  }

  public String getCheckNumber()
  {
    return this.checkNumber;
  }

  public void setCheckNumber(String value)
  {
    this.checkNumber = value;
  }

  public String getRefNumber()
  {
    return this.refNumber;
  }

  public void setRefNumber(String value)
  {
    this.refNumber = value;
  }

  public String getConfirmationNumber()
  {
    return this.confirmationNumber;
  }

  public void setConfirmationNumber(String value)
  {
    this.confirmationNumber = value;
  }

  public String getPayeeId()
  {
    return this.payeeId;
  }

  public void setPayeeId(String value)
  {
    this.payeeId = value;
  }

  public String getPayeeName()
  {
    return this.payeeName;
  }

  public void setPayeeName(String value)
  {
    this.payeeName = value;
  }

  public String getExtendedPayeeName()
  {
    return this.extendedPayeeName;
  }

  public void setExtendedPayeeName(String value)
  {
    this.extendedPayeeName = value;
  }

  public String getMemo()
  {
    return this.memo;
  }

  public void setMemo(String value)
  {
    this.memo = value;
  }

  public String getType()
  {
    return this.type;
  }

  public void setType(String value)
  {
    this.type = value;
  }

  public String getValueType()
  {
    return this.valueType;
  }

  public void setValueType(String value)
  {
    this.valueType = value;
  }

  public BigDecimal getCurrencyRate()
  {
    return this.currencyRate;
  }

  public void setCurrencyRate(BigDecimal value)
  {
    this.currencyRate = value;
  }

  public Boolean isOriginalCurrency()
  {
    return this.originalCurrency;
  }

  public void setOriginalCurrency(Boolean value)
  {
    this.originalCurrency = value;
  }

  public Calendar getPostedDate()
  {
    return this.postedDate;
  }

  public void setPostedDate(Calendar value)
  {
    this.postedDate = value;
  }

  public Calendar getUserDate()
  {
    return this.userDate;
  }

  public void setUserDate(Calendar value)
  {
    this.userDate = value;
  }

  public Calendar getAvailableDate()
  {
    return this.availableDate;
  }

  public void setAvailableDate(Calendar value)
  {
    this.availableDate = value;
  }

  public BigDecimal getAmount()
  {
    return this.amount;
  }

  public void setAmount(BigDecimal value)
  {
    this.amount = value;
  }

  public BigDecimal getRunningBalanceAmount()
  {
    return this.runningBalanceAmount;
  }

  public void setRunningBalanceAmount(BigDecimal value)
  {
    this.runningBalanceAmount = value;
  }

  public boolean isPending()
  {
    return this.pending;
  }

  public void setPending(boolean value)
  {
    this.pending = value;
  }

  public Categorization getCategorization()
  {
    return this.categorization;
  }

  public void setCategorization(Categorization value)
  {
    this.categorization = value;
  }

  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
    if (!(object instanceof Transaction)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    Transaction that = (Transaction)object;

    long lhsId = getId();

    long rhsId = that.getId();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "id", lhsId), LocatorUtils.property(thatLocator, "id", rhsId), lhsId, rhsId)) {
      return false;
    }

    CurrencyCode lhsCurrencyType = getCurrencyType();

    CurrencyCode rhsCurrencyType = that.getCurrencyType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "currencyType", lhsCurrencyType), LocatorUtils.property(thatLocator, "currencyType", rhsCurrencyType), lhsCurrencyType, rhsCurrencyType)) {
      return false;
    }

    String lhsInstitutionTransactionId = getInstitutionTransactionId();

    String rhsInstitutionTransactionId = that.getInstitutionTransactionId();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "institutionTransactionId", lhsInstitutionTransactionId), LocatorUtils.property(thatLocator, "institutionTransactionId", rhsInstitutionTransactionId), lhsInstitutionTransactionId, rhsInstitutionTransactionId)) {
      return false;
    }

    String lhsCorrectInstitutionTransactionId = getCorrectInstitutionTransactionId();

    String rhsCorrectInstitutionTransactionId = that.getCorrectInstitutionTransactionId();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "correctInstitutionTransactionId", lhsCorrectInstitutionTransactionId), LocatorUtils.property(thatLocator, "correctInstitutionTransactionId", rhsCorrectInstitutionTransactionId), lhsCorrectInstitutionTransactionId, rhsCorrectInstitutionTransactionId)) {
      return false;
    }

    TransactionCorrectedType lhsCorrectAction = getCorrectAction();

    TransactionCorrectedType rhsCorrectAction = that.getCorrectAction();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "correctAction", lhsCorrectAction), LocatorUtils.property(thatLocator, "correctAction", rhsCorrectAction), lhsCorrectAction, rhsCorrectAction)) {
      return false;
    }

    String lhsServerTransactionId = getServerTransactionId();

    String rhsServerTransactionId = that.getServerTransactionId();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "serverTransactionId", lhsServerTransactionId), LocatorUtils.property(thatLocator, "serverTransactionId", rhsServerTransactionId), lhsServerTransactionId, rhsServerTransactionId)) {
      return false;
    }

    String lhsCheckNumber = getCheckNumber();

    String rhsCheckNumber = that.getCheckNumber();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "checkNumber", lhsCheckNumber), LocatorUtils.property(thatLocator, "checkNumber", rhsCheckNumber), lhsCheckNumber, rhsCheckNumber)) {
      return false;
    }

    String lhsRefNumber = getRefNumber();

    String rhsRefNumber = that.getRefNumber();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "refNumber", lhsRefNumber), LocatorUtils.property(thatLocator, "refNumber", rhsRefNumber), lhsRefNumber, rhsRefNumber)) {
      return false;
    }

    String lhsConfirmationNumber = getConfirmationNumber();

    String rhsConfirmationNumber = that.getConfirmationNumber();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "confirmationNumber", lhsConfirmationNumber), LocatorUtils.property(thatLocator, "confirmationNumber", rhsConfirmationNumber), lhsConfirmationNumber, rhsConfirmationNumber)) {
      return false;
    }

    String lhsPayeeId = getPayeeId();

    String rhsPayeeId = that.getPayeeId();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "payeeId", lhsPayeeId), LocatorUtils.property(thatLocator, "payeeId", rhsPayeeId), lhsPayeeId, rhsPayeeId)) {
      return false;
    }

    String lhsPayeeName = getPayeeName();

    String rhsPayeeName = that.getPayeeName();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "payeeName", lhsPayeeName), LocatorUtils.property(thatLocator, "payeeName", rhsPayeeName), lhsPayeeName, rhsPayeeName)) {
      return false;
    }

    String lhsExtendedPayeeName = getExtendedPayeeName();

    String rhsExtendedPayeeName = that.getExtendedPayeeName();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "extendedPayeeName", lhsExtendedPayeeName), LocatorUtils.property(thatLocator, "extendedPayeeName", rhsExtendedPayeeName), lhsExtendedPayeeName, rhsExtendedPayeeName)) {
      return false;
    }

    String lhsMemo = getMemo();

    String rhsMemo = that.getMemo();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "memo", lhsMemo), LocatorUtils.property(thatLocator, "memo", rhsMemo), lhsMemo, rhsMemo)) {
      return false;
    }

    String lhsType = getType();

    String rhsType = that.getType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "type", lhsType), LocatorUtils.property(thatLocator, "type", rhsType), lhsType, rhsType)) {
      return false;
    }

    String lhsValueType = getValueType();

    String rhsValueType = that.getValueType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "valueType", lhsValueType), LocatorUtils.property(thatLocator, "valueType", rhsValueType), lhsValueType, rhsValueType)) {
      return false;
    }

    BigDecimal lhsCurrencyRate = getCurrencyRate();

    BigDecimal rhsCurrencyRate = that.getCurrencyRate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "currencyRate", lhsCurrencyRate), LocatorUtils.property(thatLocator, "currencyRate", rhsCurrencyRate), lhsCurrencyRate, rhsCurrencyRate)) {
      return false;
    }

    Boolean lhsOriginalCurrency = isOriginalCurrency();

    Boolean rhsOriginalCurrency = that.isOriginalCurrency();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "originalCurrency", lhsOriginalCurrency), LocatorUtils.property(thatLocator, "originalCurrency", rhsOriginalCurrency), lhsOriginalCurrency, rhsOriginalCurrency)) {
      return false;
    }

    Calendar lhsPostedDate = getPostedDate();

    Calendar rhsPostedDate = that.getPostedDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "postedDate", lhsPostedDate), LocatorUtils.property(thatLocator, "postedDate", rhsPostedDate), lhsPostedDate, rhsPostedDate)) {
      return false;
    }

    Calendar lhsUserDate = getUserDate();

    Calendar rhsUserDate = that.getUserDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "userDate", lhsUserDate), LocatorUtils.property(thatLocator, "userDate", rhsUserDate), lhsUserDate, rhsUserDate)) {
      return false;
    }

    Calendar lhsAvailableDate = getAvailableDate();

    Calendar rhsAvailableDate = that.getAvailableDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "availableDate", lhsAvailableDate), LocatorUtils.property(thatLocator, "availableDate", rhsAvailableDate), lhsAvailableDate, rhsAvailableDate)) {
      return false;
    }

    BigDecimal lhsAmount = getAmount();

    BigDecimal rhsAmount = that.getAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "amount", lhsAmount), LocatorUtils.property(thatLocator, "amount", rhsAmount), lhsAmount, rhsAmount)) {
      return false;
    }

    BigDecimal lhsRunningBalanceAmount = getRunningBalanceAmount();

    BigDecimal rhsRunningBalanceAmount = that.getRunningBalanceAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "runningBalanceAmount", lhsRunningBalanceAmount), LocatorUtils.property(thatLocator, "runningBalanceAmount", rhsRunningBalanceAmount), lhsRunningBalanceAmount, rhsRunningBalanceAmount)) {
      return false;
    }

    boolean lhsPending = isPending();

    boolean rhsPending = that.isPending();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "pending", lhsPending), LocatorUtils.property(thatLocator, "pending", rhsPending), lhsPending, rhsPending)) {
      return false;
    }

    Categorization lhsCategorization = getCategorization();

    Categorization rhsCategorization = that.getCategorization();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "categorization", lhsCategorization), LocatorUtils.property(thatLocator, "categorization", rhsCategorization), lhsCategorization, rhsCategorization)) {
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

    long theId = getId();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "id", theId), currentHashCode, theId);

    CurrencyCode theCurrencyType = getCurrencyType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "currencyType", theCurrencyType), currentHashCode, theCurrencyType);

    String theInstitutionTransactionId = getInstitutionTransactionId();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "institutionTransactionId", theInstitutionTransactionId), currentHashCode, theInstitutionTransactionId);

    String theCorrectInstitutionTransactionId = getCorrectInstitutionTransactionId();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "correctInstitutionTransactionId", theCorrectInstitutionTransactionId), currentHashCode, theCorrectInstitutionTransactionId);

    TransactionCorrectedType theCorrectAction = getCorrectAction();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "correctAction", theCorrectAction), currentHashCode, theCorrectAction);

    String theServerTransactionId = getServerTransactionId();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "serverTransactionId", theServerTransactionId), currentHashCode, theServerTransactionId);

    String theCheckNumber = getCheckNumber();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "checkNumber", theCheckNumber), currentHashCode, theCheckNumber);

    String theRefNumber = getRefNumber();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "refNumber", theRefNumber), currentHashCode, theRefNumber);

    String theConfirmationNumber = getConfirmationNumber();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "confirmationNumber", theConfirmationNumber), currentHashCode, theConfirmationNumber);

    String thePayeeId = getPayeeId();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "payeeId", thePayeeId), currentHashCode, thePayeeId);

    String thePayeeName = getPayeeName();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "payeeName", thePayeeName), currentHashCode, thePayeeName);

    String theExtendedPayeeName = getExtendedPayeeName();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "extendedPayeeName", theExtendedPayeeName), currentHashCode, theExtendedPayeeName);

    String theMemo = getMemo();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "memo", theMemo), currentHashCode, theMemo);

    String theType = getType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "type", theType), currentHashCode, theType);

    String theValueType = getValueType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "valueType", theValueType), currentHashCode, theValueType);

    BigDecimal theCurrencyRate = getCurrencyRate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "currencyRate", theCurrencyRate), currentHashCode, theCurrencyRate);

    Boolean theOriginalCurrency = isOriginalCurrency();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "originalCurrency", theOriginalCurrency), currentHashCode, theOriginalCurrency);

    Calendar thePostedDate = getPostedDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "postedDate", thePostedDate), currentHashCode, thePostedDate);

    Calendar theUserDate = getUserDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "userDate", theUserDate), currentHashCode, theUserDate);

    Calendar theAvailableDate = getAvailableDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "availableDate", theAvailableDate), currentHashCode, theAvailableDate);

    BigDecimal theAmount = getAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "amount", theAmount), currentHashCode, theAmount);

    BigDecimal theRunningBalanceAmount = getRunningBalanceAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "runningBalanceAmount", theRunningBalanceAmount), currentHashCode, theRunningBalanceAmount);

    boolean thePending = isPending();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pending", thePending), currentHashCode, thePending);

    Categorization theCategorization = getCategorization();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "categorization", theCategorization), currentHashCode, theCategorization);

    return currentHashCode;
  }

  public int hashCode() {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}