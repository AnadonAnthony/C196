package com.aanadon.android.anadonc196.ui.courseAssessments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aanadon.android.anadonc196.R;
import com.aanadon.android.anadonc196.editAssessment;
import com.aanadon.android.anadonc196.editCourse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class courseAssessmentsFragment extends Fragment {

    @BindView(R.id.btnAddAssessment)
    FloatingActionButton _AddAssessment;

    @OnClick(R.id.btnAddAssessment)
    public void onClick_AddAssessment() {
        Log.i(getString(R.string.LogTag), "CLICKED!");

        Intent AssessmentIntent = new Intent(getActivity().getBaseContext(), editAssessment.class);
        startActivity(AssessmentIntent);
    }

    private courseAssessmentsViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(courseAssessmentsViewModel.class);
        View root = inflater.inflate(R.layout.frag_course_assessments, container, false);

        ButterKnife.bind(this, root);

        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Course Assessment List");
    }
}