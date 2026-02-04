package com.example.javatest.model;

import java.util.List;

public class SectionModel {

    private String title;
    private List<String> products;

    public SectionModel(String title, List<String> products) {
        this.title = title;
        this.products = products;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getProducts() {
        return products;
    }
}
