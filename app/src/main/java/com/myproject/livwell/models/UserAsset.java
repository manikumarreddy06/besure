
package com.myproject.livwell.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAsset {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("assetId")
    @Expose
    private String assetId;
    @SerializedName("uamId")
    @Expose
    private String uamId;
    @SerializedName("assetsList")
    @Expose
    private List<Assets> assetsList = null;

    public Object getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getUamId() {
        return uamId;
    }

    public void setUamId(String uamId) {
        this.uamId = uamId;
    }

    public List<Assets> getAssetsList() {
        return assetsList;
    }

    public void setAssetsList(List<Assets> assetsList) {
        this.assetsList = assetsList;
    }



}
