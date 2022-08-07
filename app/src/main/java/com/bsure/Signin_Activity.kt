package com.bsure

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bsure.TnC.TnC_Activity
import com.bsure.models.BaseResponse
import com.bsure.models.signup
import com.bsure.retrofitUtil.RetrofitClient
import kotlinx.android.synthetic.main.activity_signin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Signin_Activity : AppCompatActivity() {

    var verifyOtpFlag:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        disableSignInButton()
        btn_sign_in.setOnClickListener(View.OnClickListener {
            if(!verifyOtpFlag) {
                signinAPICall()
            }
            else{
                verifyOtpApiCall()
            }
        })

        tvChangeNumber.setOnClickListener(){
            disableOtpUI()
            verifyOtpFlag=false
        }
        checkBox.setOnClickListener(){
            if(checkBox.isChecked){
                enableSignInButton()
            }
            else{
                disableSignInButton()
            }
        }
        terms.setOnClickListener(){
//            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://bsure.live/terms-%26-conditions/f/bsure-terms-conditions"))
//            startActivity(browserIntent)
            val i = Intent(this@Signin_Activity, TnC_Activity::class.java)
            startActivity(i)
        }

    }

    fun signinAPICall() {
        val usernum = etmobile_signin.text.toString()
        if (TextUtils.isEmpty(usernum)) {
            etmobile_signin!!.error = "Mobile number cannot be empty"
            Toast.makeText(this, "Mobile number cannot be empty", Toast.LENGTH_SHORT).show()
        } else {
            val obj = signup()
            obj.mobileNumber = usernum
            val call = RetrofitClient.getInstance().apiinterface().usersignin(obj)
            call.enqueue(object : Callback<signup?> {
                override fun onResponse(call: Call<signup?>, response: Response<signup?>) {
                    if (response.isSuccessful) {
                        enableOtpUI()
                        PreferenceManager.instance(this@Signin_Activity).set(PreferenceManager.USER_ID,response.body()!!.userId)
                    }
                }

                override fun onFailure(call: Call<signup?>, t: Throwable) {
                    Toast.makeText(this@Signin_Activity, "failure", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }



    fun verifyOtpApiCall() {
        val enterOtp = otp_edit_box1.text.toString()+otp_edit_box2.text.toString()+otp_edit_box3.text.toString()+otp_edit_box4.text.toString()+otp_edit_box5.text.toString()
        if (enterOtp.length == 5) {
            Utils.showDialog(Signin_Activity@ this, "loading")
            val obj = signup()
            obj.otp=enterOtp
            obj.userId=PreferenceManager.instance(this@Signin_Activity).get(PreferenceManager.USER_ID,null)
            val call = RetrofitClient.getInstance().apiinterface().verifyOtp(obj)
            call.enqueue(object : Callback<BaseResponse?> {
                override fun onResponse(call: Call<BaseResponse?>, response: Response<BaseResponse?>) {
                        var bean: BaseResponse? =response.body()
                        if (bean!!.isvalid) {
                            openMainActvity()
                            PreferenceManager.instance(this@Signin_Activity).set(PreferenceManager.LOGIN_STATUS,true)
                            val usernum = etmobile_signin.text.toString()
                            PreferenceManager.instance(this@Signin_Activity).set(PreferenceManager.USER_MOBILE_NUMBER,usernum)

                        }
                        else{
                            if (!TextUtils.isEmpty(bean.message)) {
                                Toast.makeText(this@Signin_Activity, bean.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                            else{
                                Toast.makeText(this@Signin_Activity, "failure", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                        Utils.hideDialog()
                    }

                    override fun onFailure(call: Call<BaseResponse?>, t: Throwable) {
                        Toast.makeText(this@Signin_Activity, "failure", Toast.LENGTH_SHORT).show()
                        Utils.hideDialog()
                    }
                })
        }
        else{

            Toast.makeText(this@Signin_Activity, "enter otp", Toast.LENGTH_SHORT).show()
        }
    }

    fun openMainActvity(){
        val intent = Intent(this@Signin_Activity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun enableOtpUI(){
        root_otp_layout.visibility=View.VISIBLE
        tvChangeNumber.visibility=View.VISIBLE
        btn_sign_in.text="VERIFY OTP"
        verifyOtpFlag=true;
        attachTextWatchers()
    }

    fun disableOtpUI(){
        root_otp_layout.visibility=View.GONE
        tvChangeNumber.visibility=View.GONE
        btn_sign_in.text=getString(R.string.sign_in)
        verifyOtpFlag=false;
    }
    private fun attachTextWatchers() {
        otp_edit_box1.addTextChangedListener(GenericTextWatcher(otp_edit_box1, otp_edit_box2,Signin_Activity@this))
        otp_edit_box2.addTextChangedListener(GenericTextWatcher(otp_edit_box2, otp_edit_box3,Signin_Activity@this))
        otp_edit_box3.addTextChangedListener(GenericTextWatcher(otp_edit_box3, otp_edit_box4,Signin_Activity@this))
        otp_edit_box4.addTextChangedListener(GenericTextWatcher(otp_edit_box4, otp_edit_box5,Signin_Activity@this))
        otp_edit_box5.addTextChangedListener(GenericTextWatcher(otp_edit_box5, null,Signin_Activity@this))

        otp_edit_box1.setOnKeyListener(GenericKeyEvent(otp_edit_box1, null))
        otp_edit_box2.setOnKeyListener(GenericKeyEvent(otp_edit_box2, otp_edit_box1))
        otp_edit_box3.setOnKeyListener(GenericKeyEvent(otp_edit_box3, otp_edit_box2))
        otp_edit_box4.setOnKeyListener(GenericKeyEvent(otp_edit_box4, otp_edit_box3))
        otp_edit_box5.setOnKeyListener(GenericKeyEvent(otp_edit_box5, otp_edit_box4))
    }

    fun disableSignInButton(){
        btn_sign_in.isEnabled=false
        btn_sign_in.alpha=0.3f
    }
    fun enableSignInButton(){
        btn_sign_in.isEnabled=true
        btn_sign_in.alpha=1f
    }


}