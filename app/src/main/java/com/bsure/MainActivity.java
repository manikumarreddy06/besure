package com.bsure;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bsure.models.UserProfileDataResponse;
import com.bsure.retrofitUtil.RetrofitClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {
    String devicetoken;

    String paidFlag=null;
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO toolbar actions need to be handled
        MaterialToolbar topappbar = findViewById(R.id.topappbar);
        LinearLayout llassets = findViewById(R.id.assets);
        LinearLayout llnominee = findViewById(R.id.nominee);
        LinearLayout llnudge = findViewById(R.id.nudge);
        LinearLayout lldistribution = findViewById(R.id.distribution);
        CardView step1 = findViewById(R.id.step1);
        CardView step2 = findViewById(R.id.step2);
        CardView step3 = findViewById(R.id.step3);
        CardView step4 = findViewById(R.id.step4);




        llassets.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this, Asset_Categories_Activity.class);
            startActivity(i);
        });
        llnominee.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this, NomineeListActivity.class);
            startActivity(i);
        });
        llnudge.setOnClickListener(view -> {
            getUserProfileData();
        });
        lldistribution.setOnClickListener(view -> {
//            Intent i=new Intent(MainActivity.this,AssetDistribution.class);
//            startActivity(i);

            Utils.Companion.toast("coming soon",this);
        });


        // steps to follow
        step1.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this, User_Profile_Activity.class);
            startActivity(i);
        });
        step2.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this, Asset_Categories_Activity.class);
            startActivity(i);
        });
        step3.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this, NomineeListActivity.class);
            startActivity(i);
        });
        step4.setOnClickListener(view -> {


            getUserProfileData();

        });


//        topappbar.setNavigationOnClickListener(view -> {
//
//        });
        topappbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.profile) {
                Intent i = new Intent(MainActivity.this, Profile.class);
                startActivity(i);
            }
            return false;
        });
    }


    private void getUserProfileData() {
        // Retrofit
        Utils.Companion.showDialog(this,"loading");
        PreferenceManager mInstance = PreferenceManager.instance(this);
        String userId= mInstance.get(PreferenceManager.USER_ID,null);
        Call<UserProfileDataResponse> userProfileDataResponsebeanCall = RetrofitClient.getInstance().apiinterface().getUserProfileData(userId);
        userProfileDataResponsebeanCall.enqueue(new Callback<UserProfileDataResponse>() {
            @Override
            public void onResponse(Call<UserProfileDataResponse> call, Response<UserProfileDataResponse> response) {
                // Checking for the Response
                Utils.Companion.hideDialog();
                UserProfileDataResponse bean=response.body();
                if (!(response.body().getIsvalid())) {
                    //Utils.Companion.toast("failed to update user data",MainActivity.this);
                }
                if (response.body().getIsvalid()) {
                    if(!TextUtils.isEmpty(bean.getUserDataResponses().getPlanDetails())) {
                        paidFlag=bean.getUserDataResponses().getPaidFlag();
                        PreferenceManager.instance(MainActivity.this).set(PreferenceManager.PLAN_PAID_FLAG, bean.getUserDataResponses().getPaidFlag());
                        PreferenceManager.instance(MainActivity.this).set(PreferenceManager.PLAN_DETAILS, bean.getUserDataResponses().getPlanDetails());

                        if(!TextUtils.isEmpty(paidFlag) && paidFlag.equalsIgnoreCase("true")){
                            Intent i = new Intent(MainActivity.this, Nudgesuccess_Activity.class);
                            startActivity(i);
                        }
                        else {
                            Intent i = new Intent(MainActivity.this, NudgeActivity.class);
                            startActivity(i);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<UserProfileDataResponse> call, Throwable t) {
                //Toast.makeText(MainActivity.this, "Not getting response",Toast.LENGTH_LONG).show();
                Utils.Companion.hideDialog();
            }
        });




    }
}