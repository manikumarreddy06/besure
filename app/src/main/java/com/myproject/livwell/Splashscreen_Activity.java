package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class Splashscreen_Activity extends AppCompatActivity {
  Handler h=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

         h.postDelayed(new Runnable() {
             @Override
             public void run() {
               Intent i=new Intent(Splashscreen_Activity.this,Signin_Activity.class);
               startActivity(i);
               finish();
             }
         },1000);

    }
}