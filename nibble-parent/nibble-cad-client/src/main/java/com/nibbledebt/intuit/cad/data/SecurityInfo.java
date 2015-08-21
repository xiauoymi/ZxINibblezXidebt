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
@XmlType(name = "SecurityInfo", propOrder = { "assetClass", "fiAssetClass",
		"ticker", "uniqueId", "uniqueIdType", "asOfDate", "rating", "percent",
		"fiId", "name", "fundName", "memo", "symbolRefId", "currencyCode",
		"currencyRate", "unitPrice" })
@XmlSeeAlso({ OtherInfo.class, OptionInfo.class, StockInfo.class, MFInfo.class,
		DebtInfo.class })
public class SecurityInfo implements Serializable, Equals, HashCode {
	private static final long serialVersionUID = 1L;
	protected String assetClass;
	protected String fiAssetClass;
	protected String ticker;
	protected String uniqueId;
	protected String uniqueIdType;

	@XmlElement(type = String.class)
	@XmlJavaTypeAdapter(Adapter1.class)
	@XmlSchemaType(name = "dateTime")
	protected Calendar asOfDate;
	protected String rating;
	protected BigDecimal percent;
	protected String fiId;
	protected String name;
	protected String fundName;
	protected String memo;
	protected String symbolRefId;
	protected CurrencyCode currencyCode;
	protected BigDecimal currencyRate;
	protected BigDecimal unitPrice;

	public String getAssetClass() {
		return this.assetClass;
	}

	public void setAssetClass(String value) {
		this.assetClass = value;
	}

	public String getFiAssetClass() {
		return this.fiAssetClass;
	}

	public void setFiAssetClass(String value) {
		this.fiAssetClass = value;
	}

	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(String value) {
		this.ticker = value;
	}

	public String getUniqueId() {
		return this.uniqueId;
	}

	public void setUniqueId(String value) {
		this.uniqueId = value;
	}

	public String getUniqueIdType() {
		return this.uniqueIdType;
	}

	public void setUniqueIdType(String value) {
		this.uniqueIdType = value;
	}

	public Calendar getAsOfDate() {
		return this.asOfDate;
	}

	public void setAsOfDate(Calendar value) {
		this.asOfDate = value;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String value) {
		this.rating = value;
	}

	public BigDecimal getPercent() {
		return this.percent;
	}

	public void setPercent(BigDecimal value) {
		this.percent = value;
	}

	public String getFiId() {
		return this.fiId;
	}

