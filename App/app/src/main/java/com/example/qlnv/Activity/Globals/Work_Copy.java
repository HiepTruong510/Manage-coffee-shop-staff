package com.example.qlnv.Activity.Globals;

import com.example.qlnv.request_respond.Work_Admin.Work_admin_list;

import java.util.List;

public class Work_Copy {
    public static List<Work_admin_list> _listwork;
    public static Work_admin_list _work;

    //-------------------------------

    public static List<Work_admin_list> get_listwork() {
        return _listwork;
    }

    public static void set_listwork(List<Work_admin_list> _listwork) {
        Work_Copy._listwork = _listwork;
    }

    public static Work_admin_list get_work() {
        return _work;
    }

    public static void set_work(Work_admin_list _work) {
        Work_Copy._work = _work;
    }
}
