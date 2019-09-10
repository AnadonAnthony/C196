package com.aanadon.android.anadonc196.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aanadon.android.anadonc196.models.AssessmentNoteEntity;

import java.util.List;

@Dao
public interface AssessmentNoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(AssessmentNoteEntity pNote);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNotes(List<AssessmentNoteEntity> pNotes);

    @Delete
    void deleteNote(AssessmentNoteEntity pNote);

    @Query("SELECT * FROM tbAssessmentNote WHERE noteId=:pId")
    AssessmentNoteEntity getNoteDetails(int pId);

    @Query("SELECT * FROM tbAssessmentNote WHERE assessmentId=:pId")
    LiveData<List<AssessmentNoteEntity>> getNotesForAssessment(int pId);
}
