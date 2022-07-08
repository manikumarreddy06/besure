package com.bsure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bsure.models.UpdateUserAccountRequest;
import com.bsure.models.UpdateUserAccountResponse;
import com.bsure.retrofitUtil.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileDetails extends AppCompatActivity {

    EditText etUsername,etEmail,etSecondaryNo,etWhatsappNo,etAddress;
    RadioGroup rgGender;
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
                }
            }
        });

        btnUpdateProfile.setOnClickListener(view -> {
            updateUserAccount(createRequest());
//            Toast.makeText(EditProfileDetails.this, "profile updated",Toast.LENGTH_LONG).show();
        });
    }
    //  adding validation
    //    public boolean validate() {
//        String userName = etUsername.getText().toString().trim();
//        String email = etEmail.getText().toString().trim();
//        String phNo = etPhNo.getText().toString().trim();
//        String whatsappNo = etWhatsappNo.getText().toString().trim();
//        String address = etAddress.getText().toString().trim();
//        if(userName.isEmpty()){etUsername.setError("Please enter User name"); etUsername.requestFocus(); return false;}
//        if(email.isEmpty()){etEmail.setError("Please enter Email address"); etEmail.requestFocus(); return false;}
//        if(phNo.isEmpty()){etPhNo.setError("Please enter Phone no"); etPhNo.requestFocus(); return false;}
//        if(whatsappNo.isEmpty()){etWhatsappNo.setError("Please enter Whatsapp no"); etWhatsappNo.requestFocus(); return false;}
//        if(address.isEmpty()){etAddress.setError("Please enter Address"); etAddress.requestFocus(); return false;}
//
//        // if everything goes right
//        return true;
//    }

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