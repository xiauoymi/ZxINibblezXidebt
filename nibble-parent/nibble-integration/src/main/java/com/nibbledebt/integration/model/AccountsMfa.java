package com.nibbledebt.integration.model;

import com.nibbledebt.domain.model.account.Accounts;
import com.nibbledebt.domain.model.account.MfaChallenges;

/**
 * @author a.salachyonok
 */
public class AccountsMfa extends Accounts {

    private MfaChallenges mfaChallenges;

    public MfaChallenges getMfaChallenges() {
        return mfaChallenges;
    }

    public void setMfaChallenges(MfaChallenges mfaChallenges) {
        this.mfaChallenges = mfaChallenges;
    }
}
