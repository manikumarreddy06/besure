package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity  {
    LinearLayout llassets,llnominee,llnudge, lldistribution;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar topappbar = findViewById(R.id.topappbar);
        llassets = findViewById(R.id.assets);
        llnominee = findViewById(R.id.nominee);
        llnominee = findViewById(R.id.nudge);
        lldistribution = findViewById(R.id.distribution);

        //Implementing category recyclerview
//        recyclerView.findViewById(R.id.category_recycler_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        llassets.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this,empty_Activity.class);
            startActivity(i);
        });
        llnominee.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this,empt_Activity2.class);
            startActivity(i);
        });
//        llnudge.setOnClickListener(view -> {
//            Intent i=new Intent(MainActivity.this,empty_activity3.class);
//            startActivity(i);
//        });
//        lldistribution.setOnClickListener(view -> {
//            Intent i=new Intent(MainActivity.this,empty_activity3.class);
//            startActivity(i);
//        });


//        topappbar.setNavigationOnClickListener(view -> {
//
//        });
//        topappbar.setOnMenuItemClickListener(item -> {
//            if (item.getItemId() == R.id.profile) {
//                Intent i = new Intent(MainActivity.this, Profile.class);
//                startActivity(i);
//            }
//            return false;
//        });





    }



}