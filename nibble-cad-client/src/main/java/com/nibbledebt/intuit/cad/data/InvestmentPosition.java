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
@XmlType(name="InvestmentPosition", namespace="http://schema.intuit.com/platform/fdatafeed/invposition/v1", propOrder={"investmentPositionId", "changePercent", "costBasis", "currencyCode", "currencyRate", "currencyType", "unitPrice", "priceAsOfDate", "cusip", "dailyChange", "memo", "empPretaxContribAmount", "empMatchAmount", "heldInAccount", "holdType", "investmentAllocation", "investmentDirection", "paidPrice", "marketValue", "maturityValue", "units", "unitUserQuantity", "unitStreetQuantity", "positionType", "positionStatus", "secured", "inv401KSource", "reinvestmentCapGains", "reinvestmentDividend", "transactionType", "invSecurityType", "otherInfo", "optionInfo", "stockInfo", "mfInfo", "debtInfo"})
@XmlRootElement(name="InvestmentPosition", namespace="http://schema.intuit.com/platform/fdatafeed/invposition/v1")
public class InvestmentPosition
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  protected long investmentPositionId;
  protected BigDecimal changePercent;
  protected BigDecimal costBasis;
  protected CurrencyCode currencyCode;
  protected BigDecimal currencyRate;
  protected String currencyType;
  protected BigDecimal unitPrice;

  @XmlElement(type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar priceAsOfDate;
  protected String cusip;
  protected BigDecimal dailyChange;
  protected String memo;
  protected BigDecimal empPretaxContribAmount;
  protected BigDecimal empMatchAmount;
  protected String heldInAccount;
  protected String holdType;
  protected String investmentAllocation;
  protected String investmentDirection;
  protected BigDecimal paidPrice;
  protected BigDecimal marketValue;
  protected BigDecimal maturityValue;
  protected BigDecimal units;
  protected BigDecimal unitUserQuantity;
  protected BigDecimal unitStreetQuantity;
  protected String positionType;
  protected String positionStatus;
  protected String secured;

  @XmlElement(name="inv401kSource")
  protected String inv401KSource;
  protected Boolean reinvestmentCapGains;
  protected Boolean reinvestmentDividend;
  protected String transactionType;
  protected InvSecurityType invSecurityType;
  protected OtherInfo otherInfo;
  protected OptionInfo optionInfo;
  protected StockInfo stockInfo;
  protected MFInfo mfInfo;
  protected DebtInfo debtInfo;

  public long getInvestmentPositionId()
  {
    return this.investmentPositionId;
  }

  public void setInvestmentPositionId(long value)
  {
    this.investmentPositionId = value;
  }

  public BigDecimal getChangePercent()
  {
    return this.changePercent;
  }

  public void setChangePercent(BigDecimal value)
  {
    this.changePercent = value;
  }

  public BigDecimal getCostBasis()
  {
    return this.costBasis;
  }

  public void setCostBasis(BigDecimal value)
  {
    this.costBasis = value;
  }

  public CurrencyCode getCurrencyCode()
  {
    return this.currencyCode;
  }

  public void setCurrencyCode(CurrencyCode value)
  {
    this.currencyCode = value;
  }

  public BigDecimal getCurrencyRate()
  {
    return this.currencyRate;
  }

  public void setCurrencyRate(BigDecimal value)
  {
    this.currencyRate = value;
  }

  public String getCurrencyType()
  {
    return this.currencyType;
  }

  public void setCurrencyType(String value)
  {
    this.currencyType = value;
  }

  public BigDecimal getUnitPrice()
  {
    return this.unitPrice;
  }

  public void setUnitPrice(BigDecimal value)
  {
    this.unitPrice = value;
  }

  public Calendar getPriceAsOfDate()
  {
    return this.priceAsOfDate;
  }

  public void setPriceAsOfDate(Calendar value)
  {
    this.priceAsOfDate = value;
  }

  public String getCusip()
  {
    return this.cusip;
  }

  public void setCusip(String value)
  {
    this.cusip = value;
  }

  public BigDecimal getDailyChange()
  {
    return this.dailyChange;
  }

  public void setDailyChange(BigDecimal value)
  {
    this.dailyChange = value;
  }

  public String getMemo()
  {
    return this.memo;
  }

  public void setMemo(String value)
  {
    this.memo = value;
  }

  public BigDecimal getEmpPretaxContribAmount()
  {
    return this.empPretaxContribAmount;
  }

  public void setEmpPretaxContribAmount(BigDecimal value)
  {
    this.empPretaxContribAmount = value;
  }

  public BigDecimal getEmpMatchAmount()
  {
    return this.empMatchAmount;
  }

  public void setEmpMatchAmount(BigDecimal value)
  {
    this.empMatchAmount = value;
  }

  public String getHeldInAccount()
  {
    return this.heldInAccount;
  }

  public void setHeldInAccount(String value)
  {
    this.heldInAccount = value;
  }

  public String getHoldType()
  {
    return this.holdType;
  }

  public void setHoldType(String value)
  {
    this.holdType = value;
  }

  public String getInvestmentAllocation()
  {
    return this.investmentAllocation;
  }

  public void setInvestmentAllocation(String value)
  {
    this.investmentAllocation = value;
  }

  public String getInvestmentDirection()
  {
    return this.investmentDirection;
  }

  public void setInvestmentDirection(String value)
  {
    this.investmentDirection = value;
  }

  public BigDecimal getPaidPrice()
  {
    return this.paidPrice;
  }

  public void setPaidPrice(BigDecimal value)
  {
    this.paidPrice = value;
  }

  public BigDecimal getMarketValue()
  {
    return this.marketValue;
  }

  public void setMarketValue(BigDecimal value)
  {
    this.marketValue = value;
  }

  public BigDecimal getMaturityValue()
  {
    return this.maturityValue;
  }

  public void setMaturityValue(BigDecimal value)
  {
    this.maturityValue = value;
  }

  public BigDecimal getUnits()
  {
    return this.units;
  }

  public void setUnits(BigDecimal value)
  {
    this.units = value;
  }

  public BigDecimal getUnitUserQuantity()
  {
    return this.unitUserQuantity;
  }

  public void setUnitUserQuantity(BigDecimal value)
  {
    this.unitUserQuantity = value;
  }

  public BigDecimal getUnitStreetQuantity()
  {
    return this.unitStreetQuantity;
  }

  public void setUnitStreetQuantity(BigDecimal value)
  {
    this.unitStreetQuantity = value;
  }

  public String getPositionType()
  {
    return this.positionType;
  }

  public void setPositionType(String value)
  {
    this.positionType = value;
  }

  public String getPositionStatus()
  {
    return this.positionStatus;
  }

  public void setPositionStatus(String value)
  {
    this.positionStatus = value;
  }

  public String getSecured()
  {
    return this.secured;
  }

  public void setSecured(String value)
  {
    this.secured = value;
  }

  public String getInv401KSource()
  {
    return this.inv401KSource;
  }

  public void setInv401KSource(String value)
  {
    this.inv401KSource = value;
  }

  public Boolean isReinvestmentCapGains()
  {
    return this.reinvestmentCapGains;
  }

  public void setReinvestmentCapGains(Boolean value)
  {
    this.reinvestmentCapGains = value;
  }

  public Boolean isReinvestmentDividend()
  {
    return this.reinvestmentDividend;
  }

  public void setReinvestmentDividend(Boolean value)
  {
    this.reinvestmentDividend = value;
  }

  public String getTransactionType()
  {
    return this.transactionType;
  }

  public void setTransactionType(String value)
  {
    this.transactionType = value;
  }

  public InvSecurityType getInvSecurityType()
  {
    return this.invSecurityType;
  }

  public void setInvSecurityType(InvSecurityType value)
  {
    this.invSecurityType = value;
  }

  public OtherInfo getOtherInfo()
  {
    return this.otherInfo;
  }

  public void setOtherInfo(OtherInfo value)
  {
    this.otherInfo = value;
  }

  public OptionInfo getOptionInfo()
  {
    return this.optionInfo;
  }

  public void setOptionInfo(OptionInfo value)
  {
    this.optionInfo = value;
  }

  public StockInfo getStockInfo()
  {
    return this.stockInfo;
  }

  public void setStockInfo(StockInfo value)
  {
    this.stockInfo = value;
  }

  public MFInfo getMfInfo()
  {
    return this.mfInfo;
  }

  public void setMfInfo(MFInfo value)
  {
    this.mfInfo = value;
  }

  public DebtInfo getDebtInfo()
  {
    return this.debtInfo;
  }

  public void setDebtInfo(DebtInfo value)
  {
    this.debtInfo = value;
  }

  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
    if (!(object instanceof InvestmentPosition)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    InvestmentPosition that = (InvestmentPosition)object;

    long lhsInvestmentPositionId = getInvestmentPositionId();

    long rhsInvestmentPositionId = that.getInvestmentPositionId();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "investmentPositionId", lhsInvestmentPositionId), LocatorUtils.property(thatLocator, "investmentPositionId", rhsInvestmentPositionId), lhsInvestmentPositionId, rhsInvestmentPositionId)) {
      return false;
    }

    BigDecimal lhsChangePercent = getChangePercent();

    BigDecimal rhsChangePercent = that.getChangePercent();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "changePercent", lhsChangePercent), LocatorUtils.property(thatLocator, "changePercent", rhsChangePercent), lhsChangePercent, rhsChangePercent)) {
      return false;
    }

    BigDecimal lhsCostBasis = getCostBasis();

    BigDecimal rhsCostBasis = that.getCostBasis();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "costBasis", lhsCostBasis), LocatorUtils.property(thatLocator, "costBasis", rhsCostBasis), lhsCostBasis, rhsCostBasis)) {
      return false;
    }

    CurrencyCode lhsCurrencyCode = getCurrencyCode();

    CurrencyCode rhsCurrencyCode = that.getCurrencyCode();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "currencyCode", lhsCurrencyCode), LocatorUtils.property(thatLocator, "currencyCode", rhsCurrencyCode), lhsCurrencyCode, rhsCurrencyCode)) {
      return false;
    }

    BigDecimal lhsCurrencyRate = getCurrencyRate();

    BigDecimal rhsCurrencyRate = that.getCurrencyRate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "currencyRate", lhsCurrencyRate), LocatorUtils.property(thatLocator, "currencyRate", rhsCurrencyRate), lhsCurrencyRate, rhsCurrencyRate)) {
      return false;
    }

    String lhsCurrencyType = getCurrencyType();

    String rhsCurrencyType = that.getCurrencyType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "currencyType", lhsCurrencyType), LocatorUtils.property(thatLocator, "currencyType", rhsCurrencyType), lhsCurrencyType, rhsCurrencyType)) {
      return false;
    }

    BigDecimal lhsUnitPrice = getUnitPrice();

    BigDecimal rhsUnitPrice = that.getUnitPrice();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "unitPrice", lhsUnitPrice), LocatorUtils.property(thatLocator, "unitPrice", rhsUnitPrice), lhsUnitPrice, rhsUnitPrice)) {
      return false;
    }

    Calendar lhsPriceAsOfDate = getPriceAsOfDate();

    Calendar rhsPriceAsOfDate = that.getPriceAsOfDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "priceAsOfDate", lhsPriceAsOfDate), LocatorUtils.property(thatLocator, "priceAsOfDate", rhsPriceAsOfDate), lhsPriceAsOfDate, rhsPriceAsOfDate)) {
      return false;
    }

    String lhsCusip = getCusip();

    String rhsCusip = that.getCusip();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "cusip", lhsCusip), LocatorUtils.property(thatLocator, "cusip", rhsCusip), lhsCusip, rhsCusip)) {
      return false;
    }

    BigDecimal lhsDailyChange = getDailyChange();

    BigDecimal rhsDailyChange = that.getDailyChange();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "dailyChange", lhsDailyChange), LocatorUtils.property(thatLocator, "dailyChange", rhsDailyChange), lhsDailyChange, rhsDailyChange)) {
      return false;
    }

    String lhsMemo = getMemo();

    String rhsMemo = that.getMemo();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "memo", lhsMemo), LocatorUtils.property(thatLocator, "memo", rhsMemo), lhsMemo, rhsMemo)) {
      return false;
    }

    BigDecimal lhsEmpPretaxContribAmount = getEmpPretaxContribAmount();

    BigDecimal rhsEmpPretaxContribAmount = that.getEmpPretaxContribAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "empPretaxContribAmount", lhsEmpPretaxContribAmount), LocatorUtils.property(thatLocator, "empPretaxContribAmount", rhsEmpPretaxContribAmount), lhsEmpPretaxContribAmount, rhsEmpPretaxContribAmount)) {
      return false;
    }

    BigDecimal lhsEmpMatchAmount = getEmpMatchAmount();

    BigDecimal rhsEmpMatchAmount = that.getEmpMatchAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "empMatchAmount", lhsEmpMatchAmount), LocatorUtils.property(thatLocator, "empMatchAmount", rhsEmpMatchAmount), lhsEmpMatchAmount, rhsEmpMatchAmount)) {
      return false;
    }

    String lhsHeldInAccount = getHeldInAccount();

    String rhsHeldInAccount = that.getHeldInAccount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "heldInAccount", lhsHeldInAccount), LocatorUtils.property(thatLocator, "heldInAccount", rhsHeldInAccount), lhsHeldInAccount, rhsHeldInAccount)) {
      return false;
    }

    String lhsHoldType = getHoldType();

    String rhsHoldType = that.getHoldType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "holdType", lhsHoldType), LocatorUtils.property(thatLocator, "holdType", rhsHoldType), lhsHoldType, rhsHoldType)) {
      return false;
    }

    String lhsInvestmentAllocation = getInvestmentAllocation();

    String rhsInvestmentAllocation = that.getInvestmentAllocation();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "investmentAllocation", lhsInvestmentAllocation), LocatorUtils.property(thatLocator, "investmentAllocation", rhsInvestmentAllocation), lhsInvestmentAllocation, rhsInvestmentAllocation)) {
      return false;
    }

    String lhsInvestmentDirection = getInvestmentDirection();

    String rhsInvestmentDirection = that.getInvestmentDirection();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "investmentDirection", lhsInvestmentDirection), LocatorUtils.property(thatLocator, "investmentDirection", rhsInvestmentDirection), lhsInvestmentDirection, rhsInvestmentDirection)) {
      return false;
    }

    BigDecimal lhsPaidPrice = getPaidPrice();

    BigDecimal rhsPaidPrice = that.getPaidPrice();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "paidPrice", lhsPaidPrice), LocatorUtils.property(thatLocator, "paidPrice", rhsPaidPrice), lhsPaidPrice, rhsPaidPrice)) {
      return false;
    }

    BigDecimal lhsMarketValue = getMarketValue();

    BigDecimal rhsMarketValue = that.getMarketValue();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "marketValue", lhsMarketValue), LocatorUtils.property(thatLocator, "marketValue", rhsMarketValue), lhsMarketValue, rhsMarketValue)) {
      return false;
    }

    BigDecimal lhsMaturityValue = getMaturityValue();

    BigDecimal rhsMaturityValue = that.getMaturityValue();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "maturityValue", lhsMaturityValue), LocatorUtils.property(thatLocator, "maturityValue", rhsMaturityValue), lhsMaturityValue, rhsMaturityValue)) {
      return false;
    }

    BigDecimal lhsUnits = getUnits();

    BigDecimal rhsUnits = that.getUnits();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "units", lhsUnits), LocatorUtils.property(thatLocator, "units", rhsUnits), lhsUnits, rhsUnits)) {
      return false;
    }

    BigDecimal lhsUnitUserQuantity = getUnitUserQuantity();

    BigDecimal rhsUnitUserQuantity = that.getUnitUserQuantity();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "unitUserQuantity", lhsUnitUserQuantity), LocatorUtils.property(thatLocator, "unitUserQuantity", rhsUnitUserQuantity), lhsUnitUserQuantity, rhsUnitUserQuantity)) {
      return false;
    }

    BigDecimal lhsUnitStreetQuantity = getUnitStreetQuantity();

    BigDecimal rhsUnitStreetQuantity = that.getUnitStreetQuantity();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "unitStreetQuantity", lhsUnitStreetQuantity), LocatorUtils.property(thatLocator, "unitStreetQuantity", rhsUnitStreetQuantity), lhsUnitStreetQuantity, rhsUnitStreetQuantity)) {
      return false;
    }

    String lhsPositionType = getPositionType();

    String rhsPositionType = that.getPositionType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "positionType", lhsPositionType), LocatorUtils.property(thatLocator, "positionType", rhsPositionType), lhsPositionType, rhsPositionType)) {
      return false;
    }

    String lhsPositionStatus = getPositionStatus();

    String rhsPositionStatus = that.getPositionStatus();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "positionStatus", lhsPositionStatus), LocatorUtils.property(thatLocator, "positionStatus", rhsPositionStatus), lhsPositionStatus, rhsPositionStatus)) {
      return false;
    }

    String lhsSecured = getSecured();

    String rhsSecured = that.getSecured();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "secured", lhsSecured), LocatorUtils.property(thatLocator, "secured", rhsSecured), lhsSecured, rhsSecured)) {
      return false;
    }

    String lhsInv401KSource = getInv401KSource();

    String rhsInv401KSource = that.getInv401KSource();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "inv401KSource", lhsInv401KSource), LocatorUtils.property(thatLocator, "inv401KSource", rhsInv401KSource), lhsInv401KSource, rhsInv401KSource)) {
      return false;
    }

    Boolean lhsReinvestmentCapGains = isReinvestmentCapGains();

    Boolean rhsReinvestmentCapGains = that.isReinvestmentCapGains();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "reinvestmentCapGains", lhsReinvestmentCapGains), LocatorUtils.property(thatLocator, "reinvestmentCapGains", rhsReinvestmentCapGains), lhsReinvestmentCapGains, rhsReinvestmentCapGains)) {
      return false;
    }

    Boolean lhsReinvestmentDividend = isReinvestmentDividend();

    Boolean rhsReinvestmentDividend = that.isReinvestmentDividend();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "reinvestmentDividend", lhsReinvestmentDividend), LocatorUtils.property(thatLocator, "reinvestmentDividend", rhsReinvestmentDividend), lhsReinvestmentDividend, rhsReinvestmentDividend)) {
      return false;
    }

    String lhsTransactionType = getTransactionType();

    String rhsTransactionType = that.getTransactionType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "transactionType", lhsTransactionType), LocatorUtils.property(thatLocator, "transactionType", rhsTransactionType), lhsTransactionType, rhsTransactionType)) {
      return false;
    }

    InvSecurityType lhsInvSecurityType = getInvSecurityType();

    InvSecurityType rhsInvSecurityType = that.getInvSecurityType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "invSecurityType", lhsInvSecurityType), LocatorUtils.property(thatLocator, "invSecurityType", rhsInvSecurityType), lhsInvSecurityType, rhsInvSecurityType)) {
      return false;
    }

    OtherInfo lhsOtherInfo = getOtherInfo();

    OtherInfo rhsOtherInfo = that.getOtherInfo();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "otherInfo", lhsOtherInfo), LocatorUtils.property(thatLocator, "otherInfo", rhsOtherInfo), lhsOtherInfo, rhsOtherInfo)) {
      return false;
    }

    OptionInfo lhsOptionInfo = getOptionInfo();

    OptionInfo rhsOptionInfo = that.getOptionInfo();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "optionInfo", lhsOptionInfo), LocatorUtils.property(thatLocator, "optionInfo", rhsOptionInfo), lhsOptionInfo, rhsOptionInfo)) {
      return false;
    }

    StockInfo lhsStockInfo = getStockInfo();

    StockInfo rhsStockInfo = that.getStockInfo();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "stockInfo", lhsStockInfo), LocatorUtils.property(thatLocator, "stockInfo", rhsStockInfo), lhsStockInfo, rhsStockInfo)) {
      return false;
    }

    MFInfo lhsMfInfo = getMfInfo();

    MFInfo rhsMfInfo = that.getMfInfo();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "mfInfo", lhsMfInfo), LocatorUtils.property(thatLocator, "mfInfo", rhsMfInfo), lhsMfInfo, rhsMfInfo)) {
      return false;
    }

    DebtInfo lhsDebtInfo = getDebtInfo();

    DebtInfo rhsDebtInfo = that.getDebtInfo();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "debtInfo", lhsDebtInfo), LocatorUtils.property(thatLocator, "debtInfo", rhsDebtInfo), lhsDebtInfo, rhsDebtInfo)) {
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

    long theInvestmentPositionId = getInvestmentPositionId();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "investmentPositionId", theInvestmentPositionId), currentHashCode, theInvestmentPositionId);

    BigDecimal theChangePercent = getChangePercent();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "changePercent", theChangePercent), currentHashCode, theChangePercent);

    BigDecimal theCostBasis = getCostBasis();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "costBasis", theCostBasis), currentHashCode, theCostBasis);

    CurrencyCode theCurrencyCode = getCurrencyCode();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "currencyCode", theCurrencyCode), currentHashCode, theCurrencyCode);

    BigDecimal theCurrencyRate = getCurrencyRate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "currencyRate", theCurrencyRate), currentHashCode, theCurrencyRate);

    String theCurrencyType = getCurrencyType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "currencyType", theCurrencyType), currentHashCode, theCurrencyType);

    BigDecimal theUnitPrice = getUnitPrice();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "unitPrice", theUnitPrice), currentHashCode, theUnitPrice);

    Calendar thePriceAsOfDate = getPriceAsOfDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "priceAsOfDate", thePriceAsOfDate), currentHashCode, thePriceAsOfDate);

    String theCusip = getCusip();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "cusip", theCusip), currentHashCode, theCusip);

    BigDecimal theDailyChange = getDailyChange();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dailyChange", theDailyChange), currentHashCode, theDailyChange);

    String theMemo = getMemo();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "memo", theMemo), currentHashCode, theMemo);

    BigDecimal theEmpPretaxContribAmount = getEmpPretaxContribAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "empPretaxContribAmount", theEmpPretaxContribAmount), currentHashCode, theEmpPretaxContribAmount);

    BigDecimal theEmpMatchAmount = getEmpMatchAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "empMatchAmount", theEmpMatchAmount), currentHashCode, theEmpMatchAmount);

    String theHeldInAccount = getHeldInAccount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "heldInAccount", theHeldInAccount), currentHashCode, theHeldInAccount);

    String theHoldType = getHoldType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "holdType", theHoldType), currentHashCode, theHoldType);

    String theInvestmentAllocation = getInvestmentAllocation();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "investmentAllocation", theInvestmentAllocation), currentHashCode, theInvestmentAllocation);

    String theInvestmentDirection = getInvestmentDirection();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "investmentDirection", theInvestmentDirection), currentHashCode, theInvestmentDirection);

    BigDecimal thePaidPrice = getPaidPrice();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "paidPrice", thePaidPrice), currentHashCode, thePaidPrice);

    BigDecimal theMarketValue = getMarketValue();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "marketValue", theMarketValue), currentHashCode, theMarketValue);

    BigDecimal theMaturityValue = getMaturityValue();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maturityValue", theMaturityValue), currentHashCode, theMaturityValue);

    BigDecimal theUnits = getUnits();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "units", theUnits), currentHashCode, theUnits);

    BigDecimal theUnitUserQuantity = getUnitUserQuantity();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "unitUserQuantity", theUnitUserQuantity), currentHashCode, theUnitUserQuantity);

    BigDecimal theUnitStreetQuantity = getUnitStreetQuantity();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "unitStreetQuantity", theUnitStreetQuantity), currentHashCode, theUnitStreetQuantity);

    String thePositionType = getPositionType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "positionType", thePositionType), currentHashCode, thePositionType);

    String thePositionStatus = getPositionStatus();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "positionStatus", thePositionStatus), currentHashCode, thePositionStatus);

    String theSecured = getSecured();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "secured", theSecured), currentHashCode, theSecured);

    String theInv401KSource = getInv401KSource();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "inv401KSource", theInv401KSource), currentHashCode, theInv401KSource);

    Boolean theReinvestmentCapGains = isReinvestmentCapGains();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "reinvestmentCapGains", theReinvestmentCapGains), currentHashCode, theReinvestmentCapGains);

    Boolean theReinvestmentDividend = isReinvestmentDividend();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "reinvestmentDividend", theReinvestmentDividend), currentHashCode, theReinvestmentDividend);

    String theTransactionType = getTransactionType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "transactionType", theTransactionType), currentHashCode, theTransactionType);

    InvSecurityType theInvSecurityType = getInvSecurityType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "invSecurityType", theInvSecurityType), currentHashCode, theInvSecurityType);

    OtherInfo theOtherInfo = getOtherInfo();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "otherInfo", theOtherInfo), currentHashCode, theOtherInfo);

    OptionInfo theOptionInfo = getOptionInfo();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "optionInfo", theOptionInfo), currentHashCode, theOptionInfo);

    StockInfo theStockInfo = getStockInfo();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "stockInfo", theStockInfo), currentHashCode, theStockInfo);

    MFInfo theMfInfo = getMfInfo();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "mfInfo", theMfInfo), currentHashCode, theMfInfo);

    DebtInfo theDebtInfo = getDebtInfo();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "debtInfo", theDebtInfo), currentHashCode, theDebtInfo);

    return currentHashCode;
  }

  public int hashCode() {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}