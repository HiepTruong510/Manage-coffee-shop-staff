package com.example.qlnv.Activity.Globals;

public class MyToken {
   // private static MyToken instance;
    public static String token="haha";

    /*private MyToken() {
        // Khởi tạo giá trị mặc định cho biến toàn cục tại đây (nếu cần)
    }*/

    /*public static MyToken getInstance() {
        if (instance == null) {
            instance = new MyToken();
        }
        return instance;
    }*/

    public static String getToken() {
        return token;
    }

    public static void setToken(String Token) {
        token = Token;
    }
}
