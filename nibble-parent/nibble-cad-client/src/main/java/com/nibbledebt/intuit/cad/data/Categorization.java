package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Categorization", namespace = "http://schema.intuit.com/platform/fdatafeed/transaction/v1", propOrder = {
		"common", "contexts" })
public class Categorization implements Serializable, Equals, HashCode {
	private static final long serialVersionUID = 1L;

	@XmlElement(required = true)
	protected Common common;

	@XmlElement(name = "context")
	protected List<Context> contexts;

	public Common getCommon() {
		return this.common;
	}

	public void setCommon(Common value) {
		this.common = value;
	}

	public List<Context> getContexts() {
		if (this.contexts == null) {
			this.contexts = new ArrayList();
		}
		return this.contexts;
	}

	public void setContexts(List<Context> contexts) {
		this.contexts = contexts;
	}

	public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator,
			Object object, EqualsStrategy strategy) {
		if (!(object instanceof Categorization)) {
			return false;
		}
		if (this == object) {
			return true;
		}
		Categorization that = (Categorization) object;

		Common lhsCommon = getCommon();

		Common rhsCommon = that.getCommon();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "common", lhsCommon),
				LocatorUtils.property(thatLocator, "common", rhsCommon),
				lhsCommon, rhsCommon)) {
			return false;
		}

		List lhsContexts = (this.contexts != null)
				&& (!this.contexts.isEmpty()) ? getContexts() : null;

		List rhsContexts = (that.contexts != null)
				&& (!that.contexts.isEmpty()) ? that.getContexts() : null;
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "contexts", lhsContexts),
				LocatorUtils.property(thatLocator, "contexts", rhsContexts),
				lhsContexts, rhsContexts)) {
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

		Common theCommon = getCommon();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "common", theCommon),
				currentHashCode, theCommon);

		List theContexts = (this.contexts != null)
				&& (!this.contexts.isEmpty()) ? getContexts() : null;
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "contexts", theContexts),
				currentHashCode, theContexts);

		return currentHashCode;
	}

	public int hashCode() {
		HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
		return hashCode(null, strategy);
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "source", "categoryName", "contextType",
			"scheduleC" })
	public static class Context implements Serializable, Equals, HashCode {
		private static final long serialVersionUID = 1L;

		@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/transaction/v1", required = true)
		protected CategorizationSource source;

		@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/transaction/v1", required = true)
		protected String categoryName;

		@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/transaction/v1")
		protected String contextType;

		@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/transaction/v1")
		protected String scheduleC;

		public CategorizationSource getSource() {
			return this.source;
		}

		public void setSource(CategorizationSource value) {
			this.source = value;
		}

		public String getCategoryName() {
			return this.categoryName;
		}

		public void setCategoryName(String value) {
			this.categoryName = value;
		}

		public String getContextType() {
			return this.contextType;
		}

		public void setContextType(String value) {
			this.contextType = value;
		}

		public String getScheduleC() {
			return this.scheduleC;
		}

		public void setScheduleC(String value) {
			this.scheduleC = value;
		}

		public boolean equals(ObjectLocator thisLocator,
				ObjectLocator thatLocator, Object object,
				EqualsStrategy strategy) {
			if (!(object instanceof Context)) {
				return false;
			}
			if (this == object) {
				return true;
			}
			Context that = (Context) object;

			CategorizationSource lhsSource = getSource();

			CategorizationSource rhsSource = that.getSource();
			if (!strategy.equals(
					LocatorUtils.property(thisLocator, "source", lhsSource),
					LocatorUtils.property(thatLocator, "source", rhsSource),
					lhsSource, rhsSource)) {
				return false;
			}

			String lhsCategoryName = getCategoryName();

			String rhsCategoryName = that.getCategoryName();
			if (!strategy.equals(LocatorUtils.property(thisLocator,
					"categoryName", lhsCategoryName), LocatorUtils.property(
					thatLocator, "categoryName", rhsCategoryName),
					lhsCategoryName, rhsCategoryName)) {
				return false;
			}

			String lhsContextType = getContextType();

			String rhsContextType = that.getContextType();
			if (!strategy.equals(LocatorUtils.property(thisLocator,
					"contextType", lhsContextType), LocatorUtils.property(
					thatLocator, "contextType", rhsContextType),
					lhsContextType, rhsContextType)) {
				return false;
			}

			String lhsScheduleC = getScheduleC();

			String rhsScheduleC = that.getScheduleC();
			if (!strategy.equals(LocatorUtils.property(thisLocator,
					"scheduleC", lhsScheduleC), LocatorUtils.property(
					thatLocator, "scheduleC", rhsScheduleC), lhsScheduleC,
					rhsScheduleC)) {
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

			CategorizationSource theSource = getSource();
			currentHashCode = strategy.hashCode(
					LocatorUtils.property(locator, "source", theSource),
					currentHashCode, theSource);

			String theCategoryName = getCategoryName();
			currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
					"categoryName", theCategoryName), currentHashCode,
					theCategoryName);

			String theContextType = getContextType();
			currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
					"contextType", theContextType), currentHashCode,
					theContextType);

			String theScheduleC = getScheduleC();
			currentHashCode = strategy.hashCode(
					LocatorUtils.property(locator, "scheduleC", theScheduleC),
					currentHashCode, theScheduleC);

			return currentHashCode;
		}

		public int hashCode() {
			HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
			return hashCode(null, strategy);
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "normalizedPayeeName", "merchant", "sic" })
	public static class Common implements Serializable, Equals, HashCode {
		private static final long serialVersionUID = 1L;

		@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/transaction/v1")
		protected String normalizedPayeeName;

		@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/transaction/v1")
		protected String merchant;

		@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/transaction/v1")
		protected Integer sic;

		public String getNormalizedPayeeName() {
			return this.normalizedPayeeName;
		}

		public void setNormalizedPayeeName(String value) {
			this.normalizedPayeeName = value;
		}

		public String getMerchant() {
			return this.merchant;
		}

		public void setMerchant(String value) {
			this.merchant = value;
		}

		public Integer getSic() {
			return this.sic;
		}

		public void setSic(Integer value) {
			this.sic = value;
		}

		public boolean equals(ObjectLocator thisLocator,
				ObjectLocator thatLocator, Object object,
				EqualsStrategy strategy) {
			if (!(object instanceof Common)) {
				return false;
			}
			if (this == object) {
				return true;
			}
			Common that = (Common) object;

			String lhsNormalizedPayeeName = getNormalizedPayeeName();

			String rhsNormalizedPayeeName = that.getNormalizedPayeeName();
			if (!strategy.equals(LocatorUtils.property(thisLocator,
					"normalizedPayeeName", lhsNormalizedPayeeName),
					LocatorUtils.property(thatLocator, "normalizedPayeeName",
							rhsNormalizedPayeeName), lhsNormalizedPayeeName,
					rhsNormalizedPayeeName)) {
				return false;
			}

			String lhsMerchant = getMerchant();

			String rhsMerchant = that.getMerchant();
			if (!strategy
					.equals(LocatorUtils.property(thisLocator, "merchant",
							lhsMerchant), LocatorUtils.property(thatLocator,
							"merchant", rhsMerchant), lhsMerchant, rhsMerchant)) {
				return false;
			}

			Integer lhsSic = getSic();

			Integer rhsSic = that.getSic();
			if (!strategy.equals(
					LocatorUtils.property(thisLocator, "sic", lhsSic),
					LocatorUtils.property(thatLocator, "sic", rhsSic), lhsSic,
					rhsSic)) {
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

			String theNormalizedPayeeName = getNormalizedPayeeName();
			currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
					"normalizedPayeeName", theNormalizedPayeeName),
					currentHashCode, theNormalizedPayeeName);

			String theMerchant = getMerchant();
			currentHashCode = strategy.hashCode(
					LocatorUtils.property(locator, "merchant", theMerchant),
					currentHashCode, theMerchant);

			Integer theSic = getSic();
			currentHashCode = strategy.hashCode(
					LocatorUtils.property(locator, "sic", theSic),
					currentHashCode, theSic);

			return currentHashCode;
		}

		public int hashCode() {
			HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
			return hashCode(null, strategy);
		}
	}
}