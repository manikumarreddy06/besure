package com.bsure.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PaymentResponseBeanCF  {

    @SerializedName("isvalid")
    @Expose
    private Boolean isvalid;
    @SerializedName("finalPrce")
    @Expose
    private Long finalPrce;
    @SerializedName("discountAmount")
    @Expose
    private Long discountAmount;
    @SerializedName("paymentResponseBean")
    @Expose
    private PaymentResponseBean paymentResponseBean;

    public Boolean getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Boolean isvalid) {
        this.isvalid = isvalid;
    }

    public Long getFinalPrce() {
        return finalPrce;
    }

    public void setFinalPrce(Long finalPrce) {
        this.finalPrce = finalPrce;
    }

    public Long getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Long discountAmount) {
        this.discountAmount = discountAmount;
    }

    public PaymentResponseBean getPaymentResponseBean() {
        return paymentResponseBean;
    }

    public void setPaymentResponseBean(PaymentResponseBean paymentResponseBean) {
        this.paymentResponseBean = paymentResponseBean;
    }
}
