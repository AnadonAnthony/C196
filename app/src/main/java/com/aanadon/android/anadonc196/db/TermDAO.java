package com.aanadon.android.anadonc196.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aanadon.android.anadonc196.models.TermEntity;

import java.util.List;

@Dao
public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTerm(TermEntity pNewTerm);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TermEntity> pTermList);

    @Delete
    void deleteTerm(TermEntity pOldTerm);

    @Query("SELECT * FROM tbTerm WHERE termId = :pTermId")
    TermEntity getTermById(int pTermId);

    @Query("SELECT * FROM tbTerm ORDER BY termStart")
    LiveData<List<TermEntity>> getAllTerms();

    @Query("DELETE FROM tbTerm")
    int deleteAllTerms();

    @Query("SELECT COUNT(*) FROM tbTerm")
    int getCount();

    @Query("SELECT termId FROM tbTerm ORDER BY termId DESC LIMIT 1")
    int getLastTermId();
}
