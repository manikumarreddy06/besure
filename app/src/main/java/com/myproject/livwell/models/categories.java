package com.myproject.livwell.models;

import java.io.Serializable;

public class categories implements Serializable {

    private String categoryId;
    private String categoryName;
    private final static long serialVersionUID = -1284116185798881695L;

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


}
