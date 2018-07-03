package com.coder.nerdsoft.projectx.presentation.ui.home;

import com.coder.nerdsoft.projectx.domain.entity.ProjectEntity;

import java.util.List;

public class MainActivityViewState {
    private boolean isLoading;
    private List<ProjectEntity> mProjectEntityList;

    public MainActivityViewState(boolean isLoading, List<ProjectEntity> projectEntityList) {
        this.isLoading = isLoading;
        mProjectEntityList = projectEntityList;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void setProjectEntityList(List<ProjectEntity> projectEntityList) {
        mProjectEntityList = projectEntityList;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public List<ProjectEntity> getProjectEntityList() {
        return mProjectEntityList;
    }
}
