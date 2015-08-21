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
@XmlType(name = "OptionInfo", propOrder = { "expireDate", "strikePrice",
		"optType", "securityUniqueId", "securityUniqueIdType",
		"sharesPerContract" })
public class OptionInfo extends SecurityInfo implements Serializable, Equals,
		HashCode {
	private static final long serialVersionUID = 1L;

	@XmlElement(type = String.class)
	@XmlJavaTypeAdapter(Adapter1.class)
	@XmlSchemaType(name = "dateTime")
	protected Calendar expireDate;
	protected BigDecimal strikePrice;
	protected String optType;
	protected String securityUniqueId;
	protected String securityUniqueIdType;
	protected Long sharesPerContract;

	public Calendar getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Calendar value) {
		this.expireDate = value;
	}

	public BigDecimal getStrikePrice() {
		return this.strikePrice;
	}

	public void setStrikePrice(BigDecimal value) {
		this.strikePrice = value;
	}

	public String getOptType() {
		return this.optType;
	}

	public void setOptType(String value) {
		this.optType = value;
	}

	public String getSecurityUniqueId() {
		return this.securityUniqueId;
	}

	public void setSecurityUniqueId(String value) {
		this.securityUniqueId = value;
	}

	public String getSecurityUniqueIdType() {
		return this.securityUniqueIdType;
	}

	public void setSecurityUniqueIdType(String value) {
		this.securityUniqueIdType = value;
	}

	public Long getSharesPerContract() {
		return this.sharesPerContract;
	}

	public void setSharesPerContract(Long value) {
		this.sharesPerContract = value;
	}

	public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator,
			Object object, EqualsStrategy strategy) {
		if (!(object instanceof OptionInfo)) {
			return false;
		}
		if (this == object) {
			return true;
		}
		if (!super.equals(thisLocator, thatLocator, object, strategy)) {
			return false;
		}
		OptionInfo that = (OptionInfo) object;

		Calendar lhsExpireDate = getExpireDate();

		Calendar rhsExpireDate = that.getExpireDate();
		if (!strategy
				.equals(LocatorUtils.property(thisLocator, "expireDate",
						lhsExpireDate), LocatorUtils.property(thatLocator,
						"expireDate", rhsExpireDate), lhsExpireDate,
						rhsExpireDate)) {
			return false;
		}

		BigDecimal lhsStrikePrice = getStrikePrice();

		BigDecimal rhsStrikePrice = that.getStrikePrice();
		if (!strategy.equals(LocatorUtils.property(thisLocator, "strikePrice",
				lhsStrikePrice), LocatorUtils.property(thatLocator,
				"strikePrice", rhsStrikePrice), lhsStrikePrice, rhsStrikePrice)) {
			return false;
		}

		String lhsOptType = getOptType();

		String rhsOptType = that.getOptType();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "optType", lhsOptType),
				LocatorUtils.property(thatLocator, "optType", rhsOptType),
				lhsOptType, rhsOptType)) {
			return false;
		}

		String lhsSecurityUniqueId = getSecurityUniqueId();

		String rhsSecurityUniqueId = that.getSecurityUniqueId();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"securityUniqueId", lhsSecurityUniqueId),
				LocatorUtils.property(thatLocator, "securityUniqueId",
						rhsSecurityUniqueId), lhsSecurityUniqueId,
				rhsSecurityUniqueId)) {
			return false;
		}

		String lhsSecurityUniqueIdType = getSecurityUniqueIdType();

		String rhsSecurityUniqueIdType = that.getSecurityUniqueIdType();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"securityUniqueIdType", lhsSecurityUniqueIdType), LocatorUtils
				.property(thatLocator, "securityUniqueIdType",
						rhsSecurityUniqueIdType), lhsSecurityUniqueIdType,
				rhsSecurityUniqueIdType)) {
			return false;
		}

		Long lhsSharesPerContract = getSharesPerContract();

		Long rhsSharesPerContract = that.getSharesPerContract();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"sharesPerContract", lhsSharesPerContract), LocatorUtils
				.property(thatLocator, "sharesPerContract",
						rhsSharesPerContract), lhsSharesPerContract,
				rhsSharesPerContract)) {
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

		Calendar theExpireDate = getExpireDate();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "expireDate", theExpireDate),
				currentHashCode, theExpireDate);

		BigDecimal theStrikePrice = getStrikePrice();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "strikePrice", theStrikePrice),
				currentHashCode, theStrikePrice);

		String theOptType = getOptType();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "optType", theOptType),
				currentHashCode, theOptType);

		String theSecurityUniqueId = getSecurityUniqueId();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"securityUniqueId", theSecurityUniqueId), currentHashCode,
				theSecurityUniqueId);

		String theSecurityUniqueIdType = getSecurityUniqueIdType();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"securityUniqueIdType", theSecurityUniqueIdType),
				currentHashCode, theSecurityUniqueIdType);

		Long theSharesPerContract = getSharesPerContract();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"sharesPerContract", theSharesPerContract), currentHashCode,
				theSharesPerContract);

		return currentHashCode;
	}

	public int hashCode() {
		HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
		return hashCode(null, strategy);
	}
}