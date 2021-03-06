
package com.bsure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryDetailsBean {


    private String otherTextField;
    private String parameterValue;

    @SerializedName("subCategoryId")
    @Expose
    private String subCategoryId;
    @SerializedName("subCategoryName")
    @Expose
    private String subCategoryName;
    @SerializedName("dataType")
    @Expose
    private String dataType;
    @SerializedName("madantory")
    @Expose
    private String madantory;
    @SerializedName("categoryId")
    @Expose
    private String categoryId;

    @SerializedName("values")
    @Expose
    private String values;

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getMadantory() {
        return madantory;
    }

    public void setMadantory(String madantory) {
        this.madantory = madantory;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getOtherTextField() {
        return otherTextField;
    }

    public void setOtherTextField(String otherTextField) {
        this.otherTextField = otherTextField;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
