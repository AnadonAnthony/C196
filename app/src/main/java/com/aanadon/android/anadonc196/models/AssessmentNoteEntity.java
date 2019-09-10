package com.aanadon.android.anadonc196.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tbAssessmentNote")
public class AssessmentNoteEntity {
    @PrimaryKey(autoGenerate = true)
    private int noteId;
    private int assessmentId;

    private String userName;
    private String noteText;

    private Date createDate;

    @Ignore
    public AssessmentNoteEntity() {
        createDate  = new Date();
    }

    @Ignore
    public AssessmentNoteEntity(int termId, String userName, String noteText) {
        this.assessmentId = termId;
        this.userName = userName;
        this.noteText = noteText;
        createDate  = new Date();
    }

    public AssessmentNoteEntity(int noteId, int assessmentId, String userName, String noteText, Date createDate) {
        this.noteId = noteId;
        this.assessmentId = assessmentId;
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

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
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
