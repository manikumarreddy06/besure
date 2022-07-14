package com.bsure.models;

public class NomineeRequest {
    private String userId;
    private String userNomineeName;
    private String userNomineeMobileNumber;
    private String userNomineeRelationId;
    private String userNomineeAttachment;
    private String userGardianName;
    private String userGardianMobileNumber;
    private String userNomineeAge;
    private final static long serialVersionUID = 2074851219762988987L;

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

    public String getUserNomineeRelationId() {
        return userNomineeRelationId;
    }

    public void setUserNomineeRelationId(String userNomineeRelationId) {
        this.userNomineeRelationId = userNomineeRelationId;
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
