package com.bsure;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bsure.models.PlanDetailsResponseBean;
import com.bsure.models.userdiscountrequest;
import com.bsure.models.userdiscountresponsebean;
import com.bsure.retrofitUtil.RetrofitClient;
import com.razorpay.Checkout;
import com.razorpay.ExternalWalletListener;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Billing extends Activity implements PaymentResultWithDataListener, ExternalWalletListener {
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
                userdiscount(createrequest());

            }
        });

        btn_proceed_payment = findViewById(R.id.btn_proceed_payment);
        btn_proceed_payment.setOnClickListener(view -> {

            startPayment();
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

        double gstAmount = (planPrice * 18) / 100;
        double totalAmount = planPrice + gstAmount;


        finalAmountStringFormat = String.valueOf(totalAmount * 100);

        tvplanPrice.setText("" + planPrice);
        tvdisPrice.setText("" + 0);
        tvgstPrice.setText("" + gstAmount);
        tvtotalPrice.setText("" + totalAmount);
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

    public userdiscountrequest createrequest() {
        PreferenceManager mInstance = PreferenceManager.instance(this);
        userId = mInstance.get(PreferenceManager.USER_ID, null);

        userdiscountrequest userdiscountrequest = new userdiscountrequest();

        userdiscountrequest.setUserId(userId);
        userdiscountrequest.setCode(discountcoupon);
        userdiscountrequest.setPlanAmount(userplanprice);
        return userdiscountrequest;
    }

    public void userdiscount(userdiscountrequest userdiscountrequest) {

        discountcoupon = et_apply.getText().toString();

        if (discountcoupon.length() == 0) {
            et_apply.setError("invalid coupon");

        } else {
            Call<userdiscountresponsebean> userdiscountresponsebeanCall = RetrofitClient.getInstance().apiinterface().userdiscount(userdiscountrequest);
            userdiscountresponsebeanCall.enqueue(new Callback<userdiscountresponsebean>() {
                @Override
                public void onResponse(Call<userdiscountresponsebean> call, Response<userdiscountresponsebean> response) {
                    userdiscountresponsebean bean = response.body();

                    if (bean.getIsvalid()) {
                        tvdisPrice.setText(bean.getDiscountAmount());
                        tvtotalPrice.setText(bean.getFinalPrce());


                    } else {
                        Toast.makeText(Billing.this, "failure", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<userdiscountresponsebean> call, Throwable t) {
                    Toast.makeText(Billing.this, "failure", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

}


