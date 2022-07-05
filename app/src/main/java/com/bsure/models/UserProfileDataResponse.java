package com.bsure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserProfileDataResponse {
    @SerializedName("isvalid")
    @Expose
    private Boolean isvalid;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("userData")
    @Expose
    private UserDataResponse userDataResponses;

    public UserProfileDataResponse(Boolean isvalid, String message, UserDataResponse userDataResponses) {
        this.isvalid = isvalid;
        this.message = message;
        this.userDataResponses = userDataResponses;
    }
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

    public UserDataResponse getUserDataResponses() {
        return userDataResponses;
    }

    public void setUserDataResponses(UserDataResponse userDataResponses) {
        this.userDataResponses = userDataResponses;
    }
}
