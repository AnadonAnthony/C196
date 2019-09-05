package com.aanadon.android.anadonc196.ui.termNotes;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aanadon.android.anadonc196.db.AppRepository;
import com.aanadon.android.anadonc196.models.TermNoteEntity;
import com.aanadon.android.anadonc196.utilities.Constants;

import java.util.List;

public class frag_vmod_TermNote extends AndroidViewModel {

    public LiveData<List<TermNoteEntity>> _Notes;
    private AppRepository _Repository;

    public frag_vmod_TermNote(@NonNull Application application) {
        super(application);

        _Repository = AppRepository.getInstance(application.getApplicationContext());
        _Notes      = _Repository.TermNoteList;
    }
}