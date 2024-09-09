package com.example.qlnv.Activity.Admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlnv.Activity.Globals.Info_Admin_Copy;
import com.example.qlnv.Activity.Globals.MyToken;
import com.example.qlnv.CallAPI.API_Auth;
import com.example.qlnv.R;
import com.example.qlnv.request_respond.Info_Admin.Info;
import com.example.qlnv.request_respond.Info_Admin.Info_respond;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_List_Info extends AppCompatActivity {

    private ListView list_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_info);

        list_info = findViewById(R.id.list_info);
        load_data();
        list_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // gán info click vào vào info cục bộ để hiển thị lên form mới
                Info_Admin_Copy.set_info(Info_Admin_Copy.get_listinfo().get(position));
                Intent intent = new Intent(Admin_List_Info.this, Admin_Info.class);
                startActivity(intent);
            }
        });
        list_info.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Xử lý sự kiện khi một item trong ListView được nhấn và giữ.
                // position là vị trí của item được nhấn và giữ trong ListView.
                Info_Admin_Copy.set_info(Info_Admin_Copy.get_listinfo().get(position));
                //--
                AlertDialog.Builder builder = new AlertDialog.Builder(Admin_List_Info.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn muốn xóa nhân viên này không?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý sự kiện khi người dùng nhấn nút "Duyệt"
                        del_employee();
                        load_data();
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
                //--

                return true;
            }
        });
    }
    void load_data(){
        String token = "Bearer " + MyToken.getToken();
        API_Auth.apiauth.get_listInfo(token).enqueue(new Callback<Info_respond>() {
            @Override
            public void onResponse(Call<Info_respond> call, Response<Info_respond> response) {
                Info_respond res_listinfo = response.body();
                if(res_listinfo != null && res_listinfo.getSuccess())
                {

                    List<Info> listinfo = res_listinfo.getInfos();
                    Info_Admin_Copy.set_listinfo(listinfo);
                    if(listinfo != null)
                    {
                        //Info info = listinfo.get(0);
                        List<String> items = new ArrayList<>();
                        int count = listinfo.size();
                        for(int i =0 ; i<count; i++)
                        {
                            Info info = listinfo.get(i);
                            items.add(info.getFullname() + " - " + info.getPhonenumber());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Admin_List_Info.this, android.R.layout.simple_list_item_1, items);
                        list_info.setAdapter(adapter);
                    }

                }
                else {
                    Toast.makeText(Admin_List_Info.this, "khong co thong tin",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Info_respond> call, Throwable t) {
                Toast.makeText(Admin_List_Info.this, "get that bai",Toast.LENGTH_SHORT).show();
            }
        });

    }
    void del_employee(){
        String token = "Bearer " + MyToken.getToken();
        API_Auth.apiauth.del_Info(token,Info_Admin_Copy.get_info().get_id()).enqueue(new Callback<Info_respond>() {
            @Override
            public void onResponse(Call<Info_respond> call, Response<Info_respond> response) {
                Toast.makeText(Admin_List_Info.this, "Xóa thành công",Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onFailure(Call<Info_respond> call, Throwable t) {
                Toast.makeText(Admin_List_Info.this, "Không xóa được",Toast.LENGTH_SHORT).show();

            }
        });
    }
}