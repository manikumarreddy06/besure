package com.bsure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class signupupdate implements Serializable {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("usmToken")
    @Expose
    private String usmToken;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsmToken() {
        return usmToken;
    }

    public void setUsmToken(String usmToken) {
        this.usmToken = usmToken;
    }

}

