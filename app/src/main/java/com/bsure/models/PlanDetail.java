
package com.bsure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlanDetail {

    @SerializedName("yearlyPlanPrice")
    @Expose
    private Double yearlyPlanPrice;
    @SerializedName("lifetimePlanPrice")
    @Expose
    private Double lifetimePlanPrice;
    @SerializedName("yearlyPlanText")
    @Expose
    private Object yearlyPlanText;
    @SerializedName("lifetimePlanText")
    @Expose
    private Object lifetimePlanText;

    public Double getYearlyPlanPrice() {
        return yearlyPlanPrice;
    }

    public void setYearlyPlanPrice(Double yearlyPlanPrice) {
        this.yearlyPlanPrice = yearlyPlanPrice;
    }

    public Double getLifetimePlanPrice() {
        return lifetimePlanPrice;
    }

    public void setLifetimePlanPrice(Double lifetimePlanPrice) {
        this.lifetimePlanPrice = lifetimePlanPrice;
    }

    public Object getYearlyPlanText() {
        return yearlyPlanText;
    }

    public void setYearlyPlanText(Object yearlyPlanText) {
        this.yearlyPlanText = yearlyPlanText;
    }

    public Object getLifetimePlanText() {
        return lifetimePlanText;
    }

    public void setLifetimePlanText(Object lifetimePlanText) {
        this.lifetimePlanText = lifetimePlanText;
    }

}
