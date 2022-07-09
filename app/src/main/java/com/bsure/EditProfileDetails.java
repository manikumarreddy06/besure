package com.bsure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bsure.models.UpdateUserAccountRequest;
import com.bsure.models.UpdateUserAccountResponse;
import com.bsure.models.UserProfileDataResponse;
import com.bsure.retrofitUtil.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileDetails extends AppCompatActivity {

    EditText etUsername,etEmail,etSecondaryNo,etWhatsappNo,etAddress;
    RadioGroup rgGender; RadioButton rbMale,rbFemale, rbOther;
    Button btnUpdateProfile;
    String strGender,userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_account);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etSecondaryNo = findViewById(R.id.etSecondaryNo);
        etWhatsappNo = findViewById(R.id.etWhatsappNo);
        etAddress = findViewById(R.id.etAddress);
        rgGender = findViewById(R.id.rgGender);
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile);
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);
        rbOther = findViewById(R.id.rb_other);

        // Show old data
        getUserProfileData();

        // handling gender radio group click actions
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rb_male:
                        strGender="male";
//                        Toast.makeText(EditProfileDetails.this, "male is clicked",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.rb_female:
                        strGender="female";
//                        Toast.makeText(EditProfileDetails.this, "female is clicked",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.rb_other:
                        strGender="other";
//                        Toast.makeText(EditProfileDetails.this, "oth is clicked",Toast.LENGTH_LONG).show();
                        break;
                    default:
                        strGender="other";
                        break;
                }
            }
        });

        btnUpdateProfile.setOnClickListener(view -> {
            updateUserAccount(createRequest());
//            Toast.makeText(EditProfileDetails.this, "profile updated",Toast.LENGTH_LONG).show();
        });
    }
    //  adding validation
        public boolean validate() {
        String userName = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String secondaryNo = etSecondaryNo.getText().toString().trim();
        String whatsappNo = etWhatsappNo.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        if(userName.isEmpty()){etUsername.setError("Please enter User name"); etUsername.requestFocus(); return false;}
        if(email.isEmpty()){etEmail.setError("Please enter Email address"); etEmail.requestFocus(); return false;}
        if(secondaryNo.isEmpty()){etSecondaryNo.setError("Please enter Phone no"); etSecondaryNo.requestFocus(); return false;}
        if(whatsappNo.isEmpty()){etWhatsappNo.setError("Please enter Whatsapp no"); etWhatsappNo.requestFocus(); return false;}
        if(address.isEmpty()){etAddress.setError("Please enter Address"); etAddress.requestFocus(); return false;}

        // if everything goes right
        return true;
    }

    public UpdateUserAccountRequest createRequest(){
        PreferenceManager mInstance = PreferenceManager.instance(this);
        userId= mInstance.get(PreferenceManager.USER_ID,null);

        UpdateUserAccountRequest updateUserAccountRequest = new UpdateUserAccountRequest();

        updateUserAccountRequest.setUserId(userId);
        updateUserAccountRequest.setUserName(etUsername.getText().toString());
        updateUserAccountRequest.setGender(strGender);
        updateUserAccountRequest.setEmail(etEmail.getText().toString());
        updateUserAccountRequest.setSecondaryPhoneNo(etSecondaryNo.getText().toString());
        updateUserAccountRequest.setWhatsUpNumber(etWhatsappNo.getText().toString());
        updateUserAccountRequest.setAddress(etAddress.getText().toString());

        return updateUserAccountRequest;
    }

    public void updateUserAccount(UpdateUserAccountRequest updateUserAccountRequest){
        if(validate()){
            Call<UpdateUserAccountResponse> updateUserAccountResponseCall = RetrofitClient.getInstance().apiinterface().updateUserAccount(updateUserAccountRequest);
            updateUserAccountResponseCall.enqueue(new Callback<UpdateUserAccountResponse>() {
                @Override
                public void onResponse( Call<UpdateUserAccountResponse> call, Response<UpdateUserAccountResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(EditProfileDetails.this,"updated successfully ",Toast.LENGTH_SHORT).show();
//                    Log.i("Edit Profile", "Updated successfully ");

                        Intent i=new Intent(EditProfileDetails.this, User_Profile_Activity.class);
                        startActivity(i);
                    }else{
//                    Log.i("Edit Profile", "request failed");
                        Toast.makeText(EditProfileDetails.this,"request failed",Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure( Call<UpdateUserAccountResponse> call, Throwable t) {
                    Toast.makeText(EditProfileDetails.this,"request failed here "+t,Toast.LENGTH_LONG).show();
                }
            });
        }
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
//                if (!(response.body().getIsvalid())) {
//                    Utils.Companion.toast("failed to update user data",EditProfileDetails.this);
//                    Toast.makeText(User_Profile.this, "failed to update",Toast.LENGTH_LONG).show();
//                }
                if (response.body() != null && response.body().getIsvalid()) {
                    // Log.i(TAG, "name of the user " + response.body().getUserDataResponses().getUserName());
                    etUsername.setText(response.body().getUserDataResponses().getUserName());
                    etEmail.setText(response.body().getUserDataResponses().getEmail());
                    etSecondaryNo.setText(response.body().getUserDataResponses().getAlternateNumber());
                    etWhatsappNo.setText(response.body().getUserDataResponses().getWhatsUpNumber());
                    etAddress.setText(response.body().getUserDataResponses().getAddess());
                    if(response.body().getUserDataResponses().getGender()=="male"){
                        rbMale.setChecked(true);
                    } else if(response.body().getUserDataResponses().getGender()=="female"){
                        rbFemale.setChecked(true);
                    } else{
                        rbOther.setChecked(true);
                    }
                }
            }
            @Override
            public void onFailure(Call<UserProfileDataResponse> call, Throwable t) {
                Toast.makeText(EditProfileDetails.this, "Not getting response",Toast.LENGTH_LONG).show();
            }
        });
    }
}