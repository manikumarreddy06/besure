package com.bsure.refundPolicy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.bsure.ContactUs;
import com.bsure.FAQ.FAQ_Activity;
import com.bsure.R;

import java.util.ArrayList;

public class refundPolicy_Activity extends AppCompatActivity {
    ArrayList<refund_items>refundItemsList;
    RecyclerView recyclerView;
    Button sendMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_policy);

        // adding data to the arraylist
        recyclerView = findViewById(R.id.recycler_view);
        initData();
        setRecyclerView();

        // sending message
        sendMessage = findViewById(R.id.btn_send_a_Msg);
        sendMessage.setOnClickListener(view -> {
            Intent i=new Intent(refundPolicy_Activity.this, ContactUs.class);
            startActivity(i);
        });
    }

    private void setRecyclerView() {
        refundpolicy_item_Adapter refundpolicyItemAdapter = new refundpolicy_item_Adapter(refundItemsList);
        recyclerView.setAdapter(refundpolicyItemAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        refundItemsList = new ArrayList<>();
        refundItemsList.add(new refund_items("My amount got deducted still subscription is not shown. What is to be done?","Generally, if the amount was stuck with Payment gateway and your bank, it will be automatically sent to your account in 7 days. If the amount is received by Bsure, we will mark this as subscription fees payment. If you have made more than one payment, we will refund the excess account to your source within 7-14 days."));
        refundItemsList.add(new refund_items("I have already taken lifetime subscription but have changed my mind. Can i get my money back?","Bsure have a no question ask refund policy. If you are not happy with Bsure's services, we will refund the amount deducting the charges already incurred. We will refund your money no questions asked."));
        refundItemsList.add(new refund_items("I have taken a yearly plan. but don't want it now, should i get a refund ?","Yes. We have a no question asked refund policy. If you want to stop the plan, we will deduct the amount for number of days you have used the plan and refund the rest of the amount."));
    }


}