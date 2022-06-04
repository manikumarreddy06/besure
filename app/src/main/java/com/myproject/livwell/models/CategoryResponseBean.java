
package com.myproject.livwell.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryResponseBean {

    @SerializedName("isvalid")
    @Expose
    private Boolean isvalid;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("categorysReponse")
    @Expose
    private List<CategorysReponse> categorysReponse = null;

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

    public List<CategorysReponse> getCategorysReponse() {
        return categorysReponse;
    }

    public void setCategorysReponse(List<CategorysReponse> categorysReponse) {
        this.categorysReponse = categorysReponse;
    }

}
