package com.example.qlnv.request_respond.Adjust_Admin;

public class Adjust_admin {
    private Boolean acknowledged;
    private int modifiedCount;
    private String upsertedId;
    private int upsertedCount;
    private int matchedCount;
    //------------------------


    public Boolean getAcknowledged() {
        return acknowledged;
    }

    public void setAcknowledged(Boolean acknowledged) {
        this.acknowledged = acknowledged;
    }

    public int getModifiedCount() {
        return modifiedCount;
    }

    public void setModifiedCount(int modifiedCount) {
        this.modifiedCount = modifiedCount;
    }

    public String getUpsertedId() {
        return upsertedId;
    }

    public void setUpsertedId(String upsertedId) {
        this.upsertedId = upsertedId;
    }

    public int getUpsertedCount() {
        return upsertedCount;
    }

    public void setUpsertedCount(int upsertedCount) {
        this.upsertedCount = upsertedCount;
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public void setMatchedCount(int matchedCount) {
        this.matchedCount = matchedCount;
    }
}
