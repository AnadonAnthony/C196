package com.aanadon.android.anadonc196.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tbAssessment")
public class AssessmentEntity {
    public static final String PRIMARY_KEY  = "assessmentId";

    @PrimaryKey(autoGenerate = true)
    private int assessmentId;
    private int courseId;
    private int assessmentType;

    private String assessmentName;

    private Date createDate;

    @Ignore
    public AssessmentEntity() {
        createDate  = new Date();
    }

    @Ignore
    public AssessmentEntity(int courseId, int assessmentType, String assessmentName) {
        this.courseId   = courseId;
        this.assessmentType = assessmentType;
        this.assessmentName = assessmentName;

        createDate  = new Date();
    }

    public AssessmentEntity(int assessmentId, int courseId, int assessmentType, String assessmentName, Date createDate) {
        this.assessmentId = assessmentId;
        this.courseId   = courseId;
        this.assessmentType = assessmentType;
        this.assessmentName = assessmentName;
        this.createDate = createDate;
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(int assessmentType) {
        this.assessmentType = assessmentType;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
