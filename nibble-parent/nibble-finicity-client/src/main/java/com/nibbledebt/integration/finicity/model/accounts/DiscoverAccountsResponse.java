package com.nibbledebt.integration.finicity.model.accounts;

/**
 * @author a.salachyonok
 */
public class DiscoverAccountsResponse {

    private Accounts accounts;

    private MfaChallenges mfaChallenges;

    private MfaType type;


    public DiscoverAccountsResponse() {
        this.type = MfaType.NON_MFA;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public MfaChallenges getMfaChallenges() {
        return mfaChallenges;
    }

    public void setMfaChallenges(MfaChallenges mfaChallenges) {
        this.mfaChallenges = mfaChallenges;
    }

    public MfaType getType() {
        return type;
    }

    public void setType(MfaType type) {
        this.type = type;
    }
}
