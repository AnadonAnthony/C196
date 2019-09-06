package com.aanadon.android.anadonc196.ui.courseNotes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class frag_vmdl_CourseNotes extends ViewModel {

    private MutableLiveData<String> mText;

    public frag_vmdl_CourseNotes() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}