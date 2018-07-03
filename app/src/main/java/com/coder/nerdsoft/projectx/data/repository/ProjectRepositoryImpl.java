package com.coder.nerdsoft.projectx.data.repository;

import com.coder.nerdsoft.projectx.data.mapper.ProjectMapper;
import com.coder.nerdsoft.projectx.data.remote.RestApiManager;
import com.coder.nerdsoft.projectx.domain.contract.ProjectRepository;
import com.coder.nerdsoft.projectx.domain.entity.ProjectEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class ProjectRepositoryImpl implements ProjectRepository {

    private final RestApiManager mApiManager;

    @Inject
    public ProjectRepositoryImpl(RestApiManager apiManager) {
        mApiManager = apiManager;
    }


    @Override
    public Single<List<ProjectEntity>> getProjects() {
        return mApiManager.getProjects()
                .flatMap(projectList -> Observable.fromIterable(projectList)
                        .map(ProjectMapper.INSTANCE::projectModelToEntity)
                        .toList());

    }

    @Override
    public Single<ProjectEntity> getProjectById(long id) {
        return mApiManager.getProject(id).map(ProjectMapper.INSTANCE::projectModelToEntity);
    }
}