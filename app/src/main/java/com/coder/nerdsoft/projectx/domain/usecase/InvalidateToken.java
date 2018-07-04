package com.coder.nerdsoft.projectx.domain.usecase;

import com.coder.nerdsoft.projectx.domain.contract.SessionRepository;

public class InvalidateToken {
    private final SessionRepository mSessionRepository;

    public InvalidateToken(SessionRepository sessionRepository) {
        mSessionRepository = sessionRepository;
    }

    public boolean deleteToken(){
        return mSessionRepository.invalidateToken();
    }
}
