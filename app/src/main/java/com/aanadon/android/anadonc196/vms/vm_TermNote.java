package com.aanadon.android.anadonc196.vms;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aanadon.android.anadonc196.db.AppRepository;
import com.aanadon.android.anadonc196.models.TermNoteEntity;

import java.util.List;

public class vm_TermNote
    extends AndroidViewModel {

    public LiveData<List<TermNoteEntity>> _Notes;

    public vm_TermNote(@NonNull Application application) {
        super(application);

        _Notes = AppRepository.getInstance(application.getApplicationContext()).TermNotes;
    }
}
