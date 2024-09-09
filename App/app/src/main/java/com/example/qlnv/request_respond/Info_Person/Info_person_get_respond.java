package com.example.qlnv.request_respond.Info_Person;

import java.util.List;

public class Info_person_get_respond {
    private Boolean success;
    private String message;
    private List<Info_person_get> infos;

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

    public List<Info_person_get> getInfos() {
        return infos;
    }

    public void setInfos(List<Info_person_get> infos) {
        this.infos = infos;
    }
}
