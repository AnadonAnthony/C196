package com.aanadon.android.anadonc196.ui.courseMentors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class frag_vmdl_CourseMentors extends ViewModel {

    private MutableLiveData<String> mText;

    public frag_vmdl_CourseMentors() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}