package com.myproject.livwell.models;

import java.io.Serializable;

public class relationresponse implements Serializable {

    private String relationId;
    private String relationName;
    private final static long serialVersionUID = 8149295045976738676L;

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
