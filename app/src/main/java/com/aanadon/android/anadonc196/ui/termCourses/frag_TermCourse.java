package com.aanadon.android.anadonc196.ui.termCourses;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
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
import com.aanadon.android.anadonc196.editCourse;
import com.aanadon.android.anadonc196.models.CourseEntity;
import com.aanadon.android.anadonc196.ui.adapters.adapter_TermCourseItem;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.aanadon.android.anadonc196.vms.vm_TermCourse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class frag_TermCourse extends Fragment {

    //  <editor-fold defaultstate="collapsed" des="Butterknife Injections">
    @BindView(R.id.btnAddCourse)
    FloatingActionButton _AddCourse;
    @BindView(R.id.viewCourseListRecycler)
    RecyclerView _Recycler;

    @OnClick(R.id.btnAddCourse)
    public void onClick_AddCourse() {
        Intent CourseIntent = new Intent(getActivity().getBaseContext(), editCourse.class);
        startActivity(CourseIntent);
    }
    //  </editor-fold>

    //  <editor-fold defaultstate="collapsed" des="Member Variables">
    private AppRepository _Repository;
    private vm_TermCourse _ViewModel;
    private adapter_TermCourseItem _Adapter;
    private List<CourseEntity> _Courses = new ArrayList<>();
    //  </editor-fold>

    private void initializeViewModel()  {
        _Repository = AppRepository.getInstance(getContext());

        final Observer<List<CourseEntity>> CourseObserver = new Observer<List<CourseEntity>>() {
            @Override
            public void onChanged(List<CourseEntity> courseEntities) {
                _Courses.clear();
                _Courses.addAll(courseEntities);

                if (null == _Adapter)   {
                    _Adapter    = new adapter_TermCourseItem(_Courses,
                        frag_TermCourse.this.getContext());
                    _Recycler.setAdapter(_Adapter);
                }
                else
                    _Adapter.notifyDataSetChanged();
            }
        };

        _ViewModel._Courses.observe(this, CourseObserver);
    }

    private void initializeRecyclerView()   {
        _Recycler.setHasFixedSize(true);

        LinearLayoutManager Layout  = new LinearLayoutManager(getContext());
        _Recycler.setLayoutManager(Layout);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        _ViewModel =
                ViewModelProviders.of(this).get(vm_TermCourse.class);
        View root = inflater.inflate(R.layout.frag_term_courses, container, false);

        ButterKnife.bind(this, root);
        initializeRecyclerView();
        initializeViewModel();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ActionBar Bar   = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (null != Bar)    {
            Bar.setDisplayHomeAsUpEnabled(true);
            Bar.setHomeAsUpIndicator(R.drawable.ic_save);
            Bar.setTitle(Html.fromHtml(String.format(Constants.TITLE_MOD, "Term Course List")));
        }
    }
}