package com.bsure.models;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentResponseBean {

    @SerializedName("cfOrderId")
    @Expose
    private Long cfOrderId;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("entity")
    @Expose
    private String entity;
    @SerializedName("orderCurrency")
    @Expose
    private String orderCurrency;
    @SerializedName("orderAmount")
    @Expose
    private Double orderAmount;
    @SerializedName("orderExpiryTime")
    @Expose
    private String orderExpiryTime;
    @SerializedName("orderStatus")
    @Expose
    private String orderStatus;
    @SerializedName("orderToken")
    @Expose
    private String orderToken;
    @SerializedName("orderNote")
    @Expose
    private Object orderNote;
    @SerializedName("paymentLink")
    @Expose
    private String paymentLink;
    @SerializedName("orderTags")
    @Expose
    private Object orderTags;
    @SerializedName("orderSplits")
    @Expose
    private List<Object> orderSplits = null;

    public Long getCfOrderId() {
        return cfOrderId;
    }

    public void setCfOrderId(Long cfOrderId) {
        this.cfOrderId = cfOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getOrderCurrency() {
        return orderCurrency;
    }

    public void setOrderCurrency(String orderCurrency) {
        this.orderCurrency = orderCurrency;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderExpiryTime() {
        return orderExpiryTime;
    }

    public void setOrderExpiryTime(String orderExpiryTime) {
        this.orderExpiryTime = orderExpiryTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderToken() {
        return orderToken;
    }

    public void setOrderToken(String orderToken) {
        this.orderToken = orderToken;
    }

    public Object getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(Object orderNote) {
        this.orderNote = orderNote;
    }

    public String getPaymentLink() {
        return paymentLink;
    }

    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }

    public Object getOrderTags() {
        return orderTags;
    }

    public void setOrderTags(Object orderTags) {
        this.orderTags = orderTags;
    }

    public List<Object> getOrderSplits() {
        return orderSplits;
    }

    public void setOrderSplits(List<Object> orderSplits) {
        this.orderSplits = orderSplits;
    }

}