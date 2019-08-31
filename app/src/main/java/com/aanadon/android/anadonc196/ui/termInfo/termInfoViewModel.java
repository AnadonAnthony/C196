package com.aanadon.android.anadonc196.ui.termInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class termInfoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public termInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}