
package com.bsure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Assets {

    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("label")
    @Expose
    private String label;

    @SerializedName("type")
    @Expose
    private String type;



    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
