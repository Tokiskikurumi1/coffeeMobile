package com.example.javatest.model;

public class Product {

    private int id;
    private String name;
    private int price;
    private int imageResId;
    private String category;

    public Product(int id, String name, int price, int imageResId, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.category = category;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getImageResId() { return imageResId; }
    public String getCategory() { return category; }
}
