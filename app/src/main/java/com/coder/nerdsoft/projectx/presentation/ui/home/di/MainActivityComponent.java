package com.coder.nerdsoft.projectx.presentation.ui.home.di;

import com.coder.nerdsoft.projectx.presentation.di.OnaApiComponent;
import com.coder.nerdsoft.projectx.presentation.ui.home.MainActivity;

import dagger.Component;

@MainActivityScope
@Component(dependencies = OnaApiComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
