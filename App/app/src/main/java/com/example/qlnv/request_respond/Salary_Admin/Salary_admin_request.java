package com.example.qlnv.request_respond.Salary_Admin;

public class Salary_admin_request {
    private String tungay;
    private String denngay;
    private String iduser;

    //-----------------------


    public Salary_admin_request(String tungay, String denngay, String iduser) {
        this.tungay = tungay;
        this.denngay = denngay;
        this.iduser = iduser;
    }

    public String getTungay() {
        return tungay;
    }

    public void setTungay(String tungay) {
        this.tungay = tungay;
    }

    public String getDenngay() {
        return denngay;
    }

    public void setDenngay(String denngay) {
        this.denngay = denngay;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }
}
