package com.bsure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.bsure.databinding.ActivityUserProfileBinding;
import com.bsure.models.UserDataResponse;
import com.bsure.models.UserProfileDataResponsebean;
import com.bsure.retrofitUtil.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Profile extends AppCompatActivity {
    private ActivityUserProfileBinding binding;
    private UserDataResponse userDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_user_profile);

        getUserProfileData();

        binding.btnEditDetails.setOnClickListener(view -> {
            Intent i=new Intent(User_Profile.this, EditProfileDetails.class);
            startActivity(i);
        });
    }

    // get user profile data
    private void getUserProfileData() {
        // Retrofit
        Call<UserProfileDataResponsebean> userProfileDataResponsebeanCall = RetrofitClient.getInstance().apiinterface().getUserProfileData();
        userProfileDataResponsebeanCall.enqueue(new Callback<UserProfileDataResponsebean>() {
            @Override
            public void onResponse(Call<UserProfileDataResponsebean> call, Response<UserProfileDataResponsebean> response) {
                // Checking for the Response
                if (!response.isSuccessful()) {
                    // check internet connection
                }if (response.body().getIsvalid()) {
                    binding.userName.setText(response.body().getUserDataResponses().getUserName());
                    binding.userWhatsappNo.setText(response.body().getUserDataResponses().getWhatsUpNumber());
                    binding.userSecondaryNo.setText(response.body().getUserDataResponses().getAlternateNumber());
                    binding.userMailId.setText(response.body().getUserDataResponses().getEmail());
                    binding.userAddress.setText(response.body().getUserDataResponses().getAddess());

                    binding.userGender.setText(response.body().getUserDataResponses().getGender());
                }
            }
            @Override
            public void onFailure(Call<UserProfileDataResponsebean> call, Throwable t) {

            }
        });
    }
}