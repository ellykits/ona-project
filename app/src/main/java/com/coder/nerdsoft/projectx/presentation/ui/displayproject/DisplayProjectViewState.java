package com.coder.nerdsoft.projectx.presentation.ui.displayproject;

import com.coder.nerdsoft.projectx.domain.entity.ProjectEntity;

class DisplayProjectViewState {
    private ProjectEntity mProjectEntity;

    public DisplayProjectViewState(ProjectEntity projectEntity) {
        mProjectEntity = projectEntity;
    }

    public ProjectEntity getProjectEntity() {
        return mProjectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        mProjectEntity = projectEntity;
    }
}
