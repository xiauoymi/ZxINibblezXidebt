package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
@XmlType(name="", propOrder={"statusMessage", "errorInfos"})
@XmlRootElement(name="Status", namespace="http://schema.intuit.com/platform/fdatafeed/common/v1")
public class Status
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;

  @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/common/v1")
  protected String statusMessage;

  @XmlElement(name="errorInfo", namespace="http://schema.intuit.com/platform/fdatafeed/common/v1")
  protected List<ErrorInfo> errorInfos;

  public String getStatusMessage()
  {
    return this.statusMessage;
  }

  public void setStatusMessage(String value)
  {
    this.statusMessage = value;
  }

  public List<ErrorInfo> getErrorInfos()
  {
    if (this.errorInfos == null) {
      this.errorInfos = new ArrayList();
    }
    return this.errorInfos;
  }

  public void setErrorInfos(List<ErrorInfo> errorInfos)
  {
    this.errorInfos = errorInfos;
  }

  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
    if (!(object instanceof Status)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    Status that = (Status)object;

    String lhsStatusMessage = getStatusMessage();

    String rhsStatusMessage = that.getStatusMessage();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "statusMessage", lhsStatusMessage), LocatorUtils.property(thatLocator, "statusMessage", rhsStatusMessage), lhsStatusMessage, rhsStatusMessage)) {
      return false;
    }

    List lhsErrorInfos = (this.errorInfos != null) && (!this.errorInfos.isEmpty()) ? getErrorInfos() : null;

    List rhsErrorInfos = (that.errorInfos != null) && (!that.errorInfos.isEmpty()) ? that.getErrorInfos() : null;
    if (!strategy.equals(LocatorUtils.property(thisLocator, "errorInfos", lhsErrorInfos), LocatorUtils.property(thatLocator, "errorInfos", rhsErrorInfos), lhsErrorInfos, rhsErrorInfos)) {
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

    String theStatusMessage = getStatusMessage();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "statusMessage", theStatusMessage), currentHashCode, theStatusMessage);

    List theErrorInfos = (this.errorInfos != null) && (!this.errorInfos.isEmpty()) ? getErrorInfos() : null;
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "errorInfos", theErrorInfos), currentHashCode, theErrorInfos);

    return currentHashCode;
  }

  public int hashCode() {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}