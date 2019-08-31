package com.aanadon.android.anadonc196.ui.termCourses;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aanadon.android.anadonc196.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;

public class termCourseViewModel extends ViewModel {

    @BindView(R.id.btnAddCourse)
    FloatingActionButton _AddCourse;

    private MutableLiveData<String> mText;

    public termCourseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}