package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;
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
@XmlType(name = "Credential", namespace = "http://schema.intuit.com/platform/fdatafeed/institutionlogin/v1", propOrder = {
		"name", "value" })
public class Credential implements Serializable, Equals, HashCode {
	private static final long serialVersionUID = 1L;

	@XmlElement(required = true)
	protected String name;

	@XmlElement(required = true)
	protected String value;

	public String getName() {
		return this.name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator,
			Object object, EqualsStrategy strategy) {
		if (!(object instanceof Credential)) {
			return false;
		}
		if (this == object) {
			return true;
		}
		Credential that = (Credential) object;

		String lhsName = getName();

		String rhsName = that.getName();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "name", lhsName),
				LocatorUtils.property(thatLocator, "name", rhsName), lhsName,
				rhsName)) {
			return false;
		}

		String lhsValue = getValue();

		String rhsValue = that.getValue();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "value", lhsValue),
				LocatorUtils.property(thatLocator, "value", rhsValue),
				lhsValue, rhsValue)) {
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

		String theName = getName();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "name", theName),
				currentHashCode, theName);

		String theValue = getValue();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "value", theValue),
				currentHashCode, theValue);

		return currentHashCode;
	}

	public int hashCode() {
		HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
		return hashCode(null, strategy);
	}
}