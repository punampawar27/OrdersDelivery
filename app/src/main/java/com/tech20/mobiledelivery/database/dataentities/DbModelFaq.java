package com.tech20.mobiledelivery.database.dataentities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.tech20.mobiledelivery.helpers.Constants;


@Entity(tableName = Constants.DatabaseConstants.TABLE_FAQ.TABLE_NAME_FAQ,
        indices = {@Index(value = Constants.DatabaseConstants.TABLE_FAQ.COLUMN_NAME_FAQ_ID, unique = true)})
public class DbModelFaq {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.DatabaseConstants.COLUMN_NAME_AUTOINCREMENT_ID)
    private long id=0;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_FAQ.COLUMN_NAME_FAQ_ID)
    private String faqId=null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_FAQ.COLUMN_NAME_QUESTION)
    private String question=null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_FAQ.COLUMN_NAME_ANSWER)
    private String answer=null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_FAQ.COLUMN_NAME_DATECREATED)
    private String dateCreated=null;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFaqId() {
        return faqId;
    }

    public void setFaqId(String faqId) {
        this.faqId = faqId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
