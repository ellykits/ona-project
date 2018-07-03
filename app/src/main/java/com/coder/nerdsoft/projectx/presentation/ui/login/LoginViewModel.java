package com.coder.nerdsoft.projectx.presentation.ui.login;

import android.arch.lifecycle.MutableLiveData;

import com.coder.nerdsoft.projectx.domain.common.DomainConstants;
import com.coder.nerdsoft.projectx.domain.usecase.AuthorizeAccess;
import com.coder.nerdsoft.projectx.domain.usecase.LoginUseCase;
import com.coder.nerdsoft.projectx.presentation.common.SingleLiveEvent;
import com.coder.nerdsoft.projectx.presentation.util.BaseViewModel;
import com.fernandocejas.arrow.optional.Optional;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

public class LoginViewModel extends BaseViewModel {

    private final LoginUseCase mUseCase;
    private final AuthorizeAccess mAuthorizeAccess;
    public MutableLiveData<LoginViewState> mLoginViewState;
    public SingleLiveEvent<Throwable> errorState;

    @Inject
    public LoginViewModel(LoginUseCase useCase, AuthorizeAccess authorizationUseCase) {
        mUseCase = useCase;
        mAuthorizeAccess = authorizationUseCase;
        mLoginViewState = new MutableLiveData<>();
        errorState = new SingleLiveEvent<>();
    }

    public void checkIfTokenIsAvailable() {
        final DisposableSingleObserver<String> disposableSingleObserver = mAuthorizeAccess
                .executor(Optional.fromNullable(null)).subscribeWith(
                        new DisposableSingleObserver<String>() {
                            @Override
                            public void onSuccess(String s) {
                                if (!s.isEmpty()) {
                                    mLoginViewState.setValue(new LoginViewState(false, true));
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                mLoginViewState.setValue(
                                        new LoginViewState(false, false));
                                errorState.setValue(e);
                            }
                        }
                );
        addDisposable(disposableSingleObserver);
    }

    public void doLogin(String username, String password) {
        Map<String, String> credentials = new HashMap<>();
        credentials.put(DomainConstants.USERNAME, username);
        credentials.put(DomainConstants.PASSWORD, password);
        final DisposableSingleObserver<String> disposableSingleObserver =
                mUseCase.executor(Optional.of(credentials))
                        .subscribeWith(
                                new DisposableSingleObserver<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        mLoginViewState.setValue(
                                                new LoginViewState(false, true));
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        mLoginViewState.setValue(
                                                new LoginViewState(false, false));
                                        errorState.setValue(e);
                                    }
                                }
                        );
        addDisposable(disposableSingleObserver);
    }
}
