package com.example.javatest.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.javatest.database.DatabaseHelper;
import com.example.javatest.model.CartItem;

public class BillDAO {

    DatabaseHelper helper;

    public BillDAO(Context context){
        helper = new DatabaseHelper(context);
    }

    public long createBill(double total){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues v = new ContentValues();
        v.put("date", System.currentTimeMillis());
        v.put("totalPrice", total);

        return db.insert("bill", null, v);
    }

    public void insertBillDetail(long idBill, CartItem item){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues v = new ContentValues();
        v.put("idBill", idBill);
        v.put("idFood", item.getIdFood());
        v.put("amount", item.getQuantity());
        v.put("price", item.getPrice());
        v.put("totalPrice", item.getPrice()*item.getQuantity());

        db.insert("billDetail", null, v);
    }
}