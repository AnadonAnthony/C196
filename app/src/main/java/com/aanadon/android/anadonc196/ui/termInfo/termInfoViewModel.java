package com.aanadon.android.anadonc196.ui.termInfo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aanadon.android.anadonc196.db.AppRepository;
import com.aanadon.android.anadonc196.models.TermEntity;
import com.aanadon.android.anadonc196.utilities.Constants;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class termInfoViewModel extends ViewModel {

    public MutableLiveData<TermEntity> TermData = new MutableLiveData<>();

    private AppRepository _Repository;
    private Executor _Executor = Executors.newSingleThreadExecutor();

    public termInfoViewModel() {
    }

    public LiveData<TermEntity> getTermData() {return TermData;}

    public void saveTerm(TermEntity temp) {
        Log.i(Constants.LOG_TAG, "termInfoViewModel.saveTerm");
        TermEntity Term = TermData.getValue();

        if (null == Term)   {
            //  The current LiveData Object is null. This is a new term we're working on. Create the
            //  TermEntity Object and save the create date.
            Term    = new TermEntity();
            Term.setCreateDate(new Date());
        }
        else    {
            Term.setTermTitle(temp.getTermTitle());
            Term.setTermStart(temp.getTermStart());
        }

        Log.i(Constants.LOG_TAG, "→\tSaving the Term Info");
        _Repository.insertTerm(Term);
    }

    public void fetchTerm(final int termId) {
        if (null == _Repository)
            _Repository = AppRepository.getInstance(null);

        _Executor.execute(new Runnable() {
            @Override
            public void run() {
                TermEntity Term = _Repository.getTermById(termId);
                TermData.postValue(Term);
            }
        });
    }

    public void deleteTerm(TermEntity temp) {
        Log.i(Constants.LOG_TAG, "termInfoViewModel.deleteTerm");
        if (null == _Repository)
            _Repository = AppRepository.getInstance(null);

        if (null != temp)
            _Repository.deleteTerm(temp);
    }
}