package com.nibbledebt.integration.finicity.model.accounts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author a.salachyonok
 */
@JsonRootName("account")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Account {

    private String id;

    private String number;

    private String name;

    private String balance;

    private String type;

    private String status;

    private String aggregationStatusCode;

    private String customerId;

    private String institutionId;

    private String balanceDate;

    private String aggregationSuccessDate;

    private String createdDate;

    private String lastUpdatedDate;

    private AccountDetail detail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAggregationStatusCode() {
        return aggregationStatusCode;
    }

    public void setAggregationStatusCode(String aggregationStatusCode) {
        this.aggregationStatusCode = aggregationStatusCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(String balanceDate) {
        this.balanceDate = balanceDate;
    }

    public String getAggregationSuccessDate() {
        return aggregationSuccessDate;
    }

    public void setAggregationSuccessDate(String aggregationSuccessDate) {
        this.aggregationSuccessDate = aggregationSuccessDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public AccountDetail getDetail() {
        return detail;
    }

    public void setDetail(AccountDetail detail) {
        this.detail = detail;
    }
}
