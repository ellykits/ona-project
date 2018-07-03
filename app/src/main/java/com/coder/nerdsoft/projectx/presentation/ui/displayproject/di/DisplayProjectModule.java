package com.coder.nerdsoft.projectx.presentation.ui.displayproject.di;

import android.arch.lifecycle.ViewModelProvider;

import com.coder.nerdsoft.projectx.domain.common.TransformerForSingle;
import com.coder.nerdsoft.projectx.domain.contract.ProjectRepository;
import com.coder.nerdsoft.projectx.domain.entity.ProjectEntity;
import com.coder.nerdsoft.projectx.domain.usecase.ViewProject;
import com.coder.nerdsoft.projectx.presentation.common.SingleAsyncTransformer;
import com.coder.nerdsoft.projectx.presentation.common.ViewModelUtil;
import com.coder.nerdsoft.projectx.presentation.ui.displayproject.DisplayProjectActivity;
import com.coder.nerdsoft.projectx.presentation.ui.displayproject.DisplayProjectViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class DisplayProjectModule {
    private final DisplayProjectActivity mDisplayProjectActivity;

    public DisplayProjectModule(DisplayProjectActivity displayProjectActivity) {
        mDisplayProjectActivity = displayProjectActivity;
    }

    @DisplayProjectScope
    @Provides
    public ViewProject provideViewProject(TransformerForSingle<ProjectEntity> transformer,
                                          ProjectRepository repository) {
        return new ViewProject(transformer, repository);
    }


    @DisplayProjectScope
    @Provides
    public TransformerForSingle<ProjectEntity> provideTransformer() {
        return new SingleAsyncTransformer<>();
    }

    @DisplayProjectScope
    @Provides
    public ViewModelProvider.Factory provideViewModelFactory(ViewModelUtil viewModelUtil,
                                                             DisplayProjectViewModel viewModel) {
        return viewModelUtil.createFor(viewModel);
    }

}
