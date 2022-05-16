package com.myproject.livwell.retrofitUtil;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL="http://65.1.163.109:8082/";
    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;


    private RetrofitClient(){
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitClient getInstance(){
        if (retrofitClient ==null){
retrofitClient=new RetrofitClient();
        }
        return retrofitClient;
    }

   public  Apiinterface apiinterface(){
        return retrofit.create(Apiinterface.class);
    }
}
