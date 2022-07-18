package com.bsure;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bsure.FAQ.FAQ_Activity;
import com.bsure.TnC.TnC_Activity;
import com.bsure.models.UserProfileDataResponse;
import com.bsure.refundPolicy.refundPolicy_Activity;
import com.bsure.retrofitUtil.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {
    TextView tv_aboutUs, tv_tnc, tv_faq, tv_contactUs, tv_logout, tv_privacypolicy, tv_refundPolicy,tv_user_name,tv_user_credential,tvshare;
    CardView btn_editProfile; String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tv_user_name = findViewById(R.id.tv_user_name);
        tv_user_credential = findViewById(R.id.tv_user_credential);
        tv_aboutUs = findViewById(R.id.tv_aboutUs);
        tv_tnc = findViewById(R.id.tv_tnc);
        tv_faq = findViewById(R.id.tv_faq);
        tv_contactUs = findViewById(R.id.tv_contactUs);
        tv_logout = findViewById(R.id.tv_logout);
        tv_privacypolicy = findViewById(R.id.tv_privacypolicy);
        tv_refundPolicy = findViewById(R.id.tv_refundPolicy);
        btn_editProfile = findViewById(R.id.cv_editProfile);
        tvshare=findViewById(R.id.tv_share);

        // get user data
        getUserProfileData();

        // Edit Profile
        btn_editProfile.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this, User_Profile_Activity.class);
            startActivity(i);
        });

        tv_aboutUs.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this, About_Us.class);
            startActivity(i);
        });
        tv_tnc.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this, TnC_Activity.class);
            startActivity(i);
        });
        tv_faq.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this, FAQ_Activity.class);
            startActivity(i);
        });
        tv_contactUs.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this, ContactUs.class);
            startActivity(i);
        });
        tv_refundPolicy.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this, refundPolicy_Activity.class);
            startActivity(i);
        });
        tv_privacypolicy.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this, privacy_Policy.class);
            startActivity(i);
        });
        tv_logout.setOnClickListener(view -> {
            AlertDialog.Builder builder
                    = new AlertDialog
                    .Builder(Profile.this);
            builder.setMessage("Confirm to Logout ?");
            builder.setTitle("Logout!");
            builder.setCancelable(false);

            builder.setPositiveButton( "Yes", (dialog, which) -> {
                                PreferenceManager.instance(Profile.this).clearUserSession();
                                Intent i=new Intent(Profile.this, Splashscreen_Activity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {  dialog.cancel(); }
                            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            // TODO

        });
        tvshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage= "\nI have secured my family's future with Bsure. You can also do the same for your family. Download now. \n\n";
                    String sharemsg2="\n Use my coupon code \"xxxxxx\" for extra benefits during payment";
                    shareMessage = shareMessage + "http://surl.li/clkca"+   "\n\n "+sharemsg2;
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "Share via"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });
    }
    // get user profile data
    private void getUserProfileData() {
        // Retrofit
        PreferenceManager mInstance = PreferenceManager.instance(this);
        userId= mInstance.get(PreferenceManager.USER_ID,null);
        Call<UserProfileDataResponse> userProfileDataResponsebeanCall = RetrofitClient.getInstance().apiinterface().getUserProfileData(userId);
        userProfileDataResponsebeanCall.enqueue(new Callback<UserProfileDataResponse>() {
            @Override
            public void onResponse(Call<UserProfileDataResponse> call, Response<UserProfileDataResponse> response) {
                // Checking for the Response
                UserProfileDataResponse bean=response.body();
                if (!(response.body().getIsvalid())) {
                    Utils.Companion.toast("failed to update user data",Profile.this);
//                    Toast.makeText(User_Profile.this, "failed to update",Toast.LENGTH_LONG).show();
                }
                if (response.body().getIsvalid()) {
//                    Log.i(TAG, "name of the user " + response.body().getUserDataResponses().getUserName());
                    tv_user_name.setText(response.body().getUserDataResponses().getUserName());
                    tv_user_credential.setText(response.body().getUserDataResponses().getEmail());
                    if(!TextUtils.isEmpty(bean.getUserDataResponses().getPlanDetails())) {
                        PreferenceManager.instance(Profile.this).set(PreferenceManager.PLAN_PAID_FLAG, bean.getUserDataResponses().getPaidFlag());
                        PreferenceManager.instance(Profile.this).set(PreferenceManager.PLAN_DETAILS, bean.getUserDataResponses().getPlanDetails());
                    }
//                    Toast.makeText(User_Profile.this, "response is valid",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<UserProfileDataResponse> call, Throwable t) {
                Toast.makeText(Profile.this, "Not getting response",Toast.LENGTH_LONG).show();
            }
        });
    }
}