package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
@XmlType(name="TransactionList", namespace="http://schema.intuit.com/platform/fdatafeed/transactionlist/v1", propOrder={"rewardsTransactions", "investmentTransactions", "loanTransactions", "investmentBankingTransactions", "creditCardTransactions", "bankingTransactions"})
@XmlRootElement(name="TransactionList", namespace="http://schema.intuit.com/platform/fdatafeed/transactionlist/v1")
public class TransactionList
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;

  @XmlElement(name="RewardsTransaction", namespace="http://schema.intuit.com/platform/fdatafeed/rewardstransaction/v1")
  protected List<RewardsTransaction> rewardsTransactions;

  @XmlElement(name="InvestmentTransaction", namespace="http://schema.intuit.com/platform/fdatafeed/invtransaction/v1")
  protected List<InvestmentTransaction> investmentTransactions;

  @XmlElement(name="LoanTransaction", namespace="http://schema.intuit.com/platform/fdatafeed/loantransaction/v1")
  protected List<LoanTransaction> loanTransactions;

  @XmlElement(name="InvestmentBankingTransaction", namespace="http://schema.intuit.com/platform/fdatafeed/ibnktransaction/v1")
  protected List<InvestmentBankingTransaction> investmentBankingTransactions;

  @XmlElement(name="CreditCardTransaction", namespace="http://schema.intuit.com/platform/fdatafeed/cctransaction/v1")
  protected List<CreditCardTransaction> creditCardTransactions;

  @XmlElement(name="BankingTransaction", namespace="http://schema.intuit.com/platform/fdatafeed/bnktransaction/v1")
  protected List<BankingTransaction> bankingTransactions;

  @XmlAttribute(name="notRefreshedReason")
  protected NotRefreshedReason notRefreshedReason;

  public List<RewardsTransaction> getRewardsTransactions()
  {
    if (this.rewardsTransactions == null) {
      this.rewardsTransactions = new ArrayList();
    }
    return this.rewardsTransactions;
  }

  public List<InvestmentTransaction> getInvestmentTransactions()
  {
    if (this.investmentTransactions == null) {
      this.investmentTransactions = new ArrayList();
    }
    return this.investmentTransactions;
  }

  public List<LoanTransaction> getLoanTransactions()
  {
    if (this.loanTransactions == null) {
      this.loanTransactions = new ArrayList();
    }
    return this.loanTransactions;
  }

  public List<InvestmentBankingTransaction> getInvestmentBankingTransactions()
  {
    if (this.investmentBankingTransactions == null) {
      this.investmentBankingTransactions = new ArrayList();
    }
    return this.investmentBankingTransactions;
  }

  public List<CreditCardTransaction> getCreditCardTransactions()
  {
    if (this.creditCardTransactions == null) {
      this.creditCardTransactions = new ArrayList();
    }
    return this.creditCardTransactions;
  }

  public List<BankingTransaction> getBankingTransactions()
  {
    if (this.bankingTransactions == null) {
      this.bankingTransactions = new ArrayList();
    }
    return this.bankingTransactions;
  }

  public NotRefreshedReason getNotRefreshedReason()
  {
    return this.notRefreshedReason;
  }

  public void setNotRefreshedReason(NotRefreshedReason value)
  {
    this.notRefreshedReason = value;
  }

  public void setRewardsTransactions(List<RewardsTransaction> rewardsTransactions)
  {
    this.rewardsTransactions = rewardsTransactions;
  }

  public void setInvestmentTransactions(List<InvestmentTransaction> investmentTransactions)
  {
    this.investmentTransactions = investmentTransactions;
  }

  public void setLoanTransactions(List<LoanTransaction> loanTransactions)
  {
    this.loanTransactions = loanTransactions;
  }

  public void setInvestmentBankingTransactions(List<InvestmentBankingTransaction> investmentBankingTransactions)
  {
    this.investmentBankingTransactions = investmentBankingTransactions;
  }

  public void setCreditCardTransactions(List<CreditCardTransaction> creditCardTransactions)
  {
    this.creditCardTransactions = creditCardTransactions;
  }

  public void setBankingTransactions(List<BankingTransaction> bankingTransactions)
  {
    this.bankingTransactions = bankingTransactions;
  }

  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
    if (!(object instanceof TransactionList)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    TransactionList that = (TransactionList)object;

    List lhsRewardsTransactions = (this.rewardsTransactions != null) && (!this.rewardsTransactions.isEmpty()) ? getRewardsTransactions() : null;

    List rhsRewardsTransactions = (that.rewardsTransactions != null) && (!that.rewardsTransactions.isEmpty()) ? that.getRewardsTransactions() : null;
    if (!strategy.equals(LocatorUtils.property(thisLocator, "rewardsTransactions", lhsRewardsTransactions), LocatorUtils.property(thatLocator, "rewardsTransactions", rhsRewardsTransactions), lhsRewardsTransactions, rhsRewardsTransactions)) {
      return false;
    }

    List lhsInvestmentTransactions = (this.investmentTransactions != null) && (!this.investmentTransactions.isEmpty()) ? getInvestmentTransactions() : null;

    List rhsInvestmentTransactions = (that.investmentTransactions != null) && (!that.investmentTransactions.isEmpty()) ? that.getInvestmentTransactions() : null;
    if (!strategy.equals(LocatorUtils.property(thisLocator, "investmentTransactions", lhsInvestmentTransactions), LocatorUtils.property(thatLocator, "investmentTransactions", rhsInvestmentTransactions), lhsInvestmentTransactions, rhsInvestmentTransactions)) {
      return false;
    }

    List lhsLoanTransactions = (this.loanTransactions != null) && (!this.loanTransactions.isEmpty()) ? getLoanTransactions() : null;

    List rhsLoanTransactions = (that.loanTransactions != null) && (!that.loanTransactions.isEmpty()) ? that.getLoanTransactions() : null;
    if (!strategy.equals(LocatorUtils.property(thisLocator, "loanTransactions", lhsLoanTransactions), LocatorUtils.property(thatLocator, "loanTransactions", rhsLoanTransactions), lhsLoanTransactions, rhsLoanTransactions)) {
      return false;
    }

    List lhsInvestmentBankingTransactions = (this.investmentBankingTransactions != null) && (!this.investmentBankingTransactions.isEmpty()) ? getInvestmentBankingTransactions() : null;

    List rhsInvestmentBankingTransactions = (that.investmentBankingTransactions != null) && (!that.investmentBankingTransactions.isEmpty()) ? that.getInvestmentBankingTransactions() : null;
    if (!strategy.equals(LocatorUtils.property(thisLocator, "investmentBankingTransactions", lhsInvestmentBankingTransactions), LocatorUtils.property(thatLocator, "investmentBankingTransactions", rhsInvestmentBankingTransactions), lhsInvestmentBankingTransactions, rhsInvestmentBankingTransactions)) {
      return false;
    }

    List lhsCreditCardTransactions = (this.creditCardTransactions != null) && (!this.creditCardTransactions.isEmpty()) ? getCreditCardTransactions() : null;

    List rhsCreditCardTransactions = (that.creditCardTransactions != null) && (!that.creditCardTransactions.isEmpty()) ? that.getCreditCardTransactions() : null;
    if (!strategy.equals(LocatorUtils.property(thisLocator, "creditCardTransactions", lhsCreditCardTransactions), LocatorUtils.property(thatLocator, "creditCardTransactions", rhsCreditCardTransactions), lhsCreditCardTransactions, rhsCreditCardTransactions)) {
      return false;
    }

    List lhsBankingTransactions = (this.bankingTransactions != null) && (!this.bankingTransactions.isEmpty()) ? getBankingTransactions() : null;

    List rhsBankingTransactions = (that.bankingTransactions != null) && (!that.bankingTransactions.isEmpty()) ? that.getBankingTransactions() : null;
    if (!strategy.equals(LocatorUtils.property(thisLocator, "bankingTransactions", lhsBankingTransactions), LocatorUtils.property(thatLocator, "bankingTransactions", rhsBankingTransactions), lhsBankingTransactions, rhsBankingTransactions)) {
      return false;
    }

    NotRefreshedReason lhsNotRefreshedReason = getNotRefreshedReason();

    NotRefreshedReason rhsNotRefreshedReason = that.getNotRefreshedReason();
    if (!strategy.equals(LocatorUtils.property(thisLocator, "notRefreshedReason", lhsNotRefreshedReason), LocatorUtils.property(thatLocator, "notRefreshedReason", rhsNotRefreshedReason), lhsNotRefreshedReason, rhsNotRefreshedReason)) {
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

    List theRewardsTransactions = (this.rewardsTransactions != null) && (!this.rewardsTransactions.isEmpty()) ? getRewardsTransactions() : null;
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rewardsTransactions", theRewardsTransactions), currentHashCode, theRewardsTransactions);

    List theInvestmentTransactions = (this.investmentTransactions != null) && (!this.investmentTransactions.isEmpty()) ? getInvestmentTransactions() : null;
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "investmentTransactions", theInvestmentTransactions), currentHashCode, theInvestmentTransactions);

    List theLoanTransactions = (this.loanTransactions != null) && (!this.loanTransactions.isEmpty()) ? getLoanTransactions() : null;
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "loanTransactions", theLoanTransactions), currentHashCode, theLoanTransactions);

    List theInvestmentBankingTransactions = (this.investmentBankingTransactions != null) && (!this.investmentBankingTransactions.isEmpty()) ? getInvestmentBankingTransactions() : null;
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "investmentBankingTransactions", theInvestmentBankingTransactions), currentHashCode, theInvestmentBankingTransactions);

    List theCreditCardTransactions = (this.creditCardTransactions != null) && (!this.creditCardTransactions.isEmpty()) ? getCreditCardTransactions() : null;
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "creditCardTransactions", theCreditCardTransactions), currentHashCode, theCreditCardTransactions);

    List theBankingTransactions = (this.bankingTransactions != null) && (!this.bankingTransactions.isEmpty()) ? getBankingTransactions() : null;
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "bankingTransactions", theBankingTransactions), currentHashCode, theBankingTransactions);

    NotRefreshedReason theNotRefreshedReason = getNotRefreshedReason();
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "notRefreshedReason", theNotRefreshedReason), currentHashCode, theNotRefreshedReason);

    return currentHashCode;
  }

  public int hashCode() {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}