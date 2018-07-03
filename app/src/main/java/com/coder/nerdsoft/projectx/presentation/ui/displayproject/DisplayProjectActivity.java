package com.coder.nerdsoft.projectx.presentation.ui.displayproject;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.coder.nerdsoft.projectx.R;
import com.coder.nerdsoft.projectx.domain.common.DomainConstants;
import com.coder.nerdsoft.projectx.domain.entity.ProjectEntity;
import com.coder.nerdsoft.projectx.presentation.OnaApplication;
import com.coder.nerdsoft.projectx.presentation.ui.displayproject.di.DaggerDisplayProjectComponent;
import com.coder.nerdsoft.projectx.presentation.ui.displayproject.di.DisplayProjectComponent;
import com.coder.nerdsoft.projectx.presentation.ui.displayproject.di.DisplayProjectModule;
import com.coder.nerdsoft.projectx.presentation.util.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class DisplayProjectActivity extends BaseActivity<DisplayProjectViewState> {

    @Inject
    ViewModelProvider.Factory factory;

    @Inject
    DisplayProjectViewModel viewModel;

    @BindView(R.id.ona_prj_name)
    TextView projectNameTV;

    @BindView(R.id.this_project_name)
    TextView projectName2TV;

    @BindView((R.id.ona_prj_category))
    TextView projectCategoryTV;

    @BindView(R.id.ona_prj_datasets)
    TextView projectDatasetsTV;

    @BindView(R.id.ona_prj_starred)
    TextView projectIsStarredTV;

    @BindView(R.id.ona_prj_public)
    TextView projectIsPublicTV;

    @BindView(R.id.ona_prj_last_modified)
    TextView projectLastModifiedTV;

    @BindView(R.id.ona_prj_location)
    TextView projectLocationTV;

    @BindView(R.id.ona_prj_description)
    TextView projectDescriptionTV;

    @BindView(R.id.ona_prj_billing)
    TextView projectBillingTV;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);
        ButterKnife.bind(this);
        //Init dependencies
        DisplayProjectComponent component = DaggerDisplayProjectComponent.builder()
                .onaApiComponent(OnaApplication.get(this).getOnaApiComponent())
                .displayProjectModule(new DisplayProjectModule(this))
                .build();
        component.inject(DisplayProjectActivity.this);

        //Setup Viewmodels and observers
        viewModel = ViewModelProviders.of(this, factory).get(DisplayProjectViewModel.class);

        //Get the passed id and use it to query database
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey(DomainConstants.PROJECT_ID)) {
                long id = bundle.getLong(DomainConstants.PROJECT_ID);
                viewModel.displayProject(id);
            }
        }
        viewModel.mViewData.observe(this, this::handleViewState);
        viewModel.mErrorState.observe(this, this::handleErrors);
    }

    @Override
    protected void handleViewState(DisplayProjectViewState viewState) {
        setupView(viewState.getProjectEntity());
    }

    @Override
    protected void handleErrors(Throwable t) {
        Toasty.error(getBaseContext(), t.getMessage(), 30, true).show();
    }

    private void setupView(ProjectEntity project){
        projectNameTV.setText(project.getName());
        projectName2TV.setText(project.getName());
        projectCategoryTV.setText(project.getMetadataEntity().getCategory());
        projectDatasetsTV.setText(String.valueOf(project.getNumDatasets()));
        String isPublic = project.getPublic()?"Yes":"No";
        projectIsPublicTV.setText(isPublic);
        String isFavorite = project.getStarred()?"Yes":"No";
        projectIsStarredTV.setText(isFavorite);
        projectLastModifiedTV.setText(project.getDateModified().toString());
        projectLocationTV.setText(project.getMetadataEntity().getLocation());
        projectBillingTV.setText(project.getMetadataEntity().getBillingCode());
        projectDescriptionTV.setText(project.getMetadataEntity().getDescription());
    }
}
