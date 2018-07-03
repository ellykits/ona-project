package com.coder.nerdsoft.projectx.data.remote;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class RetrofitBuilder {

    private Retrofit mRetrofit;

    @Inject
    public RetrofitBuilder(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    public <T> T createService(Class<T> service) {
        return mRetrofit.create(service);
    }

    public final Retrofit getRetrofit(){
        return mRetrofit;
    }

}

