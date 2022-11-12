package com.bsure;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bsure.models.PaymentRequestCF;
import com.bsure.models.PaymentResponseBean;
import com.bsure.models.PaymentResponseBeanCF;
import com.bsure.models.userdiscountrequest;
import com.bsure.models.userdiscountresponsebean;
import com.bsure.retrofitUtil.RetrofitClient;
import com.cashfree.pg.api.CFPaymentGatewayService;
import com.cashfree.pg.core.api.CFSession;
import com.cashfree.pg.core.api.CFTheme;
import com.cashfree.pg.core.api.callback.CFCheckoutResponseCallback;
import com.cashfree.pg.core.api.exception.CFException;
import com.cashfree.pg.core.api.utils.CFErrorResponse;
import com.cashfree.pg.ui.api.CFDropCheckoutPayment;
import com.cashfree.pg.ui.api.CFPaymentComponent;
import com.razorpay.Checkout;
import com.razorpay.ExternalWalletListener;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Billing extends AppCompatActivity implements PaymentResultWithDataListener, ExternalWalletListener, CFCheckoutResponseCallback {
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
    Dialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        Checkout.preload(getApplicationContext());
        try {
            // If you are using a fragment then you need to add this line inside onCreate() of your Fragment
            CFPaymentGatewayService.getInstance().setCheckoutCallback(this);
        } catch (CFException e) {
            e.printStackTrace();
        }
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

        dialog=new Dialog(this);
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyCouponAPI();

            }
        });

        btn_proceed_payment = findViewById(R.id.btn_proceed_payment);
        btn_proceed_payment.setOnClickListener(view -> {

            //startPayment();
            //payThroughSDK();
            getPaymentDetailsForFC();
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
            redirectSuccessPage();
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

                        showcashbackdialog();
                        tvdisPrice.setText("" + bean.getDiscountAmount());
                        tvtotalPrice.setText("" + bean.getFinalPrce());
                        totalAmount= bean.getFinalPrce();



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



    private void showcashbackdialog(){

        dialog.setContentView(R.layout.cashback_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        };

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 3000);
    }


    public void doDropCheckoutPayment(PaymentResponseBean bean) {

        String token=bean.getOrderToken();
        String orderID=bean.getOrderId();
        CFSession.Environment cfEnvironment = CFSession.Environment.PRODUCTION;

        if(TextUtils.isEmpty(orderID)){
            Toast.makeText(this,"payment failure try again", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(token)){
            Toast.makeText(this,"payment failure try again", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            CFSession cfSession = new CFSession.CFSessionBuilder()
                    .setEnvironment(cfEnvironment)
                    .setOrderToken(token)
                    .setOrderId(orderID)
                    .build();
            CFPaymentComponent cfPaymentComponent = new CFPaymentComponent.CFPaymentComponentBuilder()
                    .add(CFPaymentComponent.CFPaymentModes.UPI)
                    .add(CFPaymentComponent.CFPaymentModes.NB)
                    .build();
            CFTheme cfTheme = new CFTheme.CFThemeBuilder()
                    .setNavigationBarBackgroundColor("#006EE1")
                    .setNavigationBarTextColor("#ffffff")
                    .setButtonBackgroundColor("#006EE1")
                    .setButtonTextColor("#ffffff")
                    .setPrimaryTextColor("#000000")
                    .setSecondaryTextColor("#000000")
                    .build();
            CFDropCheckoutPayment cfDropCheckoutPayment = new CFDropCheckoutPayment.CFDropCheckoutPaymentBuilder()
                    .setSession(cfSession)
                    //By default all modes are enabled. If you want to restrict the payment modes uncomment the next line
                        .setCFUIPaymentModes(cfPaymentComponent)
                    .setCFNativeCheckoutUITheme(cfTheme)
                    .build();
            CFPaymentGatewayService gatewayService = CFPaymentGatewayService.getInstance();
            gatewayService.doPayment(Billing.this, cfDropCheckoutPayment);
        } catch (CFException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void onPaymentVerify(String orderID) {
        Log.e("onPaymentVerify", "verifyPayment triggered");
        Log.d("onPaymentVerify", "verifyPayment triggered");
        // Start verifying your payment
        redirectSuccessPage();
    }

    @Override
    public void onPaymentFailure(CFErrorResponse cfErrorResponse, String orderID) {
        Log.e("onPaymentFailure " + orderID, cfErrorResponse.getMessage());
        Log.d("onPaymentFailure " + orderID, cfErrorResponse.getMessage());
    }


    private void getPaymentDetailsForFC(){

        PaymentRequestCF paymentRequestCF=new PaymentRequestCF();
        paymentRequestCF.setCustomerId(PreferenceManager.instance(this).get(PreferenceManager.USER_ID,null));
        paymentRequestCF.setOrderAmount(totalAmount);
        paymentRequestCF.setCustomerPhone(PreferenceManager.instance(this).get(PreferenceManager.USER_MOBILE_NUMBER,null));

        Call<PaymentResponseBeanCF> call=RetrofitClient.getInstance().apiinterface().getPaymentDetailsForCF(paymentRequestCF);
        call.enqueue(new Callback<PaymentResponseBeanCF>() {
            @Override
            public void onResponse(Call<PaymentResponseBeanCF> call, Response<PaymentResponseBeanCF> response) {

                PaymentResponseBeanCF bean=response.body();
                if(bean.getIsvalid().booleanValue()==true && bean.getPaymentResponseBean()!=null) {
                    doDropCheckoutPayment(bean.getPaymentResponseBean());
                }

                else{
                    Toast.makeText(Billing.this,"failure",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PaymentResponseBeanCF> call, Throwable t) {
                Toast.makeText(Billing.this,"failure",Toast.LENGTH_SHORT).show();

                Utils.Companion.toast("Payment Failed:\nPayment Data: ", Billing.this);
            }
        });


    }


    private void redirectSuccessPage(){

        Intent i = new Intent(Billing.this, payment_success.class);
        i.putExtra("plan",planName);
        i.putExtra("price",planPrice);
        i.putExtra("coupon",discountcoupon);
        startActivity(i);
    }


}


