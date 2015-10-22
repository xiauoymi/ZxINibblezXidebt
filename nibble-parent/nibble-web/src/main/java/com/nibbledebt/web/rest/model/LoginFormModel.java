package com.nibbledebt.web.rest.model;

import com.nibbledebt.integration.model.LoginField;

import java.util.List;

/**
 * @author a.salachyonok
 */
public class LoginFormModel {

    private String institutionId;

    private List<LoginField> loginField;

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public List<LoginField> getLoginField() {
        return loginField;
    }

    public void setLoginField(List<LoginField> loginField) {
        this.loginField = loginField;
    }
}
