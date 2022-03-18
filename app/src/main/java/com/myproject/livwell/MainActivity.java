package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity  {
    LinearLayout llbanks,llmutualfunds,llstocks;

    private MaterialToolbar topappbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llbanks=findViewById(R.id.llbanks);
        llmutualfunds=findViewById(R.id.llmutualfunds);
        llstocks=findViewById(R.id.llstocks);
          topappbar=findViewById(R.id.topappbar);


       llbanks.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(MainActivity.this,empty_Activity.class);
               startActivity(i);
           }
       });
       llmutualfunds.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(MainActivity.this,empt_Activity2.class);
               startActivity(i);
           }
       });
       llstocks.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(MainActivity.this,empty_activity3.class);
               startActivity(i);
           }
       });


        topappbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        topappbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case  R.id.usernomineeswill:
                    {


                    }
                    case R.id.usernominees:
                    {

                        Intent i=new Intent(MainActivity.this,Nominees_Activity.class);
                        startActivity(i);

                    }
                    case R.id.privacypolicy:{

                    }
                    case R.id.tandc:{

                    }
                    case R.id.logout:{

                    }
                }
                return false;
            }
        });

    }



}