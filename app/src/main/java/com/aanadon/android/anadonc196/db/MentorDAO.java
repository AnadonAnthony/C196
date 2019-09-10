package com.aanadon.android.anadonc196.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aanadon.android.anadonc196.models.MentorEntity;

import java.util.List;

@Dao
public interface MentorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMentor(MentorEntity pMentor);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllMentors(List<MentorEntity> pMentors);

    @Delete
    void deleteMentor(MentorEntity pMentor);

    @Query("SELECT * FROM tbMentor WHERE mentorId = :pMentorId ORDER BY mentorName")
    MentorEntity getMentorById(int pMentorId);

    @Query("SELECT * FROM tbMentor ORDER BY mentorName")
    LiveData<List<MentorEntity>> getMentors();
}
