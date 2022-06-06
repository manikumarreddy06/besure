package com.myproject.livwell.models;

import java.io.Serializable;

public class signup implements Serializable {
    private String mobileNumber;
    private String otp;
    private String userId;

    private final static long serialVersionUID = 4516047969515212918L;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
