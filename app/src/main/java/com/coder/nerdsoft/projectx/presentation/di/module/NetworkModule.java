package com.coder.nerdsoft.projectx.presentation.di.module;

import android.content.Context;

import com.coder.nerdsoft.projectx.presentation.di.ApplicationScope;
import com.coder.nerdsoft.projectx.presentation.di.ForApplication;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

@Module(includes = {AppContextModule.class})
public class NetworkModule {

    @ApplicationScope
    @Provides
    public OkHttpClient provideOkHttpClient(Dispatcher dispatcher, Interceptor interceptor,
                                            Cache cache, HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .dispatcher(dispatcher)
                .build();
    }

    @ApplicationScope
    @Provides
    public Dispatcher provideDispatcher() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);
        return dispatcher;
    }

    @ApplicationScope
    @Provides
    public Cache provideCache(@ForApplication Context context) {
        int cacheSize = 10 * 1024 * 1024; //Approx 10MB
        return new Cache(context.getCacheDir(), cacheSize);
    }


    @ApplicationScope
    @Provides
    public HttpLoggingInterceptor providesLoggingInterceptor() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor(
                message -> Timber.tag("ONA").d(message));
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logger;
    }

    @ApplicationScope
    @Provides
    public Interceptor provideInterceptor(){
       return chain -> {
           Request request = chain.request();

           Request.Builder builder = request.newBuilder()
                   .addHeader("Accept", "application/json")
                   .addHeader("Connection", "close");

           request = builder.build();

           return chain.proceed(request);
       };
    }
}
