package com.example.qlnv.Activity.Admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlnv.Activity.Globals.Info_Admin_Copy;
import com.example.qlnv.R;
import com.example.qlnv.request_respond.Info_Admin.Info;

public class Admin_Info extends AppCompatActivity {

    private EditText employee_name_et;
    private EditText employee_phone_et;
    private EditText employee_email_et;
    private EditText employee_birth_year_et;
    private EditText employee_address_et;
    private EditText employee_more_et;
    private Button save_button;
    //private Boolean put = true;
    private String id_info = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_info);

        employee_name_et = findViewById(R.id.employee_name_et);
        employee_phone_et = findViewById(R.id.employee_phone_et);
        employee_email_et = findViewById(R.id.employee_email_et);
        employee_birth_year_et = findViewById(R.id.employee_birth_year_et);
        employee_address_et = findViewById(R.id.employee_address_et);
        employee_more_et = findViewById(R.id.employee_more_et);

        load_info();
    }
    void load_info(){

        Info info = Info_Admin_Copy.get_info();
        //----- add giá trị vào layout -------------
        if(info==null)
        {
            Toast.makeText(Admin_Info.this, "info null",Toast.LENGTH_SHORT).show();
        }
        id_info = info.get_id();
        employee_name_et.setText(info.getFullname());
        employee_phone_et.setText(info.getPhonenumber());
        employee_email_et.setText(info.getEmail());
        employee_birth_year_et.setText(info.getBirthyear());
        employee_address_et.setText(info.getAddress());
        employee_more_et.setText(info.getMore());
        //-------------------------

    }
}