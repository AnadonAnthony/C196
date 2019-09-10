package com.aanadon.android.anadonc196.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aanadon.android.anadonc196.models.AssessmentEntity;

import java.util.List;

import butterknife.OnTextChanged;

@Dao
public interface AssessmentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAssessment(AssessmentEntity assessment);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAssessments(List<AssessmentEntity> assessments);

    @Query("SELECT * FROM tbAssessment WHERE assessmentId=:assessmentId")
    AssessmentEntity fetchAssessment(int assessmentId);

    @Query("SELECT * FROM tbAssessment")
    LiveData<List<AssessmentEntity>> fetchAssessments();

    @Query("SELECT * FROM tbAssessment WHERE courseId=:courseId")
    LiveData<List<AssessmentEntity>> fetchAssessments(int courseId);

    @Delete
    void deleteAssessment(AssessmentEntity assessment);
}
