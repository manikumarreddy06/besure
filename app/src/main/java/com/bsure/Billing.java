package com.bsure;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bsure.models.CategoryResponseBean;
import com.bsure.models.PlanDetailsResponseBean;
import com.bsure.models.userdiscountrequest;
import com.bsure.models.userdiscountresponsebean;
import com.bsure.retrofitUtil.RetrofitClient;
import com.razorpay.Checkout;
import com.razorpay.ExternalWalletListener;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import dev.shreyaspatil.easyupipayment.EasyUpiPayment;
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener;
import dev.shreyaspatil.easyupipayment.model.PaymentApp;
import dev.shreyaspatil.easyupipayment.model.TransactionDetails;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Billing extends Activity implements PaymentResultWithDataListener, ExternalWalletListener, PaymentStatusListener {
    Button btn_proceed_payment, btn_apply;
    private static final String TAG = Billing.class.getSimpleName();
    private AlertDialog.Builder alertDialogBuilder;

    private String planName;
    private double planPrice;

    private TextView tvPlanDetails;
    private TextView tvPlanPrice;

    private TextView tvplanPrice, tvdisPrice, tvtotalPrice, tvgstPrice;

    String finalAmountStringFormat = "0";

    private EditText et_apply;
    String userId;
    String discountcoupon;
    String userplanprice;

    double totalAmount=0;

    double GSTPercentage=0;


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
        planPrice = getIntent().getDoubleExtra("price", 0);


        tvPlanDetails.setText("Plan Details : " + planName);
        tvPlanPrice.setText("cost : " + planPrice + "");


        et_apply = findViewById(R.id.et_couponcode);
        btn_apply = findViewById(R.id.btn_apply);
        userplanprice = tvPlanPrice.getText().toString();
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyCouponAPI();

            }
        });

        btn_proceed_payment = findViewById(R.id.btn_proceed_payment);
        btn_proceed_payment.setOnClickListener(view -> {

            //startPayment();
            payThroughSDK();
        });
        alertDialogBuilder = new AlertDialog.Builder(Billing.this);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Payment Result");
        alertDialogBuilder.setPositiveButton("Ok", (dialog, which) -> {


            Intent i = new Intent(Billing.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        });

        updatePriceDetails();

    }

    private void updatePriceDetails() {
        double gstAmount=0;

        if(GSTPercentage>0) {
            gstAmount = (planPrice * GSTPercentage) / 100;
        }
         totalAmount = planPrice + gstAmount;

        tvplanPrice.setText("" + planPrice);
        tvdisPrice.setText("" + 0);
        tvgstPrice.setText("" + gstAmount);
        tvtotalPrice.setText("" + totalAmount);
    }


    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */


        finalAmountStringFormat = String.valueOf(totalAmount * 100);
        final Activity activity = this;

        final Checkout co = new Checkout();


        co.setKeyID("rzp_live_kaS85ZjjJygKfr");


        try {
            JSONObject options = new JSONObject();
            options.put("name", "Bsure");
            options.put("description", "Bsure Plan purchase");
            options.put("send_sms_hash", true);
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
        try {
            alertDialogBuilder.setMessage("External Wallet Selected:\nPayment Data: " + paymentData.getData());
            alertDialogBuilder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        try {
            Intent i = new Intent(Billing.this, payment_success.class);
            startActivity(i);
           /* alertDialogBuilder.setMessage("Payment Successful :\nPayment ID: "+s+"\nPayment Data: "+paymentData.getData());
            alertDialogBuilder.show();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        try {
            Utils.Companion.toast("Payment Failed:\nPayment Data: " + paymentData.getData(), Billing.this);
            //alertDialogBuilder.setMessage("Payment Failed:\nPayment Data: "+paymentData.getData());
            //alertDialogBuilder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void applyCouponAPI() {

        discountcoupon = et_apply.getText().toString();

        if (discountcoupon.length() == 0) {
            et_apply.setError("invalid coupon");

        } else {

            userdiscountrequest userdiscountrequest=new userdiscountrequest();
            userdiscountrequest.setUserId(PreferenceManager.instance(this).get(PreferenceManager.USER_ID,null));
            userdiscountrequest.setPlanAmount(String.valueOf((int)planPrice));
            userdiscountrequest.setCode(discountcoupon);

            Call<userdiscountresponsebean> call=RetrofitClient.getInstance().apiinterface().userdiscount(userdiscountrequest);
            call.enqueue(new Callback<userdiscountresponsebean>() {
                @Override
                public void onResponse(Call<userdiscountresponsebean> call, Response<userdiscountresponsebean> response) {

                    userdiscountresponsebean bean=response.body();
                    if(bean.getIsvalid().booleanValue()==true) {

                        Toast.makeText(Billing.this,"Success",Toast.LENGTH_SHORT).show();
                        tvdisPrice.setText("" + bean.getDiscountAmount());
                        tvtotalPrice.setText("" + bean.getFinalPrce());
                    }

                    else{
                        Toast.makeText(Billing.this,"failure",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<userdiscountresponsebean> call, Throwable t) {
                    Toast.makeText(Billing.this,"failure",Toast.LENGTH_SHORT).show();
                }
            });


        }

    }


    private EasyUpiPayment easyUpiPayment;
    private void payThroughSDK(){

        String payeeVpa = "srinu532avula@icici";
        String payeeName = "strinu";
        String payeeMerchantCode = "sss";
        String description = "Bsure Plan purchase";
        String amount = finalAmountStringFormat;


        String transactionId = "TID" + System.currentTimeMillis();
        PaymentApp paymentApp;
        paymentApp = PaymentApp.ALL;

        // START PAYMENT INITIALIZATION
        EasyUpiPayment.Builder builder = new EasyUpiPayment.Builder(this)
                .with(paymentApp)
                .setPayeeVpa(payeeVpa)
                .setPayeeName(payeeName)
                .setTransactionId(transactionId)
                .setTransactionRefId(transactionId)
                .setPayeeMerchantCode(payeeMerchantCode)
                .setDescription(description)
                .setAmount(amount);
        // END INITIALIZATION

        try {
            // Build instance
            easyUpiPayment = builder.build();

            // Register Listener for Events
            easyUpiPayment.setPaymentStatusListener(this);

            // Start payment / transaction
            easyUpiPayment.startPayment();
        } catch (Exception exception) {
            exception.printStackTrace();
            toast("Error: " + exception.getMessage());
        }
    }

    @Override
    public void onTransactionCompleted(TransactionDetails transactionDetails) {
        // Transaction Completed
        Log.d("TransactionDetails", transactionDetails.toString());

        switch (transactionDetails.getTransactionStatus()) {
            case SUCCESS:
                onTransactionSuccess();
                break;
            case FAILURE:
                onTransactionFailed();
                break;
            case SUBMITTED:
                onTransactionSubmitted();
                break;
        }
    }

    @Override
    public void onTransactionCancelled() {
        // Payment Cancelled by User
        toast("Cancelled by user");
    }

    private void onTransactionSuccess() {
        // Payment Success
        toast("Success");
    }

    private void onTransactionSubmitted() {
        // Payment Pending
        toast("Pending | Submitted");
    }

    private void onTransactionFailed() {
        // Payment Failed
        toast("Failed");
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}


