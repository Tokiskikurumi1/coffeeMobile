package com.example.javatest.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.javatest.database.DatabaseHelper;

public class BillDetailDAO {

    SQLiteDatabase db;

    public BillDetailDAO(Context context){
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(int idBill,int idFood,int qty,double price){

        ContentValues v = new ContentValues();
        v.put("idBill",idBill);
        v.put("idFood",idFood);
        v.put("amount",qty);
        v.put("price",price);
        v.put("totalPrice",price*qty);

        db.insert("billDetail",null,v);
    }
}