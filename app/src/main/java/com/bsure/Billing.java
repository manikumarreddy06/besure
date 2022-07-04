package com.bsure;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.ExternalWalletListener;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

public class Billing extends Activity implements PaymentResultWithDataListener, ExternalWalletListener {
    Button btn_proceed_payment;
    private static final String TAG = Billing.class.getSimpleName();
    private AlertDialog.Builder alertDialogBuilder;

    private String planName;
    private double planPrice;

    private TextView tvPlanDetails;
    private TextView tvPlanPrice;

    private TextView tvplanPrice,tvdisPrice,tvtotalPrice,tvgstPrice;

    String finalAmountStringFormat="0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        Checkout.preload(getApplicationContext());
        tvPlanDetails = findViewById(R.id.tvPlanDetails);
        tvPlanPrice = findViewById(R.id.tvPlanPrice);

        tvplanPrice = findViewById(R.id.tvplanPrice);
        tvdisPrice = findViewById(R.id.tvdisPrice);
        tvtotalPrice = findViewById(R.id.tvtotalPrice);
        tvgstPrice = findViewById(R.id.tvgstPrice);

        planName = getIntent().getStringExtra("plan");
        planPrice = getIntent().getDoubleExtra("price",0);

        tvPlanDetails.setText("Plan Details : "+planName);
        tvPlanPrice.setText("cost : "+planPrice+"");

        btn_proceed_payment = findViewById(R.id.btn_proceed_payment);
        btn_proceed_payment.setOnClickListener(view -> {

            startPayment();
        });
        alertDialogBuilder = new AlertDialog.Builder(Billing.this);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Payment Result");
        alertDialogBuilder.setPositiveButton("Ok", (dialog, which) -> {


            Intent i=new Intent(Billing.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        });

        updatePriceDetails();

    }

    private void updatePriceDetails() {

        double gstAmount=(planPrice*18)/100;
        double totalAmount=planPrice+gstAmount;

        finalAmountStringFormat=String.valueOf(totalAmount*100);

        tvplanPrice.setText(""+planPrice);
        tvdisPrice.setText(""+0);
        tvgstPrice.setText(""+gstAmount);
        tvtotalPrice.setText(""+totalAmount);
    }



    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();


        co.setKeyID("rzp_live_kaS85ZjjJygKfr");


        try {
            JSONObject options = new JSONObject();
            options.put("name", "Bsure");
            options.put("description", "Bsure Plan purchase");
            options.put("send_sms_hash",true);
            options.put("allow_rotation", true);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", finalAmountStringFormat);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }


    }

    @Override
    public void onExternalWalletSelected(String s, PaymentData paymentData) {
        try{
            alertDialogBuilder.setMessage("External Wallet Selected:\nPayment Data: "+paymentData.getData());
            alertDialogBuilder.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        try{
            alertDialogBuilder.setMessage("Payment Successful :\nPayment ID: "+s+"\nPayment Data: "+paymentData.getData());
            alertDialogBuilder.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        try{
            Utils.Companion.toast("Payment Failed:\nPayment Data: "+paymentData.getData(),Billing.this);
            //alertDialogBuilder.setMessage("Payment Failed:\nPayment Data: "+paymentData.getData());
            //alertDialogBuilder.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}