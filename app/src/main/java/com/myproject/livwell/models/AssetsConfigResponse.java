
package com.myproject.livwell.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssetsConfigResponse {

    @SerializedName("isvalid")
    @Expose
    private Boolean isvalid;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("categoryDetailsBean")
    @Expose
    private List<CategoryDetailsBean> categoryDetailsBean = null;

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

    public List<CategoryDetailsBean> getCategoryDetailsBean() {
        return categoryDetailsBean;
    }

    public void setCategoryDetailsBean(List<CategoryDetailsBean> categoryDetailsBean) {
        this.categoryDetailsBean = categoryDetailsBean;
    }

}
