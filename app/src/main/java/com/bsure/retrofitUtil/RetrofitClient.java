package com.bsure.retrofitUtil;

import com.bsure.retrofitUtil.Apiinterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL="http://65.1.163.109:8082/";
    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;


    private RetrofitClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
    public static synchronized RetrofitClient getInstance(){
        if (retrofitClient ==null){
            retrofitClient=new RetrofitClient();
        }
        return retrofitClient;
    }

    public com.bsure.retrofitUtil.Apiinterface apiinterface(){
        return retrofit.create(Apiinterface.class);
    }

}
