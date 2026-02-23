package com.example.javatest.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.javatest.database.DatabaseHelper;

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