	public void setFiId(String value) {
		this.fiId = value;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getFundName() {
		return this.fundName;
	}

	public void setFundName(String value) {
		this.fundName = value;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String value) {
		this.memo = value;
	}

	public String getSymbolRefId() {
		return this.symbolRefId;
	}

	public void setSymbolRefId(String value) {
		this.symbolRefId = value;
	}

	public CurrencyCode getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(CurrencyCode value) {
		this.currencyCode = value;
	}

	public BigDecimal getCurrencyRate() {
		return this.currencyRate;
	}

	public void setCurrencyRate(BigDecimal value) {
		this.currencyRate = value;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal value) {
		this.unitPrice = value;
	}

	public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator,
			Object object, EqualsStrategy strategy) {
		if (!(object instanceof SecurityInfo)) {
			return false;
		}
		if (this == object) {
			return true;
		}
		SecurityInfo that = (SecurityInfo) object;

		String lhsAssetClass = getAssetClass();

		String rhsAssetClass = that.getAssetClass();
		if (!strategy
				.equals(LocatorUtils.property(thisLocator, "assetClass",
						lhsAssetClass), LocatorUtils.property(thatLocator,
						"assetClass", rhsAssetClass), lhsAssetClass,
						rhsAssetClass)) {
			return false;
		}

		String lhsFiAssetClass = getFiAssetClass();

		String rhsFiAssetClass = that.getFiAssetClass();
		if (!strategy.equals(LocatorUtils.property(thisLocator, "fiAssetClass",
				lhsFiAssetClass), LocatorUtils.property(thatLocator,
				"fiAssetClass", rhsFiAssetClass), lhsFiAssetClass,
				rhsFiAssetClass)) {
			return false;
		}

		String lhsTicker = getTicker();

		String rhsTicker = that.getTicker();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "ticker", lhsTicker),
				LocatorUtils.property(thatLocator, "ticker", rhsTicker),
				lhsTicker, rhsTicker)) {
			return false;
		}

		String lhsUniqueId = getUniqueId();

		String rhsUniqueId = that.getUniqueId();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "uniqueId", lhsUniqueId),
				LocatorUtils.property(thatLocator, "uniqueId", rhsUniqueId),
				lhsUniqueId, rhsUniqueId)) {
			return false;
		}

		String lhsUniqueIdType = getUniqueIdType();

		String rhsUniqueIdType = that.getUniqueIdType();
		if (!strategy.equals(LocatorUtils.property(thisLocator, "uniqueIdType",
				lhsUniqueIdType), LocatorUtils.property(thatLocator,
				"uniqueIdType", rhsUniqueIdType), lhsUniqueIdType,
				rhsUniqueIdType)) {
			return false;
		}

		Calendar lhsAsOfDate = getAsOfDate();

		Calendar rhsAsOfDate = that.getAsOfDate();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "asOfDate", lhsAsOfDate),
				LocatorUtils.property(thatLocator, "asOfDate", rhsAsOfDate),
				lhsAsOfDate, rhsAsOfDate)) {
			return false;
		}

		String lhsRating = getRating();

		String rhsRating = that.getRating();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "rating", lhsRating),
				LocatorUtils.property(thatLocator, "rating", rhsRating),
				lhsRating, rhsRating)) {
			return false;
		}

		BigDecimal lhsPercent = getPercent();

		BigDecimal rhsPercent = that.getPercent();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "percent", lhsPercent),
				LocatorUtils.property(thatLocator, "percent", rhsPercent),
				lhsPercent, rhsPercent)) {
			return false;
		}

		String lhsFiId = getFiId();

		String rhsFiId = that.getFiId();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "fiId", lhsFiId),
				LocatorUtils.property(thatLocator, "fiId", rhsFiId), lhsFiId,
				rhsFiId)) {
			return false;
		}

		String lhsName = getName();

		String rhsName = that.getName();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "name", lhsName),
				LocatorUtils.property(thatLocator, "name", rhsName), lhsName,
				rhsName)) {
			return false;
		}

		String lhsFundName = getFundName();

		String rhsFundName = that.getFundName();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "fundName", lhsFundName),
				LocatorUtils.property(thatLocator, "fundName", rhsFundName),
				lhsFundName, rhsFundName)) {
			return false;
		}

		String lhsMemo = getMemo();

		String rhsMemo = that.getMemo();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "memo", lhsMemo),
				LocatorUtils.property(thatLocator, "memo", rhsMemo), lhsMemo,
				rhsMemo)) {
			return false;
		}

		String lhsSymbolRefId = getSymbolRefId();

		String rhsSymbolRefId = that.getSymbolRefId();
		if (!strategy.equals(LocatorUtils.property(thisLocator, "symbolRefId",
				lhsSymbolRefId), LocatorUtils.property(thatLocator,
				"symbolRefId", rhsSymbolRefId), lhsSymbolRefId, rhsSymbolRefId)) {
			return false;
		}

		CurrencyCode lhsCurrencyCode = getCurrencyCode();

		CurrencyCode rhsCurrencyCode = that.getCurrencyCode();
		if (!strategy.equals(LocatorUtils.property(thisLocator, "currencyCode",
				lhsCurrencyCode), LocatorUtils.property(thatLocator,
				"currencyCode", rhsCurrencyCode), lhsCurrencyCode,
				rhsCurrencyCode)) {
			return false;
		}

		BigDecimal lhsCurrencyRate = getCurrencyRate();

		BigDecimal rhsCurrencyRate = that.getCurrencyRate();
		if (!strategy.equals(LocatorUtils.property(thisLocator, "currencyRate",
				lhsCurrencyRate), LocatorUtils.property(thatLocator,
				"currencyRate", rhsCurrencyRate), lhsCurrencyRate,
				rhsCurrencyRate)) {
			return false;
		}

		BigDecimal lhsUnitPrice = getUnitPrice();

		BigDecimal rhsUnitPrice = that.getUnitPrice();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "unitPrice", lhsUnitPrice),
				LocatorUtils.property(thatLocator, "unitPrice", rhsUnitPrice),
				lhsUnitPrice, rhsUnitPrice)) {
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

		String theAssetClass = getAssetClass();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "assetClass", theAssetClass),
				currentHashCode, theAssetClass);

		String theFiAssetClass = getFiAssetClass();
		currentHashCode = strategy
				.hashCode(LocatorUtils.property(locator, "fiAssetClass",
						theFiAssetClass), currentHashCode, theFiAssetClass);

		String theTicker = getTicker();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "ticker", theTicker),
				currentHashCode, theTicker);

		String theUniqueId = getUniqueId();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "uniqueId", theUniqueId),
				currentHashCode, theUniqueId);

		String theUniqueIdType = getUniqueIdType();
		currentHashCode = strategy
				.hashCode(LocatorUtils.property(locator, "uniqueIdType",
						theUniqueIdType), currentHashCode, theUniqueIdType);

		Calendar theAsOfDate = getAsOfDate();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "asOfDate", theAsOfDate),
				currentHashCode, theAsOfDate);

		String theRating = getRating();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "rating", theRating),
				currentHashCode, theRating);

		BigDecimal thePercent = getPercent();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "percent", thePercent),
				currentHashCode, thePercent);

		String theFiId = getFiId();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "fiId", theFiId),
				currentHashCode, theFiId);

		String theName = getName();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "name", theName),
				currentHashCode, theName);

		String theFundName = getFundName();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "fundName", theFundName),
				currentHashCode, theFundName);

		String theMemo = getMemo();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "memo", theMemo),
				currentHashCode, theMemo);

		String theSymbolRefId = getSymbolRefId();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "symbolRefId", theSymbolRefId),
				currentHashCode, theSymbolRefId);

		CurrencyCode theCurrencyCode = getCurrencyCode();
		currentHashCode = strategy
				.hashCode(LocatorUtils.property(locator, "currencyCode",
						theCurrencyCode), currentHashCode, theCurrencyCode);

		BigDecimal theCurrencyRate = getCurrencyRate();
		currentHashCode = strategy
				.hashCode(LocatorUtils.property(locator, "currencyRate",
						theCurrencyRate), currentHashCode, theCurrencyRate);

		BigDecimal theUnitPrice = getUnitPrice();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "unitPrice", theUnitPrice),
				currentHashCode, theUnitPrice);

		return currentHashCode;
	}

	public int hashCode() {
		HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
		return hashCode(null, strategy);
	}
}