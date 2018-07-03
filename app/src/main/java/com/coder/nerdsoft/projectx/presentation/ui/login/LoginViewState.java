package com.coder.nerdsoft.projectx.presentation.ui.login;

public class LoginViewState {
    private  boolean isLoading;
    private  boolean isAuthenticated;

    public LoginViewState(boolean isLoading, boolean isAuthenticated) {
        this.isLoading = isLoading;
        this.isAuthenticated = isAuthenticated;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public boolean isLoading() {
        return isLoading;
    }
}
