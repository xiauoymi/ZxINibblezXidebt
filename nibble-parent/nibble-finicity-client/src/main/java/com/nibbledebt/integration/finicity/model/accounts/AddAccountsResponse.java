package com.nibbledebt.integration.finicity.model.accounts;

/**
 * @author a.salachyonok
 */
public class AddAccountsResponse  {

    private Accounts accounts;

    private MfaChallenges mfaChallenges;

    private MfaType mfaType;


    public AddAccountsResponse() {
        this.mfaType = MfaType.NON_MFA;
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

    public MfaType getMfaType() {
        return mfaType;
    }

    public void setMfaType(MfaType mfaType) {
        this.mfaType = mfaType;
    }
}

