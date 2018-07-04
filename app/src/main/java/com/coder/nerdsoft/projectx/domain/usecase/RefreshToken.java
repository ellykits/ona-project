package com.coder.nerdsoft.projectx.domain.usecase;

import com.coder.nerdsoft.projectx.domain.common.TransformerForSingle;
import com.coder.nerdsoft.projectx.domain.contract.SessionRepository;
import com.fernandocejas.arrow.optional.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import io.reactivex.Single;

public class RefreshToken extends SessionUseCase<String> {

    private final SessionRepository mSessionRepository;

    public RefreshToken(@NotNull TransformerForSingle<String> transformer, SessionRepository sessionRepository) {
        super(transformer);
        mSessionRepository = sessionRepository;
    }

    @NotNull
    @Override
    protected Single<String> createObservable(@Nullable Optional<Map<String, String>> optionalData) {
        return mSessionRepository.refreshToken();
    }
}
