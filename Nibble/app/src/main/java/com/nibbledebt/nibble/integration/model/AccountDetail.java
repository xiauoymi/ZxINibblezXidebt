package com.nibbledebt.nibble.integration.model;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author a.salachyonok
 */
@JsonRootName("detail")
public class AccountDetail {

    /* Account Details: Checking / Savings / CD / Money Market */
    private String availableBalanceAmount;
    private String interestYtdAmount;
    private String periodInterestRate;
    private String periodInterestAmount;

    /* Account Details: Credit Card / Line of Credit */
    private String creditMaxAmount;
    private String creditAvailableAmount;
    private String paymentMinAmount;
    private String paymentDueDate;
    private String lastPaymentAmount;
    private String lastPaymentDate;
    private String interestRate;
    private String cashAdvanceInterestRate;

    /* Account Details: Mortgage / Loan */
    /* field private String interestRate; */
    private String nextPaymentDate;
    private String nextPayment;
    private String escrowBalance;
    private String payoffAmount;
    private String principalBalance;
    private String ytdPrincipalPaid;
    private String ytdInterestPaid;
    /* field private String lastPaymentAmount; */
    private String lastPaymentReceiveDate;

    public String getAvailableBalanceAmount() {
        return availableBalanceAmount;
    }

    public void setAvailableBalanceAmount(String availableBalanceAmount) {
        this.availableBalanceAmount = availableBalanceAmount;
    }

    public String getInterestYtdAmount() {
        return interestYtdAmount;
    }

    public void setInterestYtdAmount(String interestYtdAmount) {
        this.interestYtdAmount = interestYtdAmount;
    }

    public String getPeriodInterestRate() {
        return periodInterestRate;
    }

    public void setPeriodInterestRate(String periodInterestRate) {
        this.periodInterestRate = periodInterestRate;
    }

    public String getPeriodInterestAmount() {
        return periodInterestAmount;
    }

    public void setPeriodInterestAmount(String periodInterestAmount) {
        this.periodInterestAmount = periodInterestAmount;
    }

    public String getCreditMaxAmount() {
        return creditMaxAmount;
    }

    public void setCreditMaxAmount(String creditMaxAmount) {
        this.creditMaxAmount = creditMaxAmount;
    }

    public String getCreditAvailableAmount() {
        return creditAvailableAmount;
    }

    public void setCreditAvailableAmount(String creditAvailableAmount) {
        this.creditAvailableAmount = creditAvailableAmount;
    }

    public String getPaymentMinAmount() {
        return paymentMinAmount;
    }

    public void setPaymentMinAmount(String paymentMinAmount) {
        this.paymentMinAmount = paymentMinAmount;
    }

    public String getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(String paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public String getLastPaymentAmount() {
        return lastPaymentAmount;
    }

    public void setLastPaymentAmount(String lastPaymentAmount) {
        this.lastPaymentAmount = lastPaymentAmount;
    }

    public String getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getCashAdvanceInterestRate() {
        return cashAdvanceInterestRate;
    }

    public void setCashAdvanceInterestRate(String cashAdvanceInterestRate) {
        this.cashAdvanceInterestRate = cashAdvanceInterestRate;
    }

    public String getNextPaymentDate() {
        return nextPaymentDate;
    }

    public void setNextPaymentDate(String nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    public String getNextPayment() {
        return nextPayment;
    }

    public void setNextPayment(String nextPayment) {
        this.nextPayment = nextPayment;
    }

    public String getEscrowBalance() {
        return escrowBalance;
    }

    public void setEscrowBalance(String escrowBalance) {
        this.escrowBalance = escrowBalance;
    }

    public String getPayoffAmount() {
        return payoffAmount;
    }

    public void setPayoffAmount(String payoffAmount) {
        this.payoffAmount = payoffAmount;
    }

    public String getPrincipalBalance() {
        return principalBalance;
    }

    public void setPrincipalBalance(String principalBalance) {
        this.principalBalance = principalBalance;
    }

    public String getYtdPrincipalPaid() {
        return ytdPrincipalPaid;
    }

    public void setYtdPrincipalPaid(String ytdPrincipalPaid) {
        this.ytdPrincipalPaid = ytdPrincipalPaid;
    }

    public String getYtdInterestPaid() {
        return ytdInterestPaid;
    }

    public void setYtdInterestPaid(String ytdInterestPaid) {
        this.ytdInterestPaid = ytdInterestPaid;
    }

    public String getLastPaymentReceiveDate() {
        return lastPaymentReceiveDate;
    }

    public void setLastPaymentReceiveDate(String lastPaymentReceiveDate) {
        this.lastPaymentReceiveDate = lastPaymentReceiveDate;
    }
}
