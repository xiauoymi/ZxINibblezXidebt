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
@XmlType(name="StockInfo", propOrder={"stockType", "yield", "yieldAsOfDate"})
public class StockInfo extends SecurityInfo
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  protected String stockType;
  protected BigDecimal yield;

  @XmlElement(type=String.class)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name="dateTime")
  protected Calendar yieldAsOfDate;

  public String getStockType()
  {
    return this.stockType;
  }

  public void setStockType(String value)
  {
    this.stockType = value;
  }

  public BigDecimal getYield()
  {
    return this.yield;
  }

  public void setYield(BigDecimal value)
  {
    this.yield = value;
  }

  public Calendar getYieldAsOfDate()
  {
    return this.yieldAsOfDate;
  }

  public void setYieldAsOfDate(Calendar value)
  {
    this.yieldAsOfDate = value;
  }

  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
    if (!(object instanceof StockInfo)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    if (!super.equals(thisLocator, thatLocator, object, strategy)) {
      return false;
    }
    StockInfo that = (StockInfo)object;

    String lhsStockType = getStockType();

    String rhsStockType = that.getStockType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "stockType", lhsStockType), LocatorUtils.property(thatLocator, "stockType", rhsStockType), lhsStockType, rhsStockType)) {
      return false;
    }

    BigDecimal lhsYield = getYield();

    BigDecimal rhsYield = that.getYield();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "yield", lhsYield), LocatorUtils.property(thatLocator, "yield", rhsYield), lhsYield, rhsYield)) {
      return false;
    }

    Calendar lhsYieldAsOfDate = getYieldAsOfDate();

    Calendar rhsYieldAsOfDate = that.getYieldAsOfDate();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "yieldAsOfDate", lhsYieldAsOfDate), LocatorUtils.property(thatLocator, "yieldAsOfDate", rhsYieldAsOfDate), lhsYieldAsOfDate, rhsYieldAsOfDate)) {
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

    String theStockType = getStockType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "stockType", theStockType), currentHashCode, theStockType);

    BigDecimal theYield = getYield();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "yield", theYield), currentHashCode, theYield);

    Calendar theYieldAsOfDate = getYieldAsOfDate();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "yieldAsOfDate", theYieldAsOfDate), currentHashCode, theYieldAsOfDate);

    return currentHashCode;
  }

  public int hashCode() {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}