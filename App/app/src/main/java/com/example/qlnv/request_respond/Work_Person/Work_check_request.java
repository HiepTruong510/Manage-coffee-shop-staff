package com.example.qlnv.request_respond.Work_Person;

import java.util.Date;

public class Work_check_request {
    private String ngay_dang_ky;
    private int ca_dang_ky;

    //--------------------


    public Work_check_request(String ngay_dang_ky, int ca_dang_ky) {
        this.ngay_dang_ky = ngay_dang_ky;
        this.ca_dang_ky = ca_dang_ky;
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
}
