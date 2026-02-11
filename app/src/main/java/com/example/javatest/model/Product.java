package com.example.javatest.model;

public class Product {

    private String name;
    private int price;
    private int imageResId;   // id ảnh trong drawable
    private String category;  // loại sản phẩm

    public Product(String name, int price, int imageResId, String category) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getCategory() {
        return category;
    }
}
