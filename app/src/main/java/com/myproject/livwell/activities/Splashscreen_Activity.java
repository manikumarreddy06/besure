package com.myproject.livwell.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.myproject.livwell.R;

public class Splashscreen_Activity extends AppCompatActivity {
  Handler h=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

         h.postDelayed(new Runnable() {
             @Override
             public void run() {
               Intent i=new Intent(Splashscreen_Activity.this, Bank_ResultActivity.Signin_Activity.class);
               startActivity(i);
               finish();
             }
         },1000);

    }
}