package com.bsure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.bsure.R;

public class empty_activity3 extends AppCompatActivity {
    Button buttonadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty3);
        buttonadd=findViewById(R.id.btn_add);
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(empty_activity3.this, Stocks_Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
}