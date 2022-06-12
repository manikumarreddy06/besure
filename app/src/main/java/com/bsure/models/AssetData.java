package com.bsure.models;

import java.io.Serializable;

public class AssetData implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = -5202290173168041319L;

    private String id;
    private String value;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }





}