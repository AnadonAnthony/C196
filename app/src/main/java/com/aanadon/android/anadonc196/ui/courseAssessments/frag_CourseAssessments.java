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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aanadon.android.anadonc196.R;
import com.aanadon.android.anadonc196.db.AppRepository;
import com.aanadon.android.anadonc196.editAssessment;
import com.aanadon.android.anadonc196.editCourse;
import com.aanadon.android.anadonc196.models.AssessmentEntity;
import com.aanadon.android.anadonc196.ui.adapters.adapter_AssessmentItem;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.aanadon.android.anadonc196.vms.vm_CourseAssessment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class frag_CourseAssessments extends Fragment {

    //  <editor-fold defaultstate="collapsed" des="Butterknife Injections">
    @BindView(R.id.btnAddAssessment)
    FloatingActionButton _AddAssessment;
    @BindView(R.id.viewAssessmentRecycler)
    RecyclerView _Recycler;

    @OnClick(R.id.btnAddAssessment)
    public void onClick_AddAssessment() {
        Log.i(getString(R.string.LogTag), "Starting Assessment Intent");

        Intent AssessmentIntent = new Intent(getContext(), editAssessment.class);
        startActivity(AssessmentIntent);
    }
    //  </editor-fold>

    //  <editor-fold defaultstate="collapsed" des="Member Variables">
    private AppRepository _Repo;
    private vm_CourseAssessment _ViewModel;
    private adapter_AssessmentItem _Adapter;
    private List<AssessmentEntity> _Assessments = new ArrayList<>();
    //  </editor-fold>

    private void initializeViewModel() {
        _Repo   = AppRepository.getInstance(getContext());

        final Observer<List<AssessmentEntity>> Observer = new Observer<List<AssessmentEntity>>() {
            @Override
            public void onChanged(List<AssessmentEntity> assessments) {
                _Assessments.clear();
                _Assessments.addAll(assessments);

                if (null == _Adapter)   {
                    _Adapter    = new adapter_AssessmentItem(_Assessments,
                        frag_CourseAssessments.this.getContext());
                    _Recycler.setAdapter(_Adapter);
                    _Adapter.notifyDataSetChanged();
                }
                else
                    _Adapter.notifyDataSetChanged();
            }
        };

        _ViewModel._Assessments.observe(this, Observer);
    }

    private void initializeRecyclerView() {
        _Recycler.setHasFixedSize(true);

        LinearLayoutManager Layout  = new LinearLayoutManager(getContext());
        _Recycler.setLayoutManager(Layout);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        _ViewModel  = ViewModelProviders.of(this).get(vm_CourseAssessment.class);
        View root = inflater.inflate(R.layout.frag_course_assessments, container, false);

        ButterKnife.bind(this, root);
        initializeRecyclerView();
        initializeViewModel();

        _Repo.Assessments   = _Repo.fetchAssessments(editCourse.getCourseId());

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