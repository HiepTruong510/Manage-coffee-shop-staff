package com.example.qlnv.CallAPI;


import com.example.qlnv.Activity.Globals.MyToken;
import com.example.qlnv.request_respond.Info_Person.Info_person_post_respond;
import com.example.qlnv.request_respond.Info_Person.Info_person_request;
import com.example.qlnv.request_respond.Info_Person.Info_person_get_respond;
import com.example.qlnv.request_respond.Info_Admin.Info_respond;
import com.example.qlnv.request_respond.Login.Login_request;
import com.example.qlnv.request_respond.Login.Login_respond;
import com.example.qlnv.request_respond.Register.Register_request;
import com.example.qlnv.request_respond.Register.Register_respond;
import com.example.qlnv.request_respond.Adjust_Admin.Adjust_admin_request;
import com.example.qlnv.request_respond.Adjust_Admin.Adjust_admin_respond;
import com.example.qlnv.request_respond.Salary_Admin.Salary_admin_request;
import com.example.qlnv.request_respond.Salary_Admin.Salary_admin_respond;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_getname_request;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_getname_respond;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_put_request;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_put_respond;
import com.example.qlnv.request_respond.Work_Person.Work_check_request;
import com.example.qlnv.request_respond.Work_Person.Work_post_request;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_list_respond;
import com.example.qlnv.request_respond.Work_Admin.Work_admin_list_request;
import com.example.qlnv.request_respond.Work_Person.Work_check_respond;
import com.example.qlnv.request_respond.Work_Person.Work_post_respond;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API_Auth {
    //chưa dùng
    Interceptor interceptor = chain -> {
        Request request = chain.request();
        Request.Builder builder =request.newBuilder();
        builder.addHeader("Authorization", "Bearer "+ MyToken.getToken());
        return chain.proceed(builder.build());
    };
    OkHttpClient.Builder okbuilder = new OkHttpClient.Builder().addInterceptor(interceptor);


    //---------------- lấy ip riêng
    String ipPC ="172.17.24.193";//"LAPTOP-NF57A8IQ";
    //--------------------
    API_Auth apiauth = new Retrofit.Builder()
            .baseUrl("http://" + ipPC + ":5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API_Auth.class);
            //.client(okbuilder.build())


//---------------------------------------------hàm call-------------------------
    //------------------- Login ------------------------------------------------
    @Headers("Content-Type: application/json")
    @POST("api/auth/login")
    Call<Login_respond> checklogin(@Body Login_request user);

    //------------------- Register ---------------------------------------------
    @Headers("Content-Type: application/json")
    @POST("api/auth/register")
    Call<Register_respond> dangky(@Body Register_request user);

    //------------------- Person_Info ------------------------------------------
    //-- lấy thông tin của mình
    @GET("api/infos/person")
    Call<Info_person_get_respond> getInfo(@Header("Authorization") String authorization);

    //-- sửa thông tin của mình
    @Headers("Content-Type: application/json")
    @PUT("api/infos/{id}" )
    Call<Info_person_post_respond> putInfo(@Header("Authorization") String authorization, @Path("id") String id, @Body Info_person_request info);

    //-- tạo mới thông tin
    @Headers("Content-Type: application/json")
    @POST("api/infos")
    Call<Info_person_post_respond> postInfo(@Header("Authorization") String authorization, @Body Info_person_request info);

    //------------------- Admin_Info ---------------------------------------------------------
    //-- danh sách nhân viên
    @GET("api/infos/list")
    Call<Info_respond> get_listInfo(@Header("Authorization") String authorization);

    //-- xóa nhân viên
    @DELETE("api/infos/{id}")
    Call<Info_respond> del_Info(@Header("Authorization") String authorization, @Path("id") String id);

    //-- lấy fullname của nhân viên
    @Headers("Content-Type: application/json")
    @POST("api/infos/fullname")
    Call<Work_admin_getname_respond> get_Fullname(@Header("Authorization") String authorization, @Body Work_admin_getname_request iduser);


    //-------------------- Person_Work--------------------------------------------------------
    //-- thêm work
    @Headers("Content-Type: application/json")
    @POST("api/works")
    Call<Work_post_respond> post_work_person(@Header("Authorization") String authorization, @Body Work_post_request work);

    //-- hủy work
    @DELETE("api/works/{id}")
    Call<Work_post_respond> del_work_person(@Header("Authorization") String authorization, @Path("id") String id);


    //check đk chưa
    @Headers("Content-Type: application/json")
    @POST("api/works/person/check")
    Call<Work_check_respond> get_check_work_person(@Header("Authorization") String authorization, @Body Work_check_request work);

    //-------------------- Admin_Work---------------------------------------------------------
    //--get danh sách
    @Headers("Content-Type: application/json")
    @POST("api/works/list2")
    Call<Work_admin_list_respond> get_listwork(@Header("Authorization") String authorization, @Body Work_admin_list_request work);
    // sửa tình trạng
    @Headers("Content-Type: application/json")
    @PUT("api/works/{id}")
    Call<Work_admin_put_respond> put_listwork(@Header("Authorization") String authorization, @Path("id") String id, @Body Work_admin_put_request work);


    //-------------------- Admin_Adjust---------------------------------------------------------
    @Headers("Content-Type: application/json")
    @PUT("api/adjusts" )
    Call<Adjust_admin_respond> put_Work_Adjust(@Header("Authorization") String authorization, @Body Adjust_admin_request work_request);

    //-------------------- Admin_Salary---------------------------------------------------------
    @Headers("Content-Type: application/json")
    @POST("api/salarys")
    Call<Salary_admin_respond> get_admin_salry(@Header("Authorization") String authorization, @Body Salary_admin_request salary);

}
