package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ContactUs extends AppCompatActivity {
    ImageView instagram, linkedIn, email, call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        instagram = findViewById(R.id.iv_instagram);
        linkedIn = findViewById(R.id.iv_linkedIn);
        email = findViewById(R.id.iv_mail);
        call = findViewById(R.id.iv_call);

        instagram.setOnClickListener(view -> {

        });
        linkedIn.setOnClickListener(view -> {

        });
        email.setOnClickListener(view -> {

        });
        call.setOnClickListener(view -> {

        });
    }
}