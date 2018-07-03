package com.coder.nerdsoft.projectx.presentation.ui.login.di;

import android.arch.lifecycle.ViewModelProvider;

import com.coder.nerdsoft.projectx.data.repository.SessionRepositoryImpl;
import com.coder.nerdsoft.projectx.domain.usecase.AuthorizeAccess;
import com.coder.nerdsoft.projectx.domain.usecase.LoginUseCase;
import com.coder.nerdsoft.projectx.presentation.common.SingleAsyncTransformer;
import com.coder.nerdsoft.projectx.presentation.common.ViewModelUtil;
import com.coder.nerdsoft.projectx.presentation.ui.login.LoginActivity;
import com.coder.nerdsoft.projectx.presentation.ui.login.LoginViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {
    private final LoginActivity mLoginActivity;

    public LoginModule(LoginActivity loginActivity) {
        mLoginActivity = loginActivity;
    }
    @LoginActivityScope
    @Provides
    public LoginUseCase provideLoginUseCase(SingleAsyncTransformer<String> transformer,
                                            SessionRepositoryImpl sessionRepository){
        return new LoginUseCase(transformer,sessionRepository);
    }
    @LoginActivityScope
    @Provides
    public AuthorizeAccess provideAuthorizationUseCase(SingleAsyncTransformer<String> transformer,
                                                       SessionRepositoryImpl sessionRepository){
        return new AuthorizeAccess(transformer,sessionRepository);
    }


    @LoginActivityScope
    @Provides
    public SingleAsyncTransformer<String> provideTransformer(){
        return new SingleAsyncTransformer<>();
    }

    @LoginActivityScope
    @Provides
    public ViewModelProvider.Factory provideViewModelFactory(ViewModelUtil viewModelUtil,
                                                             LoginViewModel viewModel){
        return viewModelUtil.createFor(viewModel);
    }
}
