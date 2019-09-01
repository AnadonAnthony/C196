package com.aanadon.android.anadonc196.vms;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aanadon.android.anadonc196.db.AppRepository;
import com.aanadon.android.anadonc196.models.TermEntity;

import java.util.List;

public class TermViewModel extends AndroidViewModel {

    public LiveData<List<TermEntity>> TermList;
    private AppRepository _Repository;

    public TermViewModel(@NonNull Application application) {
        super(application);

        _Repository = AppRepository.getInstance(application.getApplicationContext());
        TermList    = _Repository.TermList;
    }

    public void addSampleData() {
        _Repository.addSampleData();
    }

    public void deleteSampleData() {
        _Repository.deleteSampleData();
    }
}
