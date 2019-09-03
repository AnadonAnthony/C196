package com.aanadon.android.anadonc196.ui.termNotes;

import android.content.Intent;
import android.os.Bundle;
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
import com.aanadon.android.anadonc196.editTermNote;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class termNotesFragment extends Fragment {

    @BindView(R.id.btnAddTermNote)
    FloatingActionButton _AddTermNote;
    @OnClick(R.id.btnAddTermNote)
    public void onClick_AddTermNote()   {
        Intent AddTermNote = new Intent(getActivity().getBaseContext(), editTermNote.class);
        startActivity(AddTermNote);
    }

    private termNotesViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(termNotesViewModel.class);
        View root = inflater.inflate(R.layout.frag_term_notes, container, false);

        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("TermData Notes");
    }
}