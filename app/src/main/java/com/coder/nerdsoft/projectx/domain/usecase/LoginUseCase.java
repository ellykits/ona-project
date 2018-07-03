package com.coder.nerdsoft.projectx.domain.usecase;

import com.coder.nerdsoft.projectx.domain.common.DomainConstants;
import com.coder.nerdsoft.projectx.domain.common.TransformerForSingle;
import com.coder.nerdsoft.projectx.domain.contract.SessionRepository;
import com.fernandocejas.arrow.optional.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import io.reactivex.Single;

public class LoginUseCase extends SessionUseCase<String> {

    private final SessionRepository mSessionRepository;

    public LoginUseCase(@NotNull TransformerForSingle<String> transformer, SessionRepository
            sessionRepository) {
        super(transformer);
        mSessionRepository = sessionRepository;
    }

    @NotNull
    @Override
    protected Single<String> createObservable(@Nullable Optional<Map<String, String>> optionalData) {

        //Check if any data were passed in Map with the Observable
        Map<String, String> credentials;
        if (!optionalData.isPresent()) {
            throw new NullPointerException("Username and Password are required");
        }
        credentials = optionalData.get();
        String username = credentials.get(DomainConstants.USERNAME);
        String password = credentials.get(DomainConstants.PASSWORD);

        /**
         * Check if user is logged in and return the token otherwise proceed to request token
         */
        return mSessionRepository.isUserLoggedIn().flatMap((String token) -> {
            if(!token.isEmpty()){
                return  Single.just(token);
            }
            return mSessionRepository.requestToken(username, password);
        });

    }
}