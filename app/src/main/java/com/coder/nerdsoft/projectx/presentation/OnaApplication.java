package com.coder.nerdsoft.projectx.presentation;

import android.app.Activity;
import android.app.Application;

import com.coder.nerdsoft.projectx.presentation.di.DaggerOnaApiComponent;
import com.coder.nerdsoft.projectx.presentation.di.OnaApiComponent;
import com.coder.nerdsoft.projectx.presentation.di.module.AppContextModule;

import timber.log.Timber;

public class OnaApplication extends Application {
    private OnaApiComponent mOnaApiComponent;

    public static OnaApplication get(Activity activity) {
        return (OnaApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        //init application component
        mOnaApiComponent = DaggerOnaApiComponent.builder()
                .appContextModule(new AppContextModule(this)).build();

    }

    public OnaApiComponent getOnaApiComponent() {
        return mOnaApiComponent;

    }
}
