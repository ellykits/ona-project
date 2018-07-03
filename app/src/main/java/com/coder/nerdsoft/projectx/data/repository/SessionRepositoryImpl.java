package com.coder.nerdsoft.projectx.data.repository;

import com.coder.nerdsoft.projectx.data.remote.RestApiManager;
import com.coder.nerdsoft.projectx.data.storage.TokenPersistence;
import com.coder.nerdsoft.projectx.domain.contract.SessionRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class SessionRepositoryImpl implements SessionRepository {

    private final RestApiManager mApiManager;
    private final TokenPersistence mTokenPersistence;

    @Inject
    public SessionRepositoryImpl(RestApiManager apiManager, TokenPersistence tokenPersistence) {
        mApiManager = apiManager;
        mTokenPersistence = tokenPersistence;
    }

    @Override
    public Single<String> requestToken(String username, String password) {
        return mApiManager.login(username, password).map(token -> {
            mTokenPersistence.saveToken(token);
            return token.getAccessToken();
        });
    }

    @Override
    public Single<String> refreshToken() {
        String refreshToken = mTokenPersistence.getToken().getRefreshToken();
        if(refreshToken == null){
            return Single.error(new NullPointerException("No refresh token is provided!!"));
        }
        return mApiManager.refreshToken().map(token -> {
            mTokenPersistence.saveToken(token);
            return token.getAccessToken();
        });
    }

    @Override
    public Single<Boolean> invalidateToken() {
        return Single.just(mTokenPersistence.deleteToken());
    }

    @Override
    public Single<String> isUserLoggedIn() {
        if(mTokenPersistence.getToken().getAccessToken() == null){
            return Single.just("");
        }
        return Single.just(mTokenPersistence.getToken().getAccessToken());

    }
}
