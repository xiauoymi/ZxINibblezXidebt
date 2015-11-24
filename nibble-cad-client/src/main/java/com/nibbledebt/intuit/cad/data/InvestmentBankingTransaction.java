package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
@XmlType(name="InvestmentBankingTransaction", namespace="http://schema.intuit.com/platform/fdatafeed/ibnktransaction/v1", propOrder={"bankingTransactionType", "subaccountFundType", "banking401KSourceType"})
@XmlRootElement(name="InvestmentBankingTransaction", namespace="http://schema.intuit.com/platform/fdatafeed/ibnktransaction/v1")
public class InvestmentBankingTransaction
  extends Transaction
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  protected String bankingTransactionType;
  protected InvestmentSubAccountType subaccountFundType;
  protected Banking401KSourceType banking401KSourceType;
  
  public String getBankingTransactionType()
  {
    return this.bankingTransactionType;
  }
  
  public void setBankingTransactionType(String value)
  {
    this.bankingTransactionType = value;
  }
  
  public InvestmentSubAccountType getSubaccountFundType()
  {
    return this.subaccountFundType;
  }
  
  public void setSubaccountFundType(InvestmentSubAccountType value)
  {
    this.subaccountFundType = value;
  }
  
  public Banking401KSourceType getBanking401KSourceType()
  {
    return this.banking401KSourceType;
  }
  
  public void setBanking401KSourceType(Banking401KSourceType value)
  {
    this.banking401KSourceType = value;
  }
  
  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy)
  {
    if (!(object instanceof InvestmentBankingTransaction)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    if (!super.equals(thisLocator, thatLocator, object, strategy)) {
      return false;
    }
    InvestmentBankingTransaction that = (InvestmentBankingTransaction)object;
    
    String lhsBankingTransactionType = getBankingTransactionType();
    
    String rhsBankingTransactionType = that.getBankingTransactionType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "bankingTransactionType", lhsBankingTransactionType), LocatorUtils.property(thatLocator, "bankingTransactionType", rhsBankingTransactionType), lhsBankingTransactionType, rhsBankingTransactionType)) {
      return false;
    }
    InvestmentSubAccountType lhsSubaccountFundType = getSubaccountFundType();
    
    InvestmentSubAccountType rhsSubaccountFundType = that.getSubaccountFundType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "subaccountFundType", lhsSubaccountFundType), LocatorUtils.property(thatLocator, "subaccountFundType", rhsSubaccountFundType), lhsSubaccountFundType, rhsSubaccountFundType)) {
      return false;
    }
    Banking401KSourceType lhsBanking401KSourceType = getBanking401KSourceType();
    
    Banking401KSourceType rhsBanking401KSourceType = that.getBanking401KSourceType();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "banking401KSourceType", lhsBanking401KSourceType), LocatorUtils.property(thatLocator, "banking401KSourceType", rhsBanking401KSourceType), lhsBanking401KSourceType, rhsBanking401KSourceType)) {
      return false;
    }
    return true;
  }
  
  public boolean equals(Object object)
  {
    EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
    return equals(null, null, object, strategy);
  }
  
  public int hashCode(ObjectLocator locator, HashCodeStrategy strategy)
  {
    int currentHashCode = super.hashCode(locator, strategy);
    
    String theBankingTransactionType = getBankingTransactionType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "bankingTransactionType", theBankingTransactionType), currentHashCode, theBankingTransactionType);
    
    InvestmentSubAccountType theSubaccountFundType = getSubaccountFundType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "subaccountFundType", theSubaccountFundType), currentHashCode, theSubaccountFundType);
    
    Banking401KSourceType theBanking401KSourceType = getBanking401KSourceType();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "banking401KSourceType", theBanking401KSourceType), currentHashCode, theBanking401KSourceType);
    
    return currentHashCode;
  }
  
  public int hashCode()
  {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}
