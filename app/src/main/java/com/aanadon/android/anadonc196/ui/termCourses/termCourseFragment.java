package com.aanadon.android.anadonc196.ui.termCourses;

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
import com.aanadon.android.anadonc196.editCourse;
import com.aanadon.android.anadonc196.editTerm;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class termCourseFragment extends Fragment {

    @BindView(R.id.btnAddCourse)
    FloatingActionButton _AddCourse;
    @OnClick(R.id.btnAddCourse)
    public void onClick_AddCourse() {
        Intent CourseIntent = new Intent(getActivity().getBaseContext(), editCourse.class);
        startActivity(CourseIntent);
    }

    private termCourseViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(termCourseViewModel.class);
        View root = inflater.inflate(R.layout.frag_term_courses, container, false);

        ButterKnife.bind(this, root);
        if (null == _AddCourse)
            Log.wtf(getString(R.string.LogTag), "Butterknife Failed");

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("TermData Course List");
    }
}