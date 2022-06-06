package com.myproject.livwell.retrofitUtil;

import com.myproject.livwell.models.AssetsConfigResponse;
import com.myproject.livwell.models.BaseResponse;
import com.myproject.livwell.models.CategoryResponseBean;
import com.myproject.livwell.models.SaveAssetRequest;
import com.myproject.livwell.models.UserAsset;
import com.myproject.livwell.models.UserAssetResponseBean;
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
import retrofit2.http.Path;

public interface Apiinterface {

     @POST("v1/signup")
     Call<signup>usersignin(@Body signup data );

    @POST("v1/user/verifyOtp")
    Call<signup>verifyOtp(@Body signup data );

    @POST("v1/categories")
    Call<CategoryResponseBean>getAssetCategories();


    @GET("v1/relations")
    Call<relationresponse>getRelationresponse();

    @FormUrlEncoded
    @POST("v1/create/nominee")
    Call<createnomineeresponse>createnominee(@Field("userNomineeName")String userNomineeName,
                                             @Field("userNomineeMobileNumber")String userNomineeMobileNumber);


    @GET("v1/getAssetConfig/{id}")
    Call<AssetsConfigResponse>getAssestsConfigurations(@Path("id") String id);


    @POST("v1/user/saveAssetInfo")
    Call<BaseResponse>saveAssetData(@Body SaveAssetRequest request);


    @POST("v1/getAssets")
    Call<UserAssetResponseBean>getUserLevelAssetsByCategory(@Body UserAsset request);





}
