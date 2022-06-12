
package com.bsure.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class RelationResponseBean {

    @SerializedName("isvalid")
    @Expose
    private Boolean isvalid;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("relationsResponse")
    @Expose
    private List<RelationsResponse> relationsResponse = null;

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

    public List<RelationsResponse> getRelationsResponse() {
        return relationsResponse;
    }

    public void setRelationsResponse(List<RelationsResponse> relationsResponse) {
        this.relationsResponse = relationsResponse;
    }

}
