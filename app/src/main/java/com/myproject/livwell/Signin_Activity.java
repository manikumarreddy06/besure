package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class Signin_Activity extends AppCompatActivity  {
    private Button signin;
    private TextView register;
    private EditText mobilenum;
    private EditText etpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        signin=findViewById(R.id.btn_sign_in);
        register=findViewById(R.id.joinnow);
        mobilenum=findViewById(R.id.etmobile);
        etpassword=findViewById(R.id.et_password);



    }
    public void onregister(View view) {

        Intent intent=new Intent(this,Register_Activity.class);
        startActivity(intent);

    }

    public void signin(View view) {
        if (TextUtils.isEmpty(mobilenum.getText().toString()) ){
            mobilenum.setError("Mobile number cannot be empty");
            Toast.makeText(this, "Mobile number cannot be empty", Toast.LENGTH_SHORT).show();
        }
       else if (TextUtils.isEmpty(etpassword.getText().toString())){
            etpassword.setError("Password cannot be empty");
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }


    }


}