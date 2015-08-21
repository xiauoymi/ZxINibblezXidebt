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
@XmlType(name = "MFInfo", propOrder = { "mfType", "fundManager", "yield",
		"yieldAsOfDate", "secCurrencyCode", "secCurrencyRate" })
public class MFInfo extends SecurityInfo implements Serializable, Equals,
		HashCode {
	private static final long serialVersionUID = 1L;
	protected String mfType;
	protected String fundManager;
	protected BigDecimal yield;

	@XmlElement(type = String.class)
	@XmlJavaTypeAdapter(Adapter1.class)
	@XmlSchemaType(name = "dateTime")
	protected Calendar yieldAsOfDate;
	protected CurrencyCode secCurrencyCode;
	protected BigDecimal secCurrencyRate;

	public String getMfType() {
		return this.mfType;
	}

	public void setMfType(String value) {
		this.mfType = value;
	}

	public String getFundManager() {
		return this.fundManager;
	}

	public void setFundManager(String value) {
		this.fundManager = value;
	}

	public BigDecimal getYield() {
		return this.yield;
	}

	public void setYield(BigDecimal value) {
		this.yield = value;
	}

	public Calendar getYieldAsOfDate() {
		return this.yieldAsOfDate;
	}

	public void setYieldAsOfDate(Calendar value) {
		this.yieldAsOfDate = value;
	}

	public CurrencyCode getSecCurrencyCode() {
		return this.secCurrencyCode;
	}

	public void setSecCurrencyCode(CurrencyCode value) {
		this.secCurrencyCode = value;
	}

	public BigDecimal getSecCurrencyRate() {
		return this.secCurrencyRate;
	}

	public void setSecCurrencyRate(BigDecimal value) {
		this.secCurrencyRate = value;
	}

	public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator,
			Object object, EqualsStrategy strategy) {
		if (!(object instanceof MFInfo)) {
			return false;
		}
		if (this == object) {
			return true;
		}
		if (!super.equals(thisLocator, thatLocator, object, strategy)) {
			return false;
		}
		MFInfo that = (MFInfo) object;

		String lhsMfType = getMfType();

		String rhsMfType = that.getMfType();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "mfType", lhsMfType),
				LocatorUtils.property(thatLocator, "mfType", rhsMfType),
				lhsMfType, rhsMfType)) {
			return false;
		}

		String lhsFundManager = getFundManager();

		String rhsFundManager = that.getFundManager();
		if (!strategy.equals(LocatorUtils.property(thisLocator, "fundManager",
				lhsFundManager), LocatorUtils.property(thatLocator,
				"fundManager", rhsFundManager), lhsFundManager, rhsFundManager)) {
			return false;
		}

		BigDecimal lhsYield = getYield();

		BigDecimal rhsYield = that.getYield();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "yield", lhsYield),
				LocatorUtils.property(thatLocator, "yield", rhsYield),
				lhsYield, rhsYield)) {
			return false;
		}

		Calendar lhsYieldAsOfDate = getYieldAsOfDate();

		Calendar rhsYieldAsOfDate = that.getYieldAsOfDate();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"yieldAsOfDate", lhsYieldAsOfDate), LocatorUtils.property(
				thatLocator, "yieldAsOfDate", rhsYieldAsOfDate),
				lhsYieldAsOfDate, rhsYieldAsOfDate)) {
			return false;
		}

		CurrencyCode lhsSecCurrencyCode = getSecCurrencyCode();

		CurrencyCode rhsSecCurrencyCode = that.getSecCurrencyCode();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"secCurrencyCode", lhsSecCurrencyCode), LocatorUtils.property(
				thatLocator, "secCurrencyCode", rhsSecCurrencyCode),
				lhsSecCurrencyCode, rhsSecCurrencyCode)) {
			return false;
		}

		BigDecimal lhsSecCurrencyRate = getSecCurrencyRate();

		BigDecimal rhsSecCurrencyRate = that.getSecCurrencyRate();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"secCurrencyRate", lhsSecCurrencyRate), LocatorUtils.property(
				thatLocator, "secCurrencyRate", rhsSecCurrencyRate),
				lhsSecCurrencyRate, rhsSecCurrencyRate)) {
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

		String theMfType = getMfType();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "mfType", theMfType),
				currentHashCode, theMfType);

		String theFundManager = getFundManager();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "fundManager", theFundManager),
				currentHashCode, theFundManager);

		BigDecimal theYield = getYield();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "yield", theYield),
				currentHashCode, theYield);

		Calendar theYieldAsOfDate = getYieldAsOfDate();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"yieldAsOfDate", theYieldAsOfDate), currentHashCode,
				theYieldAsOfDate);

		CurrencyCode theSecCurrencyCode = getSecCurrencyCode();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"secCurrencyCode", theSecCurrencyCode), currentHashCode,
				theSecCurrencyCode);

		BigDecimal theSecCurrencyRate = getSecCurrencyRate();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"secCurrencyRate", theSecCurrencyRate), currentHashCode,
				theSecCurrencyRate);

		return currentHashCode;
	}

	public int hashCode() {
		HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
		return hashCode(null, strategy);
	}
}