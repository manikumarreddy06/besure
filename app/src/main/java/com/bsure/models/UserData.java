
package com.bsure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("addess")
    @Expose
    private String addess;
    @SerializedName("alternateNumber")
    @Expose
    private String alternateNumber;
    @SerializedName("whatsUpNumber")
    @Expose
    private String whatsUpNumber;
    @SerializedName("paidFlag")
    @Expose
    private String paidFlag;
    @SerializedName("planDetails")
    @Expose
    private String planDetails;
    @SerializedName("refralCode")
    @Expose
    private String refralCode;
    @SerializedName("token")
    @Expose
    private String token;

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

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
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

    public String getPaidFlag() {
        return paidFlag;
    }

    public void setPaidFlag(String paidFlag) {
        this.paidFlag = paidFlag;
    }

    public String getPlanDetails() {
        return planDetails;
    }

    public void setPlanDetails(String planDetails) {
        this.planDetails = planDetails;
    }

    public String getRefralCode() {
        return refralCode;
    }

    public void setRefralCode(String refralCode) {
        this.refralCode = refralCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
