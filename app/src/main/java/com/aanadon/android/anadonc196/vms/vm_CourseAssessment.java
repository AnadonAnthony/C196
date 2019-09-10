package com.aanadon.android.anadonc196.vms;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aanadon.android.anadonc196.db.AppRepository;
import com.aanadon.android.anadonc196.models.AssessmentEntity;

import java.util.List;

public class vm_CourseAssessment extends AndroidViewModel {

    public LiveData<List<AssessmentEntity>> _Assessments;

    public vm_CourseAssessment(@NonNull Application application) {
        super(application);

        _Assessments    = AppRepository.getInstance(application.getApplicationContext()).Assessments;
    }
}