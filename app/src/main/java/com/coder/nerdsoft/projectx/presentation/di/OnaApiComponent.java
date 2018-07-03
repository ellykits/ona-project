package com.coder.nerdsoft.projectx.presentation.di;


import com.coder.nerdsoft.projectx.data.remote.RestApiManager;
import com.coder.nerdsoft.projectx.data.storage.TokenPersistence;
import com.coder.nerdsoft.projectx.domain.contract.ProjectRepository;
import com.coder.nerdsoft.projectx.domain.contract.SessionRepository;
import com.coder.nerdsoft.projectx.presentation.di.module.OnaApiServiceModule;
import com.coder.nerdsoft.projectx.data.remote.RetrofitBuilder;
import com.coder.nerdsoft.projectx.presentation.di.module.ViewModelModule;

import dagger.Component;

@ApplicationScope
@Component(modules = {OnaApiServiceModule.class, ViewModelModule.class})
public interface OnaApiComponent {
    RetrofitBuilder getRetrofitBuilder();

    TokenPersistence getTokenManager();

    RestApiManager getApiManager();

    ProjectRepository getProjectRepository();

    SessionRepository getSessionRepository();
}
