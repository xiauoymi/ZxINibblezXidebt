/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.model.cad;

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


/**
 * @author ralam
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "creditAccountType", "detailedDescription",
		"interestRate", "creditAvailableAmount", "creditMaxAmount",
		"cashAdvanceAvailableAmount", "cashAdvanceMaxAmount",
		"cashAdvanceBalance", "cashAdvanceInterestRate", "currentBalance",
		"paymentMinAmount", "paymentDueDate", "previousBalance",
		"statementEndDate", "statementPurchaseAmount",
		"statementFinanceAmount", "pastDueAmount", "lastPaymentAmount",
		"lastPaymentDate", "statementCloseBalance", "statementLateFeeAmount" })
@XmlRootElement(name = "CreditAccount", namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
public class CreditAccount extends Account implements Serializable, Equals,
		HashCode {
	private static final long serialVersionUID = 1L;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected CreditAccountType creditAccountType;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected String detailedDescription;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected BigDecimal interestRate;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected BigDecimal creditAvailableAmount;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected BigDecimal creditMaxAmount;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected BigDecimal cashAdvanceAvailableAmount;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected BigDecimal cashAdvanceMaxAmount;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected BigDecimal cashAdvanceBalance;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected BigDecimal cashAdvanceInterestRate;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected BigDecimal currentBalance;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected BigDecimal paymentMinAmount;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1", type = String.class)
	@XmlJavaTypeAdapter(DateTimeParserAdapter.class)
	@XmlSchemaType(name = "dateTime")
	protected Calendar paymentDueDate;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected BigDecimal previousBalance;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1", type = String.class)
	@XmlJavaTypeAdapter(DateTimeParserAdapter.class)
	@XmlSchemaType(name = "dateTime")
	protected Calendar statementEndDate;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected BigDecimal statementPurchaseAmount;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected BigDecimal statementFinanceAmount;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected BigDecimal pastDueAmount;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected BigDecimal lastPaymentAmount;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1", type = String.class)
	@XmlJavaTypeAdapter(DateTimeParserAdapter.class)
	@XmlSchemaType(name = "dateTime")
	protected Calendar lastPaymentDate;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected BigDecimal statementCloseBalance;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/creditaccount/v1")
	protected BigDecimal statementLateFeeAmount;

	public CreditAccountType getCreditAccountType() {
		return this.creditAccountType;
	}

	public void setCreditAccountType(CreditAccountType value) {
		this.creditAccountType = value;
	}

	public String getDetailedDescription() {
		return this.detailedDescription;
	}

	public void setDetailedDescription(String value) {
		this.detailedDescription = value;
	}

	public BigDecimal getInterestRate() {
		return this.interestRate;
	}

	public void setInterestRate(BigDecimal value) {
		this.interestRate = value;
	}

	public BigDecimal getCreditAvailableAmount() {
		return this.creditAvailableAmount;
	}

	public void setCreditAvailableAmount(BigDecimal value) {
		this.creditAvailableAmount = value;
	}

	public BigDecimal getCreditMaxAmount() {
		return this.creditMaxAmount;
	}

	public void setCreditMaxAmount(BigDecimal value) {
		this.creditMaxAmount = value;
	}

	public BigDecimal getCashAdvanceAvailableAmount() {
		return this.cashAdvanceAvailableAmount;
	}

	public void setCashAdvanceAvailableAmount(BigDecimal value) {
		this.cashAdvanceAvailableAmount = value;
	}

	public BigDecimal getCashAdvanceMaxAmount() {
		return this.cashAdvanceMaxAmount;
	}

	public void setCashAdvanceMaxAmount(BigDecimal value) {
		this.cashAdvanceMaxAmount = value;
	}

	public BigDecimal getCashAdvanceBalance() {
		return this.cashAdvanceBalance;
	}

	public void setCashAdvanceBalance(BigDecimal value) {
		this.cashAdvanceBalance = value;
	}

	public BigDecimal getCashAdvanceInterestRate() {
		return this.cashAdvanceInterestRate;
	}

	public void setCashAdvanceInterestRate(BigDecimal value) {
		this.cashAdvanceInterestRate = value;
	}

	public BigDecimal getCurrentBalance() {
		return this.currentBalance;
	}

	public void setCurrentBalance(BigDecimal value) {
		this.currentBalance = value;
	}

	public BigDecimal getPaymentMinAmount() {
		return this.paymentMinAmount;
	}

	public void setPaymentMinAmount(BigDecimal value) {
		this.paymentMinAmount = value;
	}

	public Calendar getPaymentDueDate() {
		return this.paymentDueDate;
	}

	public void setPaymentDueDate(Calendar value) {
		this.paymentDueDate = value;
	}

	public BigDecimal getPreviousBalance() {
		return this.previousBalance;
	}

	public void setPreviousBalance(BigDecimal value) {
		this.previousBalance = value;
	}

	public Calendar getStatementEndDate() {
		return this.statementEndDate;
	}

	public void setStatementEndDate(Calendar value) {
		this.statementEndDate = value;
	}

	public BigDecimal getStatementPurchaseAmount() {
		return this.statementPurchaseAmount;
	}

	public void setStatementPurchaseAmount(BigDecimal value) {
		this.statementPurchaseAmount = value;
	}

	public BigDecimal getStatementFinanceAmount() {
		return this.statementFinanceAmount;
	}

	public void setStatementFinanceAmount(BigDecimal value) {
		this.statementFinanceAmount = value;
	}

	public BigDecimal getPastDueAmount() {
		return this.pastDueAmount;
	}

	public void setPastDueAmount(BigDecimal value) {
		this.pastDueAmount = value;
	}

	public BigDecimal getLastPaymentAmount() {
		return this.lastPaymentAmount;
	}

	public void setLastPaymentAmount(BigDecimal value) {
		this.lastPaymentAmount = value;
	}

	public Calendar getLastPaymentDate() {
		return this.lastPaymentDate;
	}

	public void setLastPaymentDate(Calendar value) {
		this.lastPaymentDate = value;
	}

	public BigDecimal getStatementCloseBalance() {
		return this.statementCloseBalance;
	}

	public void setStatementCloseBalance(BigDecimal value) {
		this.statementCloseBalance = value;
	}

	public BigDecimal getStatementLateFeeAmount() {
		return this.statementLateFeeAmount;
	}

	public void setStatementLateFeeAmount(BigDecimal value) {
		this.statementLateFeeAmount = value;
	}

	public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator,
			Object object, EqualsStrategy strategy) {
		if (!(object instanceof CreditAccount)) {
			return false;
		}
		if (this == object) {
			return true;
		}
		if (!super.equals(thisLocator, thatLocator, object, strategy)) {
			return false;
		}
		CreditAccount that = (CreditAccount) object;

		CreditAccountType lhsCreditAccountType = getCreditAccountType();

		CreditAccountType rhsCreditAccountType = that.getCreditAccountType();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"creditAccountType", lhsCreditAccountType), LocatorUtils
				.property(thatLocator, "creditAccountType",
						rhsCreditAccountType), lhsCreditAccountType,
				rhsCreditAccountType)) {
			return false;
		}

		String lhsDetailedDescription = getDetailedDescription();

		String rhsDetailedDescription = that.getDetailedDescription();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"detailedDescription", lhsDetailedDescription), LocatorUtils
				.property(thatLocator, "detailedDescription",
						rhsDetailedDescription), lhsDetailedDescription,
				rhsDetailedDescription)) {
			return false;
		}

		BigDecimal lhsInterestRate = getInterestRate();

		BigDecimal rhsInterestRate = that.getInterestRate();
		if (!strategy.equals(LocatorUtils.property(thisLocator, "interestRate",
				lhsInterestRate), LocatorUtils.property(thatLocator,
				"interestRate", rhsInterestRate), lhsInterestRate,
				rhsInterestRate)) {
			return false;
		}

		BigDecimal lhsCreditAvailableAmount = getCreditAvailableAmount();

		BigDecimal rhsCreditAvailableAmount = that.getCreditAvailableAmount();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"creditAvailableAmount", lhsCreditAvailableAmount),
				LocatorUtils.property(thatLocator, "creditAvailableAmount",
						rhsCreditAvailableAmount), lhsCreditAvailableAmount,
				rhsCreditAvailableAmount)) {
			return false;
		}

		BigDecimal lhsCreditMaxAmount = getCreditMaxAmount();

		BigDecimal rhsCreditMaxAmount = that.getCreditMaxAmount();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"creditMaxAmount", lhsCreditMaxAmount), LocatorUtils.property(
				thatLocator, "creditMaxAmount", rhsCreditMaxAmount),
				lhsCreditMaxAmount, rhsCreditMaxAmount)) {
			return false;
		}

		BigDecimal lhsCashAdvanceAvailableAmount = getCashAdvanceAvailableAmount();

		BigDecimal rhsCashAdvanceAvailableAmount = that
				.getCashAdvanceAvailableAmount();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"cashAdvanceAvailableAmount", lhsCashAdvanceAvailableAmount),
				LocatorUtils.property(thatLocator,
						"cashAdvanceAvailableAmount",
						rhsCashAdvanceAvailableAmount),
				lhsCashAdvanceAvailableAmount, rhsCashAdvanceAvailableAmount)) {
			return false;
		}

		BigDecimal lhsCashAdvanceMaxAmount = getCashAdvanceMaxAmount();

		BigDecimal rhsCashAdvanceMaxAmount = that.getCashAdvanceMaxAmount();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"cashAdvanceMaxAmount", lhsCashAdvanceMaxAmount), LocatorUtils
				.property(thatLocator, "cashAdvanceMaxAmount",
						rhsCashAdvanceMaxAmount), lhsCashAdvanceMaxAmount,
				rhsCashAdvanceMaxAmount)) {
			return false;
		}

		BigDecimal lhsCashAdvanceBalance = getCashAdvanceBalance();

		BigDecimal rhsCashAdvanceBalance = that.getCashAdvanceBalance();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"cashAdvanceBalance", lhsCashAdvanceBalance), LocatorUtils
				.property(thatLocator, "cashAdvanceBalance",
						rhsCashAdvanceBalance), lhsCashAdvanceBalance,
				rhsCashAdvanceBalance)) {
			return false;
		}

		BigDecimal lhsCashAdvanceInterestRate = getCashAdvanceInterestRate();

		BigDecimal rhsCashAdvanceInterestRate = that
				.getCashAdvanceInterestRate();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"cashAdvanceInterestRate", lhsCashAdvanceInterestRate),
				LocatorUtils.property(thatLocator, "cashAdvanceInterestRate",
						rhsCashAdvanceInterestRate),
				lhsCashAdvanceInterestRate, rhsCashAdvanceInterestRate)) {
			return false;
		}

		BigDecimal lhsCurrentBalance = getCurrentBalance();

		BigDecimal rhsCurrentBalance = that.getCurrentBalance();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"currentBalance", lhsCurrentBalance), LocatorUtils.property(
				thatLocator, "currentBalance", rhsCurrentBalance),
				lhsCurrentBalance, rhsCurrentBalance)) {
			return false;
		}

		BigDecimal lhsPaymentMinAmount = getPaymentMinAmount();

		BigDecimal rhsPaymentMinAmount = that.getPaymentMinAmount();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"paymentMinAmount", lhsPaymentMinAmount),
				LocatorUtils.property(thatLocator, "paymentMinAmount",
						rhsPaymentMinAmount), lhsPaymentMinAmount,
				rhsPaymentMinAmount)) {
			return false;
		}

		Calendar lhsPaymentDueDate = getPaymentDueDate();

		Calendar rhsPaymentDueDate = that.getPaymentDueDate();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"paymentDueDate", lhsPaymentDueDate), LocatorUtils.property(
				thatLocator, "paymentDueDate", rhsPaymentDueDate),
				lhsPaymentDueDate, rhsPaymentDueDate)) {
			return false;
		}

		BigDecimal lhsPreviousBalance = getPreviousBalance();

		BigDecimal rhsPreviousBalance = that.getPreviousBalance();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"previousBalance", lhsPreviousBalance), LocatorUtils.property(
				thatLocator, "previousBalance", rhsPreviousBalance),
				lhsPreviousBalance, rhsPreviousBalance)) {
			return false;
		}

		Calendar lhsStatementEndDate = getStatementEndDate();

		Calendar rhsStatementEndDate = that.getStatementEndDate();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"statementEndDate", lhsStatementEndDate),
				LocatorUtils.property(thatLocator, "statementEndDate",
						rhsStatementEndDate), lhsStatementEndDate,
				rhsStatementEndDate)) {
			return false;
		}

		BigDecimal lhsStatementPurchaseAmount = getStatementPurchaseAmount();

		BigDecimal rhsStatementPurchaseAmount = that
				.getStatementPurchaseAmount();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"statementPurchaseAmount", lhsStatementPurchaseAmount),
				LocatorUtils.property(thatLocator, "statementPurchaseAmount",
						rhsStatementPurchaseAmount),
				lhsStatementPurchaseAmount, rhsStatementPurchaseAmount)) {
			return false;
		}

		BigDecimal lhsStatementFinanceAmount = getStatementFinanceAmount();

		BigDecimal rhsStatementFinanceAmount = that.getStatementFinanceAmount();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"statementFinanceAmount", lhsStatementFinanceAmount),
				LocatorUtils.property(thatLocator, "statementFinanceAmount",
						rhsStatementFinanceAmount), lhsStatementFinanceAmount,
				rhsStatementFinanceAmount)) {
			return false;
		}

		BigDecimal lhsPastDueAmount = getPastDueAmount();

		BigDecimal rhsPastDueAmount = that.getPastDueAmount();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"pastDueAmount", lhsPastDueAmount), LocatorUtils.property(
				thatLocator, "pastDueAmount", rhsPastDueAmount),
				lhsPastDueAmount, rhsPastDueAmount)) {
			return false;
		}

		BigDecimal lhsLastPaymentAmount = getLastPaymentAmount();

		BigDecimal rhsLastPaymentAmount = that.getLastPaymentAmount();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"lastPaymentAmount", lhsLastPaymentAmount), LocatorUtils
				.property(thatLocator, "lastPaymentAmount",
						rhsLastPaymentAmount), lhsLastPaymentAmount,
				rhsLastPaymentAmount)) {
			return false;
		}

		Calendar lhsLastPaymentDate = getLastPaymentDate();

		Calendar rhsLastPaymentDate = that.getLastPaymentDate();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"lastPaymentDate", lhsLastPaymentDate), LocatorUtils.property(
				thatLocator, "lastPaymentDate", rhsLastPaymentDate),
				lhsLastPaymentDate, rhsLastPaymentDate)) {
			return false;
		}

		BigDecimal lhsStatementCloseBalance = getStatementCloseBalance();

		BigDecimal rhsStatementCloseBalance = that.getStatementCloseBalance();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"statementCloseBalance", lhsStatementCloseBalance),
				LocatorUtils.property(thatLocator, "statementCloseBalance",
						rhsStatementCloseBalance), lhsStatementCloseBalance,
				rhsStatementCloseBalance)) {
			return false;
		}

		BigDecimal lhsStatementLateFeeAmount = getStatementLateFeeAmount();

		BigDecimal rhsStatementLateFeeAmount = that.getStatementLateFeeAmount();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"statementLateFeeAmount", lhsStatementLateFeeAmount),
				LocatorUtils.property(thatLocator, "statementLateFeeAmount",
						rhsStatementLateFeeAmount), lhsStatementLateFeeAmount,
				rhsStatementLateFeeAmount)) {
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

		CreditAccountType theCreditAccountType = getCreditAccountType();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"creditAccountType", theCreditAccountType), currentHashCode,
				theCreditAccountType);

		String theDetailedDescription = getDetailedDescription();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"detailedDescription", theDetailedDescription),
				currentHashCode, theDetailedDescription);

		BigDecimal theInterestRate = getInterestRate();
		currentHashCode = strategy
				.hashCode(LocatorUtils.property(locator, "interestRate",
						theInterestRate), currentHashCode, theInterestRate);

		BigDecimal theCreditAvailableAmount = getCreditAvailableAmount();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"creditAvailableAmount", theCreditAvailableAmount),
				currentHashCode, theCreditAvailableAmount);

		BigDecimal theCreditMaxAmount = getCreditMaxAmount();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"creditMaxAmount", theCreditMaxAmount), currentHashCode,
				theCreditMaxAmount);

		BigDecimal theCashAdvanceAvailableAmount = getCashAdvanceAvailableAmount();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"cashAdvanceAvailableAmount", theCashAdvanceAvailableAmount),
				currentHashCode, theCashAdvanceAvailableAmount);

		BigDecimal theCashAdvanceMaxAmount = getCashAdvanceMaxAmount();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"cashAdvanceMaxAmount", theCashAdvanceMaxAmount),
				currentHashCode, theCashAdvanceMaxAmount);

		BigDecimal theCashAdvanceBalance = getCashAdvanceBalance();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"cashAdvanceBalance", theCashAdvanceBalance), currentHashCode,
				theCashAdvanceBalance);

		BigDecimal theCashAdvanceInterestRate = getCashAdvanceInterestRate();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"cashAdvanceInterestRate", theCashAdvanceInterestRate),
				currentHashCode, theCashAdvanceInterestRate);

		BigDecimal theCurrentBalance = getCurrentBalance();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"currentBalance", theCurrentBalance), currentHashCode,
				theCurrentBalance);

		BigDecimal thePaymentMinAmount = getPaymentMinAmount();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"paymentMinAmount", thePaymentMinAmount), currentHashCode,
				thePaymentMinAmount);

		Calendar thePaymentDueDate = getPaymentDueDate();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"paymentDueDate", thePaymentDueDate), currentHashCode,
				thePaymentDueDate);

		BigDecimal thePreviousBalance = getPreviousBalance();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"previousBalance", thePreviousBalance), currentHashCode,
				thePreviousBalance);

		Calendar theStatementEndDate = getStatementEndDate();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"statementEndDate", theStatementEndDate), currentHashCode,
				theStatementEndDate);

		BigDecimal theStatementPurchaseAmount = getStatementPurchaseAmount();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"statementPurchaseAmount", theStatementPurchaseAmount),
				currentHashCode, theStatementPurchaseAmount);

		BigDecimal theStatementFinanceAmount = getStatementFinanceAmount();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"statementFinanceAmount", theStatementFinanceAmount),
				currentHashCode, theStatementFinanceAmount);

		BigDecimal thePastDueAmount = getPastDueAmount();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"pastDueAmount", thePastDueAmount), currentHashCode,
				thePastDueAmount);

		BigDecimal theLastPaymentAmount = getLastPaymentAmount();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"lastPaymentAmount", theLastPaymentAmount), currentHashCode,
				theLastPaymentAmount);

		Calendar theLastPaymentDate = getLastPaymentDate();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"lastPaymentDate", theLastPaymentDate), currentHashCode,
				theLastPaymentDate);

		BigDecimal theStatementCloseBalance = getStatementCloseBalance();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"statementCloseBalance", theStatementCloseBalance),
				currentHashCode, theStatementCloseBalance);

		BigDecimal theStatementLateFeeAmount = getStatementLateFeeAmount();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"statementLateFeeAmount", theStatementLateFeeAmount),
				currentHashCode, theStatementLateFeeAmount);

		return currentHashCode;
	}

	public int hashCode() {
		HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
		return hashCode(null, strategy);
	}
}
