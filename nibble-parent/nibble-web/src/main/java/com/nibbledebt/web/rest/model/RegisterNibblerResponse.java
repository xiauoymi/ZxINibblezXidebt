package com.nibbledebt.web.rest.model;

import com.nibbledebt.integration.model.MfaChallenges;

import java.util.List;

/**
 * @author a.salachyonok
 */
public class RegisterNibblerResponse {

    private Boolean hasMfa;

    private List<AccountModel> accounts;

    private String mfaType;

    private MfaChallenges mfaChallenges;

    public Boolean getHasMfa() {
        return hasMfa;
    }

    public void setHasMfa(Boolean hasMfa) {
        this.hasMfa = hasMfa;
    }

    public List<AccountModel> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountModel> accounts) {
        this.accounts = accounts;
    }

    public String getMfaType() {
        return mfaType;
    }

    public void setMfaType(String mfaType) {
        this.mfaType = mfaType;
    }

    public MfaChallenges getMfaChallenges() {
        return mfaChallenges;
    }

    public void setMfaChallenges(MfaChallenges mfaChallenges) {
        this.mfaChallenges = mfaChallenges;
    }
}
