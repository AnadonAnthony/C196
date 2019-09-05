package com.aanadon.android.anadonc196.db;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.aanadon.android.anadonc196.R;
import com.aanadon.android.anadonc196.models.TermEntity;
import com.aanadon.android.anadonc196.models.TermNoteEntity;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.aanadon.android.anadonc196.utilities.Samples;

import java.util.Collection;
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
    public LiveData<List<TermNoteEntity>> TermNoteList;

    private AppDatabase _Db;
    private Executor _Executor  = Executors.newSingleThreadExecutor();


    private AppRepository(Context pContext) {
        _Db             = AppDatabase.getInstance(pContext);
        TermList        = fetchTermData();
        TermNoteList    = fetchTermNotesData();
    }

    public void addSampleData() {
        _Executor.execute(new Runnable() {
            @Override
            public void run() {
                _Db.TermDAO().insertAll(Samples.getSampleTerms());
            }
        });
    }

    public void deleteSampleData() {
        _Executor.execute(new Runnable() {
            @Override
            public void run() {
                _Db.TermDAO().deleteAllTerms();
            }
        });
    }

    //  <editor-fold desc="Term Methods">
    private LiveData<List<TermEntity>> fetchTermData()    {
        return _Db.TermDAO().getAllTerms();
    }

    public TermEntity fetchTermData(int termId) {
        return _Db.TermDAO().getTermById(termId);
    }

    public void insertTerm(final TermEntity term) {
        _Executor.execute(new Runnable() {
            @Override
            public void run() {
                _Db.TermDAO().insertTerm(term);
            }
        });
    }

    public void deleteTerm(final TermEntity term) {
        _Executor.execute(new Runnable() {
            @Override
            public void run() {
                _Db.TermDAO().deleteTerm(term);
            }
        });
    }
    //  </editor-fold>

    //  <editor-fold default-state="collapsed" desc="Term Note Methods">
    public void deleteTermNote(final TermNoteEntity note) {
        if (null != note)   {
            _Executor.execute(new Runnable() {
                @Override
                public void run() {
                    _Db.TermNoteDAO().deleteTermNote(note);
                }
            });
        }
    }

    public void insertTermNote(final TermNoteEntity note)   {
        _Executor.execute(new Runnable() {
            @Override
            public void run() {
                _Db.TermNoteDAO().insertTermNote(note);
            }
        });
    }

    public TermNoteEntity fetchTermNoteData(int pNoteId)    {
        return _Db.TermNoteDAO().getTermNoteById(pNoteId);
    }

    public LiveData<List<TermNoteEntity>> fetchTermNotesData()  {
        return _Db.TermNoteDAO().getAllTermNotes();
    }

    public LiveData<List<TermNoteEntity>> fetchTermNotesData(int pTermId)   {
        return _Db.TermNoteDAO().getTermNotes(pTermId);
    }

    //  </editor-fold>
}
