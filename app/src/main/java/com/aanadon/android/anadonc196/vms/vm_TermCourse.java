package com.aanadon.android.anadonc196.vms;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aanadon.android.anadonc196.db.AppRepository;
import com.aanadon.android.anadonc196.models.CourseEntity;

import java.util.List;

public class vm_TermCourse
    extends AndroidViewModel {

    public LiveData<List<CourseEntity>> _Courses;

    public vm_TermCourse(@NonNull Application application) {
        super(application);

        _Courses    = AppRepository.getInstance(application.getApplicationContext()).Courses;
    }
}
