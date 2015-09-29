package com.nibbledebt.integration.finicity.model.accounts;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author a.salachyonok
 */
@JsonRootName("accounts")
public class CustomerAccountMfaRequest extends CustomerAccountsRequest{

    private ChallengesRequest mfaChallenges;

    public ChallengesRequest getMfaChallenges() {
        return mfaChallenges;
    }

    public void setMfaChallenges(ChallengesRequest mfaChallenges) {
        this.mfaChallenges = mfaChallenges;
    }
}
