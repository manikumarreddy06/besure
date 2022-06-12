package com.bsure.models;

public class NomineeRequest {
    private String userId;
    private String userNomineeName;
    private String userNomineeMobileNumber;
    private String userNomineeRelationId;
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


}
