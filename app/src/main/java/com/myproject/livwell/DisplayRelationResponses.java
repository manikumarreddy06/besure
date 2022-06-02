package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.myproject.livwell.models.relationresponse;
import com.myproject.livwell.models.signup;
import com.myproject.livwell.retrofitUtil.Apiinterface;
import com.myproject.livwell.retrofitUtil.RetrofitClient;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayRelationResponses extends AppCompatActivity {
TextView tvnomineerelation;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RelationsAdapter relationsAdapter;
    List<relationresponse.data> relationstList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_relation_responses);
        tvnomineerelation=findViewById(R.id.tv_relation);
        recyclerView=findViewById(R.id.category_recycler_view);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        relationsAdapter= new RelationsAdapter(relationstList);
        recyclerView.setAdapter(relationsAdapter);
        relationresonses();

    }
    private void relationresonses(){
        Call<relationresponse>call=RetrofitClient.getInstance().apiinterface().getRelationresponse();
       call.enqueue(new Callback<relationresponse>() {
           @Override
           public void onResponse(Call<relationresponse> call, Response<relationresponse> response) {
               if (response.isSuccessful()&& response.body()!=null){
                   relationresponse responsedata=response.body();
                   relationstList.addAll(responsedata.data);
                   relationsAdapter.notifyDataSetChanged();
                   
               }
           }

           @Override
           public void onFailure(Call<relationresponse> call, Throwable t) {

           }
       });

    }
}