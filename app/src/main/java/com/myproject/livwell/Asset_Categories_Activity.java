package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.myproject.livwell.retrofitUtil.Apiinterface;
import com.myproject.livwell.retrofitUtil.RetrofitClient;

import retrofit2.Retrofit;

public class Asset_Categories_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_categories);

//        Apiinterface apiinterface = RetrofitClient.getInstance().create(Apiinterface.class);
    }
}