package com.example.qlnv.request_respond.Info_Admin;

import com.example.qlnv.request_respond.Info_Admin.Info;

import java.util.List;

public class Info_respond {
    private Boolean success;
    private  String message;
    private List<Info> infos;


//----------------------------------
    public String getMessage() {
    return message;
}

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Info> getInfos() {
        return infos;
    }

    public void setInfos(List<Info> infos) {
        this.infos = infos;
    }

}
