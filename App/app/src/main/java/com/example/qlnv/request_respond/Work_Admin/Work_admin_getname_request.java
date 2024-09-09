package com.example.qlnv.request_respond.Work_Admin;

public class Work_admin_getname_request {
    private String iduser;

    //-----

    public Work_admin_getname_request(String iduser) {
        this.iduser = iduser;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }
}
