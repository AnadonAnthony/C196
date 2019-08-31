package com.aanadon.android.anadonc196.ui.assessmentNotes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class assesNotesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public assesNotesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}