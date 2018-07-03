package com.coder.nerdsoft.projectx.presentation.ui.displayproject.di;

import com.coder.nerdsoft.projectx.presentation.di.OnaApiComponent;
import com.coder.nerdsoft.projectx.presentation.ui.displayproject.DisplayProjectActivity;

import dagger.Component;

@DisplayProjectScope
@Component(dependencies = OnaApiComponent.class,modules = DisplayProjectModule.class)
public interface DisplayProjectComponent {
    void inject(DisplayProjectActivity activity);
}
