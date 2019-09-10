package com.aanadon.android.anadonc196.db;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import com.aanadon.android.anadonc196.editCourse;
import com.aanadon.android.anadonc196.editTerm;
import com.aanadon.android.anadonc196.models.AssessmentEntity;
import com.aanadon.android.anadonc196.models.CourseEntity;
import com.aanadon.android.anadonc196.models.CourseNoteEntity;
import com.aanadon.android.anadonc196.models.TermEntity;
import com.aanadon.android.anadonc196.models.TermNoteEntity;
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

    public LiveData<List<TermEntity>> Terms;
    public LiveData<List<TermNoteEntity>> TermNotes;
    public LiveData<List<CourseEntity>> Courses;
    public LiveData<List<CourseNoteEntity>> CourseNotes;
    public LiveData<List<AssessmentEntity>> Assessments;

    private AppDatabase _Db;
    private Executor _Executor  = Executors.newSingleThreadExecutor();


    private AppRepository(Context pContext) {
        _Db             = AppDatabase.getInstance(pContext);

        Terms           = fetchTermData();
        TermNotes       = fetchTermNotes(editTerm.getTermId());

        Courses         = fetchTermCourseData(editTerm.getTermId());
        CourseNotes     = fetchCourseNotes(editCourse.getCourseId());

        Assessments     = fetchAssessments(editCourse.getCourseId());
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
        TermNotes = fetchTermNotes(termId);
        Courses = fetchTermCourseData(termId);
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

    public LiveData<List<TermNoteEntity>> fetchTermNotes(int pTermId)   {
        if (pTermId < 0)
            return _Db.TermNoteDAO().getAllTermNotes();

        return _Db.TermNoteDAO().getTermNotes(pTermId);
    }

    //  </editor-fold>

    //  <editor-fold defaultstate="collapsed" des="Course Methods">
    public LiveData<List<CourseEntity>> fetchTermCourseData(int pTermId)   {
        if (pTermId < 0)
            return _Db.CourseDAO().getAllCourses();

        return _Db.CourseDAO().getCoursesByTerm(pTermId);
    }

    public void insertCourse(final CourseEntity course) {
        _Executor.execute(new Runnable() {
            @Override
            public void run() {
                _Db.CourseDAO().insertCourse(course);
            }
        });
    }

    public CourseEntity fetchCourseData(final int courseId) {
        CourseNotes = _Db.CourseNoteDAO().getNotesByCourse(courseId);
        Assessments = _Db.AssessmentDAO().fetchAssessments(courseId);
        return _Db.CourseDAO().fetchCourse(courseId);
    }
    //  </editor-fold>

    //  <editor-fold defaultstate="collapsed" des="Assessment Methods">
    public void insertAssessment(final AssessmentEntity assessment) {
        _Executor.execute(new Runnable() {
            @Override
            public void run() {
                _Db.AssessmentDAO().insertAssessment(assessment);
            }
        });
    }

    public AssessmentEntity fetchAssessmentData(int assessmentId) {
        return _Db.AssessmentDAO().fetchAssessment(assessmentId);
    }

    public LiveData<List<AssessmentEntity>> fetchAssessments(int courseId)  {
        Log.i(Constants.LOG_TAG,
                "AppRepository.fetchAssessments(int)");
        Log.i(Constants.LOG_TAG,
                "→\tFetching Assessments for CourseId: " + courseId);
        if (courseId >= 0) {
            Assessments = _Db.AssessmentDAO().fetchAssessments(courseId);
            return Assessments;
        }
        else
            return _Db.AssessmentDAO().fetchAssessments();
    }

    public void deleteAssessment(final AssessmentEntity assessment) {
        _Executor.execute(new Runnable() {
            @Override
            public void run() {
                _Db.AssessmentDAO().deleteAssessment(assessment);
            }
        });
    }
    //  </editor-fold>

    public LiveData<List<CourseNoteEntity>> fetchCourseNotes(int courseId)  {
        return _Db.CourseNoteDAO().getNotesByCourse(courseId);
    }
}
