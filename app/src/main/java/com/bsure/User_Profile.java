package com.bsure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bsure.models.UserDataResponse;
import com.bsure.models.UserProfileDataResponse;
import com.bsure.retrofitUtil.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Profile extends AppCompatActivity {
    CardView btn_editProfileDetails;
    TextView userName,userGender,userWhatsappNo,userSecondaryNo,userMailId,userAddress;
    private UserDataResponse userDetails;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userName = findViewById(R.id.userName);
        userGender = findViewById(R.id.userGender);
        userWhatsappNo = findViewById(R.id.userWhatsappNo);
        userSecondaryNo = findViewById(R.id.userSecondaryNo);
        userMailId = findViewById(R.id.userMailId);
        userAddress = findViewById(R.id.userAddress);
        btn_editProfileDetails = findViewById(R.id.btn_editProfileDetails);

        // get user data
        getUserProfileData();

        // to let the user edit user data
        btn_editProfileDetails.setOnClickListener(view -> {
            Intent i=new Intent(User_Profile.this, EditProfileDetails.class);
            startActivity(i);
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
                if (!(response.body().getIsvalid())) {
                    userName.setText("Username Not Set yet");
//                    Toast.makeText(User_Profile.this, "failed to update",Toast.LENGTH_LONG).show();
                }if (response.body().getIsvalid()) {
                    String s= response.message();
                    final String TAG = "Profile activity";
                    userName.setText(response.body().toString());
                    userGender.setText(response.body().getUserDataResponses().getGender());
//                    Log.i(TAG, "name of the user " + response.body().getUserDataResponses().getUserName());
                    userName.setText(response.body().getUserDataResponses().getUserName());
                    userWhatsappNo.setText(response.body().getUserDataResponses().getWhatsUpNumber());
                    userSecondaryNo.setText(response.body().getUserDataResponses().getAlternateNumber());
                    userMailId.setText(response.body().getUserDataResponses().getEmail());
                    userAddress.setText(response.body().getUserDataResponses().getAddess());
                    Toast.makeText(User_Profile.this, "response is valid",Toast.LENGTH_LONG).show();
                    PreferenceManager.instance(User_Profile.this).set(PreferenceManager.USER_NAME,response.body().getUserDataResponses().getUserName());
                }
            }
            @Override
            public void onFailure(Call<UserProfileDataResponse> call, Throwable t) {
                Toast.makeText(User_Profile.this, "Not getting response",Toast.LENGTH_LONG).show();
            }
        });
    }
}