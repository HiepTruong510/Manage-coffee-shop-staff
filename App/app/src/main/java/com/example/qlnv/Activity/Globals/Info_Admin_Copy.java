package com.example.qlnv.Activity.Globals;

import com.example.qlnv.request_respond.Info_Admin.Info;

import java.util.List;

public class Info_Admin_Copy {
    public static List<Info> _listinfo;
    public static Info _info;

//---------------------------------------


    public static Info get_info() {
        return _info;
    }

    public static void set_info(Info _info) {
        Info_Admin_Copy._info = _info;
    }

    public static List<Info> get_listinfo() {
        return _listinfo;
    }

    public static void set_listinfo(List<Info> _listinfo) {
        Info_Admin_Copy._listinfo = _listinfo;
    }
//-------------------------------

}
