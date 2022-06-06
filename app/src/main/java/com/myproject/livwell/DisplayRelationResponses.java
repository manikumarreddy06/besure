package com.myproject.livwell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.myproject.livwell.models.relationresponse;
import com.myproject.livwell.models.signup;
import com.myproject.livwell.retrofitUtil.Apiinterface;
import com.myproject.livwell.retrofitUtil.RetrofitClient;

import java.util.ArrayList;
import java.util.Collection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayRelationResponses extends AppCompatActivity {
RecyclerView recyclerView;
ProgressBar progressBar;
LinearLayoutManager linearLayoutManager;
RelationsAdapter adapter;
ArrayList<relationresponse.data>relationlist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_relation_responses);
        recyclerView=findViewById(R.id.category_recycler_view);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new RelationsAdapter(relationlist);
        recyclerView.setAdapter(adapter);
        
        
        fetchrelationdata();
        
    }

    private void fetchrelationdata() {
       /* Apiinterface methods = RetrofitClient.getInstance().apiinterface();
        Call<relationresponse> call = methods.getRelationresponse();*/

        Call<relationresponse>call=RetrofitClient.getInstance().apiinterface().getRelationresponse();
        call.enqueue(new Callback<relationresponse>() {
            @Override
            public void onResponse(Call<relationresponse> call, Response<relationresponse> response) {
                Toast.makeText(DisplayRelationResponses.this, "success", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()&&response!=null){
          
                }
            }

            @Override
            public void onFailure(Call<relationresponse> call, Throwable t) {
                Toast.makeText(DisplayRelationResponses.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

}