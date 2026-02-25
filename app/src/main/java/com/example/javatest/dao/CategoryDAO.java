package com.example.javatest.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.javatest.database.DatabaseHelper;
import com.example.javatest.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    DatabaseHelper dbHelper;

    public CategoryDAO(Context c){
        dbHelper = new DatabaseHelper(c);
    }

    public List<Category> getAll(){

        List<Category> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM category",null);

        while(c.moveToNext()){
            list.add(new Category(
                    c.getInt(0),
                    c.getString(1)
            ));
        }

        c.close();
        return list;
    }
}