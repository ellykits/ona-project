package com.coder.nerdsoft.projectx.presentation.ui.login.di;

import com.coder.nerdsoft.projectx.presentation.di.OnaApiComponent;
import com.coder.nerdsoft.projectx.presentation.ui.login.LoginActivity;

import dagger.Component;

@LoginActivityScope
@Component(dependencies = OnaApiComponent.class, modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
