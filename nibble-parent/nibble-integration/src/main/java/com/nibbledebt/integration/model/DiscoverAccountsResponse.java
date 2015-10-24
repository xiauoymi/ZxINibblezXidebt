package com.nibbledebt.integration.model;

import com.nibbledebt.domain.model.account.Accounts;
import com.nibbledebt.domain.model.account.MfaChallenges;
import com.nibbledebt.domain.model.account.MfaType;

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
