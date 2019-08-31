package com.aanadon.android.anadonc196.ui.courseNotes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class courseNotesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public courseNotesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}