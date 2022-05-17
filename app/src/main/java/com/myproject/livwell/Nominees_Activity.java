package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.myproject.livwell.models.createnomineeresponse;
import com.myproject.livwell.retrofitUtil.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Nominees_Activity extends AppCompatActivity {
    private EditText nomineesname,relationwithnominee,nomineesmobilenumber,nomineesmailid,address;
    Button savenomineedata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nominees);
        nomineesname=findViewById(R.id.et_nomineename);
        nomineesmobilenumber=findViewById(R.id.nomineephone);
        savenomineedata=findViewById(R.id.btn_nomineesavedata);
        savenomineedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createnominee();
            }
        });



    }

    public void createnominee(){
        String nomineename=nomineesname.getText().toString();
        String nomineemobilenum=nomineesmobilenumber.getText().toString();
        if (TextUtils.isEmpty(nomineename)){
            Toast.makeText(Nominees_Activity.this,"Nominee name cannot be empty",Toast.LENGTH_SHORT).show();
        }

       else if (TextUtils.isEmpty(nomineemobilenum)){
            Toast.makeText(Nominees_Activity.this,"nominee mobile number cannot be empty",Toast.LENGTH_SHORT).show();
        }
       else{
            Call<createnomineeresponse>call= RetrofitClient.getInstance().apiinterface().createnominee(nomineename,nomineemobilenum);
            {
                call.enqueue(new Callback<createnomineeresponse>() {
                    @Override
                    public void onResponse(Call<createnomineeresponse> call, Response<createnomineeresponse> response) {
                        Toast.makeText(Nominees_Activity.this, response+"is success", Toast.LENGTH_SHORT).show();
                        createnomineeresponse nomineeresponse=response.body();

                    }

                    @Override
                    public void onFailure(Call<createnomineeresponse> call, Throwable t) {
                        Toast.makeText(Nominees_Activity.this, "is failure"+t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}