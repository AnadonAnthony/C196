package com.aanadon.android.anadonc196.db;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.aanadon.android.anadonc196.models.TermEntity;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.aanadon.android.anadonc196.utilities.Samples;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {

    private static AppRepository _Instance;
    public static AppRepository getInstance(Context pContext) {
        if (null == _Instance)
            _Instance= new AppRepository(pContext);

        return _Instance;
    }

    public LiveData<List<TermEntity>> TermList;

    private AppDatabase _Db;
    private Executor _Executor  = Executors.newSingleThreadExecutor();


    private AppRepository(Context pContext) {
        _Db         = AppDatabase.getInstance(pContext);
        TermList    = getAllNotes();
    }

    public void addSampleData() {
        _Executor.execute(new Runnable() {
            @Override
            public void run() {
                _Db.TermDAO().insertAll(Samples.getSampleTerms());
            }
        });
    }

    private LiveData<List<TermEntity>> getAllNotes()    {
        return _Db.TermDAO().getAllTerms();
    }

    public void deleteSampleData() {
        _Executor.execute(new Runnable() {
            @Override
            public void run() {
                _Db.TermDAO().deleteAllTerms();
            }
        });
    }
}
