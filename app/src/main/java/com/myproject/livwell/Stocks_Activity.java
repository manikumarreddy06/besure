package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Stocks_Activity extends AppCompatActivity {
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