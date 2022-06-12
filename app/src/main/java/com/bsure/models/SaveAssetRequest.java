package com.bsure.models;


import com.bsure.models.AssetData;

import java.io.Serializable;
import java.util.List;

public class SaveAssetRequest implements Serializable{


    private static final long serialVersionUID = -6999338196401174002L;

    private String userId;
    private String createdBy;
    private String assetId;

    private String uamId;

    private List<com.bsure.models.AssetData> assetsList;



    public String getUserId() {
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<com.bsure.models.AssetData> getAssetsList() {
        return assetsList;
    }

    public void setAssetsList(List<AssetData> assetsList) {
        this.assetsList = assetsList;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
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








}
