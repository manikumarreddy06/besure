package com.bsure;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bsure.FAQ.FAQ_Activity;
import com.bsure.R;
import com.bsure.TnC.TnC_Activity;
import com.bsure.refundPolicy.refundPolicy_Activity;

public class Profile extends AppCompatActivity {
    TextView tv_aboutUs, tv_tnc, tv_faq, tv_contactUs, tv_logout, tv_privacypolicy, tv_refundPolicy;
    CardView btn_editProfile;
    TextView tvUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        tvUsername = findViewById(R.id.tv_user_name);
        String userName=PreferenceManager.instance(Profile.this).get(PreferenceManager.USER_NAME,null);
        if(!TextUtils.isEmpty(userName)){
            tvUsername.setText(userName);
        }

        tv_aboutUs = findViewById(R.id.tv_aboutUs);
        tv_tnc = findViewById(R.id.tv_tnc);
        tv_faq = findViewById(R.id.tv_faq);
        tv_contactUs = findViewById(R.id.tv_contactUs);
        tv_logout = findViewById(R.id.tv_logout);
        tv_privacypolicy = findViewById(R.id.tv_privacypolicy);
        tv_refundPolicy = findViewById(R.id.tv_refundPolicy);
        btn_editProfile = findViewById(R.id.cv_editProfile);

        // Edit Profile
        btn_editProfile.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this, User_Profile.class);
            startActivity(i);
        });

        tv_aboutUs.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this, About_Us.class);
            startActivity(i);
        });
        tv_tnc.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this, TnC_Activity.class);
            startActivity(i);
        });
        tv_faq.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this, FAQ_Activity.class);
            startActivity(i);
        });
        tv_contactUs.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this, ContactUs.class);
            startActivity(i);
        });
        tv_refundPolicy.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this, refundPolicy_Activity.class);
            startActivity(i);
        });
        tv_privacypolicy.setOnClickListener(view -> {
            Intent i=new Intent(Profile.this, privacy_Policy.class);
            startActivity(i);
        });
        tv_logout.setOnClickListener(view -> {
            AlertDialog.Builder builder
                    = new AlertDialog
                    .Builder(Profile.this);
            builder.setMessage("Confirm to Logout ?");
            builder.setTitle("Logout!");
            builder.setCancelable(false);

            builder
                    .setPositiveButton(
                            "Yes",
                            new DialogInterface
                                    .OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which)
                                {
                                    PreferenceManager.instance(Profile.this).clearUserSession();
                                    Intent i=new Intent(Profile.this, Splashscreen_Activity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);
                                }
                            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {  dialog.cancel(); }
                            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            // TODO



        });

    }
}