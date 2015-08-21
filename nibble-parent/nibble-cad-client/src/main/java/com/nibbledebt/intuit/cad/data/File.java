package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;
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
@XmlType(name = "File", namespace = "http://schema.intuit.com/platform/fdatafeed/files/v1", propOrder = {
		"name", "size", "modifiedTs" })
@XmlRootElement(name = "File", namespace = "http://schema.intuit.com/platform/fdatafeed/files/v1")
public class File implements Serializable, Equals, HashCode {
	private static final long serialVersionUID = 1L;

	@XmlElement(required = true)
	protected String name;
	protected long size;

	@XmlElement(required = true, type = String.class)
	@XmlJavaTypeAdapter(Adapter1.class)
	@XmlSchemaType(name = "dateTime")
	protected Calendar modifiedTs;

	public String getName() {
		return this.name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public long getSize() {
		return this.size;
	}

	public void setSize(long value) {
		this.size = value;
	}

	public Calendar getModifiedTs() {
		return this.modifiedTs;
	}

	public void setModifiedTs(Calendar value) {
		this.modifiedTs = value;
	}

	public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator,
			Object object, EqualsStrategy strategy) {
		if (!(object instanceof File)) {
			return false;
		}
		if (this == object) {
			return true;
		}
		File that = (File) object;

		String lhsName = getName();

		String rhsName = that.getName();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "name", lhsName),
				LocatorUtils.property(thatLocator, "name", rhsName), lhsName,
				rhsName)) {
			return false;
		}

		long lhsSize = getSize();

		long rhsSize = that.getSize();
		if (!strategy.equals(
				LocatorUtils.property(thisLocator, "size", lhsSize),
				LocatorUtils.property(thatLocator, "size", rhsSize), lhsSize,
				rhsSize)) {
			return false;
		}

		Calendar lhsModifiedTs = getModifiedTs();

		Calendar rhsModifiedTs = that.getModifiedTs();
		if (!strategy
				.equals(LocatorUtils.property(thisLocator, "modifiedTs",
						lhsModifiedTs), LocatorUtils.property(thatLocator,
						"modifiedTs", rhsModifiedTs), lhsModifiedTs,
						rhsModifiedTs)) {
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

		long theSize = getSize();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "size", theSize),
				currentHashCode, theSize);

		Calendar theModifiedTs = getModifiedTs();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "modifiedTs", theModifiedTs),
				currentHashCode, theModifiedTs);

		return currentHashCode;
	}

	public int hashCode() {
		HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
		return hashCode(null, strategy);
	}
}