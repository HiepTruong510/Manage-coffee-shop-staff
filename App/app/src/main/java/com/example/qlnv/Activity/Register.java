package com.example.qlnv.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlnv.CallAPI.API_Auth;
import com.example.qlnv.R;
import com.example.qlnv.request_respond.Register.Register_request;
import com.example.qlnv.request_respond.Register.Register_respond;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText confirmpassword;
    private Button btn_register ;
    private TextView tv_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        confirmpassword = findViewById(R.id.et_confirmpassword);
        btn_register = findViewById(R.id.btn_register);
        tv_login = findViewById(R.id.tv_login);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirmpassword.getText().toString() != password.getText().toString())
                {
                    Toast.makeText(Register.this, "Xác nhận mật khẩu sai",Toast.LENGTH_SHORT).show();
                }
                dangkytk();
            }
        });
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }

    void dangkytk(){
        Register_request user = new Register_request(username.getText().toString(),password.getText().toString()); //("hieppi", "hieppi");
        API_Auth.apiauth.dangky(user).enqueue(new Callback<Register_respond>() {
            @Override
            public void onResponse(Call<Register_respond> call, Response<Register_respond> response) {
                Register_respond regis = response.body();
                if(regis != null && regis.getSuccess())
                {
                    Toast.makeText(Register.this, "thah cong",Toast.LENGTH_SHORT).show();
                    //username.setText("thanh cong roi do"

                }
            }

            @Override
            public void onFailure(Call<Register_respond> call, Throwable t) {
                Toast.makeText(Register.this, "that bai",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
