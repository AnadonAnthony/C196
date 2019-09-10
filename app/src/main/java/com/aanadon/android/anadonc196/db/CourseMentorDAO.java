package com.aanadon.android.anadonc196.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aanadon.android.anadonc196.models.CourseMentorEntity;

import java.util.List;

@Dao
public interface CourseMentorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourseMentor(CourseMentorEntity pCourseMentor);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourseMentors(List<CourseMentorEntity> pCourseMentors);

    @Delete
    void deleteCourseMentor(CourseMentorEntity pCourseMentor);

    @Query("SELECT * FROM tbCourseMentor WHERE entityId=:pEntityId")
    CourseMentorEntity getDetails(int pEntityId);

    @Query("SELECT * FROM tbCourseMentor WHERE courseId=:pCourseId")
    LiveData<List<CourseMentorEntity>> getCourseMentors(int pCourseId);
}
