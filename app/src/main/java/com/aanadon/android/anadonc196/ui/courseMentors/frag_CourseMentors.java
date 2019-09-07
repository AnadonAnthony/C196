package com.aanadon.android.anadonc196.ui.courseMentors;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.aanadon.android.anadonc196.R;
import com.aanadon.android.anadonc196.editCourseMentor;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class frag_CourseMentors extends Fragment {

    //  <editor-fold defaultstate="collapsed" des="Butterknife Injections">
    @BindView(R.id.btnAddCourseMentor)
    FloatingActionButton _Add;
    @OnClick(R.id.btnAddCourseMentor)
    public void onClick_AddCourseNote() {
        Intent MentorIntent   = new Intent(getActivity().getBaseContext(), editCourseMentor.class);
        startActivity(MentorIntent);
    }
    //  </editor-fold>

    //  <editor-fold defaultstate="collapsed" des="Member Variables">
    private frag_vmdl_CourseMentors _ViewModel;
    //  </editor-fold>

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        _ViewModel =
                ViewModelProviders.of(this).get(frag_vmdl_CourseMentors.class);
        View root = inflater.inflate(R.layout.frag_course_mentors, container, false);

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
            Bar.setTitle(Html.fromHtml(String.format(Constants.TITLE_MOD, "Course Mentors")));
        }
    }
}