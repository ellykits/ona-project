package com.coder.nerdsoft.projectx.presentation.ui.login;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.coder.nerdsoft.projectx.R;
import com.coder.nerdsoft.projectx.presentation.OnaApplication;
import com.coder.nerdsoft.projectx.presentation.ui.home.MainActivity;
import com.coder.nerdsoft.projectx.presentation.ui.login.di.DaggerLoginComponent;
import com.coder.nerdsoft.projectx.presentation.ui.login.di.LoginComponent;
import com.coder.nerdsoft.projectx.presentation.ui.login.di.LoginModule;
import com.coder.nerdsoft.projectx.presentation.util.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.HttpException;
import timber.log.Timber;

public class LoginActivity extends BaseActivity<LoginViewState> {

    @BindView(R.id.login_email)
    EditText emailEditText;
    @BindView(R.id.login_password)
    EditText passwordEditText;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @Inject
    ViewModelProvider.Factory factory;

    @Inject
    LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //init dependencies
        LoginComponent component = DaggerLoginComponent
                .builder()
                .loginModule(new LoginModule(this))
                .onaApiComponent(OnaApplication.get(this).getOnaApiComponent())
                .build();
        component.inject(LoginActivity.this);

        //Init View Model and its observers
        viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);

        viewModel.mLoginViewState.observe(LoginActivity.this,
                this::handleViewState);
        viewModel.errorState.observe(LoginActivity.this, this::handleErrors);

        //Check if token is available
        viewModel.checkIfTokenIsAvailable();

        //Change progress-bar's color
        mProgressBar.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.colorPrimary),
                PorterDuff.Mode.MULTIPLY
        );
    }

    @OnClick(R.id.login_button)
    void login() {
        mProgressBar.setVisibility(View.VISIBLE);
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        viewModel.doLogin(email, password);
    }

    @Override
    protected void handleErrors(Throwable t) {

        Timber.tag("ONA").e("intercepted an error");
        Timber.tag("ONA").e(t);

        //Display useful error to user by typecasting the throwable to HttpException
        String errorMessage = "Encountered an error try again later";
        if (t instanceof HttpException) {
            HttpException exception = (HttpException) t;
            int statusCode = exception.code();
            switch (statusCode) {
                case 400:
                    errorMessage = "Invalid request username or password missing";
                    break;
                case 401:
                    errorMessage = "Invalid credentials username or password incorrect";
                    break;
                default:
                    errorMessage = "Encountered an error try again later";
            }

        }

        Toasty.error(getBaseContext(), errorMessage, 15, true).show();

    }

    @Override
    protected void handleViewState(LoginViewState viewState) {
        mProgressBar.setVisibility(viewState.isLoading() ? View.VISIBLE : View.GONE);
        if (viewState.isAuthenticated()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

