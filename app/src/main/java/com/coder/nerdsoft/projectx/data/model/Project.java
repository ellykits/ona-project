package com.coder.nerdsoft.projectx.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class Project implements Serializable {

    private final static long serialVersionUID = 7635247684360556305L;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("projectid")
    @Expose
    private Long projectid;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("starred")
    @Expose
    private Boolean starred;
    @SerializedName("users")
    @Expose
    private List<User> users = null;
    @SerializedName("forms")
    @Expose
    private List<Form> forms = null;
    @SerializedName("public")
    @Expose
    private Boolean isPublic;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("num_datasets")
    @Expose
    private Long numDatasets;
    @SerializedName("last_submission_date")
    @Expose
    private String lastSubmissionDate;
    @SerializedName("teams")
    @Expose
    private List<Team> teams = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getProjectid() {
        return projectid;
    }

    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Boolean getStarred() {
        return starred;
    }

    public void setStarred(Boolean starred) {
        this.starred = starred;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Long getNumDatasets() {
        return numDatasets;
    }

    public void setNumDatasets(Long numDatasets) {
        this.numDatasets = numDatasets;
    }

    public String getLastSubmissionDate() {
        return lastSubmissionDate;
    }

    public void setLastSubmissionDate(String lastSubmissionDate) {
        this.lastSubmissionDate = lastSubmissionDate;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }
}
