package com.example.leejinyeong.a6oclock.Retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("/test")
    Call<Void> postMail(
            @Field("title") String title,
            @Field("content") String content
    );


}
