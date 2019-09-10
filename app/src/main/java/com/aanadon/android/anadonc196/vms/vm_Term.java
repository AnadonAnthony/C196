package com.aanadon.android.anadonc196.vms;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aanadon.android.anadonc196.db.AppRepository;
import com.aanadon.android.anadonc196.models.TermEntity;

import java.util.List;

public class vm_Term extends AndroidViewModel {

    public LiveData<List<TermEntity>> TermList;
    private AppRepository _Repository;

    public vm_Term(@NonNull Application application) {
        super(application);

        _Repository = AppRepository.getInstance(application.getApplicationContext());
        TermList    = _Repository.Terms;
    }

    public void addSampleData() {
        _Repository.addSampleData();
    }

    public void deleteSampleData() {
        _Repository.deleteSampleData();
    }
}
