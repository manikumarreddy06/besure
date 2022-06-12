package com.bsure;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bsure.R;

public class ContactUs extends AppCompatActivity {
    ImageView instagram, linkedIn, email, call, website;
    EditText et_name, et_mail, et_suggestion;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        instagram = findViewById(R.id.iv_instagram);
        linkedIn = findViewById(R.id.iv_linkedIn);
        email = findViewById(R.id.iv_mail);
        call = findViewById(R.id.iv_call);
        website = findViewById(R.id.iv_website);
        et_name = findViewById(R.id.et_name);
        et_mail = findViewById(R.id.et_mailId);
        et_suggestion = findViewById(R.id.et_suggestion);
        send = findViewById(R.id.btn_send);

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
        send.setOnClickListener(view -> {

        });
    }
}