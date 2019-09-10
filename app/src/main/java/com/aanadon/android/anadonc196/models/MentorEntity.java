package com.aanadon.android.anadonc196.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tbMentor")
public class MentorEntity {

    @PrimaryKey(autoGenerate = true)
    private int mentorId;

    private String mentorName;
    private String mentorPhone;
    private String mentorEmail;

    private Date createDate;

    @Ignore
    public MentorEntity() {
        createDate  = new Date();
    }

    @Ignore
    public MentorEntity(String mentorName, String mentorPhone, String mentorEmail) {
        this.mentorName = mentorName;
        this.mentorPhone = mentorPhone;
        this.mentorEmail = mentorEmail;

        createDate  = new Date();
    }

    public MentorEntity(int mentorId, String mentorName, String mentorPhone, String mentorEmail, Date createDate) {
        this.mentorId = mentorId;
        this.mentorName = mentorName;
        this.mentorPhone = mentorPhone;
        this.mentorEmail = mentorEmail;
        this.createDate = createDate;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMentorPhone() {
        return mentorPhone;
    }

    public void setMentorPhone(String mentorPhone) {
        this.mentorPhone = mentorPhone;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
