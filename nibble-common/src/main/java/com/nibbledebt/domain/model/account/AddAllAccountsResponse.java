package com.nibbledebt.domain.model.account;

import java.util.Map;
import java.util.Set;

/**
 * @author a.salachyonok
 */
public class AddAllAccountsResponse {

    private Map<String, Set<String>> roundupBankQuestionAnswer;
    private Map<String, String> roundupBankSession;
    

    private Map<String, Set<String>> loanBankQuestionAnswer;
    private Map<String, String> loanBankSession;

    private MfaType roundupMfaType;
    private MfaType loanMfaType;

    private Accounts accounts;

    /**
	 * @return the roundupBankQuestionAnswer
	 */
	public Map<String, Set<String>> getRoundupBankQuestionAnswer() {
		return roundupBankQuestionAnswer;
	}

	/**
	 * @param roundupBankQuestionAnswer the roundupBankQuestionAnswer to set
	 */
	public void setRoundupBankQuestionAnswer(Map<String, Set<String>> roundupBankQuestionAnswer) {
		this.roundupBankQuestionAnswer = roundupBankQuestionAnswer;
	}

	/**
	 * @return the roundupBankSession
	 */
	public Map<String, String> getRoundupBankSession() {
		return roundupBankSession;
	}

	/**
	 * @param roundupBankSession the roundupBankSession to set
	 */
	public void setRoundupBankSession(Map<String, String> roundupBankSession) {
		this.roundupBankSession = roundupBankSession;
	}

	/**
	 * @return the loanBankQuestionAnswer
	 */
	public Map<String, Set<String>> getLoanBankQuestionAnswer() {
		return loanBankQuestionAnswer;
	}

	/**
	 * @param loanBankQuestionAnswer the loanBankQuestionAnswer to set
	 */
	public void setLoanBankQuestionAnswer(Map<String, Set<String>> loanBankQuestionAnswer) {
		this.loanBankQuestionAnswer = loanBankQuestionAnswer;
	}

	/**
	 * @return the loanBankSession
	 */
	public Map<String, String> getLoanBankSession() {
		return loanBankSession;
	}

	/**
	 * @param loanBankSession the loanBankSession to set
	 */
	public void setLoanBankSession(Map<String, String> loanBankSession) {
		this.loanBankSession = loanBankSession;
	}

	/**
	 * @return the roundupMfaType
	 */
	public MfaType getRoundupMfaType() {
		return roundupMfaType;
	}

	/**
	 * @param roundupMfaType the roundupMfaType to set
	 */
	public void setRoundupMfaType(MfaType roundupMfaType) {
		this.roundupMfaType = roundupMfaType;
	}

	/**
	 * @return the loanMfaType
	 */
	public MfaType getLoanMfaType() {
		return loanMfaType;
	}

	/**
	 * @param loanMfaType the loanMfaType to set
	 */
	public void setLoanMfaType(MfaType loanMfaType) {
		this.loanMfaType = loanMfaType;
	}

	/**
	 * @return the accounts
	 */
	public Accounts getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

   
}
