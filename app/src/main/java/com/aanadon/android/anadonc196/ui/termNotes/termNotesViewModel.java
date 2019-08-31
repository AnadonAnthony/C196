package com.aanadon.android.anadonc196.ui.termNotes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class termNotesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public termNotesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}