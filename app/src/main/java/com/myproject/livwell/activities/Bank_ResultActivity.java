package com.myproject.livwell.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myproject.livwell.R;

public class Bank_ResultActivity extends AppCompatActivity {
private TextView bankname,bankaccountnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_result);
        bankname=findViewById(R.id.bankname);
        bankaccountnum=findViewById(R.id.bankaccnum);

        String data1=getIntent().getStringExtra("Extratext");
        int data2=getIntent().getIntExtra("Extranum",0);
       bankname.setText(data1);
       bankaccountnum.setText(data2);

    }

    public static class bankaccounts extends AppCompatActivity {
        private EditText et_bankname,etbankAccountno,et_IFSCCODE,et_Branch,et_Textbox,et_Atttachment;
        private Button savedata;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_bankaccounts);



         et_bankname=findViewById(R.id.et_bankname);
         etbankAccountno=findViewById(R.id.et_bankAccountnum);
         et_IFSCCODE=findViewById(R.id.et_ifsc);
         et_Branch=findViewById(R.id.et_bankbranch);
         et_Textbox=findViewById(R.id.et_bankdes);
         /*et_Atttachment=findViewById(R.id.etbankdoc);*/
         savedata=findViewById(R.id.btn_banksave);

         savedata.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

            String textbankname=et_bankname.getText().toString();
           int textaccno=Integer.parseInt(etbankAccountno.getText().toString());
           /*String ifsccode=et_IFSCCODE.getText().toString();
           String branch=et_Branch.getText().toString();*/



                 Intent intent=new Intent(bankaccounts.this, Bank_ResultActivity.class);
            intent.putExtra("Extratext",textbankname);
            intent.putExtra("Extranum",textaccno);
                 startActivity(intent);

             }
         });

        }
        public void setdata(){

        }
    }

    public static class empty_Activity extends AppCompatActivity {
    Button buttonadd;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_empty);
            buttonadd=findViewById(R.id.btn_add);
           buttonadd.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent i=new Intent(empty_Activity.this, bankaccounts.class);
                   startActivity(i);
               }
           });
        }
    }

    public static class usernominees_will_Activity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_usernominees_will);
        }
    }

    public static class empt_Activity2 extends AppCompatActivity {
    Button btnadd;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_empt2);
         btnadd=findViewById(R.id.btn_add);
         btnadd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i=new Intent(empt_Activity2.this, Mutualfunds_Activity.class);
                 startActivity(i);
                 finish();
             }
         });
        }
    }

    public static class Signin_Activity extends AppCompatActivity  {
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
            etpassword=findViewById(R.id.inputPw);


            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (TextUtils.isEmpty(mobilenum.getText().toString()) ){
                        mobilenum.setError("Mobile number cannot be empty");
                         Toast.makeText(Signin_Activity.this, "Mobile number cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(etpassword.getText().toString())){
                        etpassword.setError("Password cannot be empty");
                         Toast.makeText(Signin_Activity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent=new Intent(Signin_Activity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });



        }
        public void onregister(View view) {

            Intent intent=new Intent(Signin_Activity.this, Register_Activity.class);
            startActivity(intent);

        }

        /*public void signin() {

            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });*/



        }

    public static class empty_Activity4 extends AppCompatActivity {
    private Button btnadd;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_empty4);
            btnadd=findViewById(R.id.btn_add);
            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(empty_Activity4.this, empty_activity3.Nominees_Activity.class);
                    startActivity(i);
                    finish();
                }
            });
        }
    }
}