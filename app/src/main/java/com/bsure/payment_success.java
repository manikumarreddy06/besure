package com.bsure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bsure.models.BaseResponse;
import com.bsure.models.userdiscountrequest;
import com.bsure.models.userdiscountresponsebean;
import com.bsure.retrofitUtil.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class payment_success extends AppCompatActivity {
    LottieAnimationView lottieAnimationView,lottieanim2;
    TextView tv1,tv2;
    Button btndone;

    private String planName;
    private double planPrice;
    private String coupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);


        planName = getIntent().getStringExtra("plan");
        planPrice = getIntent().getDoubleExtra("price", 0);
        coupon = getIntent().getStringExtra("coupon");

        lottieAnimationView=findViewById(R.id.payment_succ);
        lottieanim2=findViewById(R.id.plansuccess);
        tv1=findViewById(R.id.textview_paysuccess);
        tv2=findViewById(R.id.textview_nudge);
        btndone=findViewById(R.id.btn_done);
        //starting bgthread on main thread
        thread.start();
        PreferenceManager.instance(this).set(PreferenceManager.PLAN_PAID_FLAG,"true");
        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(payment_success.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        updateUserInfo();
    }

    Thread thread = new Thread() {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv1.setVisibility(View.INVISIBLE);
                    lottieAnimationView.setVisibility(lottieAnimationView.INVISIBLE);
                    lottieanim2.setVisibility(lottieanim2.VISIBLE);
                    tv2.setVisibility(View.VISIBLE);
                }
            });
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i=new Intent(payment_success.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }


    private void updateUserInfo(){


        userdiscountrequest userdiscountrequest=new userdiscountrequest();
        userdiscountrequest.setUserId(PreferenceManager.instance(this).get(PreferenceManager.USER_ID,null));
        userdiscountrequest.setPlanAmount(String.valueOf((int)planPrice));
        userdiscountrequest.setCode(coupon);

        Call<BaseResponse> call= RetrofitClient.getInstance().apiinterface().updateUserInfo(userdiscountrequest);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Log.d("updateUserInfo","onResponse");
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d("updateUserInfo","onFailure");
            }
        });


    }
}