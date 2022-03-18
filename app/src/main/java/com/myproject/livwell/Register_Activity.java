package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_Activity extends AppCompatActivity {
private EditText name,mobilenum,password,repassword;
private Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.et_name);
       mobilenum=findViewById(R.id.et_mobilenum);
        password=findViewById(R.id.et_password);
        signup=findViewById(R.id.btn_sign_up);
        repassword=findViewById(R.id.et_repassword);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();

            }
        });
    }
    public void register(){
        if (TextUtils.isEmpty(name.getText().toString())){
            name.setError("Please enter your name ");
        }
       else if (TextUtils.isEmpty(mobilenum.getText().toString())){
            mobilenum.setError("Enter a valid mobile number");
        }
        else if (TextUtils.isEmpty(password.getText().toString())){
            password.setError("enter your password");
        }
        else if (TextUtils.isEmpty(password.getText().toString())){
           repassword.setError("passwords doesn't match");
        }
        else {
            Toast.makeText(this, "regisration successful", Toast.LENGTH_SHORT).show();
            // Todo:authenticate  a user using api
        }
    }
}