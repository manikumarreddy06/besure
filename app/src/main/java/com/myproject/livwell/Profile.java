package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.myproject.livwell.FAQ.FAQ_Activity;

public class Profile extends AppCompatActivity {
    TextView tv_aboutUs, tv_tnc, tv_faq, tv_contactUs, tv_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_aboutUs = findViewById(R.id.tv_aboutUs);
        tv_tnc = findViewById(R.id.tv_tnc);
        tv_faq = findViewById(R.id.tv_faq);
        tv_contactUs = findViewById(R.id.tv_contactUs);
        tv_logout = findViewById(R.id.tv_logout);

        tv_aboutUs.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this,About_Us.class);
            startActivity(i);
        });
        tv_tnc.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this,TnC_Activity.class);
            startActivity(i);
        });
        tv_faq.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this, FAQ_Activity.class);
            startActivity(i);
        });
        tv_contactUs.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this,ContactUs.class);
            startActivity(i);
        });
        tv_logout.setOnClickListener(view -> {
            // TODO
        });
    }
}