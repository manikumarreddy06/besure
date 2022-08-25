package com.bsure.retrofitUtil;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bsure.PreferenceManager;
import com.bsure.retrofitUtil.Apiinterface;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL="http://3.111.240.149:8082/";
    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;




    private RetrofitClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request Originalrequest= chain.request();
                        Request newrequest=Originalrequest.newBuilder()
                                .header("usertoken","123")
                                .build();
                        return chain.proceed(newrequest);
                    }
                })
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
