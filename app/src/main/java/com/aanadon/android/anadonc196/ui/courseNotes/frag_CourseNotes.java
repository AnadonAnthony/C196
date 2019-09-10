package com.aanadon.android.anadonc196.ui.courseNotes;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.aanadon.android.anadonc196.editCourseNote;
import com.aanadon.android.anadonc196.models.CourseNoteEntity;
import com.aanadon.android.anadonc196.ui.adapters.adapter_CourseNoteItem;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.aanadon.android.anadonc196.vms.vm_CourseNote;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class frag_CourseNotes extends Fragment {

    //  <editor-fold defaultstate="collapsed" des="Butterknife Injections">
    @BindView(R.id.btnAddCourseNote)
    FloatingActionButton _AddCourseNote;
    @BindView(R.id.viewCourseNotes)
    RecyclerView _Recycler;

    @OnClick(R.id.btnAddCourseNote)
    public void onClick_AddCourseNote() {
        Log.i(Constants.LOG_TAG,
                "→\tCreate a new Course Note");
        Intent NoteIntent   = new Intent(getContext(), editCourseNote.class);
        startActivity(NoteIntent);
    }
    //  </editor-fold>

    //  <editor-fold defaultstate="collapsed" des="Member Variables">
    private frag_vmdl_CourseNotes notificationsViewModel;
    private AppRepository _Repo;
    private adapter_CourseNoteItem _Adapter;
    private vm_CourseNote _ViewModel;
    private List<CourseNoteEntity> _Notes   = new ArrayList<>();

    //  </editor-fold>

    private void initializeViewModel()  {
        _Repo   = AppRepository.getInstance(getContext());

        final Observer<List<CourseNoteEntity>> NoteObserver = new Observer<List<CourseNoteEntity>>() {
            @Override
            public void onChanged(List<CourseNoteEntity> Notes) {
                _Notes.clear();
                _Notes.addAll(Notes);

                if (null == _Adapter)   {
                    _Adapter    = new adapter_CourseNoteItem(_Notes,
                            frag_CourseNotes.this.getContext());
                    _Recycler.setAdapter(_Adapter);
                }
                else
                    _Adapter.notifyDataSetChanged();
            }
        };

        _ViewModel._Notes.observe(this, NoteObserver);
    }

    private void initializeRecyclerView()   {
        _Recycler.setHasFixedSize(true);

        LinearLayoutManager Layout  = new LinearLayoutManager(getContext());
        _Recycler.setLayoutManager(Layout);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        _ViewModel  = ViewModelProviders.of(this).get(vm_CourseNote.class);
        View root   = inflater.inflate(R.layout.frag_course_notes, container, false);

        ButterKnife.bind(this, root);
        initializeRecyclerView();
        initializeViewModel();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ActionBar Bar   = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (null != Bar) {
            Bar.setDisplayHomeAsUpEnabled(true);
            Bar.setHomeAsUpIndicator(R.drawable.ic_save);
            Bar.setTitle(Html.fromHtml(String.format(Constants.TITLE_MOD, "Course Notes")));
        }

        if (!editCourse.isNewCourse())
            _Repo.CourseNotes   = _Repo.fetchCourseNotes(editCourse.getCourseId());
    }
}