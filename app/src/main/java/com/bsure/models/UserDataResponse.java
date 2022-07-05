package com.bsure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDataResponse {

    private String userId;

    private String userName;

    private String email;

    private String gender;

    private String addess;

    private String alternateNumber;

    private String whatsUpNumber;

    public UserDataResponse(String userId, String userName, String email, String gender, String addess, String alternateNumber, String whatsUpNumber) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.addess = addess;
        this.alternateNumber = alternateNumber;
        this.whatsUpNumber = whatsUpNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddess() {
        return addess;
    }

    public void setAddess(String addess) {
        this.addess = addess;
    }

    public String getAlternateNumber() {
        return alternateNumber;
    }

    public void setAlternateNumber(String alternateNumber) {
        this.alternateNumber = alternateNumber;
    }

    public String getWhatsUpNumber() {
        return whatsUpNumber;
    }

    public void setWhatsUpNumber(String whatsUpNumber) {
        this.whatsUpNumber = whatsUpNumber;
    }
}
