package com.myproject.livwell.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Asset_categories implements Serializable {





//    private String categoryId;
//    private String categoryName;
//    private final static long serialVersionUID = -1284116185798881695L;
//
//    public String getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(String categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }

    boolean isvalid;
    String message;
    int userId;
    ArrayList<categorysReponse>categorysReponse;

    public boolean isIsvalid() {
        return isvalid;
    }

    public void setIsvalid(boolean isvalid) {
        this.isvalid = isvalid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Asset_categories.categorysReponse> getCategorysReponse() {
        return categorysReponse;
    }

    public void setCategorysReponse(ArrayList<Asset_categories.categorysReponse> categorysReponse) {
        this.categorysReponse = categorysReponse;
    }

    public class categorysReponse{
        String categoryId;
        String categoryName;

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


}
