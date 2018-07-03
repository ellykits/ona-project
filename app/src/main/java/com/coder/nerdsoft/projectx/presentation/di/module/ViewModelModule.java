package com.coder.nerdsoft.projectx.presentation.di.module;

import com.coder.nerdsoft.projectx.presentation.common.ViewModelUtil;
import com.coder.nerdsoft.projectx.presentation.di.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {
    @ApplicationScope
    @Provides
    public ViewModelUtil provideViewModelUtil(){
        return new ViewModelUtil();
    }
}
