package com.nibbledebt.integration.model;

/**
 * @author a.salachyonok
 */
public class DiscoverAccountsResponse {

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
