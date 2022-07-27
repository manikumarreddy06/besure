package com.bsure.models;

public class userdiscountrequest {
    private String userId;
    private String code;
    private String planAmount;
    private final static long serialVersionUID = -2981230877013734493L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlanAmount() {
        return planAmount;
    }

    public void setPlanAmount(String planAmount) {
        this.planAmount = planAmount;
    }




}
