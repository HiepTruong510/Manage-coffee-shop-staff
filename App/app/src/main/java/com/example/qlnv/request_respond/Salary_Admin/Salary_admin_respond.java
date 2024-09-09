package com.example.qlnv.request_respond.Salary_Admin;

import java.util.List;

public class Salary_admin_respond {
    private Boolean success;
    private  String message;
    private List<Salary_admin> salarys;

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

    public List<Salary_admin> getSalarys() {
        return salarys;
    }

    public void setSalarys(List<Salary_admin> salarys) {
        this.salarys = salarys;
    }
}
