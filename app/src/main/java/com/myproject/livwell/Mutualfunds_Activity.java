package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

public class Mutualfunds_Activity extends AppCompatActivity {
private EditText mutualfundname,folioname,schemename,fundtype,funddes,fundproof;

private Button savedata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutualfunds);
        mutualfundname=findViewById(R.id.et_mutualfundname);
        folioname=findViewById(R.id.et_folioname);
        schemename=findViewById(R.id.et_Schemename);
        fundtype=findViewById(R.id.et_fundtype);
        funddes=findViewById(R.id.et_funddes);
        fundproof=findViewById(R.id.et_fundproof);
        savedata=findViewById(R.id.btn_banksave);



        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}