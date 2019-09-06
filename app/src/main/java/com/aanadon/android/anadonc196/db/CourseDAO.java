package com.aanadon.android.anadonc196.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aanadon.android.anadonc196.models.CourseEntity;

import java.util.List;

@Dao
public interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourse(CourseEntity pCourse);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourses(List<CourseEntity> pCourses);

    @Delete
    void deleteCourse(CourseEntity pCourse);

    @Query("SELECT * FROM tbCourse WHERE termId = :pTermId")
    LiveData<List<CourseEntity>> getCoursesByTerm(int pTermId);

    @Query("SELECT * FROM tbCourse")
    LiveData<List<CourseEntity>> getAllCourses();

    @Query("DELETE FROM tbCourse WHERE termId = :pTermId")
    int deleteCourses(int pTermId);

    @Query("DELETE FROM tbCourse")
    int deleteAllCourses();

    @Query("SELECT COUNT(*) FROM tbCourse")
    int getCourseCount();

    @Query("SELECT COUNT(*) FROM tbCourse WHERE termId = :pTermId")
    int getCourseCount(int pTermId);

    @Query("SELECT * FROM tbCourse WHERE courseId = :pCourseId")
    CourseEntity fetchCourse(int pCourseId);
}
