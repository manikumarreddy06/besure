package com.bsure.models;

public class UpdateUserAccountRequest {
    private String userId;
    private String userName;
    private String gender;
    private String secondaryPhoneNo;
    private String email;
    private String address;
    private String whatsUpNumber;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSecondaryPhoneNo() {
        return secondaryPhoneNo;
    }

    public void setSecondaryPhoneNo(String secondaryPhoneNo) {
        this.secondaryPhoneNo = secondaryPhoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWhatsUpNumber() {
        return whatsUpNumber;
    }

    public void setWhatsUpNumber(String whatsUpNumber) {
        this.whatsUpNumber = whatsUpNumber;
    }
}
