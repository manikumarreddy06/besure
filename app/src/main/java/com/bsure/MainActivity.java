package com.bsure;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO toolbar actions need to be handled
        MaterialToolbar topappbar = findViewById(R.id.topappbar);
        LinearLayout llassets = findViewById(R.id.assets);
        LinearLayout llnominee = findViewById(R.id.nominee);
        LinearLayout llnudge = findViewById(R.id.nudge);
        LinearLayout lldistribution = findViewById(R.id.distribution);
        CardView step1 = findViewById(R.id.step1);
        CardView step2 = findViewById(R.id.step2);
        CardView step3 = findViewById(R.id.step3);
        CardView step4 = findViewById(R.id.step4);

        llassets.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this, Asset_Categories_Activity.class);
            startActivity(i);
        });
        llnominee.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this, NomineeListActivity.class);
            startActivity(i);
        });
        llnudge.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this, NudgeActivity.class);
            startActivity(i);
            //Utils.Companion.toast("coming soon",this);
        });
        lldistribution.setOnClickListener(view -> {
//            Intent i=new Intent(MainActivity.this,AssetDistribution.class);
//            startActivity(i);

            Utils.Companion.toast("coming soon",this);
        });


        // steps to follow
        step1.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this, User_Profile_Activity.class);
            startActivity(i);
        });
        step2.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this, Asset_Categories_Activity.class);
            startActivity(i);
        });
        step3.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this, NomineeListActivity.class);
            startActivity(i);
        });
        step4.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this, NudgeActivity.class);
            startActivity(i);
        });


//        topappbar.setNavigationOnClickListener(view -> {
//
//        });
        topappbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.profile) {
                Intent i = new Intent(MainActivity.this, Profile.class);
                startActivity(i);
            }
            return false;
        });
    }
}