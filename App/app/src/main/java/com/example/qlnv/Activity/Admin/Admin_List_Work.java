package com.example.qlnv.Activity.Admin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlnv.Activity.Globals.MyToken;
import com.example.qlnv.Activity.Globals.Work_Copy;
import com.example.qlnv.CallAPI.API_Auth;
import com.example.qlnv.R;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_getname;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_getname_request;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_getname_respond;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_list;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_list_request;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_list_respond;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_list_user;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_put_request;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_put_respond;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_List_Work extends AppCompatActivity {
    private DatePicker chonngay;
    private RadioGroup chonca;
    private RadioButton ca1;
    private RadioButton ca2;
    private RadioButton ca3;
    private TextView name_list;
    private String Ndk ;
    private Calendar NgayDK = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private  int Ca =  0;
    private ListView dslv;

    private  RadioGroup tinhtrang;
    private  RadioButton choduyet;
    private  RadioButton daduyet;
    private  String loaiduyet="choduyet";

    List<String> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_work);

        chonngay = findViewById(R.id.datePicker);
        chonca =findViewById(R.id.radioGroup);
        ca1 =findViewById(R.id.radioButtonMorning);
        ca2 =findViewById(R.id.radioButtonAfternoon);
        ca3 =findViewById(R.id.radioButtonEvening);
        dslv=findViewById(R.id.listView);
        name_list= findViewById(R.id.name_list);
        tinhtrang =findViewById(R.id.statusRadioGroup);
        choduyet =findViewById(R.id.pendingRadioButton);
        daduyet =findViewById(R.id.approvedRadioButton);
        choduyet.setChecked(true);

        Date date = NgayDK.getTime();
        String isoDate = dateFormat.format(date);
        Ndk = isoDate;
        chonngay.init(chonngay.getYear(), chonngay.getMonth(), chonngay.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        NgayDK.set(Calendar.YEAR, year);
                        NgayDK.set(Calendar.MONTH, monthOfYear);
                        NgayDK.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        NgayDK.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                        Date date = NgayDK.getTime();
                        String isoDate = dateFormat.format(date);
                        Ndk = isoDate;

                    }
                });
        tinhtrang.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Xử lý khi RadioButton được chọn
                if (checkedId == R.id.pendingRadioButton) {
                    // Xử lý khi RadioButton thứ nhất được chọn
                    loaiduyet="choduyet";

                } else if (checkedId == R.id.approvedRadioButton) {
                    // Xử lý khi RadioButton thứ hai được chọn
                    loaiduyet="daduyet";
                }
            }
        });
        chonca.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Xử lý khi RadioButton được chọn
                if (checkedId == R.id.radioButtonMorning) {
                    // Xử lý khi RadioButton thứ nhất được chọn
                    name_list.setText("Danh sách nhân viên đăng ký ca 1:");
                    Ca = 1;
                    load_listwork();

                } else if (checkedId == R.id.radioButtonAfternoon) {
                    // Xử lý khi RadioButton thứ hai được chọn
                    name_list.setText("Danh sách nhân viên đăng ký ca 2:");
                    Ca = 2;
                    load_listwork();

                } else if (checkedId == R.id.radioButtonEvening) {
                    // Xử lý khi RadioButton thứ ba được chọn
                    name_list.setText("Danh sách nhân viên đăng ký ca 3:");
                    Ca = 3;
                    load_listwork();
                }

            }
        });
        dslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // gán info click vào vào info cục bộ để hiển thị lên form mới
                Work_Copy.set_work(Work_Copy.get_listwork().get(position));
                //Intent intent = new Intent(Admin_List_Work.this, Admin_Info.class);
                //startActivity(intent);
                if(loaiduyet.equals("daduyet"))
                    return;
                AlertDialog.Builder builder = new AlertDialog.Builder(Admin_List_Work.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn muốn duyệt hay hủy?");
                builder.setPositiveButton("Duyệt", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý sự kiện khi người dùng nhấn nút "Duyệt"
                        loaiduyet= "daduyet";
                        categorize_work();
                        load_listwork();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Không duyệt", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý sự kiện khi người dùng nhấn nút "Không duyệt"
                        loaiduyet ="khongduyet";
                        categorize_work();
                        load_listwork();
                        dialog.dismiss();
                    }
                });

                // Thêm nút "Hủy" vào hộp thoại
                builder.setNeutralButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý sự kiện khi người dùng nhấn nút "Hủy"
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                // Đặt vị trí của nút "Hủy" ở bên trái
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button neutralButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEUTRAL);
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) neutralButton.getLayoutParams();
                        params.gravity = Gravity.START;
                        neutralButton.setLayoutParams(params);
                    }
                });

                dialog.show();
            }
        });

    }

    void load_listwork(){

        Work_admin_list_request worklist_req = new Work_admin_list_request(
                Ndk,
                Ca,
                loaiduyet
        );
        String token = "Bearer " + MyToken.getToken();
        API_Auth.apiauth.get_listwork(token,worklist_req).enqueue(new Callback<Work_admin_list_respond>() {
            @Override
            public void onResponse(Call<Work_admin_list_respond> call, Response<Work_admin_list_respond> response) {
                Work_admin_list_respond res_listwork = response.body();
                items.clear();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Admin_List_Work.this, android.R.layout.simple_list_item_1, items);
                dslv.setAdapter(adapter);
                if(res_listwork != null && res_listwork.getSuccess())
                {

                    //load_info();
                    //Toast.makeText(Person_Work.this, " get thah cong",Toast.LENGTH_SHORT).show();
                    List<Work_admin_list> r_work = res_listwork.getWorks();
                    Work_Copy.set_listwork(r_work); //---- set_listword
                    if(r_work != null) {
                        int count = r_work.size();
                        for (int i = 0; i < count; i++) {
                            Work_admin_list work = r_work.get(i);
                            /*
                            List<Work_admin_list_user> user_info = worklist.getUser_info();
                            Work_admin_list_user user = user_info.get(0);*/

                            //items.add(work.get_id()+ " - Ca" + work.getCa_dang_ky());
                            int cadangky =work.getCa_dang_ky();
                            load_name_on_item(work.getUser(),cadangky);

                        }


                    }
                }
                else {
                    Toast.makeText(Admin_List_Work.this, "get fails",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Work_admin_list_respond> call, Throwable t) {
                Toast.makeText(Admin_List_Work.this, "kh get đc",Toast.LENGTH_SHORT).show();

            }
        });
    }
    void load_name_on_item(String iduser, int cadangky ){
        Work_admin_getname_request getname_req = new Work_admin_getname_request(
                iduser
        );
        String token = "Bearer " + MyToken.getToken();
            API_Auth.apiauth.get_Fullname(token,getname_req).enqueue(new Callback<Work_admin_getname_respond>() {
                @Override
                public void onResponse(Call<Work_admin_getname_respond> call, Response<Work_admin_getname_respond> response) {
                    Work_admin_getname_respond res_name = response.body();
                    if(res_name != null && res_name.getSuccess())
                    {
                        Work_admin_getname name = res_name.getInfos();
                        items.add(name.getFullname()+ " - Ca" + cadangky);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Admin_List_Work.this, android.R.layout.simple_list_item_1, items);
                        dslv.setAdapter(adapter);
                    }
                    else {
                        Toast.makeText(Admin_List_Work.this, "get name fails",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Work_admin_getname_respond> call, Throwable t) {
                    Toast.makeText(Admin_List_Work.this, "kh get name đc",Toast.LENGTH_SHORT).show();
                }
            });
    }
    void categorize_work(){
        Work_admin_put_request worklist_req = new Work_admin_put_request(
                loaiduyet
        );
        String token = "Bearer " + MyToken.getToken();
        API_Auth.apiauth.put_listwork(token,Work_Copy.get_work().get_id(), worklist_req).enqueue(new Callback<Work_admin_put_respond>() {
            @Override
            public void onResponse(Call<Work_admin_put_respond> call, Response<Work_admin_put_respond> response) {
                Work_admin_put_respond res_listwork = response.body();
                if(res_listwork != null && res_listwork.getSuccess()) {
                    Toast.makeText(Admin_List_Work.this, "Duyệt tc",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Work_admin_put_respond> call, Throwable t) {

            }
        });
    }
}