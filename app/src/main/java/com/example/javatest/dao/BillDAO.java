package com.example.javatest.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.javatest.database.DatabaseHelper;
import com.example.javatest.model.Revenue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BillDAO {

    DatabaseHelper helper;
    SQLiteDatabase db;

    public BillDAO(Context context){
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public long createBill(){
        ContentValues v = new ContentValues();
        v.put("date",System.currentTimeMillis());
        v.put("totalPrice",0);
        return db.insert("bill",null,v);
    }

    public void payBill(int idBill,double total){
        ContentValues v = new ContentValues();
        v.put("totalPrice",total);
        db.update("bill",v,"idBill=?",new String[]{String.valueOf(idBill)});
    }

    // ⭐ LẤY DOANH THU
    public ArrayList<Revenue> getAllRevenue(){

        ArrayList<Revenue> list = new ArrayList<>();

        Cursor c = db.rawQuery(
                "SELECT idBill,date,totalPrice FROM bill ORDER BY idBill DESC",
                null
        );

        while(c.moveToNext()){

            int id = c.getInt(0);
            String date = c.getString(1);
            double total = c.getDouble(2);

            list.add(new Revenue(id,date,total));
        }

        c.close();
        return list;
    }
    public ArrayList<Revenue> getRevenueByDate(long start, long end){

        ArrayList<Revenue> list = new ArrayList<>();

        String sql = "SELECT idBill, date, totalPrice FROM bill WHERE date BETWEEN ? AND ?";

        Cursor c = db.rawQuery(sql,
                new String[]{String.valueOf(start), String.valueOf(end)});

        while(c.moveToNext()){

            int id = c.getInt(0);
            String date = c.getString(1);
            double total = c.getDouble(2);

            list.add(new Revenue(id,date,total));
        }

        c.close();
        return list;
    }
    public void debugBills(){

        Cursor c = db.rawQuery("SELECT * FROM bill",null);

        while(c.moveToNext()){
            Log.d("BILL",
                    "id="+c.getInt(0)+
                            " total="+c.getDouble(2));
        }

        c.close();
    }
}