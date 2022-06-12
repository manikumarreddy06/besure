
package com.bsure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.bsure.models.UserAsset;

import java.util.List;

public class UserAssetResponseBean {

    @SerializedName("isvalid")
    @Expose
    private Boolean isvalid;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("userAssets")
    @Expose
    private List<com.bsure.models.UserAsset> userAssets = null;

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

    public List<com.bsure.models.UserAsset> getUserAssets() {
        return userAssets;
    }

    public void setUserAssets(List<UserAsset> userAssets) {
        this.userAssets = userAssets;
    }

}
