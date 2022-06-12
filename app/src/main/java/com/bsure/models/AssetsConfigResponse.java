
package com.bsure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.bsure.models.CategoryDetailsBean;

import java.util.List;

public class AssetsConfigResponse {

    @SerializedName("isvalid")
    @Expose
    private Boolean isvalid;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("categoryDetailsBean")
    @Expose
    private List<com.bsure.models.CategoryDetailsBean> categoryDetailsBean = null;

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

    public List<com.bsure.models.CategoryDetailsBean> getCategoryDetailsBean() {
        return categoryDetailsBean;
    }

    public void setCategoryDetailsBean(List<CategoryDetailsBean> categoryDetailsBean) {
        this.categoryDetailsBean = categoryDetailsBean;
    }

}
