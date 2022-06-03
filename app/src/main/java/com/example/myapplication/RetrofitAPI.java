package com.example.myapplication;

import com.example.myapplication.models.User;
import com.example.myapplication.models.UserData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
    public static final String BASE_URL = "http://147.83.7.206:8080/dsaApp/";
    @POST("user/login")
    Call<User> login(@Body UserData user);

    @POST("user/add")
    Call<User> add(@Body UserData user);

}