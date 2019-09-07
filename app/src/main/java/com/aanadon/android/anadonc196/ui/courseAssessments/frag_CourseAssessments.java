package com.aanadon.android.anadonc196.ui.courseAssessments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.aanadon.android.anadonc196.R;
import com.aanadon.android.anadonc196.editAssessment;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class frag_CourseAssessments extends Fragment {

    @BindView(R.id.btnAddAssessment)
    FloatingActionButton _AddAssessment;

    @OnClick(R.id.btnAddAssessment)
    public void onClick_AddAssessment() {
        Log.i(getString(R.string.LogTag), "Starting Assessment Intent");

        Intent AssessmentIntent = new Intent(getContext(), editAssessment.class);
        startActivity(AssessmentIntent);
    }

    private frag_vmdl_CourseAssessments dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(frag_vmdl_CourseAssessments.class);
        View root = inflater.inflate(R.layout.frag_course_assessments, container, false);

        ButterKnife.bind(this, root);

        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ActionBar Bar   = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (null != Bar) {
            Bar.setDisplayHomeAsUpEnabled(true);
            Bar.setHomeAsUpIndicator(R.drawable.ic_save);
            Bar.setTitle(Html.fromHtml(String.format(Constants.TITLE_MOD, "Course Assessment List")));
        }
    }
}