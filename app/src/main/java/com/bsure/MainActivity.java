package com.bsure;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bsure.R;
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

        //Implementing category recyclerview
//        recyclerView.findViewById(R.id.category_recycler_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        llassets.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this, Asset_Categories_Activity.class);
            startActivity(i);
        });
        llnominee.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this, NomineeListActivity.class);
            startActivity(i);
        });
        llnudge.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this, Nudge_activity.class);
            startActivity(i);
        });
//        lldistribution.setOnClickListener(view -> {
//            Intent i=new Intent(MainActivity.this,empty_activity3.class);
//            startActivity(i);
//        });


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