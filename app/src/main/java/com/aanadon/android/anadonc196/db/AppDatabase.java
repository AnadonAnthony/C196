package com.aanadon.android.anadonc196.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.aanadon.android.anadonc196.models.AssessmentEntity;
import com.aanadon.android.anadonc196.models.AssessmentNoteEntity;
import com.aanadon.android.anadonc196.models.CourseEntity;
import com.aanadon.android.anadonc196.models.CourseMentorEntity;
import com.aanadon.android.anadonc196.models.CourseNoteEntity;
import com.aanadon.android.anadonc196.models.MentorEntity;
import com.aanadon.android.anadonc196.models.TermEntity;
import com.aanadon.android.anadonc196.models.TermNoteEntity;
import com.aanadon.android.anadonc196.utilities.Converter_Date;

@Database(entities = {TermEntity.class, CourseEntity.class, TermNoteEntity.class,
                        CourseMentorEntity.class, CourseNoteEntity.class,
                        AssessmentEntity.class, AssessmentNoteEntity.class,
                        MentorEntity.class}, version = 5, exportSchema = false)
@TypeConverters(Converter_Date.class)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME    = "C196.db";

    //  Volatile == ONLY REFERENCED FROM MAIN MEMORY
    private static volatile AppDatabase _Instance;
    private static final Object _Lock = new Object();

    // TODO: 8/31/2019 ONE FOR EACH DAO INSTANCE
    public abstract TermDAO TermDAO();
    public abstract TermNoteDAO TermNoteDAO();
    public abstract CourseDAO CourseDAO();
    public abstract CourseNoteDAO CourseNoteDAO();
    public abstract CourseMentorDAO CourseMentorDAO();
    public abstract AssessmentDAO AssessmentDAO();
    public abstract AssessmentNoteDAO AssessmentNoteDAO();
    public abstract MentorDAO MentorDAO();

    public static AppDatabase getInstance(Context pContext) {
        if (null == _Instance) {
            synchronized (_Lock)    {
                if (null == _Instance)  {
                    _Instance = Room.databaseBuilder(pContext.getApplicationContext(),
                        AppDatabase.class,
                        DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
                }
            }
        }
        return _Instance;
    }
}
