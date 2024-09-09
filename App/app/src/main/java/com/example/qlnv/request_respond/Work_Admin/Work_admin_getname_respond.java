package com.example.qlnv.request_respond.Work_Admin;

public class Work_admin_getname_respond {
    private Boolean success;
    private  String message;
    private  Work_admin_getname infos;

    //-------------------------

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

    public Work_admin_getname getInfos() {
        return infos;
    }

    public void setInfos(Work_admin_getname infos) {
        this.infos = infos;
    }
}
