package com.example.qlnv.request_respond.Info_Person;

public class Info_person_request {
    private String permission;
    private String fullname;
    private String phonenumber;
    private String email;
    private String birthyear;
    private String address;
    private String more;

    public Info_person_request(String permission, String fullname, String phonenumber, String email, String birthyear, String address, String more) {
        this.permission = permission;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.birthyear = birthyear;
        this.address = address;
        this.more = more;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }
}
