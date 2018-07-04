package com.coder.nerdsoft.projectx.domain.contract;


import io.reactivex.Single;

public interface SessionRepository {

    Single<String> requestToken(String username, String password);

    Single<String> refreshToken();

    boolean invalidateToken();

    Single<String> isUserLoggedIn();

}
