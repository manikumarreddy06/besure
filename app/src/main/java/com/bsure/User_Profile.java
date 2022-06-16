package com.bsure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class User_Profile extends AppCompatActivity {
    CardView cv_editProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        cv_editProfile = findViewById(R.id.btn_editDetails);
        cv_editProfile.setOnClickListener(view -> {
            Intent i=new Intent(User_Profile.this, EditProfileDetails.class);
            startActivity(i);
        });
    }
}