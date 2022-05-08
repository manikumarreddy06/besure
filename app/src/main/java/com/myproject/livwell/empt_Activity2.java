package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class empt_Activity2 extends AppCompatActivity {
Button btnadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empt2);
     btnadd=findViewById(R.id.btn_add);
     btnadd.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent i=new Intent(empt_Activity2.this,Mutualfunds_Activity.class);
             startActivity(i);
             finish();
         }
     });
    }
}