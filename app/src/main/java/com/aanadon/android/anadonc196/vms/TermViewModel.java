package com.aanadon.android.anadonc196.vms;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.aanadon.android.anadonc196.models.TermEntity;
import com.aanadon.android.anadonc196.utilities.Samples;

import java.util.List;

public class TermViewModel extends AndroidViewModel {

    public List<TermEntity> TermList    = Samples.getSampleTerms();

    public TermViewModel(@NonNull Application application) {
        super(application);
    }

}
