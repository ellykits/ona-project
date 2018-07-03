package com.coder.nerdsoft.projectx.presentation.util;

import android.support.v7.app.AppCompatActivity;

import timber.log.Timber;

public  class BaseActivity<T> extends AppCompatActivity {
    protected  void handleViewState(T viewState){
        Timber.tag("ONA").i(viewState.toString());
    }

    protected void handleErrors(Throwable throwable) {
        Timber.tag("ONA").e(throwable);
    }
}
