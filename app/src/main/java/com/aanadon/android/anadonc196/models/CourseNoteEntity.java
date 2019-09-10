package com.aanadon.android.anadonc196.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tbCourseNote")
public class CourseNoteEntity {
    @PrimaryKey(autoGenerate = true)
    private int noteId;
    private int courseId;

    private String userName;
    private String noteText;

    private Date createDate;

    @Ignore
    public CourseNoteEntity() {
        createDate  = new Date();
    }

    @Ignore
    public CourseNoteEntity(int courseId, String userName, String noteText) {
        this.courseId = courseId;
        this.userName = userName;
        this.noteText = noteText;

        createDate  = new Date();
    }

    public CourseNoteEntity(int noteId, int courseId, String userName, String noteText, Date createDate) {
        this.noteId = noteId;
        this.courseId = courseId;
        this.userName = userName;
        this.noteText = noteText;
        this.createDate = createDate;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
