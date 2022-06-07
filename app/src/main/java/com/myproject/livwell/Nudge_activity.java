package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.myproject.livwell.TnC.TnC_Activity;

public class Nudge_activity extends AppCompatActivity {
    Button btn_proceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nudge);

        btn_proceed = findViewById(R.id.btn_proceed);
        btn_proceed.setOnClickListener(view -> {
            //  carry card data
            Intent i=new Intent(Nudge_activity.this, Billing.class);
            startActivity(i);
        });
    }
}