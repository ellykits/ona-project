package com.coder.nerdsoft.projectx.data.remote;

import com.coder.nerdsoft.projectx.data.model.OAuthToken;
import com.coder.nerdsoft.projectx.data.model.Project;
import com.coder.nerdsoft.projectx.data.storage.TokenPersistence;
import com.coder.nerdsoft.projectx.data.util.DataConstants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import okhttp3.Credentials;

public class RestApiManager {
    private final OnaApiService mService;
    private final TokenPersistence mTokenPersistence;

    @Inject
    public RestApiManager(RetrofitBuilder builder, TokenPersistence tokenPersistence) {
        mService = builder.createService(OnaApiService.class);
        mTokenPersistence = tokenPersistence;
    }

    public Single<List<Project>> getProjects() {
        return mService.getProjects(DataConstants.TOKEN_TYPE + mTokenPersistence.getToken().getAccessToken());
    }
    public Single<Project> getProject(long pk) {
        return mService.getProject(DataConstants.TOKEN_TYPE + mTokenPersistence.getToken().getAccessToken(),pk);
    }


    public Single<OAuthToken> login(String username, String password) {
        return mService.login(Credentials.basic(ConfigApi.CLIENT_KEY, ConfigApi.CLIENT_SECRET)
                , username, password);
    }

    public Single<OAuthToken> refreshToken() {
        return mService.refreshToken(Credentials.basic(ConfigApi.CLIENT_KEY, ConfigApi.CLIENT_SECRET),
                mTokenPersistence.getToken().getRefreshToken());
    }

}
