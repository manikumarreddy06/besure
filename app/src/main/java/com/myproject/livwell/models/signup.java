package com.myproject.livwell.models;

import java.io.Serializable;

public class signup implements Serializable {
    private String mobileNumber;

    private final static long serialVersionUID = 4516047969515212918L;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


}
