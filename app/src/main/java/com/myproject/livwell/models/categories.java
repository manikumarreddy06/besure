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

//    boolean isvalid;
//    String message;
//    int userId;
//
//    public boolean isIsvalid() {
//        return isvalid;
//    }
//
//    public void setIsvalid(boolean isvalid) {
//        this.isvalid = isvalid;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    private class categorysReponse{
//        String categoryId;
//        String categoryName;
//        final static long serialVersionUID = -1284116185798881695L;
//
//        public String getCategoryId() {
//            return categoryId;
//        }
//
//        public void setCategoryId(String categoryId) {
//            this.categoryId = categoryId;
//        }
//
//        public String getCategoryName() {
//            return categoryName;
//        }
//
//        public void setCategoryName(String categoryName) {
//            this.categoryName = categoryName;
//        }
//
//        public long getSerialVersionUID() {
//            return serialVersionUID;
//        }
//    }


}
