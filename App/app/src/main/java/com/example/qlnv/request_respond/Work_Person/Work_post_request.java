package com.example.qlnv.request_respond.Work_Person;

public class Work_post_request {
    private String ngay_dang_ky;
    private int ca_dang_ky;
    private String tinh_trang;

    //--------------------------------------

    public Work_post_request(String ngay_dang_ky, int ca_dang_ky, String tinh_trang) {
        this.ngay_dang_ky = ngay_dang_ky;
        this.ca_dang_ky = ca_dang_ky;
        this.tinh_trang = tinh_trang;
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

    public String getTinh_trang() {
        return tinh_trang;
    }

    public void setTinh_trang(String tinh_trang) {
        this.tinh_trang = tinh_trang;
    }
}
