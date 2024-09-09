package com.example.qlnv.request_respond.Info_Admin;

public class Info {
    private String _id;
    private String permission;
    private String fullname;
    private String phonenumber;
    private String email;
    private String more;
    private String birthyear;
    private String address;
    private Info_user user;
    private int __v;


//----------------------
    public int get__v() {
    return __v;
}

    public void set__v(int __v) {
        this.__v = __v;
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public Info_user getUser() {
        return user;
    }

    public void setUser(Info_user user) {
        this.user = user;
    }
}
