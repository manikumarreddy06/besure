package com.bsure.retrofitUtil;

import com.bsure.models.AssetsConfigResponse;
import com.bsure.models.BaseResponse;
import com.bsure.models.CategoryResponseBean;
import com.bsure.models.NomineeListResponseBean;
import com.bsure.models.NomineeRequest;
import com.bsure.models.PlanDetailsResponseBean;
import com.bsure.models.RelationResponseBean;
import com.bsure.models.SaveAssetRequest;
//import com.bsure.models.UpdateUserAccountRequest;
//import com.bsure.models.UpdateUserAccountResponse;
import com.bsure.models.UserAsset;
import com.bsure.models.UserAssetResponseBean;
import com.bsure.models.signup;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
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


    @POST("v1/relations")
    Call<RelationResponseBean>getRelationList();

    @POST("v1/create/nominee")
    Call<BaseResponse>addNomineeDetails(@Body NomineeRequest request);


    @GET("v1/nominees/{userId}")
    Call<NomineeListResponseBean>getNomineeList(@Path("userId") String userId);


    @GET("v1/getAssetConfig/{id}")
    Call<AssetsConfigResponse>getAssestsConfigurations(@Path("id") String id);


    @POST("v1/user/saveAssetInfo")
    Call<BaseResponse>saveAssetData(@Body SaveAssetRequest request);


    @POST("v1/getAssets")
    Call<UserAssetResponseBean>getUserLevelAssetsByCategory(@Body UserAsset request);



    @POST("v1/getPlanDetails")
    Call<PlanDetailsResponseBean>getPlanDetails();
//
//    @POST("update/details")
//    Call<UpdateUserAccountResponse> updateUserAccount(@Body UpdateUserAccountRequest updateUserAccountRequest);


}
