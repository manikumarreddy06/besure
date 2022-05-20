package com.myproject.livwell.retrofitUtil;

import com.myproject.livwell.models.createnomineeresponse;
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
      Call<signup>usersignin(@Field("mobileNumber")String mobileNumber);

    @FormUrlEncoded
    @POST("v1/create/nominee")
    Call<createnomineeresponse>createnominee(@Field("userNomineeName")String userNomineeName,
                                             @Field("userNomineeMobileNumber")String userNomineeMobileNumber);
}
