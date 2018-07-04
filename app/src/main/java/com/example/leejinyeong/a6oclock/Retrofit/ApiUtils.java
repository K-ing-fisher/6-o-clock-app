package com.example.leejinyeong.a6oclock.Retrofit;

public class ApiUtils {

    private static final String BASE_URL = "https://floating-atoll-83135.herokuapp.com/";

    public static ApiInterface getApiInterface(){
        return RetrofitClient.getRetrofit(BASE_URL).create(ApiInterface.class);
    }
}
