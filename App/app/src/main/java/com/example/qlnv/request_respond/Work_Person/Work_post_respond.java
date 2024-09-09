package com.example.qlnv.request_respond.Work_Person;

import java.util.List;

public class Work_post_respond {
    private Boolean success;
    private String message;
    private Work works;

    //-------------------------------

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Work getWorks() {
        return works;
    }

    public void setWorks(Work works) {
        this.works = works;
    }
}
