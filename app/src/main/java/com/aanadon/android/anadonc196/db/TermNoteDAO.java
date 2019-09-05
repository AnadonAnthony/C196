package com.aanadon.android.anadonc196.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aanadon.android.anadonc196.models.TermNoteEntity;

import java.util.List;

@Dao
public interface TermNoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTermNote(TermNoteEntity pNote);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllTermNotes(List<TermNoteEntity> pNotes);

    @Delete
    void deleteTermNote(TermNoteEntity pOldNote);

    @Query("SELECT * FROM tbTermNote WHERE noteId = :pNoteId")
    TermNoteEntity getTermNoteById(int pNoteId);

    @Query("SELECT * FROM tbTermNote ORDER BY createDate DESC")
    LiveData<List<TermNoteEntity>> getAllTermNotes();

    @Query("SELECT * FROM tbTermNote WHERE termId = :pTermId ORDER BY createDate DESC")
    LiveData<List<TermNoteEntity>> getTermNotes(int pTermId);

    @Query("DELETE FROM tbTermNote")
    int deleteAllTermNotes();

    @Query("DELETE FROM tbTermNote WHERE termId = :pTermId")
    int deleteTermNotes(int pTermId);

    @Query("SELECT COUNT(*) FROM tbTermNote")
    int getTotalTermNoteCount();

    @Query("SELECT COUNT(*) FROM tbTermNote WHERE termId = :pTermId")
    int getTermNoteCount(int pTermId);
}
