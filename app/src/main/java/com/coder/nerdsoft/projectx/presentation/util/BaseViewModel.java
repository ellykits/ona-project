package com.coder.nerdsoft.projectx.presentation.util;

import android.arch.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    protected final void addDisposable(@NotNull Disposable disposable) {
        this.mCompositeDisposable.add(disposable);
    }

    private void clearDisposables() {
        this.mCompositeDisposable.clear();
    }

    protected void onCleared() {
        clearDisposables();
    }
}
