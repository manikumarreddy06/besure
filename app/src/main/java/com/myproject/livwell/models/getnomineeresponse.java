package com.myproject.livwell.models;

import java.io.Serializable;

public class getnomineeresponse implements Serializable {


    public class Relationresponse implements Serializable {
        private String userId;
        private String userNomineeName;
        private String userNomineeMobileNumber;
        private String userNomineeRelation;
        private String userNomineePercentage;
        private final static long serialVersionUID = 7693771628136562898L;

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

        public String getUserNomineePercentage() {
            return userNomineePercentage;
        }

        public void setUserNomineePercentage(String userNomineePercentage) {
            this.userNomineePercentage = userNomineePercentage;
        }


    }
}
