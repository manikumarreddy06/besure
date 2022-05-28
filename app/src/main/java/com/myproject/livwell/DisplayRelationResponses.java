package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.myproject.livwell.models.relationresponse;
import com.myproject.livwell.retrofitUtil.Apiinterface;
import com.myproject.livwell.retrofitUtil.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayRelationResponses extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_relation_responses);

        // fetching relation responses
        Apiinterface methods = RetrofitClient.getInstance().apiinterface();
        Call<relationresponse> call = methods.getRelationresponse();

        call.enqueue(new Callback<relationresponse>() {
            @Override
            public void onResponse(Call<relationresponse> call, Response<relationresponse> response) {

//                ArrayList<relationresponse.data> data = relationresponseList.get(position).getRelationName();
//                holder.tvRelation.setText(data.getRelationName());

//                ArrayList<relationresponse.data> p= new ArrayList(response.body().getData().getClass().getRelationName());


            }

            @Override
            public void onFailure(Call<relationresponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}