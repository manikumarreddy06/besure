package com.bsure;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsure.R;
import com.bsure.models.CategoryResponseBean;
import com.bsure.models.CategorysReponse;
import com.bsure.retrofitUtil.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Asset_Categories_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_categories);


        getAssestsData();
    }

    private void getAssestsData() {

        Call<CategoryResponseBean> call=RetrofitClient.getInstance().apiinterface().getAssetCategories();
        call.enqueue(new Callback<CategoryResponseBean>() {
            @Override
            public void onResponse(Call<CategoryResponseBean> call, Response<CategoryResponseBean> response) {

                CategoryResponseBean bean=response.body();
                if(bean.getIsvalid()) {
                    adapterupdate(response.body().getCategorysReponse());
                }
                else{
                    Toast.makeText(Asset_Categories_Activity.this,"failure",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponseBean> call, Throwable t) {
                Toast.makeText(Asset_Categories_Activity.this,"failure",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void adapterupdate(List<CategorysReponse> categorysReponse) {

        RecyclerView recyclerView=findViewById(R.id.rv);
        CategoriesAdapter adapter=new CategoriesAdapter(this,categorysReponse);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

}