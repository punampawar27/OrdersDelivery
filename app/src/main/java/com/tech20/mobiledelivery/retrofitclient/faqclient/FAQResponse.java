package com.tech20.mobiledelivery.retrofitclient.faqclient;

import com.google.gson.annotations.SerializedName;


public class FAQResponse {


    @SerializedName("FAQId")
    private String faqId = null;

    @SerializedName("Question")
    private String question = null;

    @SerializedName("Answer")
    private String answer = null;

    @SerializedName("SourceId")
    private String sourceId = null;

    @SerializedName("RaisedBy")
    private String raisedBy = null;

    @SerializedName("DateCreated")
    private String dateCreated = null;

    @SerializedName("ClientId")
    private String clientId = null;

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

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getRaisedBy() {
        return raisedBy;
    }

    public void setRaisedBy(String raisedBy) {
        this.raisedBy = raisedBy;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
