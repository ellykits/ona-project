package com.coder.nerdsoft.projectx.data.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Form implements Serializable {

    private final static long serialVersionUID = 7616675848501628966L;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("formid")
    @Expose
    private Long formid;
    @SerializedName("id_string")
    @Expose
    private String idString;
    @SerializedName("is_merged_dataset")
    @Expose
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
