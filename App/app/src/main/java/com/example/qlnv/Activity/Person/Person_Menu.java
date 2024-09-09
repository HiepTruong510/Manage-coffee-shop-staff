package com.example.qlnv.Activity.Person;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlnv.Activity.Admin.Admin_List_Info;
import com.example.qlnv.Activity.Login;
import com.example.qlnv.R;

public class Person_Menu extends AppCompatActivity {
    private ImageView person_info;
    private ImageView person_work;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_menu);


        person_info = findViewById(R.id.person_info_iv);
        person_work = findViewById(R.id.person_work_iv);

        person_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Person_Menu.this, Person_Info.class);
                startActivity(intent);
            }
        });
        person_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Person_Menu.this, Person_Work.class);
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
                        Intent intent = new Intent(Person_Menu.this, Login.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}