package com.example.qlnv.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlnv.Activity.Admin.Admin_Menu;
import com.example.qlnv.Activity.Globals.MyToken;
import com.example.qlnv.Activity.Globals.New_user;
import com.example.qlnv.Activity.Person.Person_Info;
import com.example.qlnv.Activity.Person.Person_Menu;
import com.example.qlnv.CallAPI.API_Auth;
import com.example.qlnv.R;
import com.example.qlnv.request_respond.Info_Person.Info_person_get;
import com.example.qlnv.request_respond.Info_Person.Info_person_get_respond;
import com.example.qlnv.request_respond.Login.Login_request;
import com.example.qlnv.request_respond.Login.Login_respond;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class Login extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button btn_Login ;
    private TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        btn_Login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.tv_signup);


        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                checkUser();
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
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
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    void checkUser(){
        Login_request user = new Login_request(username.getText().toString(),password.getText().toString()); //("hieppi", "hieppi");
        API_Auth.apiauth.checklogin(user).enqueue(new Callback<Login_respond>() {
            @Override
            public void onResponse(Call<Login_respond> call, Response<Login_respond> response) {
                Login_respond lgin = response.body();
                if(lgin != null && lgin.getSuccess())
                {
                    //Toast.makeText(Login.this, "thah cong",Toast.LENGTH_SHORT).show();
                    MyToken.setToken(lgin.getAccessToken());
                    checkPermission();
                }
                else
                    Toast.makeText(Login.this, "Tài khoản không tồn tại",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Login_respond> call, Throwable t) {
                Toast.makeText(Login.this, "Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
            }
        });
    }

    void checkPermission(){
        String token = "Bearer " + MyToken.getToken();
        API_Auth.apiauth.getInfo(token).enqueue(new Callback<Info_person_get_respond>() {
            @Override
            public void onResponse(Call<Info_person_get_respond> call, Response<Info_person_get_respond> response) {
                Info_person_get_respond info = response.body();
                if(info != null && info.getSuccess())
                {
                    List<Info_person_get> inf = info.getInfos();
                    if(inf!= null && !inf.isEmpty() && inf.size() != 0)
                    {
                        Info_person_get firstInfo = inf.get(0);
                        if( firstInfo.getPermission().equals("quanly") ) {
                            Intent intent = new Intent(Login.this, Admin_Menu.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Intent intent = new Intent(Login.this, Person_Menu.class);
                            New_user.setNew_user(false);
                            startActivity(intent);
                        }
                    }
                    else
                    {
                        Intent intent = new Intent(Login.this, Person_Info.class);
                        New_user.setNew_user(true);
                        startActivity(intent);
                    }
                }
                else
                    Toast.makeText(Login.this, "Không có thông tin",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Info_person_get_respond> call, Throwable t) {
                Toast.makeText(Login.this, "kiểm tra thất bại",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
