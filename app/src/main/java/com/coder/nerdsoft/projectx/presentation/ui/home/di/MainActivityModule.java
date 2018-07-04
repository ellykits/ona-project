package com.coder.nerdsoft.projectx.presentation.ui.home.di;

import android.arch.lifecycle.ViewModelProvider;

import com.coder.nerdsoft.projectx.domain.common.TransformerForSingle;
import com.coder.nerdsoft.projectx.domain.contract.ProjectRepository;
import com.coder.nerdsoft.projectx.domain.contract.SessionRepository;
import com.coder.nerdsoft.projectx.domain.entity.ProjectEntity;
import com.coder.nerdsoft.projectx.domain.usecase.GetProjects;
import com.coder.nerdsoft.projectx.domain.usecase.InvalidateToken;
import com.coder.nerdsoft.projectx.domain.usecase.RefreshToken;
import com.coder.nerdsoft.projectx.presentation.common.SingleAsyncTransformer;
import com.coder.nerdsoft.projectx.presentation.common.ViewModelUtil;
import com.coder.nerdsoft.projectx.presentation.ui.home.MainActivity;
import com.coder.nerdsoft.projectx.presentation.ui.home.MainActivityViewModel;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private final MainActivity mMainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @MainActivityScope
    @Provides
    public GetProjects provideProjects( TransformerForSingle<List<ProjectEntity>> transformer,
                                        ProjectRepository repository){
        return new GetProjects(transformer,repository);
    }

    @MainActivityScope
    @Provides
    public RefreshToken provideRefreshToken(TransformerForSingle<String> transformer, SessionRepository
                                            repository){
        return new RefreshToken(transformer,repository);
    }

    @MainActivityScope
    @Provides
    public InvalidateToken provideInvalidateToken(SessionRepository repository){
        return new InvalidateToken(repository);
    }

    @MainActivityScope
    @Provides
    public TransformerForSingle<List<ProjectEntity>> provideTransformer(){
        return new SingleAsyncTransformer<>();
    }

    @MainActivityScope
    @Provides
    public TransformerForSingle<String> provideStringTransformer(){
        return new SingleAsyncTransformer<>();
    }

    @MainActivityScope
    @Provides
    public ViewModelProvider.Factory provideViewModelFactory(ViewModelUtil viewModelUtil,
                                                             MainActivityViewModel viewModel){
        return viewModelUtil.createFor(viewModel);
    }

}
