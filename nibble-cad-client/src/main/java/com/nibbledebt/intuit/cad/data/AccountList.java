package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElements;
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
@XmlType(name="AccountList", namespace="http://schema.intuit.com/platform/fdatafeed/accountlist/v1", propOrder={"bankingAccountsAndCreditAccountsAndLoanAccounts"})
@XmlRootElement(name="AccountList", namespace="http://schema.intuit.com/platform/fdatafeed/accountlist/v1")
public class AccountList
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  @XmlElements({@javax.xml.bind.annotation.XmlElement(name="BankingAccount", namespace="http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1", type=BankingAccount.class), @javax.xml.bind.annotation.XmlElement(name="CreditAccount", namespace="http://schema.intuit.com/platform/fdatafeed/creditaccount/v1", type=CreditAccount.class), @javax.xml.bind.annotation.XmlElement(name="LoanAccount", namespace="http://schema.intuit.com/platform/fdatafeed/loanaccount/v1", type=LoanAccount.class), @javax.xml.bind.annotation.XmlElement(name="InvestmentAccount", namespace="http://schema.intuit.com/platform/fdatafeed/investmentaccount/v1", type=InvestmentAccount.class), @javax.xml.bind.annotation.XmlElement(name="RewardsAccount", namespace="http://schema.intuit.com/platform/fdatafeed/rewardsaccount/v1", type=RewardsAccount.class), @javax.xml.bind.annotation.XmlElement(name="OtherAccount", namespace="http://schema.intuit.com/platform/fdatafeed/otheraccount/v1", type=OtherAccount.class)})
  protected List<Account> bankingAccountsAndCreditAccountsAndLoanAccounts;
  
  public List<Account> getBankingAccountsAndCreditAccountsAndLoanAccounts()
  {
    if (this.bankingAccountsAndCreditAccountsAndLoanAccounts == null) {
      this.bankingAccountsAndCreditAccountsAndLoanAccounts = new ArrayList();
    }
    return this.bankingAccountsAndCreditAccountsAndLoanAccounts;
  }
  
  public void setBankingAccountsAndCreditAccountsAndLoanAccounts(List<Account> bankingAccountsAndCreditAccountsAndLoanAccounts)
  {
    this.bankingAccountsAndCreditAccountsAndLoanAccounts = bankingAccountsAndCreditAccountsAndLoanAccounts;
  }
  
  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy)
  {
    if (!(object instanceof AccountList)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    AccountList that = (AccountList)object;
    
    List<Account> lhsBankingAccountsAndCreditAccountsAndLoanAccounts = (this.bankingAccountsAndCreditAccountsAndLoanAccounts != null) && (!this.bankingAccountsAndCreditAccountsAndLoanAccounts.isEmpty()) ? getBankingAccountsAndCreditAccountsAndLoanAccounts() : null;
    
    List<Account> rhsBankingAccountsAndCreditAccountsAndLoanAccounts = (that.bankingAccountsAndCreditAccountsAndLoanAccounts != null) && (!that.bankingAccountsAndCreditAccountsAndLoanAccounts.isEmpty()) ? that.getBankingAccountsAndCreditAccountsAndLoanAccounts() : null;
    if (!strategy.equals(LocatorUtils.property(thisLocator, "bankingAccountsAndCreditAccountsAndLoanAccounts", lhsBankingAccountsAndCreditAccountsAndLoanAccounts), LocatorUtils.property(thatLocator, "bankingAccountsAndCreditAccountsAndLoanAccounts", rhsBankingAccountsAndCreditAccountsAndLoanAccounts), lhsBankingAccountsAndCreditAccountsAndLoanAccounts, rhsBankingAccountsAndCreditAccountsAndLoanAccounts)) {
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
    int currentHashCode = 1;
    
    List<Account> theBankingAccountsAndCreditAccountsAndLoanAccounts = (this.bankingAccountsAndCreditAccountsAndLoanAccounts != null) && (!this.bankingAccountsAndCreditAccountsAndLoanAccounts.isEmpty()) ? getBankingAccountsAndCreditAccountsAndLoanAccounts() : null;
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "bankingAccountsAndCreditAccountsAndLoanAccounts", theBankingAccountsAndCreditAccountsAndLoanAccounts), currentHashCode, theBankingAccountsAndCreditAccountsAndLoanAccounts);
    
    return currentHashCode;
  }
  
  public int hashCode()
  {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}
