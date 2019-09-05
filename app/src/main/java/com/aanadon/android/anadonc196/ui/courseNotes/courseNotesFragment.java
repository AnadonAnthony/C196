package com.aanadon.android.anadonc196.ui.courseNotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.aanadon.android.anadonc196.R;
import com.aanadon.android.anadonc196.editCourseNote;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class courseNotesFragment extends Fragment {

    //  <editor-fold defaultstate="collapsed" des="Butterknife Injections">
    @BindView(R.id.btnAddCourseNote)
    FloatingActionButton _AddCourseNote;
    @OnClick(R.id.btnAddCourseNote)
    public void onClick_AddCourseNote() {
        Intent NoteIntent   = new Intent(getActivity().getBaseContext(), editCourseNote.class);
        startActivity(NoteIntent);
    }
    //  </editor-fold>

    //  <editor-fold defaultstate="collapsed" des="Member Variables">
    private courseNotesViewModel notificationsViewModel;
    //  </editor-fold>

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(courseNotesViewModel.class);
        View root = inflater.inflate(R.layout.frag_course_notes, container, false);

        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Course Notes");
    }
}