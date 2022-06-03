package com.myproject.livwell.retrofitUtil;

import com.myproject.livwell.models.Asset_categories;
import com.myproject.livwell.models.createnomineeresponse;
import com.myproject.livwell.models.relationresponse;
import com.myproject.livwell.models.signup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Apiinterface {

     @POST("v1/signup")
      Call<signup>usersignin(@Body signup data );
      Call<signup>usersignin(@Field("mobileNumber")String mobileNumber);

    @GET("v1/categories")
    Call<List<Asset_categories>>getAssetCategories();


    @GET("v1/relations")
    Call<relationresponse>getRelationresponse();

    @FormUrlEncoded
    @POST("v1/create/nominee")
    Call<createnomineeresponse>createnominee(@Field("userNomineeName")String userNomineeName,
                                             @Field("userNomineeMobileNumber")String userNomineeMobileNumber);
}
