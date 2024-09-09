package com.example.qlnv.Activity.Person;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlnv.Activity.Globals.MyToken;
import com.example.qlnv.CallAPI.API_Auth;
import com.example.qlnv.R;
import com.example.qlnv.request_respond.Work_Person.Work_check_request;
import com.example.qlnv.request_respond.Work_Person.Work_post_request;
import com.example.qlnv.request_respond.Work_Person.Work;
import com.example.qlnv.request_respond.Work_Person.Work_check_respond;
import com.example.qlnv.request_respond.Work_Person.Work_post_respond;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Person_Work extends AppCompatActivity {

    private DatePicker chonngay;
    private RadioGroup chonca;
    private RadioButton ca1;
    private RadioButton ca2;
    private RadioButton ca3;
    private TextView Message;
    private Button btn_Submit;
    private Button btn_cancel;
    private String Ndk ;
    private Calendar NgayDK = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private  int Ca =  0;
    private String id_work = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_work);

        chonngay = findViewById(R.id.datePicker);
        chonca =findViewById(R.id.radioGroup);
        ca1 =findViewById(R.id.radioButtonMorning);
        ca2 =findViewById(R.id.radioButtonAfternoon);
        ca3 =findViewById(R.id.radioButtonEvening);
        Message =findViewById(R.id.message_tv);
        btn_Submit =findViewById(R.id.btn_Submit);
        btn_cancel =findViewById(R.id.btn_Cancel);

        btn_Submit.setVisibility(View.GONE);
        btn_cancel.setVisibility(View.GONE);
        Date date = NgayDK.getTime();
        String isoDate = dateFormat.format(date);
        Ndk = isoDate;
        chonngay.init(chonngay.getYear(), chonngay.getMonth(), chonngay.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Xử lý sự kiện khi người dùng chọn ngày
                        //String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        //textView.setText(date);
                        NgayDK.set(Calendar.YEAR, year);
                        NgayDK.set(Calendar.MONTH, monthOfYear);
                        NgayDK.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        NgayDK.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                        Date date = NgayDK.getTime();
                        String isoDate = dateFormat.format(date);
                        Ndk = isoDate;

                    }
                });
        chonca.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Xử lý khi RadioButton được chọn
                if (checkedId == R.id.radioButtonMorning) {
                    // Xử lý khi RadioButton thứ nhất được chọn
                    Ca = 1;
                    check();

                } else if (checkedId == R.id.radioButtonAfternoon) {
                    // Xử lý khi RadioButton thứ hai được chọn
                    Ca = 2;
                    check();

                } else if (checkedId == R.id.radioButtonEvening) {
                    // Xử lý khi RadioButton thứ ba được chọn
                    Ca = 3;
                    check();
                }

            }
        });
        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               submit();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }
    void cancel(){

        String token = "Bearer " + MyToken.getToken();
        API_Auth.apiauth.del_work_person(token,id_work).enqueue(new Callback<Work_post_respond>() {
            @Override
            public void onResponse(Call<Work_post_respond> call, Response<Work_post_respond> response) {
                Work_post_respond res_work = response.body();
                if(res_work != null && res_work.getSuccess())
                {
                    Work work = res_work.getWorks();
                    Toast.makeText(Person_Work.this, "Hủy thành công",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Person_Work.this, "Hủy thất bại",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Work_post_respond> call, Throwable t) {
                Toast.makeText(Person_Work.this, "Không hủy được",Toast.LENGTH_SHORT).show();
            }
        });
    }
    void check(){
        Work_check_request work_req = new Work_check_request(
                Ndk,
                Ca
        );
        String token = "Bearer " + MyToken.getToken();
        API_Auth.apiauth.get_check_work_person(token,work_req).enqueue(new Callback<Work_check_respond>() {
            @Override
            public void onResponse(Call<Work_check_respond> call, Response<Work_check_respond> response) {
                Work_check_respond res_listwork = response.body();
                if(res_listwork != null && res_listwork.getSuccess())
                {
                    //load_info();
                    //Toast.makeText(Person_Work.this, " get thah cong",Toast.LENGTH_SHORT).show();
                    List<Work> r_work = res_listwork.getWorks();
                    //Work work = r_work.get(0);
                    if(r_work != null && !r_work.isEmpty() && r_work.size()!=0){
                        Work work = r_work.get(0);
                        id_work = work.get_id();
                        if(work.getTinh_trang().equals("choduyet")) {
                            Message.setText("Bạn đã đăng ký, hãy chờ duyệt");
                            btn_cancel.setVisibility(View.VISIBLE);
                            btn_Submit.setVisibility(View.GONE);
                        }
                        else if (work.getTinh_trang().equals("daduyet"))
                        {
                            Message.setText("Bạn đã được duyệt ca làm này");
                            btn_Submit.setVisibility(View.GONE);
                            btn_Submit.setVisibility(View.GONE);
                        }
                        else if (work.getTinh_trang().equals("khongduyet"))
                        {
                            Message.setText("Bạn không dược duyệt, hãy đăng ký ca khác");
                            btn_Submit.setVisibility(View.GONE);
                            btn_Submit.setVisibility(View.GONE);
                        }
                    }
                    else{
                        Message.setText("Nhấn <Đăng ký> để đăng ký ca làm việc này");
                        btn_Submit.setVisibility(View.VISIBLE);
                        btn_cancel.setVisibility(View.GONE);
                    }

                }
                else {
                    Toast.makeText(Person_Work.this, "get fails",Toast.LENGTH_SHORT).show();
                    Message.setText("");
                }
            }

            @Override
            public void onFailure(Call<Work_check_respond> call, Throwable t) {
                Toast.makeText(Person_Work.this, "kh get đc",Toast.LENGTH_SHORT).show();
            }
        });
    }
    void submit(){
        Work_post_request work_req = new Work_post_request(
                Ndk,
                Ca,
                "choduyet"
        );
        String token = "Bearer " + MyToken.getToken();
        API_Auth.apiauth.post_work_person(token,work_req).enqueue(new Callback<Work_post_respond>() {
            @Override
            public void onResponse(Call<Work_post_respond> call, Response<Work_post_respond> response) {
                /*Work_post_respond res_work = response.body();
                if(res_work != null && res_work.getSuccess())
                {
                    Work work = res_work.getWorks();
                    id_work = work.get_id().toString();
                    //load_info();
                    Toast.makeText(Person_Work.this, "Đăng ký thành công",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Person_Work.this, "Đăng ký thất bại",Toast.LENGTH_SHORT).show();
                }*/
                Toast.makeText(Person_Work.this, "Đăng ký thành công",Toast.LENGTH_SHORT).show();
                check();
            }

            @Override
            public void onFailure(Call<Work_post_respond> call, Throwable t) {
                Toast.makeText(Person_Work.this, "Không đăng ký được",Toast.LENGTH_SHORT).show();

            }
        });
    }
}