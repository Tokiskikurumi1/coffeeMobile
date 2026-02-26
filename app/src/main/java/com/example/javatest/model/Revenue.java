package com.example.javatest.model;

public class Revenue {

    int idBill;
    String date;
    double total;

    public Revenue(int idBill, String date, double total) {
        this.idBill = idBill;
        this.date = date;
        this.total = total;
    }

    public int getIdBill() {
        return idBill;
    }

    public String getDate() {
        return date;
    }

    public double getTotal() {
        return total;
    }
}