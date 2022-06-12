package com.bsure;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bsure.R;

public class Bank_ResultActivity extends AppCompatActivity {
    private TextView bankname,bankaccountnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_item_assets);
        bankname=findViewById(R.id.bankname);
        bankaccountnum=findViewById(R.id.bankaccnum);

        String data1=getIntent().getStringExtra("Extratext");
        int data2=getIntent().getIntExtra("Extranum",0);
        bankname.setText(data1);
        bankaccountnum.setText(data2);

    }
}