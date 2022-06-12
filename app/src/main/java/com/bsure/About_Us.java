package com.bsure;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bsure.R;

public class About_Us extends AppCompatActivity {
    ImageView instagram, linkedIn, email, call, website;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        instagram = findViewById(R.id.iv_instagram);
        linkedIn = findViewById(R.id.iv_linkedIn);
        email = findViewById(R.id.iv_mail);
        call = findViewById(R.id.iv_call);
        website = findViewById(R.id.iv_website);

        instagram.setOnClickListener(view -> {

        });
        linkedIn.setOnClickListener(view -> {

        });
        email.setOnClickListener(view -> {

        });
        call.setOnClickListener(view -> {

        });
        website.setOnClickListener(view -> {

        });

        // to change toolbar to an actionbar
//        toolbar = findViewById(R.id.topappbar);
//        setSupportActionBar(toolbar);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

}