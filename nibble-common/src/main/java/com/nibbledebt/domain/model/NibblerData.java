/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.domain.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.nibbledebt.core.data.model.NibblerDirectoryStatus;
import com.nibbledebt.domain.model.account.Account;
import com.nibbledebt.domain.model.account.QuestionRequest;

/**
 * @author ralam1
 *
 */
public class NibblerData {
	private Long internalUserId;
	private String password;	
	private String firstName;	
	private String lastName;
	private String email;	
	private String phone;	
	private String address1;	
	private String address2;	
	private String city;	
	private String state;	
	private Integer zip;
	private Bank roundupAccountBank;
	private Bank loanAccountBank;
	private String activationCode;
	private String url;
	private boolean suspendedUp90;
	private String status;
	private String resetCode;
	private String referral;
	private boolean loanConnected;
	private boolean fundingConnected;
	private String dateOfBirth;
	private String ssn;
    private String mfaQuestion;
    private String mfaAnswer;
    private String iavToken;
    private List<QuestionRequest> questionRequests;
    private String fundingSourceToken;
    private String loanToken;
    private String accountNumber;
    
    private Double weeklyTargetAmount;
    
    private Double feeAmount;
	
	private Boolean isFirstLogin;
	
	private List<Account> accounts = new ArrayList<>();
	
	private List<String> roles = new ArrayList<>();	 
	
	private boolean contributor;
	
	private Integer invitationCode;
	private List<String> inviteEmails;

	/**
	 * @return the internalUserId
	 */
	public Long getInternalUserId() {
		return internalUserId;
	}


	/**
	 * @param internalUserId the internalUserId to set
	 */
	public void setInternalUserId(Long internalUserId) {
		this.internalUserId = internalUserId;
	}


	/**
	 * @return the isFirstLogin
	 */
	public Boolean getIsFirstLogin() {
		return isFirstLogin;
	}


	/**
	 * @param isFirstLogin the isFirstLogin to set
	 */
	public void setIsFirstLogin(Boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}


	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}


	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}


	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * @return the zip
	 */
	public Integer getZip() {
		return zip;
	}


	/**
	 * @param zip the zip to set
	 */
	public void setZip(Integer zip) {
		this.zip = zip;
	}


	/**
	 * @return the roles
	 */
	public List<String> getRoles() {
		if(roles == null) roles = new ArrayList<>();
		return roles;
	}


	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	/**
	 * @return the roundupAccountBank
	 */
	public Bank getRoundupAccountBank() {
		return roundupAccountBank;
	}


	/**
	 * @param roundupAccountBank the roundupAccountBank to set
	 */
	public void setRoundupAccountBank(Bank roundupAccountBank) {
		this.roundupAccountBank = roundupAccountBank;
	}


	/**
	 * @return the loanAccountBank
	 */
	public Bank getLoanAccountBank() {
		return loanAccountBank;
	}


	/**
	 * @param loanAccountBank the loanAccountBank to set
	 */
	public void setLoanAccountBank(Bank loanAccountBank) {
		this.loanAccountBank = loanAccountBank;
	}


	/**
	 * @return the activationCode
	 */
	public String getActivationCode() {
		return activationCode;
	}


	/**
	 * @param activationCode the activationCode to set
	 */
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}


	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the resetCode
	 */
	public String getResetCode() {
		return resetCode;
	}


	/**
	 * @param resetCode the resetCode to set
	 */
	public void setResetCode(String resetCode) {
		this.resetCode = resetCode;
	}

    public String getMfaQuestion() {
        return mfaQuestion;
    }

    public void setMfaQuestion(String mfaQuestion) {
        this.mfaQuestion = mfaQuestion;
    }

    public String getMfaAnswer() {
        return mfaAnswer;
    }

    public void setMfaAnswer(String mfaAnswer) {
        this.mfaAnswer = mfaAnswer;
    }

    /**
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}


	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}


	/**
	 * @return the contributor
	 */
	public boolean isContributor() {
		return contributor;
	}


	/**
	 * @param contributor the contributor to set
	 */
	public void setContributor(boolean contributor) {
		this.contributor = contributor;
	}


	/**
	 * @return the invitationCode
	 */
	public Integer getInvitationCode() {
		return invitationCode;
	}


	/**
	 * @param invitationCode the invitationCode to set
	 */
	public void setInvitationCode(Integer invitationCode) {
		this.invitationCode = invitationCode;
	}


	/**
	 * @return the inviteEmails
	 */
	public List<String> getInviteEmails() {
		return inviteEmails;
	}


	/**
	 * @param inviteEmails the inviteEmails to set
	 */
	public void setInviteEmails(List<String> inviteEmails) {
		this.inviteEmails = inviteEmails;
	}
	
	public boolean isSuspendedUp90() {
		return suspendedUp90;
	}


	public void setSuspendedUp90(boolean suspendedUp90) {
		this.suspendedUp90 = suspendedUp90;
	}
	
	public boolean isActive(){
		return NibblerDirectoryStatus.ACTIVE.name().equals(status);
	}

	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}


	public String getReferral() {
		return referral;
	}


	public void setReferral(String referral) {
		this.referral = referral;
	}


	public Double getWeeklyTargetAmount() {
		return weeklyTargetAmount;
	}


	public void setWeeklyTargetAmount(Double weeklyTargetAmount) {
		this.weeklyTargetAmount = weeklyTargetAmount;
	}


	public Double getFeeAmount() {
		return feeAmount;
	}


	public void setFeeAmount(Double feeAmount) {
		this.feeAmount = feeAmount;
	}


	public boolean isLoanConnected() {
		return loanConnected;
	}


	public void setLoanConnected(boolean loanConnected) {
		this.loanConnected = loanConnected;
	}


	public boolean isFundingConnected() {
		return fundingConnected;
	}


	public void setFundingConnected(boolean fundingConnected) {
		this.fundingConnected = fundingConnected;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getSsn() {
		return ssn;
	}


	public void setSsn(String ssn) {
		this.ssn = ssn;
	}


	public String getIavToken() {
		return iavToken;
	}


	public void setIavToken(String iavToken) {
		this.iavToken = iavToken;
	}


	public List<QuestionRequest> getQuestionRequests() {
		return questionRequests;
	}


	public void setQuestionRequests(List<QuestionRequest> questionRequests) {
		this.questionRequests = questionRequests;
	}


	public String getFundingSourceToken() {
		return fundingSourceToken;
	}


	public void setFundingSourceToken(String fundingSourceToken) {
		this.fundingSourceToken = fundingSourceToken;
	}


	public String getLoanToken() {
		return loanToken;
	}


	public void setLoanToken(String loanToken) {
		this.loanToken = loanToken;
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	
	
	
	
	
}
