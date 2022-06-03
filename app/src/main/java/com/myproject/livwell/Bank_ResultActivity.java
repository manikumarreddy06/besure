package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Bank_ResultActivity extends AppCompatActivity {
    private TextView bankname,bankaccountnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_result);
        /*bankname=findViewById(R.id.bankname);
        bankaccountnum=findViewById(R.id.bankaccnum);

        String data1=getIntent().getStringExtra("Extratext");
        int data2=getIntent().getIntExtra("Extranum",0);
        bankname.setText(data1);
        bankaccountnum.setText(data2);
*/
    }
}