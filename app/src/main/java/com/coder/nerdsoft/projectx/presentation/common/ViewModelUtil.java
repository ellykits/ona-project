package com.coder.nerdsoft.projectx.presentation.common;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 Creates a one off view model factory for the given view model instance.
 */
public class ViewModelUtil {

    @Inject
    public ViewModelUtil() {}

    public <T extends ViewModel> ViewModelProvider.Factory createFor(@NonNull T viewModel) {
        return new ViewModelProvider.Factory() {

            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if (modelClass.isAssignableFrom(viewModel.getClass())) {
                    return (T) viewModel;
                }
                throw new IllegalArgumentException("unexpected viewModel class " + modelClass);
            }
        };
    }

}
