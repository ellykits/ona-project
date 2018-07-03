package com.coder.nerdsoft.projectx.presentation.ui.displayproject;

import android.arch.lifecycle.MutableLiveData;

import com.coder.nerdsoft.projectx.domain.common.DomainConstants;
import com.coder.nerdsoft.projectx.domain.entity.ProjectEntity;
import com.coder.nerdsoft.projectx.domain.usecase.ViewProject;
import com.coder.nerdsoft.projectx.presentation.common.SingleLiveEvent;
import com.coder.nerdsoft.projectx.presentation.util.BaseViewModel;
import com.fernandocejas.arrow.optional.Optional;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

public class DisplayProjectViewModel extends BaseViewModel {

    private final ViewProject mViewProject;
    public MutableLiveData<DisplayProjectViewState> mViewData;
    public SingleLiveEvent<Throwable> mErrorState;

    @Inject
    public DisplayProjectViewModel(ViewProject viewProject) {
        mViewProject = viewProject;
        mViewData = new MutableLiveData<>();
        mErrorState = new SingleLiveEvent<>();
    }

    public void displayProject(long projectId) {
        Map<String, Long> passedData = new HashMap<>();
        passedData.put(DomainConstants.PROJECT_ID, projectId);

        DisposableSingleObserver disposableSO = mViewProject.executor(Optional.of(passedData))
                .subscribeWith(new DisposableSingleObserver<ProjectEntity>() {
                    @Override
                    public void onSuccess(ProjectEntity projectEntity) {

                        mViewData.setValue(new DisplayProjectViewState(projectEntity));

                    }

                    @Override
                    public void onError(Throwable e) {
                        mErrorState.setValue(e);
                    }
                });
        addDisposable(disposableSO);
    }


}
