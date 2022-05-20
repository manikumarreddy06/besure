package com.myproject.livwell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;



public class bankaccounts extends AppCompatActivity {
    private EditText et_bankname,etbankAccountno,et_IFSCCODE,et_Branch,et_Textbox,et_Atttachment;
    private Button savedata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankaccounts);



        et_bankname=findViewById(R.id.et_bankname);
        etbankAccountno=findViewById(R.id.et_bankAccountnum);
        et_IFSCCODE=findViewById(R.id.et_ifsc);
        et_Branch=findViewById(R.id.et_bankbranch);
        et_Textbox=findViewById(R.id.et_bankdes);
        /*et_Atttachment=findViewById(R.id.etbankdoc);*/
        savedata=findViewById(R.id.btn_banksave);

        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textbankname=et_bankname.getText().toString();
                int textaccno=Integer.parseInt(etbankAccountno.getText().toString());
       /*String ifsccode=et_IFSCCODE.getText().toString();
       String branch=et_Branch.getText().toString();*/



                Intent intent=new Intent(bankaccounts.this,Bank_ResultActivity.class);
                intent.putExtra("Extratext",textbankname);
                intent.putExtra("Extranum",textaccno);
                startActivity(intent);

            }
        });

    }
    public void setdata(){

    }
}