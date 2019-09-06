package com.aanadon.android.anadonc196.ui.courseInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class frag_vmdl_CourseInfo extends ViewModel {

    private MutableLiveData<String> mText;

    public frag_vmdl_CourseInfo() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}