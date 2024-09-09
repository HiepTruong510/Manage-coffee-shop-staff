package com.example.qlnv.Activity.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlnv.Activity.Globals.Info_Admin_Copy;
import com.example.qlnv.Activity.Globals.MyToken;
import com.example.qlnv.CallAPI.API_Auth;
import com.example.qlnv.R;
import com.example.qlnv.request_respond.Info_Admin.Info;
import com.example.qlnv.request_respond.Info_Admin.Info_respond;
import com.example.qlnv.request_respond.Salary_Admin.Salary_admin;
import com.example.qlnv.request_respond.Salary_Admin.Salary_admin_request;
import com.example.qlnv.request_respond.Salary_Admin.Salary_admin_respond;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_getname;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_getname_request;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_getname_respond;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Salary extends AppCompatActivity {
    private ListView list_info;
    private TextView nhanvienx_tv;
    private EditText salary;
    private  EditText luongcoban;
    private  EditText tungay;
    private  EditText denngay;
    private  float _Salary = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_salary);

        nhanvienx_tv = findViewById(R.id.nhanvienx_tv);
        list_info = findViewById(R.id.list_info);
        salary = findViewById(R.id.salary);
        luongcoban = findViewById(R.id.basic_salary_edittext);
        tungay =findViewById(R.id.start_date_edittext);
        denngay = findViewById(R.id.end_date_edittext);

        tungay.setText("2023-06-01");
        denngay.setText("2023-06-30");
        luongcoban.setText("25000");
        load_data();
        list_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // gán info click vào vào info cục bộ để hiển thị lên form mới
                _Salary =0;
                Info_Admin_Copy.set_info(Info_Admin_Copy.get_listinfo().get(position));
                nhanvienx_tv.setText("Tiền lương của " + Info_Admin_Copy.get_info().getFullname()+ " là:");
                salary();
                //salary.setText("25000000");
            }
        });
    }
    void load_data() {
        String token = "Bearer " + MyToken.getToken();
        API_Auth.apiauth.get_listInfo(token).enqueue(new Callback<Info_respond>() {
            @Override
            public void onResponse(Call<Info_respond> call, Response<Info_respond> response) {
                Info_respond res_listinfo = response.body();
                if (res_listinfo != null && res_listinfo.getSuccess()) {

                    List<Info> listinfo = res_listinfo.getInfos();
                    Info_Admin_Copy.set_listinfo(listinfo);
                    if (listinfo != null) {
                        //Info info = listinfo.get(0);
                        List<String> items = new ArrayList<>();
                        int count = listinfo.size();
                        for (int i = 0; i < count; i++) {
                            Info info = listinfo.get(i);
                            items.add(info.getFullname() + " - " + info.getPhonenumber());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Admin_Salary.this, android.R.layout.simple_list_item_1, items);
                        list_info.setAdapter(adapter);
                    }

                } else {
                    Toast.makeText(Admin_Salary.this, "khong co thong tin", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Info_respond> call, Throwable t) {
                Toast.makeText(Admin_Salary.this, "get that bai", Toast.LENGTH_SHORT).show();
            }
        });
    }
    void salary(){
        Salary_admin_request req_salary = new Salary_admin_request(
                tungay.getText().toString(),
                denngay.getText().toString(),
                Info_Admin_Copy.get_info().getUser().get_id()
        );
        String token = "Bearer " + MyToken.getToken();
        API_Auth.apiauth.get_admin_salry(token,req_salary).enqueue(new Callback<Salary_admin_respond>() {
            @Override
            public void onResponse(Call<Salary_admin_respond> call, Response<Salary_admin_respond> response) {
                Salary_admin_respond res_list = response.body();
                if(res_list != null && res_list.getSuccess())
                {
                    List<Salary_admin> list_salary = res_list.getSalarys();
                    int count = list_salary.size();
                    for(int i=0; i<count ; i++)
                    {
                        Salary_admin salary = list_salary.get(i);
                        float sal = salary.getHe_so() * (float)salary.getSo_gio_lam() * Float.parseFloat(luongcoban.getText().toString());
                        _Salary = _Salary + sal;
                    }
                    salary.setText(String.valueOf(_Salary));

                }
                else {
                    Toast.makeText(Admin_Salary.this, "Tính lương lỗi",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Salary_admin_respond> call, Throwable t) {
                Toast.makeText(Admin_Salary.this, "Không tính lương được",Toast.LENGTH_SHORT).show();
            }
        });
    }
}