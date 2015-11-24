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
@XmlType(name="", propOrder={"loanDescription", "loanType", "postedDate", "term", "holderName", "lateFeeAmount", "payoffAmount", "payoffAmountDate", "referenceNumber", "originalMaturityDate", "taxPayeeName", "principalBalance", "escrowBalance", "interestRate", "interestPeriod", "initialAmount", "initialDate", "nextPaymentPrincipalAmount", "nextPaymentInterestAmount", "nextPayment", "nextPaymentDate", "lastPaymentDueDate", "lastPaymentReceiveDate", "lastPaymentAmount", "lastPaymentPrincipalAmount", "lastPaymentInterestAmount", "lastPaymentEscrowAmount", "lastPaymentLastFeeAmount", "lastPaymentLateCharge", "principalPaidYtd", "interestPaidYtd", "insurancePaidYtd", "taxPaidYtd", "autopayEnrolled", "collateral", "currentSchool", "firstPaymentDate", "guarantor", "firstMortgage", "loanPaymentFreq", "paymentMinAmount", "originalSchool", "recurringPaymentAmount", "lender", "endingBalanceAmount", "availableBalanceAmount", "loanTermType", "noOfPayments", "balloonAmount", "projectedInterest", "interestPaidLtd", "interestRateType", "loanPaymentType", "remainingPayments"})
@XmlRootElement(name="LoanAccount", namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
public class LoanAccount
  extends Account
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected String loanDescription;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected LoanType loanType;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1", type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar postedDate;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected String term;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected String holderName;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal lateFeeAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal payoffAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1", type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar payoffAmountDate;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected String referenceNumber;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1", type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar originalMaturityDate;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected String taxPayeeName;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal principalBalance;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal escrowBalance;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal interestRate;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected String interestPeriod;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal initialAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1", type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar initialDate;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal nextPaymentPrincipalAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal nextPaymentInterestAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal nextPayment;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1", type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar nextPaymentDate;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1", type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar lastPaymentDueDate;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1", type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar lastPaymentReceiveDate;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal lastPaymentAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal lastPaymentPrincipalAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal lastPaymentInterestAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal lastPaymentEscrowAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal lastPaymentLastFeeAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal lastPaymentLateCharge;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal principalPaidYtd;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal interestPaidYtd;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal insurancePaidYtd;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal taxPaidYtd;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected Boolean autopayEnrolled;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected String collateral;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected String currentSchool;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1", type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar firstPaymentDate;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected String guarantor;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected Boolean firstMortgage;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected String loanPaymentFreq;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal paymentMinAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected String originalSchool;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal recurringPaymentAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected String lender;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal endingBalanceAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal availableBalanceAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected LoanTermType loanTermType;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected Integer noOfPayments;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal balloonAmount;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal projectedInterest;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected BigDecimal interestPaidLtd;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected RateType interestRateType;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected PaymentType loanPaymentType;
  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1")
  protected Integer remainingPayments;
  
  public String getLoanDescription()
  {
    return this.loanDescription;
  }
  
  public void setLoanDescription(String value)
  {
    this.loanDescription = value;
  }
  
  public LoanType getLoanType()
  {
    return this.loanType;
  }
  
  public void setLoanType(LoanType value)
  {
    this.loanType = value;
  }
  
  public Calendar getPostedDate()
  {
    return this.postedDate;
  }
  
  public void setPostedDate(Calendar value)
  {
    this.postedDate = value;
  }
  
  public String getTerm()
  {
    return this.term;
  }
  
  public void setTerm(String value)
  {
    this.term = value;
  }
  
  public String getHolderName()
  {
    return this.holderName;
  }
  
  public void setHolderName(String value)
  {
    this.holderName = value;
  }
  
  public BigDecimal getLateFeeAmount()
  {
    return this.lateFeeAmount;
  }
  
  public void setLateFeeAmount(BigDecimal value)
  {
    this.lateFeeAmount = value;
  }
  
  public BigDecimal getPayoffAmount()
  {
    return this.payoffAmount;
  }
  
  public void setPayoffAmount(BigDecimal value)
  {
    this.payoffAmount = value;
  }
  
  public Calendar getPayoffAmountDate()
  {
    return this.payoffAmountDate;
  }
  
  public void setPayoffAmountDate(Calendar value)
  {
    this.payoffAmountDate = value;
  }
  
  public String getReferenceNumber()
  {
    return this.referenceNumber;
  }
  
  public void setReferenceNumber(String value)
  {
    this.referenceNumber = value;
  }
  
  public Calendar getOriginalMaturityDate()
  {
    return this.originalMaturityDate;
  }
  
  public void setOriginalMaturityDate(Calendar value)
  {
    this.originalMaturityDate = value;
  }
  
  public String getTaxPayeeName()
  {
    return this.taxPayeeName;
  }
  
  public void setTaxPayeeName(String value)
  {
    this.taxPayeeName = value;
  }
  
  public BigDecimal getPrincipalBalance()
  {
    return this.principalBalance;
  }
  
  public void setPrincipalBalance(BigDecimal value)
  {
    this.principalBalance = value;
  }
  
  public BigDecimal getEscrowBalance()
  {
    return this.escrowBalance;
  }
  
  public void setEscrowBalance(BigDecimal value)
  {
    this.escrowBalance = value;
  }
  
  public BigDecimal getInterestRate()
  {
    return this.interestRate;
  }
  
  public void setInterestRate(BigDecimal value)
  {
    this.interestRate = value;
  }
  
  public String getInterestPeriod()
  {
    return this.interestPeriod;
  }
  
  public void setInterestPeriod(String value)
  {
    this.interestPeriod = value;
  }
  
  public BigDecimal getInitialAmount()
  {
    return this.initialAmount;
  }
  
  public void setInitialAmount(BigDecimal value)
  {
    this.initialAmount = value;
  }
  
  public Calendar getInitialDate()
  {
    return this.initialDate;
  }
  
  public void setInitialDate(Calendar value)
  {
    this.initialDate = value;
  }
  
  public BigDecimal getNextPaymentPrincipalAmount()
  {
    return this.nextPaymentPrincipalAmount;
  }
  
  public void setNextPaymentPrincipalAmount(BigDecimal value)
  {
    this.nextPaymentPrincipalAmount = value;
  }
  
  public BigDecimal getNextPaymentInterestAmount()
  {
    return this.nextPaymentInterestAmount;
  }
  
  public void setNextPaymentInterestAmount(BigDecimal value)
  {
    this.nextPaymentInterestAmount = value;
  }
  
  public BigDecimal getNextPayment()
  {
    return this.nextPayment;
  }
  
  public void setNextPayment(BigDecimal value)
  {
    this.nextPayment = value;
  }
  
  public Calendar getNextPaymentDate()
  {
    return this.nextPaymentDate;
  }
  
  public void setNextPaymentDate(Calendar value)
  {
    this.nextPaymentDate = value;
  }
  
  public Calendar getLastPaymentDueDate()
  {
    return this.lastPaymentDueDate;
  }
  
  public void setLastPaymentDueDate(Calendar value)
  {
    this.lastPaymentDueDate = value;
  }
  
  public Calendar getLastPaymentReceiveDate()
  {
    return this.lastPaymentReceiveDate;
  }
  
  public void setLastPaymentReceiveDate(Calendar value)
  {
    this.lastPaymentReceiveDate = value;
  }
  
  public BigDecimal getLastPaymentAmount()
  {
    return this.lastPaymentAmount;
  }
  
  public void setLastPaymentAmount(BigDecimal value)
  {
    this.lastPaymentAmount = value;
  }
  
  public BigDecimal getLastPaymentPrincipalAmount()
  {
    return this.lastPaymentPrincipalAmount;
  }
  
  public void setLastPaymentPrincipalAmount(BigDecimal value)
  {
    this.lastPaymentPrincipalAmount = value;
  }
  
  public BigDecimal getLastPaymentInterestAmount()
  {
    return this.lastPaymentInterestAmount;
  }
  
  public void setLastPaymentInterestAmount(BigDecimal value)
  {
    this.lastPaymentInterestAmount = value;
  }
  
  public BigDecimal getLastPaymentEscrowAmount()
  {
    return this.lastPaymentEscrowAmount;
  }
  
  public void setLastPaymentEscrowAmount(BigDecimal value)
  {
    this.lastPaymentEscrowAmount = value;
  }
  
  public BigDecimal getLastPaymentLastFeeAmount()
  {
    return this.lastPaymentLastFeeAmount;
  }
  
  public void setLastPaymentLastFeeAmount(BigDecimal value)
  {
    this.lastPaymentLastFeeAmount = value;
  }
  
  public BigDecimal getLastPaymentLateCharge()
  {
    return this.lastPaymentLateCharge;
  }
  
  public void setLastPaymentLateCharge(BigDecimal value)
  {
    this.lastPaymentLateCharge = value;
  }
  
  public BigDecimal getPrincipalPaidYtd()
  {
    return this.principalPaidYtd;
  }
  
  public void setPrincipalPaidYtd(BigDecimal value)
  {
    this.principalPaidYtd = value;
  }
  
  public BigDecimal getInterestPaidYtd()
  {
    return this.interestPaidYtd;
  }
  
  public void setInterestPaidYtd(BigDecimal value)
  {
    this.interestPaidYtd = value;
  }
  
  public BigDecimal getInsurancePaidYtd()
  {
    return this.insurancePaidYtd;
  }
  
  public void setInsurancePaidYtd(BigDecimal value)
  {
    this.insurancePaidYtd = value;
  }
  
  public BigDecimal getTaxPaidYtd()
  {
    return this.taxPaidYtd;
  }
  
  public void setTaxPaidYtd(BigDecimal value)
  {
    this.taxPaidYtd = value;
  }
  
  public Boolean isAutopayEnrolled()
  {
    return this.autopayEnrolled;
  }
  
  public void setAutopayEnrolled(Boolean value)
  {
    this.autopayEnrolled = value;
  }
  
  public String getCollateral()
  {
    return this.collateral;
  }
  
  public void setCollateral(String value)
  {
    this.collateral = value;
  }
  
  public String getCurrentSchool()
  {
    return this.currentSchool;
  }
  
  public void setCurrentSchool(String value)
  {
    this.currentSchool = value;
  }
  
  public Calendar getFirstPaymentDate()
  {
    return this.firstPaymentDate;
  }
  
  public void setFirstPaymentDate(Calendar value)
  {
    this.firstPaymentDate = value;
  }
  
  public String getGuarantor()
  {
    return this.guarantor;
  }
  
  public void setGuarantor(String value)
  {
    this.guarantor = value;
  }
  
  public Boolean isFirstMortgage()
  {
    return this.firstMortgage;
  }
  
  public void setFirstMortgage(Boolean value)
  {
    this.firstMortgage = value;
  }
  
  public String getLoanPaymentFreq()
  {
    return this.loanPaymentFreq;
  }
  
  public void setLoanPaymentFreq(String value)
  {
    this.loanPaymentFreq = value;
  }
  
  public BigDecimal getPaymentMinAmount()
  {
    return this.paymentMinAmount;
  }
  
  public void setPaymentMinAmount(BigDecimal value)
  {
    this.paymentMinAmount = value;
  }
  
  public String getOriginalSchool()
  {
    return this.originalSchool;
  }
  
  public void setOriginalSchool(String value)
  {
    this.originalSchool = value;
  }
  
  public BigDecimal getRecurringPaymentAmount()
  {
    return this.recurringPaymentAmount;
  }
  
  public void setRecurringPaymentAmount(BigDecimal value)
  {
    this.recurringPaymentAmount = value;
  }
  
  public String getLender()
  {
    return this.lender;
  }
  
  public void setLender(String value)
  {
    this.lender = value;
  }
  
  public BigDecimal getEndingBalanceAmount()
  {
    return this.endingBalanceAmount;
  }
  
  public void setEndingBalanceAmount(BigDecimal value)
  {
    this.endingBalanceAmount = value;
  }
  
  public BigDecimal getAvailableBalanceAmount()
  {
    return this.availableBalanceAmount;
  }
  
  public void setAvailableBalanceAmount(BigDecimal value)
  {
    this.availableBalanceAmount = value;
  }
  
  public LoanTermType getLoanTermType()
  {
    return this.loanTermType;
  }
  
  public void setLoanTermType(LoanTermType value)
  {
    this.loanTermType = value;
  }
  
  public Integer getNoOfPayments()
  {
    return this.noOfPayments;
  }
  
  public void setNoOfPayments(Integer value)
  {
    this.noOfPayments = value;
  }
  
  public BigDecimal getBalloonAmount()
  {
    return this.balloonAmount;
  }
  
  public void setBalloonAmount(BigDecimal value)
  {
    this.balloonAmount = value;
  }
  
  public BigDecimal getProjectedInterest()
  {
    return this.projectedInterest;
  }
  
  public void setProjectedInterest(BigDecimal value)
  {
    this.projectedInterest = value;
  }
  
  public BigDecimal getInterestPaidLtd()
  {
    return this.interestPaidLtd;
  }
  
  public void setInterestPaidLtd(BigDecimal value)
  {
    this.interestPaidLtd = value;
  }
  
  public RateType getInterestRateType()
  {
    return this.interestRateType;
  }
  
  public void setInterestRateType(RateType value)
  {
    this.interestRateType = value;
  }
  
  public PaymentType getLoanPaymentType()
  {
    return this.loanPaymentType;
  }
  
  public void setLoanPaymentType(PaymentType value)
  {
    this.loanPaymentType = value;
  }
  
  public Integer getRemainingPayments()
  {
    return this.remainingPayments;
  }
  
  public void setRemainingPayments(Integer value)
  {
    this.remainingPayments = value;
  }
  
  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy)
  {
    if (!(object instanceof LoanAccount)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    if (!super.equals(thisLocator, thatLocator, object, strategy)) {
      return false;
    }
    LoanAccount that = (LoanAccount)object;
    
    String lhsLoanDescription = getLoanDescription();
    
    String rhsLoanDescription = that.getLoanDescription();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "loanDescription", lhsLoanDescription), LocatorUtils.property(thatLocator, "loanDescription", rhsLoanDescription), lhsLoanDescription, rhsLoanDescription)) {
      return false;
    }
    LoanType lhsLoanType = getLoanType();
    
    LoanType rhsLoanType = that.getLoanType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "loanType", lhsLoanType), LocatorUtils.property(thatLocator, "loanType", rhsLoanType), lhsLoanType, rhsLoanType)) {
      return false;
    }
    Calendar lhsPostedDate = getPostedDate();
    
    Calendar rhsPostedDate = that.getPostedDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "postedDate", lhsPostedDate), LocatorUtils.property(thatLocator, "postedDate", rhsPostedDate), lhsPostedDate, rhsPostedDate)) {
      return false;
    }
    String lhsTerm = getTerm();
    
    String rhsTerm = that.getTerm();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "term", lhsTerm), LocatorUtils.property(thatLocator, "term", rhsTerm), lhsTerm, rhsTerm)) {
      return false;
    }
    String lhsHolderName = getHolderName();
    
    String rhsHolderName = that.getHolderName();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "holderName", lhsHolderName), LocatorUtils.property(thatLocator, "holderName", rhsHolderName), lhsHolderName, rhsHolderName)) {
      return false;
    }
    BigDecimal lhsLateFeeAmount = getLateFeeAmount();
    
    BigDecimal rhsLateFeeAmount = that.getLateFeeAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "lateFeeAmount", lhsLateFeeAmount), LocatorUtils.property(thatLocator, "lateFeeAmount", rhsLateFeeAmount), lhsLateFeeAmount, rhsLateFeeAmount)) {
      return false;
    }
    BigDecimal lhsPayoffAmount = getPayoffAmount();
    
    BigDecimal rhsPayoffAmount = that.getPayoffAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "payoffAmount", lhsPayoffAmount), LocatorUtils.property(thatLocator, "payoffAmount", rhsPayoffAmount), lhsPayoffAmount, rhsPayoffAmount)) {
      return false;
    }
    Calendar lhsPayoffAmountDate = getPayoffAmountDate();
    
    Calendar rhsPayoffAmountDate = that.getPayoffAmountDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "payoffAmountDate", lhsPayoffAmountDate), LocatorUtils.property(thatLocator, "payoffAmountDate", rhsPayoffAmountDate), lhsPayoffAmountDate, rhsPayoffAmountDate)) {
      return false;
    }
    String lhsReferenceNumber = getReferenceNumber();
    
    String rhsReferenceNumber = that.getReferenceNumber();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "referenceNumber", lhsReferenceNumber), LocatorUtils.property(thatLocator, "referenceNumber", rhsReferenceNumber), lhsReferenceNumber, rhsReferenceNumber)) {
      return false;
    }
    Calendar lhsOriginalMaturityDate = getOriginalMaturityDate();
    
    Calendar rhsOriginalMaturityDate = that.getOriginalMaturityDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "originalMaturityDate", lhsOriginalMaturityDate), LocatorUtils.property(thatLocator, "originalMaturityDate", rhsOriginalMaturityDate), lhsOriginalMaturityDate, rhsOriginalMaturityDate)) {
      return false;
    }
    String lhsTaxPayeeName = getTaxPayeeName();
    
    String rhsTaxPayeeName = that.getTaxPayeeName();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "taxPayeeName", lhsTaxPayeeName), LocatorUtils.property(thatLocator, "taxPayeeName", rhsTaxPayeeName), lhsTaxPayeeName, rhsTaxPayeeName)) {
      return false;
    }
    BigDecimal lhsPrincipalBalance = getPrincipalBalance();
    
    BigDecimal rhsPrincipalBalance = that.getPrincipalBalance();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "principalBalance", lhsPrincipalBalance), LocatorUtils.property(thatLocator, "principalBalance", rhsPrincipalBalance), lhsPrincipalBalance, rhsPrincipalBalance)) {
      return false;
    }
    BigDecimal lhsEscrowBalance = getEscrowBalance();
    
    BigDecimal rhsEscrowBalance = that.getEscrowBalance();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "escrowBalance", lhsEscrowBalance), LocatorUtils.property(thatLocator, "escrowBalance", rhsEscrowBalance), lhsEscrowBalance, rhsEscrowBalance)) {
      return false;
    }
    BigDecimal lhsInterestRate = getInterestRate();
    
    BigDecimal rhsInterestRate = that.getInterestRate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "interestRate", lhsInterestRate), LocatorUtils.property(thatLocator, "interestRate", rhsInterestRate), lhsInterestRate, rhsInterestRate)) {
      return false;
    }
    String lhsInterestPeriod = getInterestPeriod();
    
    String rhsInterestPeriod = that.getInterestPeriod();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "interestPeriod", lhsInterestPeriod), LocatorUtils.property(thatLocator, "interestPeriod", rhsInterestPeriod), lhsInterestPeriod, rhsInterestPeriod)) {
      return false;
    }
    BigDecimal lhsInitialAmount = getInitialAmount();
    
    BigDecimal rhsInitialAmount = that.getInitialAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "initialAmount", lhsInitialAmount), LocatorUtils.property(thatLocator, "initialAmount", rhsInitialAmount), lhsInitialAmount, rhsInitialAmount)) {
      return false;
    }
    Calendar lhsInitialDate = getInitialDate();
    
    Calendar rhsInitialDate = that.getInitialDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "initialDate", lhsInitialDate), LocatorUtils.property(thatLocator, "initialDate", rhsInitialDate), lhsInitialDate, rhsInitialDate)) {
      return false;
    }
    BigDecimal lhsNextPaymentPrincipalAmount = getNextPaymentPrincipalAmount();
    
    BigDecimal rhsNextPaymentPrincipalAmount = that.getNextPaymentPrincipalAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "nextPaymentPrincipalAmount", lhsNextPaymentPrincipalAmount), LocatorUtils.property(thatLocator, "nextPaymentPrincipalAmount", rhsNextPaymentPrincipalAmount), lhsNextPaymentPrincipalAmount, rhsNextPaymentPrincipalAmount)) {
      return false;
    }
    BigDecimal lhsNextPaymentInterestAmount = getNextPaymentInterestAmount();
    
    BigDecimal rhsNextPaymentInterestAmount = that.getNextPaymentInterestAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "nextPaymentInterestAmount", lhsNextPaymentInterestAmount), LocatorUtils.property(thatLocator, "nextPaymentInterestAmount", rhsNextPaymentInterestAmount), lhsNextPaymentInterestAmount, rhsNextPaymentInterestAmount)) {
      return false;
    }
    BigDecimal lhsNextPayment = getNextPayment();
    
    BigDecimal rhsNextPayment = that.getNextPayment();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "nextPayment", lhsNextPayment), LocatorUtils.property(thatLocator, "nextPayment", rhsNextPayment), lhsNextPayment, rhsNextPayment)) {
      return false;
    }
    Calendar lhsNextPaymentDate = getNextPaymentDate();
    
    Calendar rhsNextPaymentDate = that.getNextPaymentDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "nextPaymentDate", lhsNextPaymentDate), LocatorUtils.property(thatLocator, "nextPaymentDate", rhsNextPaymentDate), lhsNextPaymentDate, rhsNextPaymentDate)) {
      return false;
    }
    Calendar lhsLastPaymentDueDate = getLastPaymentDueDate();
    
    Calendar rhsLastPaymentDueDate = that.getLastPaymentDueDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "lastPaymentDueDate", lhsLastPaymentDueDate), LocatorUtils.property(thatLocator, "lastPaymentDueDate", rhsLastPaymentDueDate), lhsLastPaymentDueDate, rhsLastPaymentDueDate)) {
      return false;
    }
    Calendar lhsLastPaymentReceiveDate = getLastPaymentReceiveDate();
    
    Calendar rhsLastPaymentReceiveDate = that.getLastPaymentReceiveDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "lastPaymentReceiveDate", lhsLastPaymentReceiveDate), LocatorUtils.property(thatLocator, "lastPaymentReceiveDate", rhsLastPaymentReceiveDate), lhsLastPaymentReceiveDate, rhsLastPaymentReceiveDate)) {
      return false;
    }
    BigDecimal lhsLastPaymentAmount = getLastPaymentAmount();
    
    BigDecimal rhsLastPaymentAmount = that.getLastPaymentAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "lastPaymentAmount", lhsLastPaymentAmount), LocatorUtils.property(thatLocator, "lastPaymentAmount", rhsLastPaymentAmount), lhsLastPaymentAmount, rhsLastPaymentAmount)) {
      return false;
    }
    BigDecimal lhsLastPaymentPrincipalAmount = getLastPaymentPrincipalAmount();
    
    BigDecimal rhsLastPaymentPrincipalAmount = that.getLastPaymentPrincipalAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "lastPaymentPrincipalAmount", lhsLastPaymentPrincipalAmount), LocatorUtils.property(thatLocator, "lastPaymentPrincipalAmount", rhsLastPaymentPrincipalAmount), lhsLastPaymentPrincipalAmount, rhsLastPaymentPrincipalAmount)) {
      return false;
    }
    BigDecimal lhsLastPaymentInterestAmount = getLastPaymentInterestAmount();
    
    BigDecimal rhsLastPaymentInterestAmount = that.getLastPaymentInterestAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "lastPaymentInterestAmount", lhsLastPaymentInterestAmount), LocatorUtils.property(thatLocator, "lastPaymentInterestAmount", rhsLastPaymentInterestAmount), lhsLastPaymentInterestAmount, rhsLastPaymentInterestAmount)) {
      return false;
    }
    BigDecimal lhsLastPaymentEscrowAmount = getLastPaymentEscrowAmount();
    
    BigDecimal rhsLastPaymentEscrowAmount = that.getLastPaymentEscrowAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "lastPaymentEscrowAmount", lhsLastPaymentEscrowAmount), LocatorUtils.property(thatLocator, "lastPaymentEscrowAmount", rhsLastPaymentEscrowAmount), lhsLastPaymentEscrowAmount, rhsLastPaymentEscrowAmount)) {
      return false;
    }
    BigDecimal lhsLastPaymentLastFeeAmount = getLastPaymentLastFeeAmount();
    
    BigDecimal rhsLastPaymentLastFeeAmount = that.getLastPaymentLastFeeAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "lastPaymentLastFeeAmount", lhsLastPaymentLastFeeAmount), LocatorUtils.property(thatLocator, "lastPaymentLastFeeAmount", rhsLastPaymentLastFeeAmount), lhsLastPaymentLastFeeAmount, rhsLastPaymentLastFeeAmount)) {
      return false;
    }
    BigDecimal lhsLastPaymentLateCharge = getLastPaymentLateCharge();
    
    BigDecimal rhsLastPaymentLateCharge = that.getLastPaymentLateCharge();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "lastPaymentLateCharge", lhsLastPaymentLateCharge), LocatorUtils.property(thatLocator, "lastPaymentLateCharge", rhsLastPaymentLateCharge), lhsLastPaymentLateCharge, rhsLastPaymentLateCharge)) {
      return false;
    }
    BigDecimal lhsPrincipalPaidYtd = getPrincipalPaidYtd();
    
    BigDecimal rhsPrincipalPaidYtd = that.getPrincipalPaidYtd();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "principalPaidYtd", lhsPrincipalPaidYtd), LocatorUtils.property(thatLocator, "principalPaidYtd", rhsPrincipalPaidYtd), lhsPrincipalPaidYtd, rhsPrincipalPaidYtd)) {
      return false;
    }
    BigDecimal lhsInterestPaidYtd = getInterestPaidYtd();
    
    BigDecimal rhsInterestPaidYtd = that.getInterestPaidYtd();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "interestPaidYtd", lhsInterestPaidYtd), LocatorUtils.property(thatLocator, "interestPaidYtd", rhsInterestPaidYtd), lhsInterestPaidYtd, rhsInterestPaidYtd)) {
      return false;
    }
    BigDecimal lhsInsurancePaidYtd = getInsurancePaidYtd();
    
    BigDecimal rhsInsurancePaidYtd = that.getInsurancePaidYtd();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "insurancePaidYtd", lhsInsurancePaidYtd), LocatorUtils.property(thatLocator, "insurancePaidYtd", rhsInsurancePaidYtd), lhsInsurancePaidYtd, rhsInsurancePaidYtd)) {
      return false;
    }
    BigDecimal lhsTaxPaidYtd = getTaxPaidYtd();
    
    BigDecimal rhsTaxPaidYtd = that.getTaxPaidYtd();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "taxPaidYtd", lhsTaxPaidYtd), LocatorUtils.property(thatLocator, "taxPaidYtd", rhsTaxPaidYtd), lhsTaxPaidYtd, rhsTaxPaidYtd)) {
      return false;
    }
    Boolean lhsAutopayEnrolled = isAutopayEnrolled();
    
    Boolean rhsAutopayEnrolled = that.isAutopayEnrolled();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "autopayEnrolled", lhsAutopayEnrolled), LocatorUtils.property(thatLocator, "autopayEnrolled", rhsAutopayEnrolled), lhsAutopayEnrolled, rhsAutopayEnrolled)) {
      return false;
    }
    String lhsCollateral = getCollateral();
    
    String rhsCollateral = that.getCollateral();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "collateral", lhsCollateral), LocatorUtils.property(thatLocator, "collateral", rhsCollateral), lhsCollateral, rhsCollateral)) {
      return false;
    }
    String lhsCurrentSchool = getCurrentSchool();
    
    String rhsCurrentSchool = that.getCurrentSchool();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "currentSchool", lhsCurrentSchool), LocatorUtils.property(thatLocator, "currentSchool", rhsCurrentSchool), lhsCurrentSchool, rhsCurrentSchool)) {
      return false;
    }
    Calendar lhsFirstPaymentDate = getFirstPaymentDate();
    
    Calendar rhsFirstPaymentDate = that.getFirstPaymentDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "firstPaymentDate", lhsFirstPaymentDate), LocatorUtils.property(thatLocator, "firstPaymentDate", rhsFirstPaymentDate), lhsFirstPaymentDate, rhsFirstPaymentDate)) {
      return false;
    }
    String lhsGuarantor = getGuarantor();
    
    String rhsGuarantor = that.getGuarantor();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "guarantor", lhsGuarantor), LocatorUtils.property(thatLocator, "guarantor", rhsGuarantor), lhsGuarantor, rhsGuarantor)) {
      return false;
    }
    Boolean lhsFirstMortgage = isFirstMortgage();
    
    Boolean rhsFirstMortgage = that.isFirstMortgage();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "firstMortgage", lhsFirstMortgage), LocatorUtils.property(thatLocator, "firstMortgage", rhsFirstMortgage), lhsFirstMortgage, rhsFirstMortgage)) {
      return false;
    }
    String lhsLoanPaymentFreq = getLoanPaymentFreq();
    
    String rhsLoanPaymentFreq = that.getLoanPaymentFreq();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "loanPaymentFreq", lhsLoanPaymentFreq), LocatorUtils.property(thatLocator, "loanPaymentFreq", rhsLoanPaymentFreq), lhsLoanPaymentFreq, rhsLoanPaymentFreq)) {
      return false;
    }
    BigDecimal lhsPaymentMinAmount = getPaymentMinAmount();
    
    BigDecimal rhsPaymentMinAmount = that.getPaymentMinAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentMinAmount", lhsPaymentMinAmount), LocatorUtils.property(thatLocator, "paymentMinAmount", rhsPaymentMinAmount), lhsPaymentMinAmount, rhsPaymentMinAmount)) {
      return false;
    }
    String lhsOriginalSchool = getOriginalSchool();
    
    String rhsOriginalSchool = that.getOriginalSchool();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "originalSchool", lhsOriginalSchool), LocatorUtils.property(thatLocator, "originalSchool", rhsOriginalSchool), lhsOriginalSchool, rhsOriginalSchool)) {
      return false;
    }
    BigDecimal lhsRecurringPaymentAmount = getRecurringPaymentAmount();
    
    BigDecimal rhsRecurringPaymentAmount = that.getRecurringPaymentAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "recurringPaymentAmount", lhsRecurringPaymentAmount), LocatorUtils.property(thatLocator, "recurringPaymentAmount", rhsRecurringPaymentAmount), lhsRecurringPaymentAmount, rhsRecurringPaymentAmount)) {
      return false;
    }
    String lhsLender = getLender();
    
    String rhsLender = that.getLender();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "lender", lhsLender), LocatorUtils.property(thatLocator, "lender", rhsLender), lhsLender, rhsLender)) {
      return false;
    }
    BigDecimal lhsEndingBalanceAmount = getEndingBalanceAmount();
    
    BigDecimal rhsEndingBalanceAmount = that.getEndingBalanceAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "endingBalanceAmount", lhsEndingBalanceAmount), LocatorUtils.property(thatLocator, "endingBalanceAmount", rhsEndingBalanceAmount), lhsEndingBalanceAmount, rhsEndingBalanceAmount)) {
      return false;
    }
    BigDecimal lhsAvailableBalanceAmount = getAvailableBalanceAmount();
    
    BigDecimal rhsAvailableBalanceAmount = that.getAvailableBalanceAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "availableBalanceAmount", lhsAvailableBalanceAmount), LocatorUtils.property(thatLocator, "availableBalanceAmount", rhsAvailableBalanceAmount), lhsAvailableBalanceAmount, rhsAvailableBalanceAmount)) {
      return false;
    }
    LoanTermType lhsLoanTermType = getLoanTermType();
    
    LoanTermType rhsLoanTermType = that.getLoanTermType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "loanTermType", lhsLoanTermType), LocatorUtils.property(thatLocator, "loanTermType", rhsLoanTermType), lhsLoanTermType, rhsLoanTermType)) {
      return false;
    }
    Integer lhsNoOfPayments = getNoOfPayments();
    
    Integer rhsNoOfPayments = that.getNoOfPayments();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "noOfPayments", lhsNoOfPayments), LocatorUtils.property(thatLocator, "noOfPayments", rhsNoOfPayments), lhsNoOfPayments, rhsNoOfPayments)) {
      return false;
    }
    BigDecimal lhsBalloonAmount = getBalloonAmount();
    
    BigDecimal rhsBalloonAmount = that.getBalloonAmount();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "balloonAmount", lhsBalloonAmount), LocatorUtils.property(thatLocator, "balloonAmount", rhsBalloonAmount), lhsBalloonAmount, rhsBalloonAmount)) {
      return false;
    }
    BigDecimal lhsProjectedInterest = getProjectedInterest();
    
    BigDecimal rhsProjectedInterest = that.getProjectedInterest();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "projectedInterest", lhsProjectedInterest), LocatorUtils.property(thatLocator, "projectedInterest", rhsProjectedInterest), lhsProjectedInterest, rhsProjectedInterest)) {
      return false;
    }
    BigDecimal lhsInterestPaidLtd = getInterestPaidLtd();
    
    BigDecimal rhsInterestPaidLtd = that.getInterestPaidLtd();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "interestPaidLtd", lhsInterestPaidLtd), LocatorUtils.property(thatLocator, "interestPaidLtd", rhsInterestPaidLtd), lhsInterestPaidLtd, rhsInterestPaidLtd)) {
      return false;
    }
    RateType lhsInterestRateType = getInterestRateType();
    
    RateType rhsInterestRateType = that.getInterestRateType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "interestRateType", lhsInterestRateType), LocatorUtils.property(thatLocator, "interestRateType", rhsInterestRateType), lhsInterestRateType, rhsInterestRateType)) {
      return false;
    }
    PaymentType lhsLoanPaymentType = getLoanPaymentType();
    
    PaymentType rhsLoanPaymentType = that.getLoanPaymentType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "loanPaymentType", lhsLoanPaymentType), LocatorUtils.property(thatLocator, "loanPaymentType", rhsLoanPaymentType), lhsLoanPaymentType, rhsLoanPaymentType)) {
      return false;
    }
    Integer lhsRemainingPayments = getRemainingPayments();
    
    Integer rhsRemainingPayments = that.getRemainingPayments();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "remainingPayments", lhsRemainingPayments), LocatorUtils.property(thatLocator, "remainingPayments", rhsRemainingPayments), lhsRemainingPayments, rhsRemainingPayments)) {
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
    
    String theLoanDescription = getLoanDescription();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "loanDescription", theLoanDescription), currentHashCode, theLoanDescription);
    
    LoanType theLoanType = getLoanType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "loanType", theLoanType), currentHashCode, theLoanType);
    
    Calendar thePostedDate = getPostedDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "postedDate", thePostedDate), currentHashCode, thePostedDate);
    
    String theTerm = getTerm();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "term", theTerm), currentHashCode, theTerm);
    
    String theHolderName = getHolderName();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "holderName", theHolderName), currentHashCode, theHolderName);
    
    BigDecimal theLateFeeAmount = getLateFeeAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lateFeeAmount", theLateFeeAmount), currentHashCode, theLateFeeAmount);
    
    BigDecimal thePayoffAmount = getPayoffAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "payoffAmount", thePayoffAmount), currentHashCode, thePayoffAmount);
    
    Calendar thePayoffAmountDate = getPayoffAmountDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "payoffAmountDate", thePayoffAmountDate), currentHashCode, thePayoffAmountDate);
    
    String theReferenceNumber = getReferenceNumber();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "referenceNumber", theReferenceNumber), currentHashCode, theReferenceNumber);
    
    Calendar theOriginalMaturityDate = getOriginalMaturityDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "originalMaturityDate", theOriginalMaturityDate), currentHashCode, theOriginalMaturityDate);
    
    String theTaxPayeeName = getTaxPayeeName();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "taxPayeeName", theTaxPayeeName), currentHashCode, theTaxPayeeName);
    
    BigDecimal thePrincipalBalance = getPrincipalBalance();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "principalBalance", thePrincipalBalance), currentHashCode, thePrincipalBalance);
    
    BigDecimal theEscrowBalance = getEscrowBalance();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "escrowBalance", theEscrowBalance), currentHashCode, theEscrowBalance);
    
    BigDecimal theInterestRate = getInterestRate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "interestRate", theInterestRate), currentHashCode, theInterestRate);
    
    String theInterestPeriod = getInterestPeriod();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "interestPeriod", theInterestPeriod), currentHashCode, theInterestPeriod);
    
    BigDecimal theInitialAmount = getInitialAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "initialAmount", theInitialAmount), currentHashCode, theInitialAmount);
    
    Calendar theInitialDate = getInitialDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "initialDate", theInitialDate), currentHashCode, theInitialDate);
    
    BigDecimal theNextPaymentPrincipalAmount = getNextPaymentPrincipalAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "nextPaymentPrincipalAmount", theNextPaymentPrincipalAmount), currentHashCode, theNextPaymentPrincipalAmount);
    
    BigDecimal theNextPaymentInterestAmount = getNextPaymentInterestAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "nextPaymentInterestAmount", theNextPaymentInterestAmount), currentHashCode, theNextPaymentInterestAmount);
    
    BigDecimal theNextPayment = getNextPayment();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "nextPayment", theNextPayment), currentHashCode, theNextPayment);
    
    Calendar theNextPaymentDate = getNextPaymentDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "nextPaymentDate", theNextPaymentDate), currentHashCode, theNextPaymentDate);
    
    Calendar theLastPaymentDueDate = getLastPaymentDueDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lastPaymentDueDate", theLastPaymentDueDate), currentHashCode, theLastPaymentDueDate);
    
    Calendar theLastPaymentReceiveDate = getLastPaymentReceiveDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lastPaymentReceiveDate", theLastPaymentReceiveDate), currentHashCode, theLastPaymentReceiveDate);
    
    BigDecimal theLastPaymentAmount = getLastPaymentAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lastPaymentAmount", theLastPaymentAmount), currentHashCode, theLastPaymentAmount);
    
    BigDecimal theLastPaymentPrincipalAmount = getLastPaymentPrincipalAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lastPaymentPrincipalAmount", theLastPaymentPrincipalAmount), currentHashCode, theLastPaymentPrincipalAmount);
    
    BigDecimal theLastPaymentInterestAmount = getLastPaymentInterestAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lastPaymentInterestAmount", theLastPaymentInterestAmount), currentHashCode, theLastPaymentInterestAmount);
    
    BigDecimal theLastPaymentEscrowAmount = getLastPaymentEscrowAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lastPaymentEscrowAmount", theLastPaymentEscrowAmount), currentHashCode, theLastPaymentEscrowAmount);
    
    BigDecimal theLastPaymentLastFeeAmount = getLastPaymentLastFeeAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lastPaymentLastFeeAmount", theLastPaymentLastFeeAmount), currentHashCode, theLastPaymentLastFeeAmount);
    
    BigDecimal theLastPaymentLateCharge = getLastPaymentLateCharge();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lastPaymentLateCharge", theLastPaymentLateCharge), currentHashCode, theLastPaymentLateCharge);
    
    BigDecimal thePrincipalPaidYtd = getPrincipalPaidYtd();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "principalPaidYtd", thePrincipalPaidYtd), currentHashCode, thePrincipalPaidYtd);
    
    BigDecimal theInterestPaidYtd = getInterestPaidYtd();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "interestPaidYtd", theInterestPaidYtd), currentHashCode, theInterestPaidYtd);
    
    BigDecimal theInsurancePaidYtd = getInsurancePaidYtd();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "insurancePaidYtd", theInsurancePaidYtd), currentHashCode, theInsurancePaidYtd);
    
    BigDecimal theTaxPaidYtd = getTaxPaidYtd();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "taxPaidYtd", theTaxPaidYtd), currentHashCode, theTaxPaidYtd);
    
    Boolean theAutopayEnrolled = isAutopayEnrolled();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "autopayEnrolled", theAutopayEnrolled), currentHashCode, theAutopayEnrolled);
    
    String theCollateral = getCollateral();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "collateral", theCollateral), currentHashCode, theCollateral);
    
    String theCurrentSchool = getCurrentSchool();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "currentSchool", theCurrentSchool), currentHashCode, theCurrentSchool);
    
    Calendar theFirstPaymentDate = getFirstPaymentDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "firstPaymentDate", theFirstPaymentDate), currentHashCode, theFirstPaymentDate);
    
    String theGuarantor = getGuarantor();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "guarantor", theGuarantor), currentHashCode, theGuarantor);
    
    Boolean theFirstMortgage = isFirstMortgage();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "firstMortgage", theFirstMortgage), currentHashCode, theFirstMortgage);
    
    String theLoanPaymentFreq = getLoanPaymentFreq();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "loanPaymentFreq", theLoanPaymentFreq), currentHashCode, theLoanPaymentFreq);
    
    BigDecimal thePaymentMinAmount = getPaymentMinAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "paymentMinAmount", thePaymentMinAmount), currentHashCode, thePaymentMinAmount);
    
    String theOriginalSchool = getOriginalSchool();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "originalSchool", theOriginalSchool), currentHashCode, theOriginalSchool);
    
    BigDecimal theRecurringPaymentAmount = getRecurringPaymentAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "recurringPaymentAmount", theRecurringPaymentAmount), currentHashCode, theRecurringPaymentAmount);
    
    String theLender = getLender();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lender", theLender), currentHashCode, theLender);
    
    BigDecimal theEndingBalanceAmount = getEndingBalanceAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "endingBalanceAmount", theEndingBalanceAmount), currentHashCode, theEndingBalanceAmount);
    
    BigDecimal theAvailableBalanceAmount = getAvailableBalanceAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "availableBalanceAmount", theAvailableBalanceAmount), currentHashCode, theAvailableBalanceAmount);
    
    LoanTermType theLoanTermType = getLoanTermType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "loanTermType", theLoanTermType), currentHashCode, theLoanTermType);
    
    Integer theNoOfPayments = getNoOfPayments();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "noOfPayments", theNoOfPayments), currentHashCode, theNoOfPayments);
    
    BigDecimal theBalloonAmount = getBalloonAmount();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "balloonAmount", theBalloonAmount), currentHashCode, theBalloonAmount);
    
    BigDecimal theProjectedInterest = getProjectedInterest();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "projectedInterest", theProjectedInterest), currentHashCode, theProjectedInterest);
    
    BigDecimal theInterestPaidLtd = getInterestPaidLtd();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "interestPaidLtd", theInterestPaidLtd), currentHashCode, theInterestPaidLtd);
    
    RateType theInterestRateType = getInterestRateType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "interestRateType", theInterestRateType), currentHashCode, theInterestRateType);
    
    PaymentType theLoanPaymentType = getLoanPaymentType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "loanPaymentType", theLoanPaymentType), currentHashCode, theLoanPaymentType);
    
    Integer theRemainingPayments = getRemainingPayments();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "remainingPayments", theRemainingPayments), currentHashCode, theRemainingPayments);
    
    return currentHashCode;
  }
  
  public int hashCode()
  {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}
