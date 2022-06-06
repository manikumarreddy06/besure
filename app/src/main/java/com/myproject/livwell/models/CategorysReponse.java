
package com.myproject.livwell.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategorysReponse {

    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("categoryImage")
    @Expose
    private Object categoryImage;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Object getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(Object categoryImage) {
        this.categoryImage = categoryImage;
    }

}
