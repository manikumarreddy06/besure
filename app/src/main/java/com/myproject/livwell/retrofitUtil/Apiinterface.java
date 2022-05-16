package com.myproject.livwell.retrofitUtil;

import com.myproject.livwell.models.signup;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Apiinterface {
    @FormUrlEncoded
     @POST("v1/signup")
      Call<signup>usersignin(@Field("mobileNumber")String mobileNumber);
}
