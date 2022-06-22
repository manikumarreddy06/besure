package com.bsure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bsure.R;
import com.bsure.models.CategoryResponseBean;
import com.bsure.models.PlanDetail;
import com.bsure.models.PlanDetailsResponseBean;
import com.bsure.retrofitUtil.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NudgeActivity extends AppCompatActivity {
    Button btn_proceed;
    RadioButton rbLifetimePlan;
    RadioButton rbYearlyPlan;
    PlanDetail mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nudge);

        btn_proceed = findViewById(R.id.btn_proceed);
        btn_proceed.setOnClickListener(view -> {
            //  carry card data

            if(rbYearlyPlan.isChecked()|| rbLifetimePlan.isChecked()) {

                Intent i = new Intent(NudgeActivity.this, Billing.class);

                if(rbYearlyPlan.isChecked()){
                    i.putExtra("plan",rbYearlyPlan.getText());
                    i.putExtra("price",mData.getYearlyPlanPrice());
                }

                if(rbLifetimePlan.isChecked()){
                    i.putExtra("plan",rbLifetimePlan.getText());
                    i.putExtra("price",mData.getLifetimePlanPrice());
                }


                startActivity(i);
            }
            else{
                Utils.Companion.toast("Please select the Plan ",this);
            }
        });

        rbLifetimePlan = findViewById(R.id.rbLifetimePlan);
        rbYearlyPlan = findViewById(R.id.rbYearlyPlan);

        rbLifetimePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbYearlyPlan.setChecked(false);
            }
        });
        rbYearlyPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbLifetimePlan.setChecked(false);
            }
        });



        getPlanDetails();
    }

    private void getPlanDetails() {

        Call<PlanDetailsResponseBean> call= RetrofitClient.getInstance().apiinterface().getPlanDetails();
        call.enqueue(new Callback<PlanDetailsResponseBean>() {
            @Override
            public void onResponse(Call<PlanDetailsResponseBean> call, Response<PlanDetailsResponseBean> response) {

                PlanDetailsResponseBean bean=response.body();
                if(bean.getIsvalid()) {
                    updatePriceDetails(bean);
                }
                else{
                    Toast.makeText(NudgeActivity.this,"failure",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PlanDetailsResponseBean> call, Throwable t) {
                Toast.makeText(NudgeActivity.this,"failure",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updatePriceDetails(PlanDetailsResponseBean bean) {
        if(bean!=null && bean.getPlanDetails()!=null && bean.getPlanDetails().size()>0){

            mData=bean.getPlanDetails().get(0);

            String yearPlanMessage="Annual Plan "+mData.getYearlyPlanPrice().intValue()+"/-  per Year";
            String lifePlanMessage="Lifetime Plan "+mData.getLifetimePlanPrice().intValue()+"/- per Lifetime\"";

            rbLifetimePlan.setText(lifePlanMessage);
            rbYearlyPlan.setText(yearPlanMessage);

        }

    }
}