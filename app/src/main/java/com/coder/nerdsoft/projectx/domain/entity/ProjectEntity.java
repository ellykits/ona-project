package com.coder.nerdsoft.projectx.domain.entity;


import java.util.Date;
import java.util.List;


public class ProjectEntity {

    private String url;
    private Long projectId;
    private String owner;
    private String createdBy;
    private MetadataEntity metadataEntity;
    private Boolean starred;
    private List<UserEntity> userEntities = null;
    private List<FormEntity> formEntities = null;
    private Boolean isPublic;
    private List<String> tags = null;
    private Long numDatasets;
    private Date lastSubmissionDate;
    private List<TeamEntity> teamEntities = null;
    private String name;
    private Date dateCreated;
    private Date dateModified;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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

    public MetadataEntity getMetadataEntity() {
        return metadataEntity;
    }

    public void setMetadataEntity(MetadataEntity metadataEntity) {
        this.metadataEntity = metadataEntity;
    }

    public Boolean getStarred() {
        return starred;
    }

    public void setStarred(Boolean starred) {
        this.starred = starred;
    }

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public List<FormEntity> getFormEntities() {
        return formEntities;
    }

    public void setFormEntities(List<FormEntity> formEntities) {
        this.formEntities = formEntities;
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

    public Date getLastSubmissionDate() {
        return lastSubmissionDate;
    }

    public void setLastSubmissionDate(Date lastSubmissionDate) {
        this.lastSubmissionDate = lastSubmissionDate;
    }

    public List<TeamEntity> getTeamEntities() {
        return teamEntities;
    }

    public void setTeamEntities(List<TeamEntity> teamEntities) {
        this.teamEntities = teamEntities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }
}
