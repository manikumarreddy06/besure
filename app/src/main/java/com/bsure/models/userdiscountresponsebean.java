package com.bsure.models;

import java.io.Serializable;

public class userdiscountresponsebean implements Serializable {
    private Boolean isvalid;
    private String message;
    private Integer finalPrce;
    private Integer discountAmount;

    private final static long serialVersionUID = -532742563539027774L;

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
