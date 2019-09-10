package com.aanadon.android.anadonc196.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aanadon.android.anadonc196.models.CourseNoteEntity;

import java.util.List;

@Dao
public interface CourseNoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(CourseNoteEntity pNote);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CourseNoteEntity> pNotes);

    @Delete
    void deleteNote(CourseNoteEntity pNote);

    @Query("SELECT * FROM tbCourseNote WHERE noteId = :pNoteId ORDER BY createDate DESC")
    CourseNoteEntity getNote(int pNoteId);

    @Query("SELECT * FROM tbCourseNote WHERE courseID = :pCourseId ORDER BY createDate DESC")
    LiveData<List<CourseNoteEntity>> getNotesByCourse(int pCourseId);
}
