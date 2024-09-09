package com.example.qlnv.Activity.Person;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlnv.Activity.Globals.MyToken;
import com.example.qlnv.Activity.Login;
import com.example.qlnv.CallAPI.API_Auth;
import com.example.qlnv.R;
import com.example.qlnv.request_respond.Info_Person.Info_person_get;
import com.example.qlnv.request_respond.Info_Person.Info_person_post_respond;
import com.example.qlnv.request_respond.Info_Person.Info_person_request;
import com.example.qlnv.request_respond.Info_Person.Info_person_get_respond;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Person_Info extends AppCompatActivity {

    private EditText employee_name_et;
    private EditText employee_phone_et;
    private EditText employee_email_et;
    private EditText employee_birth_year_et;
    private EditText employee_address_et;
    private EditText employee_more_et;
    private Button save_button;
    private Boolean put = true;
    private String id_info = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);

        employee_name_et = findViewById(R.id.employee_name_et);
        employee_phone_et = findViewById(R.id.employee_phone_et);
        employee_email_et = findViewById(R.id.employee_email_et);
        employee_birth_year_et = findViewById(R.id.employee_birth_year_et);
        employee_address_et = findViewById(R.id.employee_address_et);
        employee_more_et = findViewById(R.id.employee_more_et);
        save_button = findViewById(R.id.save_button);


        load_info();
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_info();
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(!put)
        {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận")
                .setMessage("Bạn có muốn thoát ứng dụng?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Thoát ứng dụng
                        //finish();
                        Intent intent = new Intent(Person_Info.this, Login.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        }
        else {
            Intent intent = new Intent(Person_Info.this, Person_Menu.class);
            startActivity(intent);
        }
    }
    void load_info(){
        String token = "Bearer " + MyToken.getToken();
        API_Auth.apiauth.getInfo(token).enqueue(new Callback<Info_person_get_respond>() {
            @Override
            public void onResponse(Call<Info_person_get_respond> call, Response<Info_person_get_respond> response) {
                Info_person_get_respond res_info = response.body();
                if(res_info != null && res_info.getSuccess())
                {
                    List<Info_person_get> inf = res_info.getInfos();
                    if(inf!= null && !inf.isEmpty() && inf.size() != 0)
                    {
                        Info_person_get info = inf.get(0);
                        //----- add giá trị vào layout -------------
                        id_info = info.get_id();
                        employee_name_et.setText(info.getFullname());
                        employee_phone_et.setText(info.getPhonenumber());
                        employee_email_et.setText(info.getEmail());
                        employee_birth_year_et.setText(info.getBirthyear());
                        employee_address_et.setText(info.getAddress());
                        employee_more_et.setText(info.getMore());
                        put = true;
                        //Toast.makeText(Person_Info.this, " get thah cong",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        put = false;
                    }
                }
                else {
                    Toast.makeText(Person_Info.this, "Thêm thông tin bản thân",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Info_person_get_respond> call, Throwable t) {
                Toast.makeText(Person_Info.this, "get that bai",Toast.LENGTH_SHORT).show();
            }
        });

    }
    void save_info(){
        String token = "Bearer " + MyToken.getToken();
        if(!put)
        {
            Info_person_request info_r = new Info_person_request(
                    "nhanvien",
                    employee_name_et.getText().toString(),
                    employee_phone_et.getText().toString(),
                    employee_email_et.getText().toString(),
                    employee_birth_year_et.getText().toString(),
                    employee_address_et.getText().toString(),
                    employee_more_et.getText().toString()
            );

            API_Auth.apiauth.postInfo(token,info_r).enqueue(new Callback<Info_person_post_respond>() {
                @Override
                public void onResponse(Call<Info_person_post_respond> call, Response<Info_person_post_respond> response) {
                   /* Info_person_post_respond res_info = response.body();
                    if(res_info != null && res_info.getSuccess())
                    {
                        Toast.makeText(Person_Info.this, "Tạo thông tin thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Person_Info.this, Person_Menu.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(Person_Info.this, "Tạo thông tin thất bại",Toast.LENGTH_SHORT).show();
                    }*/
                    Toast.makeText(Person_Info.this, "Tạo thông tin thành công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Person_Info.this, Person_Menu.class);
                    startActivity(intent);
                }
                @Override
                public void onFailure(Call<Info_person_post_respond> call, Throwable t) {
                    Toast.makeText(Person_Info.this, "Không tạo được thông tin",Toast.LENGTH_SHORT).show();

                 }
            });
        }
        else {
            Info_person_request info_r = new Info_person_request(
                    "nhanvien",
                    employee_name_et.getText().toString(),
                    employee_phone_et.getText().toString(),
                    employee_email_et.getText().toString(),
                    employee_birth_year_et.getText().toString(),
                    employee_address_et.getText().toString(),
                    employee_more_et.getText().toString()
            );

            API_Auth.apiauth.putInfo(token,id_info,info_r).enqueue(new Callback<Info_person_post_respond>() {
                @Override
                public void onResponse(Call<Info_person_post_respond> call, Response<Info_person_post_respond> response) {
                    Info_person_post_respond res_info = response.body();
                    if(res_info != null && res_info.getSuccess())
                    {
                        Toast.makeText(Person_Info.this, "Chỉnh sửa thành công",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Person_Info.this, "Chỉnh sữa thất bại",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Info_person_post_respond> call, Throwable t) {
                    Toast.makeText(Person_Info.this, "Không chỉnh sửa được",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}