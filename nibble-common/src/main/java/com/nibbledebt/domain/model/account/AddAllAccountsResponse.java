package com.nibbledebt.domain.model.account;

/**
 * @author a.salachyonok
 */
public class AddAllAccountsResponse {
    private MfaChallenges roundupBankMfaChallenges;
    private MfaChallenges loanBankMfaChallenges;

    private MfaType roundupMfaType;
    private MfaType loanMfaType;

    private Accounts accounts;

	/**
	 * @return the roundupBankMfaChallenges
	 */
	public MfaChallenges getRoundupBankMfaChallenges() {
		return roundupBankMfaChallenges;
	}

	/**
	 * @param roundupBankMfaChallenges the roundupBankMfaChallenges to set
	 */
	public void setRoundupBankMfaChallenges(MfaChallenges roundupBankMfaChallenges) {
		this.roundupBankMfaChallenges = roundupBankMfaChallenges;
	}

	/**
	 * @return the loanBankMfaChallenges
	 */
	public MfaChallenges getLoanBankMfaChallenges() {
		return loanBankMfaChallenges;
	}

	/**
	 * @param loanBankMfaChallenges the loanBankMfaChallenges to set
	 */
	public void setLoanBankMfaChallenges(MfaChallenges loanBankMfaChallenges) {
		this.loanBankMfaChallenges = loanBankMfaChallenges;
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
