package com.coder.nerdsoft.projectx.presentation.ui.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.coder.nerdsoft.projectx.R;
import com.coder.nerdsoft.projectx.domain.entity.ProjectEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectsRecyclerAdapter extends
        RecyclerView.Adapter<ProjectsRecyclerAdapter.ProjectsViewHolder> {

    private List<ProjectEntity> mProjectsList;

    @Inject
    public ProjectsRecyclerAdapter() {
        mProjectsList = new ArrayList<>();
    }

    public void addProjects(List<ProjectEntity> projectsList) {
        mProjectsList.clear();
        mProjectsList.addAll(projectsList);
        notifyDataSetChanged();
    }

    public List<ProjectEntity> getProjectsList() {
        return mProjectsList;
    }

    @NonNull
    @Override
    public ProjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View projectMiniView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.project_mini_info, parent, false);
        return new ProjectsViewHolder(projectMiniView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsViewHolder holder, int position) {
        ProjectEntity project = mProjectsList.get(position);
        holder.projectCategoryTextView.setText(project.getMetadataEntity().getCategory());
        holder.projectNameTextView.setText(project.getName().toUpperCase());
        holder.lastModifiedTextView.setText(project.getDateModified().toString());
        holder.projectDataSets.setText(String.valueOf(project.getNumDatasets()));
        holder.handleStarredProjects(project.getStarred());
        holder.projectId = project.getProjectId();

    }

    @Override
    public int getItemCount() {
        return mProjectsList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mClickListener;
        private GestureDetector mGestureDetector;

        RecyclerTouchListener(Context context, final OnItemClickListener clickListener) {
            mClickListener = clickListener;
            mGestureDetector = new GestureDetector(context,
                    new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                            return true;
                        }
                    });

        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View childView = rv.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mClickListener != null && mGestureDetector.onTouchEvent(e)) {
                mClickListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

    }

    class ProjectsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ona_project_category)
        TextView projectCategoryTextView;

        @BindView(R.id.ona_project_last_modified)
        TextView lastModifiedTextView;

        @BindView(R.id.ona_project_name)
        TextView projectNameTextView;

        @BindView(R.id.ona_project_datasets)
        TextView projectDataSets;

        @BindView(R.id.ona_project_star_button)
        ImageButton projectStarButton;

        Long projectId;


        ProjectsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void handleStarredProjects(boolean isStarred) {
            if (isStarred) {
                projectStarButton.setBackgroundColor(
                        itemView.getResources().getColor(R.color.colorCreamy));

            } else {
                projectStarButton.setBackgroundColor(
                        itemView.getResources().getColor(R.color.colorGray));
            }
        }
    }
}
