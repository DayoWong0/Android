package com.example.a0604litepaltest;


import org.litepal.crud.DataSupport;

public class Category extends DataSupport {

    private int id;

    private String categoryName;

    private int categoryCode;

    public Category() {
    }

    public Category(int id, String categoryName, int categoryCode) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }
}
