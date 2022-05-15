package com.myproject.livwell.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myproject.livwell.R;

public class empty_activity3 extends AppCompatActivity {
    Button buttonadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty3);
        buttonadd=findViewById(R.id.btn_add);
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(empty_activity3.this, Stocks_Activity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public static class Nominees_Activity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_nominees);

        }
    }

    public static class Stocks_Activity extends AppCompatActivity {
    EditText brokername,Dematnum,stocksdes,stockproof;
    Button savedata;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_stocks);
            brokername=findViewById(R.id.et_Brokername);
            Dematnum=findViewById(R.id.et_Dematnum);
            stocksdes=findViewById(R.id.et_stocksdes);
            stockproof=findViewById(R.id.et_stocksdproof);
            savedata=findViewById(R.id.btn_banksave);
            savedata.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (TextUtils.isEmpty(brokername.getText().toString())){
                        brokername.setError("broker  name is Mandatory");
                    }
                    else{

                    }
                }
            });
        }
    }
}