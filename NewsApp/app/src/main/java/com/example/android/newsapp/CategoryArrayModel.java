package com.example.android.newsapp;

public class CategoryArrayModel
{
    private String category;
    private String categoryimageview;

    public CategoryArrayModel(String category, String categoryimageview) {
        this.category = category;
        this.categoryimageview = categoryimageview;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryimageview() {
        return categoryimageview;
    }

    public void setCategoryimageview(String categoryimageview) {
        this.categoryimageview = categoryimageview;
    }
}
