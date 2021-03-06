
package com.bsure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NomineeDetailsResponse {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("userNomineeName")
    @Expose
    private String userNomineeName;
    @SerializedName("userNomineeMobileNumber")
    @Expose
    private String userNomineeMobileNumber;
    @SerializedName("userNomineeRelation")
    @Expose
    private String userNomineeRelation;
    @SerializedName("userNomineePercentage")
    @Expose
    private Object userNomineePercentage;

    @SerializedName("userNomineeAttachment")
    @Expose
    private String userNomineeAttachment;

    @SerializedName("userGardianName")
    @Expose
    private String userGardianName;

    @SerializedName("userGardianMobileNumber")
    @Expose
    private String userGardianMobileNumber;

    @SerializedName("userNomineeAge")
    @Expose
    private String userNomineeAge;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNomineeName() {
        return userNomineeName;
    }

    public void setUserNomineeName(String userNomineeName) {
        this.userNomineeName = userNomineeName;
    }

    public String getUserNomineeMobileNumber() {
        return userNomineeMobileNumber;
    }

    public void setUserNomineeMobileNumber(String userNomineeMobileNumber) {
        this.userNomineeMobileNumber = userNomineeMobileNumber;
    }

    public String getUserNomineeRelation() {
        return userNomineeRelation;
    }

    public void setUserNomineeRelation(String userNomineeRelation) {
        this.userNomineeRelation = userNomineeRelation;
    }

    public Object getUserNomineePercentage() {
        return userNomineePercentage;
    }

    public void setUserNomineePercentage(Object userNomineePercentage) {
        this.userNomineePercentage = userNomineePercentage;
    }

    public String getUserNomineeAttachment() {
        return userNomineeAttachment;
    }

    public void setUserNomineeAttachment(String userNomineeAttachment) {
        this.userNomineeAttachment = userNomineeAttachment;
    }

    public String getUserGardianName() {
        return userGardianName;
    }

    public void setUserGardianName(String userGardianName) {
        this.userGardianName = userGardianName;
    }

    public String getUserGardianMobileNumber() {
        return userGardianMobileNumber;
    }

    public void setUserGardianMobileNumber(String userGardianMobileNumber) {
        this.userGardianMobileNumber = userGardianMobileNumber;
    }

    public String getUserNomineeAge() {
        return userNomineeAge;
    }

    public void setUserNomineeAge(String userNomineeAge) {
        this.userNomineeAge = userNomineeAge;
    }
}
