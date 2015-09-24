package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
@XmlType(name="ErrorInfo", namespace="http://schema.intuit.com/platform/fdatafeed/common/v1", propOrder={"errorType", "errorCode", "errorMessage", "correlationId", "exceptionDetail"})
public class ErrorInfo
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  protected ErrorType errorType;
  protected String errorCode;
  protected String errorMessage;
  protected String correlationId;
  protected String exceptionDetail;

  public ErrorType getErrorType()
  {
    return this.errorType;
  }

  public void setErrorType(ErrorType value)
  {
    this.errorType = value;
  }

  public String getErrorCode()
  {
    return this.errorCode;
  }

  public void setErrorCode(String value)
  {
    this.errorCode = value;
  }

  public String getErrorMessage()
  {
    return this.errorMessage;
  }

  public void setErrorMessage(String value)
  {
    this.errorMessage = value;
  }

  public String getCorrelationId()
  {
    return this.correlationId;
  }

  public void setCorrelationId(String value)
  {
    this.correlationId = value;
  }

  public String getExceptionDetail()
  {
    return this.exceptionDetail;
  }

  public void setExceptionDetail(String value)
  {
    this.exceptionDetail = value;
  }

  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
    if (!(object instanceof ErrorInfo)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    ErrorInfo that = (ErrorInfo)object;

    ErrorType lhsErrorType = getErrorType();

    ErrorType rhsErrorType = that.getErrorType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "errorType", lhsErrorType), LocatorUtils.property(thatLocator, "errorType", rhsErrorType), lhsErrorType, rhsErrorType)) {
      return false;
    }

    String lhsErrorCode = getErrorCode();

    String rhsErrorCode = that.getErrorCode();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "errorCode", lhsErrorCode), LocatorUtils.property(thatLocator, "errorCode", rhsErrorCode), lhsErrorCode, rhsErrorCode)) {
      return false;
    }

    String lhsErrorMessage = getErrorMessage();

    String rhsErrorMessage = that.getErrorMessage();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "errorMessage", lhsErrorMessage), LocatorUtils.property(thatLocator, "errorMessage", rhsErrorMessage), lhsErrorMessage, rhsErrorMessage)) {
      return false;
    }

    String lhsCorrelationId = getCorrelationId();

    String rhsCorrelationId = that.getCorrelationId();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "correlationId", lhsCorrelationId), LocatorUtils.property(thatLocator, "correlationId", rhsCorrelationId), lhsCorrelationId, rhsCorrelationId)) {
      return false;
    }

    String lhsExceptionDetail = getExceptionDetail();

    String rhsExceptionDetail = that.getExceptionDetail();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "exceptionDetail", lhsExceptionDetail), LocatorUtils.property(thatLocator, "exceptionDetail", rhsExceptionDetail), lhsExceptionDetail, rhsExceptionDetail)) {
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

    ErrorType theErrorType = getErrorType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "errorType", theErrorType), currentHashCode, theErrorType);

    String theErrorCode = getErrorCode();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "errorCode", theErrorCode), currentHashCode, theErrorCode);

    String theErrorMessage = getErrorMessage();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "errorMessage", theErrorMessage), currentHashCode, theErrorMessage);

    String theCorrelationId = getCorrelationId();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "correlationId", theCorrelationId), currentHashCode, theCorrelationId);

    String theExceptionDetail = getExceptionDetail();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "exceptionDetail", theExceptionDetail), currentHashCode, theExceptionDetail);

    return currentHashCode;
  }

  public int hashCode() {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}