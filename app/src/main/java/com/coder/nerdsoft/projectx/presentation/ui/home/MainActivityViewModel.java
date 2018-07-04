package com.coder.nerdsoft.projectx.presentation.ui.home;

import android.arch.lifecycle.MutableLiveData;

import com.coder.nerdsoft.projectx.domain.entity.ProjectEntity;
import com.coder.nerdsoft.projectx.domain.usecase.GetProjects;
import com.coder.nerdsoft.projectx.domain.usecase.InvalidateToken;
import com.coder.nerdsoft.projectx.domain.usecase.RefreshToken;
import com.coder.nerdsoft.projectx.presentation.common.SingleLiveEvent;
import com.coder.nerdsoft.projectx.presentation.util.BaseViewModel;
import com.fernandocejas.arrow.optional.Optional;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

public class MainActivityViewModel extends BaseViewModel {
    private final GetProjects mGetProjects;
    private final RefreshToken mRefreshToken;
    private final InvalidateToken mInvalidateToken;
    public MutableLiveData<MainActivityViewState> mViewData;
    public SingleLiveEvent<Throwable> mErrorState;

    @Inject
    public MainActivityViewModel(GetProjects getProjects, RefreshToken refreshToken,
                                 InvalidateToken invalidateToken) {
        mRefreshToken = refreshToken;
        mViewData = new MutableLiveData<>();
        mGetProjects = getProjects;
        mErrorState = new SingleLiveEvent<>();
        mInvalidateToken = invalidateToken;
    }

    public void showProjects() {
        DisposableSingleObserver<List<ProjectEntity>> disposableObserver =
                mGetProjects.executor(Optional.fromNullable(null)).subscribeWith(
                        new DisposableSingleObserver<List<ProjectEntity>>() {

                            @Override
                            public void onSuccess(List<ProjectEntity> projectEntities) {
                                MainActivityViewState state = new MainActivityViewState(false,
                                        projectEntities);
                                mViewData.setValue(state);
                                Timber.tag("ONA").d(projectEntities.toString());
                            }

                            @Override
                            public void onError(Throwable e) {
                             //   mErrorState.setValue(e);
                                Timber.tag("ONA").d(e);
                                getNewAccessToken();
                            }

                        }
                );
        addDisposable(disposableObserver);
    }

    private void getNewAccessToken() {
        DisposableSingleObserver observer = mRefreshToken.executor(Optional.fromNullable(null))
                .subscribeWith(new DisposableSingleObserver<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Timber.tag("ONA").d("Got new token %s", s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mInvalidateToken.deleteToken();
                        mErrorState.setValue(e);
                    }
                });
        addDisposable(observer);
    }
}
