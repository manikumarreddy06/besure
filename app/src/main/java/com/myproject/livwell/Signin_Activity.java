package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.myproject.livwell.models.signup;
import com.myproject.livwell.retrofitUtil.RetrofitClient;
import com.myproject.livwell.retrofitUtil.Apiinterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Signin_Activity extends AppCompatActivity  {
    private EditText mobilenum,  etpassword;
    private Button signin;
    private TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);






       signin.setOnClickListener(new View.OnClickListener() {

           @Override

           public void onClick(View view) {
               mobilenum = findViewById(R.id.etmobile_signin);
               signin = findViewById(R.id.btn_sign_in);

               signin();
           }
       });
    }


    public void signin() {

           String usernum=mobilenum.getText().toString();


        if (TextUtils.isEmpty(usernum)){
            mobilenum.setError("Mobile number cannot be empty");
            Toast.makeText(this, "Mobile number cannot be empty", Toast.LENGTH_SHORT).show();
        }

       /* else if (TextUtils.isEmpty(etpassword.getText().toString())){
            etpassword.setError("Password cannot be empty");
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
        }*/

        else {

            Call<signup>call=RetrofitClient.getInstance().apiinterface().usersignin(usernum);
            call.enqueue(new Callback<signup>() {
                @Override
                public void onResponse(Call<signup> call, Response<signup> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(Signin_Activity.this,response+"is success",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<signup> call, Throwable t) {
                    Toast.makeText(Signin_Activity.this,"failure",Toast.LENGTH_SHORT).show();
                }
            });
           /* Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();*/
        }
   }

}