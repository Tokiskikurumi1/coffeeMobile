package com.example.javatest.model;

public class BillDetailItem {

    private String name;
    private int qty;
    private double price;

    public BillDetailItem(String name, int qty, double price){
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public String getName(){ return name; }
    public int getQty(){ return qty; }
    public double getPrice(){ return price; }

    public double getTotal(){
        return qty * price;
    }
}