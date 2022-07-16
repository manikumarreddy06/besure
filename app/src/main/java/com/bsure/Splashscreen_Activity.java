package com.bsure;

import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.bsure.PreferenceManager;
import com.bsure.R;

public class Splashscreen_Activity extends AppCompatActivity {
    private static final int LOCK_REQUEST_CODE = 100;
    Handler h=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                launchScreen();
            }
        },1000);

    }

    private void launchHomeScreen() {
        Intent i=new Intent(Splashscreen_Activity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void launchScreen() {
        if(com.bsure.PreferenceManager.instance(this).get(PreferenceManager.LOGIN_STATUS,false)){

            KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
            Intent screenLockIntent = keyguardManager.createConfirmDeviceCredentialIntent("Enter phone screen lock pattern, PIN, password or fingerprint", "unlock Bsure");
            startActivityForResult(screenLockIntent, LOCK_REQUEST_CODE);
        }
        else{
            Intent i=new Intent(Splashscreen_Activity.this, Signin_Activity.class);
            startActivity(i);
            finish();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(LOCK_REQUEST_CODE == requestCode){
            if (resultCode == RESULT_OK) {
                //Authentication is successful
                launchHomeScreen();
            } else {
                //Authentication failed
            }
        }
    }


}