
package com.bsure.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NomineeListResponseBean {

    @SerializedName("isvalid")
    @Expose
    private Boolean isvalid;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("nomineeDetailsResponse")
    @Expose
    private List<NomineeDetailsResponse> nomineeDetailsResponse = null;

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

    public List<NomineeDetailsResponse> getNomineeDetailsResponse() {
        return nomineeDetailsResponse;
    }

    public void setNomineeDetailsResponse(List<NomineeDetailsResponse> nomineeDetailsResponse) {
        this.nomineeDetailsResponse = nomineeDetailsResponse;
    }

}
