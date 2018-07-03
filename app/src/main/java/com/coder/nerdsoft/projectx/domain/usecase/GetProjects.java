package com.coder.nerdsoft.projectx.domain.usecase;

import com.coder.nerdsoft.projectx.domain.common.TransformerForSingle;
import com.coder.nerdsoft.projectx.domain.contract.ProjectRepository;
import com.coder.nerdsoft.projectx.domain.entity.ProjectEntity;
import com.fernandocejas.arrow.optional.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

public class GetProjects extends BaseUseCase<List<ProjectEntity>> {
    private final ProjectRepository mRepository;

    public GetProjects(@NotNull TransformerForSingle<List<ProjectEntity>> transformer,
                       ProjectRepository repository) {
        super(transformer);
        mRepository = repository;
    }

    @NotNull
    @Override
    protected Single<List<ProjectEntity>> createObservable(@Nullable Optional<Map<String, ?>>
                                                                           optionalData) {
        return mRepository.getProjects();
    }
}
