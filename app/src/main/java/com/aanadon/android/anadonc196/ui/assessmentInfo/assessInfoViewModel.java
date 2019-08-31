package com.aanadon.android.anadonc196.ui.assessmentInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class assessInfoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public assessInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}