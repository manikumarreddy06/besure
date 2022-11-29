
package com.bsure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyResponseBean {

    @SerializedName("isvalid")
    @Expose
    private Boolean isvalid;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("userData")
    @Expose
    private UserData userData;
    @SerializedName("finalPrce")
    @Expose
    private Integer finalPrce;
    @SerializedName("discountAmount")
    @Expose
    private Integer discountAmount;

    public Boolean getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Boolean isvalid) {
        this.isvalid = isvalid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Integer getFinalPrce() {
        return finalPrce;
    }

    public void setFinalPrce(Integer finalPrce) {
        this.finalPrce = finalPrce;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

}
