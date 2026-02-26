package com.example.javatest.model;

import java.util.List;

public class SectionModel {

    private String title;
    private List<Product> products;

    public SectionModel(String title, List<Product> products) {
        this.title = title;
        this.products = products;
    }

    public String getTitle() {
        return title;
    }

    public List<Product> getProducts() {
        return products;
    }
}
