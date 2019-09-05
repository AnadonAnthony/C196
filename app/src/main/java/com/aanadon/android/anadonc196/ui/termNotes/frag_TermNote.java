package com.aanadon.android.anadonc196.ui.termNotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import com.aanadon.android.anadonc196.editTerm;
import com.aanadon.android.anadonc196.editTermNote;
import com.aanadon.android.anadonc196.models.TermEntity;
import com.aanadon.android.anadonc196.models.TermNoteEntity;
import com.aanadon.android.anadonc196.ui.adapters.adapter_TermNoteItem;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.aanadon.android.anadonc196.vms.vm_TermNote;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class frag_TermNote extends Fragment {

    @BindView(R.id.btnAddTermNote)
    FloatingActionButton _AddTermNote;
    @BindView(R.id.viewTermNotesRecycler)
    RecyclerView _NoteView;

    @OnClick(R.id.btnAddTermNote)
    public void onClick_AddTermNote()   {
        Intent AddTermNote = new Intent(getActivity().getBaseContext(), editTermNote.class);
        startActivity(AddTermNote);
    }

    private AppRepository _Repository;
    private vm_TermNote _ViewModel;
    private adapter_TermNoteItem _Adapter;
    private List<TermNoteEntity> _NoteList  = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        _ViewModel =
                ViewModelProviders.of(this).get(vm_TermNote.class);
        View root = inflater.inflate(R.layout.frag_term_notes, container, false);

        ButterKnife.bind(this, root);
        initializeRecyclerView();
        initializeViewModel();

        return root;
    }

    private void initializeViewModel() {
        _Repository = AppRepository.getInstance(getContext());

        final Observer<List<TermNoteEntity>> NoteObserver   = new Observer<List<TermNoteEntity>>() {
            @Override
            public void onChanged(List<TermNoteEntity> noteEntities) {
                _NoteList.clear();
                _NoteList.addAll(noteEntities);

                if (null == _Adapter)   {
                    _Adapter    = new adapter_TermNoteItem(_NoteList,
                        frag_TermNote.this.getContext());
                    _NoteView.setAdapter(_Adapter);
                }
                else    {
                    _Adapter.notifyDataSetChanged();
                }
            }
        };

        _ViewModel._Notes.observe(this, NoteObserver);
    }

    private void initializeRecyclerView() {
        _NoteView.setHasFixedSize(true);

        LinearLayoutManager Layout  = new LinearLayoutManager(getContext());
        _NoteView.setLayoutManager(Layout);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ActionBar Bar   = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (null != Bar)    {
            Bar.setDisplayHomeAsUpEnabled(true);
            Bar.setTitle("Term Notes");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}