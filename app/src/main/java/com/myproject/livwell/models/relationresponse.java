package com.myproject.livwell.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class relationresponse implements Serializable {
    private String isvalid;
    private String message;
    private String userId;
    private String categoryResponse;
    public ArrayList<data> data;

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategoryResponse() {
        return categoryResponse;
    }

    public void setCategoryResponse(String categoryResponse) {
        this.categoryResponse = categoryResponse;
    }

    public ArrayList<relationresponse.data> getData() {
        return data;
    }

    public void setData(ArrayList<relationresponse.data> data) {
        this.data = data;
    }



    public class data{
        String relationId;
        String relationName;

        public String getRelationId() {
            return relationId;
        }

        public void setRelationId(String relationId) {
            this.relationId = relationId;
        }

        public String getRelationName() {
            return relationName;
        }

        public void setRelationName(String relationName) {
            this.relationName = relationName;
        }
    }











//    private String relationId;
//    private String relationName;
//    private final static long serialVersionUID = 8149295045976738676L;
//
//    public String getRelationId() {
//        return relationId;
//    }
//
//    public void setRelationId(String relationId) {
//        this.relationId = relationId;
//    }
//
//    public String getRelationName() {
//        return relationName;
//    }
//
//    public void setRelationName(String relationName) {
//        this.relationName = relationName;
//    }




}
