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
@XmlType(name="InvestmentTransaction", namespace="http://schema.intuit.com/platform/fdatafeed/invtransaction/v1", propOrder={"reversalInstitutionTransactionId", "description", "buyType", "incomeType", "inv401KSource", "loanId", "optionsActionType", "optionsBuyType", "optionsSellType", "positionType", "relatedInstitutionTradeId", "relatedOptionTransType", "securedType", "sellReason", "sellType", "subaccountFromType", "subaccountFundType", "subaccountSecurityType", "subaccountToType", "transferAction", "unitType", "cusip", "symbol", "unitAction", "optionsSecurity", "tradeDate", "settleDate", "accruedInterestAmount", "averageCostBasisAmount", "commissionAmount", "denominator", "payrollDate", "purchaseDate", "gainAmount", "feesAmount", "fractionalUnitsCashAmount", "loadAmount", "loanInterestAmount", "loanPrincipalAmount", "markdownAmount", "markupAmount", "newUnits", "numerator", "oldUnits", "penaltyAmount", "priorYearContribution", "sharesPerContract", "stateWithholding", "totalAmount", "taxesAmount", "taxExempt", "unitPrice", "units", "withholdingAmount", "optionsSharesPerContract", "invSecurityType", "otherInfo", "optionInfo", "stockInfo", "mfInfo", "debtInfo"})
@XmlRootElement(name="InvestmentTransaction", namespace="http://schema.intuit.com/platform/fdatafeed/invtransaction/v1")
public class InvestmentTransaction extends Transaction
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  protected String reversalInstitutionTransactionId;
  protected String description;
  protected BuyType buyType;
  protected IncomeType incomeType;

  @XmlElement(name="inv401kSource")
  protected String inv401KSource;
  protected String loanId;
  protected OptionsActionType optionsActionType;
  protected OptionsBuyType optionsBuyType;
  protected OptionsSellType optionsSellType;
  protected PositionType positionType;
  protected String relatedInstitutionTradeId;
  protected RelatedOptionTransType relatedOptionTransType;
  protected SecuredType securedType;
  protected SellReason sellReason;
  protected SellType sellType;
  protected InvestmentSubAccountType subaccountFromType;
  protected InvestmentSubAccountType subaccountFundType;
  protected InvestmentSubAccountType subaccountSecurityType;
  protected InvestmentSubAccountType subaccountToType;
  protected TransferAction transferAction;
  protected String unitType;
  protected String cusip;
  protected String symbol;
  protected String unitAction;
  protected String optionsSecurity;

  @XmlElement(type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar tradeDate;

  @XmlElement(type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar settleDate;
  protected BigDecimal accruedInterestAmount;
  protected BigDecimal averageCostBasisAmount;
  protected BigDecimal commissionAmount;
  protected BigDecimal denominator;

  @XmlElement(type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar payrollDate;

  @XmlElement(type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar purchaseDate;
  protected BigDecimal gainAmount;
  protected BigDecimal feesAmount;
  protected BigDecimal fractionalUnitsCashAmount;
  protected BigDecimal loadAmount;
  protected BigDecimal loanInterestAmount;
  protected BigDecimal loanPrincipalAmount;
  protected BigDecimal markdownAmount;
  protected BigDecimal markupAmount;
  protected BigDecimal newUnits;
  protected BigDecimal numerator;
  protected BigDecimal oldUnits;
  protected BigDecimal penaltyAmount;
  protected Boolean priorYearContribution;
  protected Short sharesPerContract;
  protected BigDecimal stateWithholding;
  protected BigDecimal totalAmount;
  protected BigDecimal taxesAmount;
  protected Boolean taxExempt;
  protected BigDecimal unitPrice;
  protected BigDecimal units;
  protected BigDecimal withholdingAmount;
  protected Long optionsSharesPerContract;
  protected InvSecurityType invSecurityType;
  protected OtherInfo otherInfo;
  protected OptionInfo optionInfo;
  protected StockInfo stockInfo;
  protected MFInfo mfInfo;
  protected DebtInfo debtInfo;

  public String getReversalInstitutionTransactionId()
  {
    return this.reversalInstitutionTransactionId;
  }

  public void setReversalInstitutionTransactionId(String value)
  {
    this.reversalInstitutionTransactionId = value;
  }

  public String getDescription()
  {
    return this.description;
  }

  public void setDescription(String value)
  {
    this.description = value;
  }

  public BuyType getBuyType()
  {
    return this.buyType;
  }

  public void setBuyType(BuyType value)
  {
    this.buyType = value;
  }

  public IncomeType getIncomeType()
  {
    return this.incomeType;
  }

  public void setIncomeType(IncomeType value)
  {
    this.incomeType = value;
  }

  public String getInv401KSource()
  {
    return this.inv401KSource;
  }

  public void setInv401KSource(String value)
  {
    this.inv401KSource = value;
  }

  public String getLoanId()
  {
    return this.loanId;
  }

  public void setLoanId(String value)
  {
    this.loanId = value;
  }

  public OptionsActionType getOptionsActionType()
  {
    return this.optionsActionType;
  }

  public void setOptionsActionType(OptionsActionType value)
  {
    this.optionsActionType = value;
  }

  public OptionsBuyType getOptionsBuyType()
  {
    return this.optionsBuyType;
  }

  public void setOptionsBuyType(OptionsBuyType value)
  {
    this.optionsBuyType = value;
  }

  public OptionsSellType getOptionsSellType()
  {
    return this.optionsSellType;
  }

  public void setOptionsSellType(OptionsSellType value)
  {
    this.optionsSellType = value;
  }

  public PositionType getPositionType()
  {
    return this.positionType;
  }

  public void setPositionType(PositionType value)
  {
    this.positionType = value;
  }

  public String getRelatedInstitutionTradeId()
  {
    return this.relatedInstitutionTradeId;
  }

  public void setRelatedInstitutionTradeId(String value)
  {
    this.relatedInstitutionTradeId = value;
  }

  public RelatedOptionTransType getRelatedOptionTransType()
  {
    return this.relatedOptionTransType;
  }

  public void setRelatedOptionTransType(RelatedOptionTransType value)
  {
    this.relatedOptionTransType = value;
  }

  public SecuredType getSecuredType()
  {
    return this.securedType;
  }

  public void setSecuredType(SecuredType value)
  {
    this.securedType = value;
  }

  public SellReason getSellReason()
  {
    return this.sellReason;
  }

  public void setSellReason(SellReason value)
  {
    this.sellReason = value;
  }

  public SellType getSellType()
  {
    return this.sellType;
  }

  public void setSellType(SellType value)
  {
    this.sellType = value;
  }

  public InvestmentSubAccountType getSubaccountFromType()
  {
    return this.subaccountFromType;
  }

  public void setSubaccountFromType(InvestmentSubAccountType value)
  {
    this.subaccountFromType = value;
  }

  public InvestmentSubAccountType getSubaccountFundType()
  {
    return this.subaccountFundType;
  }

  public void setSubaccountFundType(InvestmentSubAccountType value)
  {
    this.subaccountFundType = value;
  }

  public InvestmentSubAccountType getSubaccountSecurityType()
  {
    return this.subaccountSecurityType;
  }

  public void setSubaccountSecurityType(InvestmentSubAccountType value)
  {
    this.subaccountSecurityType = value;
  }

  public InvestmentSubAccountType getSubaccountToType()
  {
    return this.subaccountToType;
  }

  public void setSubaccountToType(InvestmentSubAccountType value)
  {
    this.subaccountToType = value;
  }

  public TransferAction getTransferAction()
  {
    return this.transferAction;
  }

  public void setTransferAction(TransferAction value)
  {
    this.transferAction = value;
  }

  public String getUnitType()
  {
    return this.unitType;
  }

  public void setUnitType(String value)
  {
    this.unitType = value;
  }

  public String getCusip()
  {
    return this.cusip;
  }

  public void setCusip(String value)
  {
    this.cusip = value;
  }

  public String getSymbol()
  {
    return this.symbol;
  }

  public void setSymbol(String value)
  {
    this.symbol = value;
  }

  public String getUnitAction()
  {
    return this.unitAction;
  }

  public void setUnitAction(String value)
  {
    this.unitAction = value;
  }

  public String getOptionsSecurity()
  {
    return this.optionsSecurity;
  }

  public void setOptionsSecurity(String value)
  {
    this.optionsSecurity = value;
  }

  public Calendar getTradeDate()
  {
    return this.tradeDate;
  }

  public void setTradeDate(Calendar value)
  {
    this.tradeDate = value;
  }

  public Calendar getSettleDate()
  {
    return this.settleDate;
  }

  public void setSettleDate(Calendar value)
  {
    this.settleDate = value;
  }

  public BigDecimal getAccruedInterestAmount()
  {
    return this.accruedInterestAmount;
  }

  public void setAccruedInterestAmount(BigDecimal value)
  {
    this.accruedInterestAmount = value;
  }

  public BigDecimal getAverageCostBasisAmount()
  {
    return this.averageCostBasisAmount;
  }

  public void setAverageCostBasisAmount(BigDecimal value)
  {
    this.averageCostBasisAmount = value;
  }

  public BigDecimal getCommissionAmount()
  {
    return this.commissionAmount;
  }

  public void setCommissionAmount(BigDecimal value)
  {
    this.commissionAmount = value;
  }

  public BigDecimal getDenominator()
  {
    return this.denominator;
  }

  public void setDenominator(BigDecimal value)
  {
    this.denominator = value;
  }

  public Calendar getPayrollDate()
  {
    return this.payrollDate;
  }

  public void setPayrollDate(Calendar value)
  {
    this.payrollDate = value;
  }

  public Calendar getPurchaseDate()
  {
    return this.purchaseDate;
  }

  public void setPurchaseDate(Calendar value)
  {
    this.purchaseDate = value;
  }

  public BigDecimal getGainAmount()
  {
    return this.gainAmount;
  }

  public void setGainAmount(BigDecimal value)
  {
    this.gainAmount = value;
  }

  public BigDecimal getFeesAmount()
  {
    return this.feesAmount;
  }

  public void setFeesAmount(BigDecimal value)
  {
    this.feesAmount = value;
  }

  public BigDecimal getFractionalUnitsCashAmount()
  {
    return this.fractionalUnitsCashAmount;
  }

  public void setFractionalUnitsCashAmount(BigDecimal value)
  {
    this.fractionalUnitsCashAmount = value;
  }

  public BigDecimal getLoadAmount()
  {
    return this.loadAmount;
  }

  public void setLoadAmount(BigDecimal value)
  {
    this.loadAmount = value;
  }

  public BigDecimal getLoanInterestAmount()
  {
    return this.loanInterestAmount;
  }

  public void setLoanInterestAmount(BigDecimal value)
  {
    this.loanInterestAmount = value;
  }

  public BigDecimal getLoanPrincipalAmount()
  {
    return this.loanPrincipalAmount;
  }

  public void setLoanPrincipalAmount(BigDecimal value)
  {
    this.loanPrincipalAmount = value;
  }

  public BigDecimal getMarkdownAmount()
  {
    return this.markdownAmount;
  }

  public void setMarkdownAmount(BigDecimal value)
  {
    this.markdownAmount = value;
  }

  public BigDecimal getMarkupAmount()
  {
    return this.markupAmount;
  }

  public void setMarkupAmount(BigDecimal value)
  {
    this.markupAmount = value;
  }

  public BigDecimal getNewUnits()
  {
    return this.newUnits;
  }

  public void setNewUnits(BigDecimal value)
  {
    this.newUnits = value;
  }

  public BigDecimal getNumerator()
  {
    return this.numerator;
  }

  public void setNumerator(BigDecimal value)
  {
    this.numerator = value;
  }

  public BigDecimal getOldUnits()
  {
    return this.oldUnits;
  }

  public void setOldUnits(BigDecimal value)
  {
    this.oldUnits = value;
  }

  public BigDecimal getPenaltyAmount()
  {
    return this.penaltyAmount;
  }

  public void setPenaltyAmount(BigDecimal value)
  {
    this.penaltyAmount = value;
  }

  public Boolean isPriorYearContribution()
  {
    return this.priorYearContribution;
  }

  public void setPriorYearContribution(Boolean value)
  {
    this.priorYearContribution = value;
  }

  public Short getSharesPerContract()
  {
    return this.sharesPerContract;
  }

  public void setSharesPerContract(Short value)
  {
    this.sharesPerContract = value;
  }

  public BigDecimal getStateWithholding()
  {
    return this.stateWithholding;
  }

  public void setStateWithholding(BigDecimal value)
  {
    this.stateWithholding = value;
  }

  public BigDecimal getTotalAmount()
  {
    return this.totalAmount;
  }

  public void setTotalAmount(BigDecimal value)
  {
    this.totalAmount = value;
  }

  public BigDecimal getTaxesAmount()
  {
    return this.taxesAmount;
  }

  public void setTaxesAmount(BigDecimal value)
  {
    this.taxesAmount = value;
  }

  public Boolean isTaxExempt()
  {
    return this.taxExempt;
  }

  public void setTaxExempt(Boolean value)
  {
    this.taxExempt = value;
  }

  public BigDecimal getUnitPrice()
  {
    return this.unitPrice;
  }

  public void setUnitPrice(BigDecimal value)
  {
    this.unitPrice = value;
  }

  public BigDecimal getUnits()
  {
    return this.units;
  }

  public void setUnits(BigDecimal value)
  {
    this.units = value;
  }

  public BigDecimal getWithholdingAmount()
  {
    return this.withholdingAmount;
  }

  public void setWithholdingAmount(BigDecimal value)
  {
    this.withholdingAmount = value;
  }

  public Long getOptionsSharesPerContract()
  {
    return this.optionsSharesPerContract;
  }

  public void setOptionsSharesPerContract(Long value)
  {
    this.optionsSharesPerContract = value;
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
    if (!(object instanceof InvestmentTransaction)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    if (!super.equals(thisLocator, thatLocator, object, strategy)) {
      return false;
    }
    InvestmentTransaction that = (InvestmentTransaction)object;

    String lhsReversalInstitutionTransactionId = getReversalInstitutionTransactionId();

    String rhsReversalInstitutionTransactionId = that.getReversalInstitutionTransactionId();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "reversalInstitutionTransactionId", lhsReversalInstitutionTransactionId), LocatorUtils.property(thatLocator, "reversalInstitutionTransactionId", rhsReversalInstitutionTransactionId), lhsReversalInstitutionTransactionId, rhsReversalInstitutionTransactionId)) {
      return false;
    }

    String lhsDescription = getDescription();

    String rhsDescription = that.getDescription();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription)) {
      return false;
    }

    BuyType lhsBuyType = getBuyType();

    BuyType rhsBuyType = that.getBuyType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "buyType", lhsBuyType), LocatorUtils.property(thatLocator, "buyType", rhsBuyType), lhsBuyType, rhsBuyType)) {
      return false;
    }

    IncomeType lhsIncomeType = getIncomeType();

    IncomeType rhsIncomeType = that.getIncomeType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "incomeType", lhsIncomeType), LocatorUtils.property(thatLocator, "incomeType", rhsIncomeType), lhsIncomeType, rhsIncomeType)) {
      return false;
    }

    String lhsInv401KSource = getInv401KSource();

    String rhsInv401KSource = that.getInv401KSource();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "inv401KSource", lhsInv401KSource), LocatorUtils.property(thatLocator, "inv401KSource", rhsInv401KSource), lhsInv401KSource, rhsInv401KSource)) {
      return false;
    }

    String lhsLoanId = getLoanId();

    String rhsLoanId = that.getLoanId();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "loanId", lhsLoanId), LocatorUtils.property(thatLocator, "loanId", rhsLoanId), lhsLoanId, rhsLoanId)) {
      return false;
    }

    OptionsActionType lhsOptionsActionType = getOptionsActionType();

    OptionsActionType rhsOptionsActionType = that.getOptionsActionType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "optionsActionType", lhsOptionsActionType), LocatorUtils.property(thatLocator, "optionsActionType", rhsOptionsActionType), lhsOptionsActionType, rhsOptionsActionType)) {
      return false;
    }

    OptionsBuyType lhsOptionsBuyType = getOptionsBuyType();

    OptionsBuyType rhsOptionsBuyType = that.getOptionsBuyType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "optionsBuyType", lhsOptionsBuyType), LocatorUtils.property(thatLocator, "optionsBuyType", rhsOptionsBuyType), lhsOptionsBuyType, rhsOptionsBuyType)) {
      return false;
    }

    OptionsSellType lhsOptionsSellType = getOptionsSellType();

    OptionsSellType rhsOptionsSellType = that.getOptionsSellType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "optionsSellType", lhsOptionsSellType), LocatorUtils.property(thatLocator, "optionsSellType", rhsOptionsSellType), lhsOptionsSellType, rhsOptionsSellType)) {
      return false;
    }

    PositionType lhsPositionType = getPositionType();

    PositionType rhsPositionType = that.getPositionType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "positionType", lhsPositionType), LocatorUtils.property(thatLocator, "positionType", rhsPositionType), lhsPositionType, rhsPositionType)) {
      return false;
    }

    String lhsRelatedInstitutionTradeId = getRelatedInstitutionTradeId();

    String rhsRelatedInstitutionTradeId = that.getRelatedInstitutionTradeId();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "relatedInstitutionTradeId", lhsRelatedInstitutionTradeId), LocatorUtils.property(thatLocator, "relatedInstitutionTradeId", rhsRelatedInstitutionTradeId), lhsRelatedInstitutionTradeId, rhsRelatedInstitutionTradeId)) {
      return false;
    }

    RelatedOptionTransType lhsRelatedOptionTransType = getRelatedOptionTransType();

    RelatedOptionTransType rhsRelatedOptionTransType = that.getRelatedOptionTransType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "relatedOptionTransType", lhsRelatedOptionTransType), LocatorUtils.property(thatLocator, "relatedOptionTransType", rhsRelatedOptionTransType), lhsRelatedOptionTransType, rhsRelatedOptionTransType)) {
      return false;
    }

    SecuredType lhsSecuredType = getSecuredType();

    SecuredType rhsSecuredType = that.getSecuredType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "securedType", lhsSecuredType), LocatorUtils.property(thatLocator, "securedType", rhsSecuredType), lhsSecuredType, rhsSecuredType)) {
      return false;
    }

    SellReason lhsSellReason = getSellReason();

    SellReason rhsSellReason = that.getSellReason();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "sellReason", lhsSellReason), LocatorUtils.property(thatLocator, "sellReason", rhsSellReason), lhsSellReason, rhsSellReason)) {
      return false;
    }

    SellType lhsSellType = getSellType();

    SellType rhsSellType = that.getSellType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "sellType", lhsSellType), LocatorUtils.property(thatLocator, "sellType", rhsSellType), lhsSellType, rhsSellType)) {
      return false;
    }

    InvestmentSubAccountType lhsSubaccountFromType = getSubaccountFromType();

    InvestmentSubAccountType rhsSubaccountFromType = that.getSubaccountFromType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "subaccountFromType", lhsSubaccountFromType), LocatorUtils.property(thatLocator, "subaccountFromType", rhsSubaccountFromType), lhsSubaccountFromType, rhsSubaccountFromType)) {
      return false;
    }

    InvestmentSubAccountType lhsSubaccountFundType = getSubaccountFundType();

    InvestmentSubAccountType rhsSubaccountFundType = that.getSubaccountFundType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "subaccountFundType", lhsSubaccountFundType), LocatorUtils.property(thatLocator, "subaccountFundType", rhsSubaccountFundType), lhsSubaccountFundType, rhsSubaccountFundType)) {
      return false;
    }

    InvestmentSubAccountType lhsSubaccountSecurityType = getSubaccountSecurityType();

    InvestmentSubAccountType rhsSubaccountSecurityType = that.getSubaccountSecurityType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "subaccountSecurityType", lhsSubaccountSecurityType), LocatorUtils.property(thatLocator, "subaccountSecurityType", rhsSubaccountSecurityType), lhsSubaccountSecurityType, rhsSubaccountSecurityType)) {
      return false;
    }

    InvestmentSubAccountType lhsSubaccountToType = getSubaccountToType();

    InvestmentSubAccountType rhsSubaccountToType = that.getSubaccountToType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "subaccountToType", lhsSubaccountToType), LocatorUtils.property(thatLocator, "subaccountToType", rhsSubaccountToType), lhsSubaccountToType, rhsSubaccountToType)) {
      return false;
    }

    TransferAction lhsTransferAction = getTransferAction();

    TransferAction rhsTransferAction = that.getTransferAction();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "transferAction", lhsTransferAction), LocatorUtils.property(thatLocator, "transferAction", rhsTransferAction), lhsTransferAction, rhsTransferAction)) {
      return false;
    }

    String lhsUnitType = getUnitType();

    String rhsUnitType = that.getUnitType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "unitType", lhsUnitType), LocatorUtils.property(thatLocator, "unitType", rhsUnitType), lhsUnitType, rhsUnitType)) {
      return false;
    }

    String lhsCusip = getCusip();

    String rhsCusip = that.getCusip();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "cusip", lhsCusip), LocatorUtils.property(thatLocator, "cusip", rhsCusip), lhsCusip, rhsCusip)) {
      return false;
    }

    String lhsSymbol = getSymbol();

    String rhsSymbol = that.getSymbol();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "symbol", lhsSymbol), LocatorUtils.property(thatLocator, "symbol", rhsSymbol), lhsSymbol, rhsSymbol)) {
      return false;
    }

    String lhsUnitAction = getUnitAction();

    String rhsUnitAction = that.getUnitAction();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "unitAction", lhsUnitAction), LocatorUtils.property(thatLocator, "unitAction", rhsUnitAction), lhsUnitAction, rhsUnitAction)) {
      return false;
    }

    String lhsOptionsSecurity = getOptionsSecurity();

    String rhsOptionsSecurity = that.getOptionsSecurity();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "optionsSecurity", lhsOptionsSecurity), LocatorUtils.property(thatLocator, "optionsSecurity", rhsOptionsSecurity), lhsOptionsSecurity, rhsOptionsSecurity)) {
      return false;
    }

    Calendar lhsTradeDate = getTradeDate();

    Calendar rhsTradeDate = that.getTradeDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "tradeDate", lhsTradeDate), LocatorUtils.property(thatLocator, "tradeDate", rhsTradeDate), lhsTradeDate, rhsTradeDate)) {
      return false;
    }

    Calendar lhsSettleDate = getSettleDate();

    Calendar rhsSettleDate = that.getSettleDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "settleDate", lhsSettleDate), LocatorUtils.property(thatLocator, "settleDate", rhsSettleDate), lhsSettleDate, rhsSettleDate)) {
      return false;
    }

    BigDecimal lhsAccruedInterestAmount = getAccruedInterestAmount();

    BigDecimal rhsAccruedInterestAmount = that.getAccruedInterestAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "accruedInterestAmount", lhsAccruedInterestAmount), LocatorUtils.property(thatLocator, "accruedInterestAmount", rhsAccruedInterestAmount), lhsAccruedInterestAmount, rhsAccruedInterestAmount)) {
      return false;
    }

    BigDecimal lhsAverageCostBasisAmount = getAverageCostBasisAmount();

    BigDecimal rhsAverageCostBasisAmount = that.getAverageCostBasisAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "averageCostBasisAmount", lhsAverageCostBasisAmount), LocatorUtils.property(thatLocator, "averageCostBasisAmount", rhsAverageCostBasisAmount), lhsAverageCostBasisAmount, rhsAverageCostBasisAmount)) {
      return false;
    }

    BigDecimal lhsCommissionAmount = getCommissionAmount();

    BigDecimal rhsCommissionAmount = that.getCommissionAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "commissionAmount", lhsCommissionAmount), LocatorUtils.property(thatLocator, "commissionAmount", rhsCommissionAmount), lhsCommissionAmount, rhsCommissionAmount)) {
      return false;
    }

    BigDecimal lhsDenominator = getDenominator();

    BigDecimal rhsDenominator = that.getDenominator();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "denominator", lhsDenominator), LocatorUtils.property(thatLocator, "denominator", rhsDenominator), lhsDenominator, rhsDenominator)) {
      return false;
    }

    Calendar lhsPayrollDate = getPayrollDate();

    Calendar rhsPayrollDate = that.getPayrollDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "payrollDate", lhsPayrollDate), LocatorUtils.property(thatLocator, "payrollDate", rhsPayrollDate), lhsPayrollDate, rhsPayrollDate)) {
      return false;
    }

    Calendar lhsPurchaseDate = getPurchaseDate();

    Calendar rhsPurchaseDate = that.getPurchaseDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "purchaseDate", lhsPurchaseDate), LocatorUtils.property(thatLocator, "purchaseDate", rhsPurchaseDate), lhsPurchaseDate, rhsPurchaseDate)) {
      return false;
    }

    BigDecimal lhsGainAmount = getGainAmount();

    BigDecimal rhsGainAmount = that.getGainAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "gainAmount", lhsGainAmount), LocatorUtils.property(thatLocator, "gainAmount", rhsGainAmount), lhsGainAmount, rhsGainAmount)) {
      return false;
    }

    BigDecimal lhsFeesAmount = getFeesAmount();

    BigDecimal rhsFeesAmount = that.getFeesAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "feesAmount", lhsFeesAmount), LocatorUtils.property(thatLocator, "feesAmount", rhsFeesAmount), lhsFeesAmount, rhsFeesAmount)) {
      return false;
    }

    BigDecimal lhsFractionalUnitsCashAmount = getFractionalUnitsCashAmount();

    BigDecimal rhsFractionalUnitsCashAmount = that.getFractionalUnitsCashAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "fractionalUnitsCashAmount", lhsFractionalUnitsCashAmount), LocatorUtils.property(thatLocator, "fractionalUnitsCashAmount", rhsFractionalUnitsCashAmount), lhsFractionalUnitsCashAmount, rhsFractionalUnitsCashAmount)) {
      return false;
    }

    BigDecimal lhsLoadAmount = getLoadAmount();

    BigDecimal rhsLoadAmount = that.getLoadAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "loadAmount", lhsLoadAmount), LocatorUtils.property(thatLocator, "loadAmount", rhsLoadAmount), lhsLoadAmount, rhsLoadAmount)) {
      return false;
    }

    BigDecimal lhsLoanInterestAmount = getLoanInterestAmount();

    BigDecimal rhsLoanInterestAmount = that.getLoanInterestAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "loanInterestAmount", lhsLoanInterestAmount), LocatorUtils.property(thatLocator, "loanInterestAmount", rhsLoanInterestAmount), lhsLoanInterestAmount, rhsLoanInterestAmount)) {
      return false;
    }

    BigDecimal lhsLoanPrincipalAmount = getLoanPrincipalAmount();

    BigDecimal rhsLoanPrincipalAmount = that.getLoanPrincipalAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "loanPrincipalAmount", lhsLoanPrincipalAmount), LocatorUtils.property(thatLocator, "loanPrincipalAmount", rhsLoanPrincipalAmount), lhsLoanPrincipalAmount, rhsLoanPrincipalAmount)) {
      return false;
    }

    BigDecimal lhsMarkdownAmount = getMarkdownAmount();

    BigDecimal rhsMarkdownAmount = that.getMarkdownAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "markdownAmount", lhsMarkdownAmount), LocatorUtils.property(thatLocator, "markdownAmount", rhsMarkdownAmount), lhsMarkdownAmount, rhsMarkdownAmount)) {
      return false;
    }

    BigDecimal lhsMarkupAmount = getMarkupAmount();

    BigDecimal rhsMarkupAmount = that.getMarkupAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "markupAmount", lhsMarkupAmount), LocatorUtils.property(thatLocator, "markupAmount", rhsMarkupAmount), lhsMarkupAmount, rhsMarkupAmount)) {
      return false;
    }

    BigDecimal lhsNewUnits = getNewUnits();

    BigDecimal rhsNewUnits = that.getNewUnits();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "newUnits", lhsNewUnits), LocatorUtils.property(thatLocator, "newUnits", rhsNewUnits), lhsNewUnits, rhsNewUnits)) {
      return false;
    }

    BigDecimal lhsNumerator = getNumerator();

    BigDecimal rhsNumerator = that.getNumerator();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "numerator", lhsNumerator), LocatorUtils.property(thatLocator, "numerator", rhsNumerator), lhsNumerator, rhsNumerator)) {
      return false;
    }

    BigDecimal lhsOldUnits = getOldUnits();

    BigDecimal rhsOldUnits = that.getOldUnits();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "oldUnits", lhsOldUnits), LocatorUtils.property(thatLocator, "oldUnits", rhsOldUnits), lhsOldUnits, rhsOldUnits)) {
      return false;
    }

    BigDecimal lhsPenaltyAmount = getPenaltyAmount();

    BigDecimal rhsPenaltyAmount = that.getPenaltyAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "penaltyAmount", lhsPenaltyAmount), LocatorUtils.property(thatLocator, "penaltyAmount", rhsPenaltyAmount), lhsPenaltyAmount, rhsPenaltyAmount)) {
      return false;
    }

    Boolean lhsPriorYearContribution = isPriorYearContribution();

    Boolean rhsPriorYearContribution = that.isPriorYearContribution();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "priorYearContribution", lhsPriorYearContribution), LocatorUtils.property(thatLocator, "priorYearContribution", rhsPriorYearContribution), lhsPriorYearContribution, rhsPriorYearContribution)) {
      return false;
    }

    Short lhsSharesPerContract = getSharesPerContract();

    Short rhsSharesPerContract = that.getSharesPerContract();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "sharesPerContract", lhsSharesPerContract), LocatorUtils.property(thatLocator, "sharesPerContract", rhsSharesPerContract), lhsSharesPerContract, rhsSharesPerContract)) {
      return false;
    }

    BigDecimal lhsStateWithholding = getStateWithholding();

    BigDecimal rhsStateWithholding = that.getStateWithholding();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "stateWithholding", lhsStateWithholding), LocatorUtils.property(thatLocator, "stateWithholding", rhsStateWithholding), lhsStateWithholding, rhsStateWithholding)) {
      return false;
    }

    BigDecimal lhsTotalAmount = getTotalAmount();

    BigDecimal rhsTotalAmount = that.getTotalAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "totalAmount", lhsTotalAmount), LocatorUtils.property(thatLocator, "totalAmount", rhsTotalAmount), lhsTotalAmount, rhsTotalAmount)) {
      return false;
    }

    BigDecimal lhsTaxesAmount = getTaxesAmount();

    BigDecimal rhsTaxesAmount = that.getTaxesAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "taxesAmount", lhsTaxesAmount), LocatorUtils.property(thatLocator, "taxesAmount", rhsTaxesAmount), lhsTaxesAmount, rhsTaxesAmount)) {
      return false;
    }

    Boolean lhsTaxExempt = isTaxExempt();

    Boolean rhsTaxExempt = that.isTaxExempt();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "taxExempt", lhsTaxExempt), LocatorUtils.property(thatLocator, "taxExempt", rhsTaxExempt), lhsTaxExempt, rhsTaxExempt)) {
      return false;
    }

    BigDecimal lhsUnitPrice = getUnitPrice();

    BigDecimal rhsUnitPrice = that.getUnitPrice();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "unitPrice", lhsUnitPrice), LocatorUtils.property(thatLocator, "unitPrice", rhsUnitPrice), lhsUnitPrice, rhsUnitPrice)) {
      return false;
    }

    BigDecimal lhsUnits = getUnits();

    BigDecimal rhsUnits = that.getUnits();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "units", lhsUnits), LocatorUtils.property(thatLocator, "units", rhsUnits), lhsUnits, rhsUnits)) {
      return false;
    }

    BigDecimal lhsWithholdingAmount = getWithholdingAmount();

    BigDecimal rhsWithholdingAmount = that.getWithholdingAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "withholdingAmount", lhsWithholdingAmount), LocatorUtils.property(thatLocator, "withholdingAmount", rhsWithholdingAmount), lhsWithholdingAmount, rhsWithholdingAmount)) {
      return false;
    }

    Long lhsOptionsSharesPerContract = getOptionsSharesPerContract();

    Long rhsOptionsSharesPerContract = that.getOptionsSharesPerContract();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "optionsSharesPerContract", lhsOptionsSharesPerContract), LocatorUtils.property(thatLocator, "optionsSharesPerContract", rhsOptionsSharesPerContract), lhsOptionsSharesPerContract, rhsOptionsSharesPerContract)) {
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
    int currentHashCode = super.hashCode(locator, strategy);

    String theReversalInstitutionTransactionId = getReversalInstitutionTransactionId();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "reversalInstitutionTransactionId", theReversalInstitutionTransactionId), currentHashCode, theReversalInstitutionTransactionId);

    String theDescription = getDescription();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription);

    BuyType theBuyType = getBuyType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "buyType", theBuyType), currentHashCode, theBuyType);

    IncomeType theIncomeType = getIncomeType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "incomeType", theIncomeType), currentHashCode, theIncomeType);

    String theInv401KSource = getInv401KSource();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "inv401KSource", theInv401KSource), currentHashCode, theInv401KSource);

    String theLoanId = getLoanId();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "loanId", theLoanId), currentHashCode, theLoanId);

    OptionsActionType theOptionsActionType = getOptionsActionType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "optionsActionType", theOptionsActionType), currentHashCode, theOptionsActionType);

    OptionsBuyType theOptionsBuyType = getOptionsBuyType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "optionsBuyType", theOptionsBuyType), currentHashCode, theOptionsBuyType);

    OptionsSellType theOptionsSellType = getOptionsSellType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "optionsSellType", theOptionsSellType), currentHashCode, theOptionsSellType);

    PositionType thePositionType = getPositionType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "positionType", thePositionType), currentHashCode, thePositionType);

    String theRelatedInstitutionTradeId = getRelatedInstitutionTradeId();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "relatedInstitutionTradeId", theRelatedInstitutionTradeId), currentHashCode, theRelatedInstitutionTradeId);

    RelatedOptionTransType theRelatedOptionTransType = getRelatedOptionTransType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "relatedOptionTransType", theRelatedOptionTransType), currentHashCode, theRelatedOptionTransType);

    SecuredType theSecuredType = getSecuredType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "securedType", theSecuredType), currentHashCode, theSecuredType);

    SellReason theSellReason = getSellReason();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sellReason", theSellReason), currentHashCode, theSellReason);

    SellType theSellType = getSellType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sellType", theSellType), currentHashCode, theSellType);

    InvestmentSubAccountType theSubaccountFromType = getSubaccountFromType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "subaccountFromType", theSubaccountFromType), currentHashCode, theSubaccountFromType);

    InvestmentSubAccountType theSubaccountFundType = getSubaccountFundType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "subaccountFundType", theSubaccountFundType), currentHashCode, theSubaccountFundType);

    InvestmentSubAccountType theSubaccountSecurityType = getSubaccountSecurityType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "subaccountSecurityType", theSubaccountSecurityType), currentHashCode, theSubaccountSecurityType);

    InvestmentSubAccountType theSubaccountToType = getSubaccountToType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "subaccountToType", theSubaccountToType), currentHashCode, theSubaccountToType);

    TransferAction theTransferAction = getTransferAction();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "transferAction", theTransferAction), currentHashCode, theTransferAction);

    String theUnitType = getUnitType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "unitType", theUnitType), currentHashCode, theUnitType);

    String theCusip = getCusip();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "cusip", theCusip), currentHashCode, theCusip);

    String theSymbol = getSymbol();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "symbol", theSymbol), currentHashCode, theSymbol);

    String theUnitAction = getUnitAction();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "unitAction", theUnitAction), currentHashCode, theUnitAction);

    String theOptionsSecurity = getOptionsSecurity();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "optionsSecurity", theOptionsSecurity), currentHashCode, theOptionsSecurity);

    Calendar theTradeDate = getTradeDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "tradeDate", theTradeDate), currentHashCode, theTradeDate);

    Calendar theSettleDate = getSettleDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "settleDate", theSettleDate), currentHashCode, theSettleDate);

    BigDecimal theAccruedInterestAmount = getAccruedInterestAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "accruedInterestAmount", theAccruedInterestAmount), currentHashCode, theAccruedInterestAmount);

    BigDecimal theAverageCostBasisAmount = getAverageCostBasisAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "averageCostBasisAmount", theAverageCostBasisAmount), currentHashCode, theAverageCostBasisAmount);

    BigDecimal theCommissionAmount = getCommissionAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "commissionAmount", theCommissionAmount), currentHashCode, theCommissionAmount);

    BigDecimal theDenominator = getDenominator();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "denominator", theDenominator), currentHashCode, theDenominator);

    Calendar thePayrollDate = getPayrollDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "payrollDate", thePayrollDate), currentHashCode, thePayrollDate);

    Calendar thePurchaseDate = getPurchaseDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "purchaseDate", thePurchaseDate), currentHashCode, thePurchaseDate);

    BigDecimal theGainAmount = getGainAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "gainAmount", theGainAmount), currentHashCode, theGainAmount);

    BigDecimal theFeesAmount = getFeesAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "feesAmount", theFeesAmount), currentHashCode, theFeesAmount);

    BigDecimal theFractionalUnitsCashAmount = getFractionalUnitsCashAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "fractionalUnitsCashAmount", theFractionalUnitsCashAmount), currentHashCode, theFractionalUnitsCashAmount);

    BigDecimal theLoadAmount = getLoadAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "loadAmount", theLoadAmount), currentHashCode, theLoadAmount);

    BigDecimal theLoanInterestAmount = getLoanInterestAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "loanInterestAmount", theLoanInterestAmount), currentHashCode, theLoanInterestAmount);

    BigDecimal theLoanPrincipalAmount = getLoanPrincipalAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "loanPrincipalAmount", theLoanPrincipalAmount), currentHashCode, theLoanPrincipalAmount);

    BigDecimal theMarkdownAmount = getMarkdownAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "markdownAmount", theMarkdownAmount), currentHashCode, theMarkdownAmount);

    BigDecimal theMarkupAmount = getMarkupAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "markupAmount", theMarkupAmount), currentHashCode, theMarkupAmount);

    BigDecimal theNewUnits = getNewUnits();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "newUnits", theNewUnits), currentHashCode, theNewUnits);

    BigDecimal theNumerator = getNumerator();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "numerator", theNumerator), currentHashCode, theNumerator);

    BigDecimal theOldUnits = getOldUnits();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "oldUnits", theOldUnits), currentHashCode, theOldUnits);

    BigDecimal thePenaltyAmount = getPenaltyAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "penaltyAmount", thePenaltyAmount), currentHashCode, thePenaltyAmount);

    Boolean thePriorYearContribution = isPriorYearContribution();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "priorYearContribution", thePriorYearContribution), currentHashCode, thePriorYearContribution);

    Short theSharesPerContract = getSharesPerContract();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sharesPerContract", theSharesPerContract), currentHashCode, theSharesPerContract);

    BigDecimal theStateWithholding = getStateWithholding();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "stateWithholding", theStateWithholding), currentHashCode, theStateWithholding);

    BigDecimal theTotalAmount = getTotalAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "totalAmount", theTotalAmount), currentHashCode, theTotalAmount);

    BigDecimal theTaxesAmount = getTaxesAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "taxesAmount", theTaxesAmount), currentHashCode, theTaxesAmount);

    Boolean theTaxExempt = isTaxExempt();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "taxExempt", theTaxExempt), currentHashCode, theTaxExempt);

    BigDecimal theUnitPrice = getUnitPrice();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "unitPrice", theUnitPrice), currentHashCode, theUnitPrice);

    BigDecimal theUnits = getUnits();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "units", theUnits), currentHashCode, theUnits);

    BigDecimal theWithholdingAmount = getWithholdingAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "withholdingAmount", theWithholdingAmount), currentHashCode, theWithholdingAmount);

    Long theOptionsSharesPerContract = getOptionsSharesPerContract();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "optionsSharesPerContract", theOptionsSharesPerContract), currentHashCode, theOptionsSharesPerContract);

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