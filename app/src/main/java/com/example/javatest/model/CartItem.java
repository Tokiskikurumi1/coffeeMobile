package com.example.javatest.model;

public class CartItem {

    private int idFood;
    private String name;
    private double price;
    private int quantity;
    private String image;

    public CartItem(int idFood,String name,double price,int quantity,String image){
        this.idFood=idFood;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.image=image;
    }

    public int getIdFood(){return idFood;}
    public String getName(){return name;}
    public double getPrice(){return price;}
    public int getQuantity(){return quantity;}
    public String getImage(){return image;}

    public void setQuantity(int q){quantity=q;}
}
