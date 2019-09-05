package com.aanadon.android.anadonc196.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tbTermNote")
public class TermNoteEntity {

    public static final String PRIMARY_KEY  = "noteId";

    @PrimaryKey(autoGenerate = true)
    private int noteId;
    private int termId;
    private String userName;
    private String noteText;
    private Date createDate;

    @Ignore
    public TermNoteEntity() {
        this.createDate = new Date();
    }

    @Ignore
    public TermNoteEntity(int termId, String userName, String noteText) {
        this.termId = termId;
        this.userName = userName;
        this.noteText = noteText;
        this.createDate = new Date();
    }

    public TermNoteEntity(int noteId, int termId, String userName, String noteText, Date createDate) {
        this.noteId = noteId;
        this.termId = termId;
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

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
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
