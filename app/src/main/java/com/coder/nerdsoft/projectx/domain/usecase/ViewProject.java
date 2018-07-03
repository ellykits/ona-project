package com.coder.nerdsoft.projectx.domain.usecase;

import com.coder.nerdsoft.projectx.domain.common.DomainConstants;
import com.coder.nerdsoft.projectx.domain.common.TransformerForSingle;
import com.coder.nerdsoft.projectx.domain.contract.ProjectRepository;
import com.coder.nerdsoft.projectx.domain.entity.ProjectEntity;
import com.fernandocejas.arrow.optional.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import io.reactivex.Single;

public class ViewProject extends BaseUseCase<ProjectEntity> {
    private final ProjectRepository mRepository;

    public ViewProject(@NotNull TransformerForSingle<ProjectEntity> transformer,
                       ProjectRepository repository) {
        super(transformer);
        mRepository = repository;
    }

    @NotNull
    @Override
    protected Single<ProjectEntity> createObservable(@Nullable Optional<Map<String, ?>> optionalData) {


        //Check if any data were passed in Map with the Observable
        if (!optionalData.isPresent()) {
            throw new NullPointerException("ProjectId is required");
        }

        Object projectId = optionalData.get().get(DomainConstants.PROJECT_ID);

        return mRepository.getProjectById((Long) projectId);
    }
}
