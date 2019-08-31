package com.aanadon.android.anadonc196.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "tbTerm")
public class TermEntity {
    @PrimaryKey(autoGenerate = true)
    private int termId;

    private String termTitle;

    private Date termStart;
    private Date createDate;

    @Ignore
    public TermEntity() {
    }

    public TermEntity(int termId, String termTitle, Date termStart, Date createDate) {
        this.termId = termId;
        this.termTitle = termTitle;
        this.termStart = termStart;
        this.createDate = createDate;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public Date getTermStart() {
        return termStart;
    }

    public void setTermStart(Date termStart) {
        this.termStart = termStart;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * NOTE: There is no endDate value stored in this entity (or the database). This is a calculated
     * value based on the stored startDate value. If there is no valid startDate (== null) then a
     * date six (6) months from today is returned.
     * @return
     */
    public Date getTermEnd()    {
        Calendar EndDate    = Calendar.getInstance();

        if (null != termStart)
            EndDate.setTime(termStart);

        EndDate.add(Calendar.MONTH, 6);

        return EndDate.getTime();
    }

    @Override
    public String toString() {
        return "TermEntity{" +
                "termId=" + termId +
                ", termTitle='" + termTitle + '\'' +
                ", termStart=" + termStart +
                '}';
    }
}
