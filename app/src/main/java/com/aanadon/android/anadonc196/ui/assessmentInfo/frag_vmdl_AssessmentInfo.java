package com.aanadon.android.anadonc196.ui.assessmentInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class frag_vmdl_AssessmentInfo extends ViewModel {

    private MutableLiveData<String> mText;

    public frag_vmdl_AssessmentInfo() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}