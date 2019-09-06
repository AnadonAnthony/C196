package com.aanadon.android.anadonc196.ui.courseAssessments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class frag_vmdl_CourseAssessments extends ViewModel {

    private MutableLiveData<String> mText;

    public frag_vmdl_CourseAssessments() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}