package com.coder.nerdsoft.projectx.domain.contract;

import com.coder.nerdsoft.projectx.domain.entity.ProjectEntity;
import com.fernandocejas.arrow.optional.Optional;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface ProjectRepository {

    Single<List<ProjectEntity>>getProjects();
    Single<ProjectEntity>getProjectById(long id);
}
