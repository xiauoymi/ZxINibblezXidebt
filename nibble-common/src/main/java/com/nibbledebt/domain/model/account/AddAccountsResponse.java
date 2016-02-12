package com.nibbledebt.domain.model.account;

import java.util.Map;
import java.util.Set;

/**
 * @author a.salachyonok
 */
public class AddAccountsResponse {
    private Map<String, Set<String>> questionAnswer;
    private Map<String, String> session;

    private MfaType mfaType;

    private Accounts accounts;

	/**
	 * @return the questionAnswer
	 */
	public Map<String, Set<String>> getQuestionAnswer() {
		return questionAnswer;
	}

	/**
	 * @param questionAnswer the questionAnswer to set
	 */
	public void setQuestionAnswer(Map<String, Set<String>> questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	/**
	 * @return the session
	 */
	public Map<String, String> getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(Map<String, String> session) {
		this.session = session;
	}

	/**
	 * @return the mfaType
	 */
	public MfaType getMfaType() {
		return mfaType;
	}

	/**
	 * @param mfaType the mfaType to set
	 */
	public void setMfaType(MfaType mfaType) {
		this.mfaType = mfaType;
	}

	/**
	 * @return the accounts
	 */
	public Accounts getAccounts() {
		if(accounts == null) accounts = new Accounts();
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

}
