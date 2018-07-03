package com.coder.nerdsoft.projectx.presentation.di.module;

import com.coder.nerdsoft.projectx.data.remote.ConfigApi;
import com.coder.nerdsoft.projectx.data.remote.RestApiManager;
import com.coder.nerdsoft.projectx.data.remote.RetrofitBuilder;
import com.coder.nerdsoft.projectx.data.repository.ProjectRepositoryImpl;
import com.coder.nerdsoft.projectx.data.repository.SessionRepositoryImpl;
import com.coder.nerdsoft.projectx.data.storage.TokenPersistence;
import com.coder.nerdsoft.projectx.domain.contract.ProjectRepository;
import com.coder.nerdsoft.projectx.domain.contract.SessionRepository;
import com.coder.nerdsoft.projectx.presentation.di.ApplicationScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class OnaApiServiceModule {
    @ApplicationScope
    @Provides
    public RetrofitBuilder provideBuilder(Retrofit retrofit){
        return new RetrofitBuilder(retrofit);
    }
    @ApplicationScope
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, GsonConverterFactory
            gsonConverterFactory) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ConfigApi.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @ApplicationScope
    @Provides
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.serializeNulls().create();
    }

    @ApplicationScope
    @Provides
    public GsonConverterFactory providesGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @ApplicationScope
    @Provides
    public ProjectRepository provideProjectRepository(RestApiManager apiManager){
        return new ProjectRepositoryImpl(apiManager);
    }

    @ApplicationScope
    @Provides
    public SessionRepository provideSessionRepository(RestApiManager apiManager,
                                                      TokenPersistence persistence){
        return new SessionRepositoryImpl(apiManager,persistence);
    }


}
