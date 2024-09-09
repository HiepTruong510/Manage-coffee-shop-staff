package com.example.qlnv.Activity.Admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlnv.Activity.Login;
import com.example.qlnv.Activity.Person.Person_Menu;
import com.example.qlnv.R;

public class Admin_Menu extends AppCompatActivity {
    private ImageView admin_info;
    private ImageView admin_work;
    private ImageView admin_adjust;
    private ImageView admin_salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        admin_info = findViewById(R.id.admin_info_iv);
        admin_work = findViewById(R.id.admin_work_iv);
        admin_adjust = findViewById(R.id.admin_adjust_iv);
        admin_salary = findViewById(R.id.admin_salary_iv);

        admin_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Menu.this, Admin_List_Info.class);
                startActivity(intent);
            }
        });
        admin_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Menu.this, Admin_List_Work.class);
                startActivity(intent);
            }
        });
        admin_adjust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Menu.this, Admin_Adjust.class);
                startActivity(intent);
            }
        });
        admin_salary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Menu.this, Admin_Salary.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận")
                .setMessage("Bạn có muốn thoát ứng dụng?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Thoát ứng dụng
                        //finish();
                        Intent intent = new Intent(Admin_Menu.this, Login.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
