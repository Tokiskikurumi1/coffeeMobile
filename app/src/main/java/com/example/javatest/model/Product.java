package com.example.javatest.model;

public class Product {

    private int idFood;
    private int idCate;
    private String nameFood;
    private double price;
    private String image; // drawable name

    public Product(){}

    public Product(int idFood, int idCate, String nameFood, double price, String image){
        this.idFood=idFood;
        this.idCate=idCate;
        this.nameFood=nameFood;
        this.price=price;
        this.image=image;
    }

    public int getIdFood(){return idFood;}
    public int getIdCate(){return idCate;}
    public String getNameFood(){return nameFood;}
    public double getPrice(){return price;}
    public String getImage(){return image;}

    public void setIdFood(int idFood){this.idFood=idFood;}
    public void setIdCate(int idCate){this.idCate=idCate;}
    public void setNameFood(String nameFood){this.nameFood=nameFood;}
    public void setPrice(double price){this.price=price;}
    public void setImage(String image){this.image=image;}
}