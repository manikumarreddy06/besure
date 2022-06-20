package com.bsure;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class nudgeActivity extends AppCompatActivity {
    Button btn_proceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nudge);

        btn_proceed = findViewById(R.id.btn_proceed);
        btn_proceed.setOnClickListener(view -> {
            //  carry card data
            Intent i=new Intent(nudgeActivity.this, Billing.class);
            startActivity(i);
        });
    }
}