package com.bsure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class payment_success extends AppCompatActivity {
    LottieAnimationView lottieAnimationView,lottieanim2;
    TextView tv1,tv2;
    Button btndone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);
        lottieAnimationView=findViewById(R.id.payment_succ);
        lottieanim2=findViewById(R.id.plansuccess);
        tv1=findViewById(R.id.textview_paysuccess);
        tv2=findViewById(R.id.textview_nudge);
        btndone=findViewById(R.id.btn_done);
        //starting bgthread on main thread
        thread.start();
        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(payment_success.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

    Thread thread = new Thread() {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv1.setVisibility(View.INVISIBLE);
                    lottieAnimationView.setVisibility(lottieAnimationView.INVISIBLE);
                    lottieanim2.setVisibility(lottieanim2.VISIBLE);
                    tv2.setVisibility(View.VISIBLE);
                }
            });
        }
    };
}