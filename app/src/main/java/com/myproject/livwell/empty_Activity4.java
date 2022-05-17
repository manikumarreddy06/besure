package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class empty_Activity4 extends AppCompatActivity {
    private Button btnadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty4);
        btnadd=findViewById(R.id.btn_add);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(empty_Activity4.this,Nominees_Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
}