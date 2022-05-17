package com.myproject.livwell.retrofitUtil;

import com.google.gson.JsonObject;
import com.myproject.livwell.models.signup;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Apiinterface {

     @POST("v1/signup")
      Call<signup>usersignin(@Body signup data );
}
