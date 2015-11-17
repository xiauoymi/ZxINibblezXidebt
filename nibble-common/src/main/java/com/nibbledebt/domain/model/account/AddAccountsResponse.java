package com.nibbledebt.domain.model.account;

/**
 * @author a.salachyonok
 */
public class AddAccountsResponse {
    private MfaChallenges mfaChallenges;

    private MfaType mfaType;

    private Accounts accounts;

    public MfaChallenges getMfaChallenges() {
        return mfaChallenges;
    }

    public void setMfaChallenges(MfaChallenges mfaChallenges) {
        this.mfaChallenges = mfaChallenges;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public MfaType getMfaType() {
        return mfaType;
    }

    public void setMfaType(MfaType mfaType) {
        this.mfaType = mfaType;
    }
}
