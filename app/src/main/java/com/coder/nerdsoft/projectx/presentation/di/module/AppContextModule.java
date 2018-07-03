package com.coder.nerdsoft.projectx.presentation.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.coder.nerdsoft.projectx.data.storage.TokenPersistence;
import com.coder.nerdsoft.projectx.presentation.di.ForApplication;
import com.coder.nerdsoft.projectx.presentation.di.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppContextModule {
    private final Context mContext;

    public AppContextModule(Context context) {
        mContext = context;
    }

    @ForApplication
    @ApplicationScope
    @Provides
    public Context getContext() {
        return mContext.getApplicationContext();
    }
    @Provides
    public SharedPreferences provideSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }
    @Provides
    public TokenPersistence provideTokenManager(SharedPreferences sharedPreferences){
        return new TokenPersistence(sharedPreferences);
    }

}
