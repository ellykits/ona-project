package com.coder.nerdsoft.projectx.presentation.ui.home;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.coder.nerdsoft.projectx.R;
import com.coder.nerdsoft.projectx.domain.common.DomainConstants;
import com.coder.nerdsoft.projectx.domain.entity.ProjectEntity;
import com.coder.nerdsoft.projectx.presentation.OnaApplication;
import com.coder.nerdsoft.projectx.presentation.ui.home.di.DaggerMainActivityComponent;
import com.coder.nerdsoft.projectx.presentation.ui.home.di.MainActivityComponent;
import com.coder.nerdsoft.projectx.presentation.ui.home.di.MainActivityModule;
import com.coder.nerdsoft.projectx.presentation.ui.displayproject.DisplayProjectActivity;
import com.coder.nerdsoft.projectx.presentation.ui.login.LoginActivity;
import com.coder.nerdsoft.projectx.presentation.util.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainActivityViewState> {

    @BindView(R.id.main_coord_layout)
    CoordinatorLayout mCoordLayout;

    @BindView(R.id.project_recycler_view)
    RecyclerView mProjectRecyclerView;

    @BindView(R.id.progressBar2)
    ProgressBar mProgressBar;


    @Inject
    ViewModelProvider.Factory mFactory;

    @Inject
    ProjectsRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //Init the dependencies
        MainActivityComponent component = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .onaApiComponent(OnaApplication.get(this).getOnaApiComponent())
                .build();
        component.inject(MainActivity.this);

        setupRecyclerView();

        //Set Viewmodel and observers
        MainActivityViewModel viewModel = ViewModelProviders.of(this, mFactory).get(
                MainActivityViewModel.class);
        viewModel.showProjects();
        viewModel.mViewData.observe(this, this::handleViewState);
        viewModel.mErrorState.observe(this, this::handleErrors);


    }

    @Override
    protected void handleViewState(MainActivityViewState viewState) {
        mAdapter.addProjects(viewState.getProjectEntityList());
        mProgressBar.setVisibility(viewState.isLoading() ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void handleErrors(Throwable e) {
        Snackbar.make(mCoordLayout, "Session expired login required", Snackbar.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void setupRecyclerView() {
        mProjectRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mProjectRecyclerView.setItemAnimator(new DefaultItemAnimator()); //set default animator
        mProjectRecyclerView.addItemDecoration(new DividerItemDecoration(getBaseContext(),
                LinearLayoutManager.VERTICAL)); //Includes line divider
        mProjectRecyclerView.setAdapter(mAdapter);
        mProjectRecyclerView.addOnItemTouchListener(new ProjectsRecyclerAdapter.RecyclerTouchListener(
                getBaseContext(), (view, position) -> {
            ProjectEntity project = mAdapter.getProjectsList().get(position);
            viewSelectedProject(project.getProjectId());
           // Toasty.info(getBaseContext(), "ProjectId: " + project.getProjectId(), 15, true).show();
        }
        ));
    }
    private void viewSelectedProject(long id){
        Intent intent = new Intent(getApplicationContext(),DisplayProjectActivity.class);
        intent.putExtra(DomainConstants.PROJECT_ID,id);
        startActivity(intent);
    }
}
