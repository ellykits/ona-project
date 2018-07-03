package com.coder.nerdsoft.projectx.domain.entity;

public class FormEntity {

    private String name;
    private Long formid;
    private String idString;
    private Boolean isMergedDataset;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFormid() {
        return formid;
    }

    public void setFormid(Long formid) {
        this.formid = formid;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public Boolean getMergedDataset() {
        return isMergedDataset;
    }

    public void setMergedDataset(Boolean mergedDataset) {
        isMergedDataset = mergedDataset;
    }
}
