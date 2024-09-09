package com.example.qlnv.request_respond.Adjust_Admin;

public class Adjust_admin_respond {
    private Boolean success;
    private  String message;
    private Adjust_admin updatedAdjust;

    //----------------


    public Adjust_admin getUpdatedAdjust() {
        return updatedAdjust;
    }

    public void setUpdatedAdjust(Adjust_admin updatedAdjust) {
        this.updatedAdjust = updatedAdjust;
    }

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

}
