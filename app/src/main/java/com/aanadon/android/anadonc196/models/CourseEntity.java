package com.aanadon.android.anadonc196.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tbCourse")
public class CourseEntity {

    public static final String PRIMARY_KEY  = "courseId";

    @PrimaryKey(autoGenerate = true)
    private int courseId;
    private int termId;
    private int courseStatus;

    private String courseTitle;

    private Date endDate;
    private Date startDate;
    private Date createDate;

    @Ignore
    public CourseEntity() {
    }

    @Ignore
    public CourseEntity(int termId, String courseTitle, Date endDate, Date startDate) {
        this.termId = termId;
        this.courseTitle = courseTitle;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public CourseEntity(int courseId, int termId, int courseStatus, String courseTitle, Date endDate, Date startDate, Date createDate) {
        this.courseId = courseId;
        this.termId = termId;
        this.courseStatus = courseStatus;
        this.courseTitle = courseTitle;
        this.endDate = endDate;
        this.startDate = startDate;
        this.createDate = createDate;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public int getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(int courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
