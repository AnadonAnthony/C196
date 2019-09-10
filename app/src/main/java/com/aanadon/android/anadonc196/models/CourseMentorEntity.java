package com.aanadon.android.anadonc196.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbCourseMentor")
public class CourseMentorEntity {
    @PrimaryKey(autoGenerate = true)
    private int entityId;
    private int courseId;
    private int mentorId;

    @Ignore
    public CourseMentorEntity() {
    }

    @Ignore
    public CourseMentorEntity(int courseId, int mentorId) {
        this.courseId = courseId;
        this.mentorId = mentorId;
    }

    public CourseMentorEntity(int entityId, int courseId, int mentorId) {
        this.entityId = entityId;
        this.courseId = courseId;
        this.mentorId = mentorId;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }
}
