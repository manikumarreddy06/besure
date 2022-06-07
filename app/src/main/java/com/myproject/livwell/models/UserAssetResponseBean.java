
package com.myproject.livwell.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAssetResponseBean {

    @SerializedName("isvalid")
    @Expose
    private Boolean isvalid;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("userAssets")
    @Expose
    private List<UserAsset> userAssets = null;

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

    public List<UserAsset> getUserAssets() {
        return userAssets;
    }

    public void setUserAssets(List<UserAsset> userAssets) {
        this.userAssets = userAssets;
    }

}
