package com.nibbledebt.nibble.integration.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

/**
 * Created by ralam on 10/7/15.
 */
@JsonRootName("items")
public class Items {
    private List<InstitutionDetail> institutionDetailList;

    public List<InstitutionDetail> getInstitutionDetailList() {
        return institutionDetailList;
    }

    public void setInstitutionDetailList(List<InstitutionDetail> institutionDetailList) {
        this.institutionDetailList = institutionDetailList;
    }
}
