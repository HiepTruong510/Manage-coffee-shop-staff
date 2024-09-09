package com.example.qlnv.request_respond.Info_Person;

public class Info_person_post_respond {
    private Boolean success;
    private String message;
    private Info_person_post infos;

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

    public Info_person_post getInfos() {
        return infos;
    }

    public void setInfos(Info_person_post infos) {
        this.infos = infos;
    }
}
