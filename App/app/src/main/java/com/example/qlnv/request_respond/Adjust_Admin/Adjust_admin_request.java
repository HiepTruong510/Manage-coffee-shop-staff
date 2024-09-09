package com.example.qlnv.request_respond.Adjust_Admin;

public class Adjust_admin_request {
    private String ngay_dang_ky;
    private int ca_dang_ky;
    private float heso;

    //-----------------------

    public Adjust_admin_request(String ngay_dang_ky, int ca_dang_ky, float heso) {
        this.ngay_dang_ky = ngay_dang_ky;
        this.ca_dang_ky = ca_dang_ky;
        this.heso = heso;
    }

    public String getNgay_dang_ky() {
        return ngay_dang_ky;
    }

    public void setNgay_dang_ky(String ngay_dang_ky) {
        this.ngay_dang_ky = ngay_dang_ky;
    }

    public int getCa_dang_ky() {
        return ca_dang_ky;
    }

    public void setCa_dang_ky(int ca_dang_ky) {
        this.ca_dang_ky = ca_dang_ky;
    }

    public float getHeso() {
        return heso;
    }

    public void setHeso(float heso) {
        this.heso = heso;
    }
}
