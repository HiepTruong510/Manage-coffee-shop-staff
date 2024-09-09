package com.example.qlnv.Activity.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.qlnv.Activity.Globals.MyToken;
import com.example.qlnv.CallAPI.API_Auth;
import com.example.qlnv.R;
import com.example.qlnv.request_respond.Adjust_Admin.Adjust_admin;
import com.example.qlnv.request_respond.Adjust_Admin.Adjust_admin_request;
import com.example.qlnv.request_respond.Adjust_Admin.Adjust_admin_respond;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Adjust extends AppCompatActivity {
    private DatePicker chonngay;
    private RadioGroup chonca;
    private RadioButton ca1;
    private RadioButton ca2;
    private RadioButton ca3;
    private int Ca =0;
    private String Ndk ;
    private Calendar NgayDK = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private EditText heso;
    private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_adjust);

        Ca = 0;
        chonngay = findViewById(R.id.datePicker);
        chonca =findViewById(R.id.caRadioGroup);
        ca1 =findViewById(R.id.radioButtonMorning);
        ca2 =findViewById(R.id.radioButtonAfternoon);
        ca3 =findViewById(R.id.radioButtonEvening);
        heso =findViewById(R.id.hesoluong_et);
        save =findViewById(R.id.save_bt);

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
        chonca.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Xử lý khi RadioButton được chọn
                if (checkedId == R.id.radioButtonMorning) {
                    // Xử lý khi RadioButton thứ nhất được chọn
                    Ca = 1;

                } else if (checkedId == R.id.radioButtonAfternoon) {
                    // Xử lý khi RadioButton thứ hai được chọn
                    Ca = 2;

                } else if (checkedId == R.id.radioButtonEvening) {
                    // Xử lý khi RadioButton thứ ba được chọn
                    Ca = 3;
                }

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Ca == 0)
                {
                    Toast.makeText(Admin_Adjust.this, "Vui lòng chọn ca",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    if(check_heso())
                        update_adjust();
                }
            }
        });

    }

    void update_adjust(){
        int hs = Integer.parseInt(heso.getText().toString());
        Adjust_admin_request work_req = new Adjust_admin_request(
                Ndk,
                Ca,
                hs
        );
        String token = "Bearer " + MyToken.getToken();
            API_Auth.apiauth.put_Work_Adjust(token, work_req).enqueue(new Callback<Adjust_admin_respond>() {
                @Override
                public void onResponse(Call<Adjust_admin_respond> call, Response<Adjust_admin_respond> response) {
                    Adjust_admin_respond res_work = response.body();
                    if (res_work != null && res_work.getSuccess()) {
                         Adjust_admin r_w = res_work.getUpdatedAdjust();
                        //String a = r_w.getNgay_dang_ky();
                        Toast.makeText(Admin_Adjust.this, "Điều chỉnh thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Admin_Adjust.this, "Điều chỉnh thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Adjust_admin_respond> call, Throwable t) {
                    Toast.makeText(Admin_Adjust.this, "kh điều chỉnh đc", Toast.LENGTH_SHORT).show();
                }
            });
    }
    boolean check_heso()
    {
        try {
            Integer.parseInt(heso.getText().toString());
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(Admin_Adjust.this, "Vui lòng nhập lại hệ số",Toast.LENGTH_SHORT).show();
            heso.setText("1");
            return false;
        }
        return true;
    }
}