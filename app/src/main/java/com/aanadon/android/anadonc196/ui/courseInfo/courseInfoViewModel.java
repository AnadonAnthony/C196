package com.aanadon.android.anadonc196.ui.courseInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class courseInfoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public courseInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}