package me.ebraheem.restaurants.data.model;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("category_id")
    private String categoryId;

    @SerializedName("category_name")
    private String categoryName;

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


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Category)) {
            return false;
        }

        Category obj = ((Category) other);
        return categoryName.equals(obj.categoryName) && categoryId.equals(obj.categoryId);
    }

}