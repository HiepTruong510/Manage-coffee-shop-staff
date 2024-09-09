package com.example.qlnv.request_respond.Work_Admin;

import java.util.List;

public class Work_admin_list_respond {
    private Boolean success;
    private  String message;
    private List<Work_admin_list> works;

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

    public List<Work_admin_list> getWorks() {
        return works;
    }

    public void setWorks(List<Work_admin_list> works) {
        this.works = works;
    }
}
